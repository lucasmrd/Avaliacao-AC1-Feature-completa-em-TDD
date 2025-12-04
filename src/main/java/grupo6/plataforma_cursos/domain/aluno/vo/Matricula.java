package grupo6.plataforma_cursos.domain.aluno.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Matricula {


    private String numero;


    protected Matricula() { }


    private Matricula(String numero) {
        this.numero = numero;
    }


    public static Matricula of(String numero) {
        if (numero == null || numero.isBlank()) throw new IllegalArgumentException("Matrícula inválida");
        return new Matricula(numero);
    }
}