# AC2 - Devops

# Plataforma de Cursos Gamificada

---

## Estrutura do projeto
- `domain/` — Entidades (Entities) e Value Objects (VO)  
- `infra/` — Adapters e repositórios JPA (`infra/jpa`, `infra/adapter`)  
- `application/` — Services (regras de negócio)  
- `web/` — Controllers e DTOs  
- `config/` — Configurações (ex.: `OpenApiConfig`)  
- `src/main/resources` — `application.properties`, `application-dev.properties`  
- `src/test/resources` — `application-test.properties` (profile de testes)

---

## 1) Camada Entity (Entidades + Value Objects)
**Objetivo:** representar o domínio e garantir invariantes por meio de VOs (ex.: `Matricula`).  
**Observações importantes (Lombok):**
- Usamos Lombok para reduzir *boilerplate* (`@Getter`, `@Builder`, `@NoArgsConstructor`, etc.).
- **Por que cada recurso é importante**:
  - **Getters/Setters:** encapsulam acesso e facilitam integração com JPA.
  - **Construtores:** JPA exige construtor sem-args (normalmente `protected`); fábricas estáticas (`of(...)`) garantem objetos válidos.
  - **toString():** útil para logs; evite incluir coleções grandes para não causar recursão.
  - **equals()/hashCode():** essenciais para coleções e comparação — preferir atributos naturais (VOs) ou estratégias apropriadas para entidades persistidas.

> **Onde checar:** `domain/<entidade>` e `domain/<entidade>/vo`.

---

## 2) Camada Repository (Spring Data JPA)
**Objetivo:** abstrair persistência seguindo Repository Pattern com Spring Data (`JpaRepository`) e adapters que implementam a porta de domínio.  
> **Onde checar:** `infra/jpa` e `infra/adapter`.

---

## 3) Profiles e JPA (configurações)
Arquivos principais:
- `src/main/resources/application.properties` — default  
- `src/main/resources/application-dev.properties` — dev (H2, `spring.h2.console.enabled=true`)  
- `src/test/resources/application-test.properties` — testes (H2 in-memory, `create-drop`, geração de DDL)

`SPRING_PROFILES_ACTIVE=dev` para rodar local com H2; testes usam `test` profile se configurado.

---

## 4) Gerar Schema a partir do ORM (H2) + H2 Console
- Configure `application-test.properties` com `javax.persistence.schema-generation.scripts.*` para gerar `target/generated-schema/schema.sql` durante testes.  
- Rodar:
```bash
mvn -DskipTests=false test
```
— o arquivo target/generated-schema/schema.sql será gerado se as propriedades estiverem ativas.

H2 console (dev): http://localhost:8080/h2-console — use JDBC URL conforme application-dev.properties (ex.: jdbc:h2:mem:devdb), usuário sa.


## 5) Camada DTO
**Objetivo:** separar entidades do contrato da API (requests/responses).
- DTOs simples.
> **Onde checar:** `web/dto`.

---

## 6) Camada Service
**Objetivo:** implementar regras de negócio e orquestrar repositórios.
- Mockito/JUnit para isolar dependências.
- Testes de integração: usar H2 com @DataJpaTest ou @SpringBootTest.
> **Onde checar:** `application/.../service`.


---

## 7) Camada Controller
**Objetivo:** expor endpoints REST, validar entradas e devolver DTOs; trate exceções com @ControllerAdvice.
> **Onde checar:** `web/controller`.

---


## Localização das implementações 

- Entities / VOs: domain/

- Repositories JPA: infra/jpa

- Repository Adapters: infra/adapter

- Services: application/.../service

- Controllers / DTOs: web/...

- OpenAPI config: config/OpenApiConfig.java

- Profiles: src/main/resources / src/test/resources


