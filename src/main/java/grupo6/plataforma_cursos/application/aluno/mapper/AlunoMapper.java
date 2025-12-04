package grupo6.plataforma_cursos.application.aluno.mapper;

import grupo6.plataforma_cursos.application.aluno.dto.AlunoDTO;
import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;

public class AlunoMapper {
    public static AlunoDTO toDto(Aluno a) {
        if (a == null) return null;
        return new AlunoDTO(a.getId(), a.getMatricula().getNumero(), a.getCreditosCursos());
    }
}