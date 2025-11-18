package grupo6.plataforma_cursos.infra.jpa;

import static org.junit.jupiter.api.Assertions.*;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AlunoJpaRepositoryTest {

    @Autowired
    private AlunoJpaRepository alunoJpaRepository;

    @Test
    void saveAndFindByMatriculaNumero() {
        Aluno aluno = Aluno.ofMatricula("R-200");
        aluno = alunoJpaRepository.save(aluno);

        var encontrado = alunoJpaRepository.findByMatricula_Numero("R-200");
        assertTrue(encontrado.isPresent(), "Aluno deve ser encontrado por matrícula");
        assertEquals(aluno.getId(), encontrado.get().getId());
        assertEquals(0, encontrado.get().getCreditosCursos());

        encontrado.get().adicionarCreditos(5);
        alunoJpaRepository.save(encontrado.get());

        var atualizado = alunoJpaRepository.findById(encontrado.get().getId());
        assertTrue(atualizado.isPresent());
        assertEquals(5, atualizado.get().getCreditosCursos());
    }
}