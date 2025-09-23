package grupo6.plataforma_cursos.service;

import grupo6.plataforma_cursos.entity.Aluno;

public class RecompensaServiceImpl implements RecompensaService {

    private static final int CREDITOS_CONCEDIDOS = 3;
    private static final double MEDIA_MINIMA = 7.0;
    
    @Override
    public int concederCreditosPorConclusao(Aluno aluno, double mediaFinal) {
        if (isMediaSuficiente(mediaFinal)) {
            concederCreditos(aluno);
            return CREDITOS_CONCEDIDOS;
        }
        return 0;
    }
    
    private boolean isMediaSuficiente(double mediaFinal) {
        return mediaFinal >= MEDIA_MINIMA;
    }
    
    private void concederCreditos(Aluno aluno) {
        aluno.adicionarCreditos(CREDITOS_CONCEDIDOS);
    }

}
