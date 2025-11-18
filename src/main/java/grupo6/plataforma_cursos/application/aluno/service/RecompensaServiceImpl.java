package grupo6.plataforma_cursos.application.aluno.service;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import grupo6.plataforma_cursos.domain.aluno.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class RecompensaServiceImpl implements RecompensaService {


    private static final int CREDITOS_CONCEDIDOS = 3;
    private static final double MEDIA_MINIMA = 7.0;


    private final AlunoRepository alunoRepository;


    public RecompensaServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }


    @Override
    @Transactional
    public int concederCreditosPorConclusao(Long alunoId, double mediaFinal) {
        if (!isMediaSuficiente(mediaFinal)) return 0;
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + alunoId));


        aluno.adicionarCreditos(CREDITOS_CONCEDIDOS);
        alunoRepository.save(aluno);
        return CREDITOS_CONCEDIDOS;
    }

    @Override
    public int concederCreditosPorConclusao(Aluno aluno, double mediaFinal) {
        return 0;
    }


    private boolean isMediaSuficiente(double mediaFinal) {
        return mediaFinal >= MEDIA_MINIMA;
    }
}
