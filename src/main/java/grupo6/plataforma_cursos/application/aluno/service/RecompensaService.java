package grupo6.plataforma_cursos.application.aluno.service;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;

public interface RecompensaService {
    int concederCreditosPorConclusao(Long alunoId, double mediaFinal);

    int concederCreditosPorConclusao(Aluno aluno, double mediaFinal);
}