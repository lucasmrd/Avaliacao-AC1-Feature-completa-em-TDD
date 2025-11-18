package grupo6.plataforma_cursos.domain.aluno.entity;

import grupo6.plataforma_cursos.domain.aluno.vo.Matricula;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alunos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Aluno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    private Matricula matricula;


    private int creditosCursos;


    public void adicionarCreditos(int quantidade) {
        this.creditosCursos += quantidade;
    }

    public static Aluno ofMatricula(String matricula) {
        return Aluno.builder()
                .matricula(Matricula.of(matricula))
                .creditosCursos(0)
                .build();
    }
}
