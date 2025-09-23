package grupo6.plataforma_cursos.service;

import grupo6.plataforma_cursos.entity.Aluno;

public interface RecompensaService {
    int concederCreditosPorConclusao(Aluno aluno, double mediaFinal);
}