package grupo6.plataforma_cursos.domain.aluno.repository;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;

import java.util.Optional;

public interface AlunoRepository {
    Optional<Aluno> findById(Long id);
    Optional<Aluno> findByMatricula(String matricula);
    Aluno save(Aluno aluno);
}
