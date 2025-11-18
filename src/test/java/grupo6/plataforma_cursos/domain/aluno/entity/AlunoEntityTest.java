package grupo6.plataforma_cursos.domain.aluno.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlunoEntityTest {

    @Test
    void adicionarCreditos_deveSomarCorretamente() {
        Aluno aluno = Aluno.ofMatricula("E-300");
        assertEquals(0, aluno.getCreditosCursos(), "Créditos iniciais devem ser 0");

        aluno.adicionarCreditos(3);
        assertEquals(3, aluno.getCreditosCursos(), "Após adicionar 3, deve ter 3");

        aluno.adicionarCreditos(2);
        assertEquals(5, aluno.getCreditosCursos(), "Após adicionar mais 2, deve ter 5");
    }
}
