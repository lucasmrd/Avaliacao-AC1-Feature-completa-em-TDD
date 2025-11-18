package grupo6.plataforma_cursos.application.aluno.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlunoDTO {
    private Long id;
    private String matricula;
    private int creditosCursos;
}
