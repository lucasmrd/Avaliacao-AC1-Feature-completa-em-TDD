package grupo6.plataforma_cursos.infra.jpa;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import grupo6.plataforma_cursos.domain.aluno.repository.AlunoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AlunoRepositoryAdapter implements AlunoRepository {


    private final AlunoJpaRepository repository;


    public AlunoRepositoryAdapter(AlunoJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Optional<Aluno> findByMatricula(String matricula) {
        return repository.findByMatricula_Numero(matricula);
    }


    @Override
    public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }
}