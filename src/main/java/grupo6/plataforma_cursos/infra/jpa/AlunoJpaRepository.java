package grupo6.plataforma_cursos.infra.jpa;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoJpaRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByMatricula_Numero(String numero);
}