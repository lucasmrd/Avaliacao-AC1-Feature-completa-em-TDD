package grupo6.plataforma_cursos.infra.jpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AlunoRepositoryAdapterTest {

    @Mock
    private AlunoJpaRepository jpaRepository;

    @InjectMocks
    private AlunoRepositoryAdapter adapter;

    @Test
    @DisplayName("findById deve delegar para jpaRepository.findById e retornar Optional com aluno")
    void findById_deveDelegarERetornarAlunoQuandoExistir() {
        // arrange
        Aluno aluno = Aluno.ofMatricula("T-100");
        Long id = 1L;
        when(jpaRepository.findById(id)).thenReturn(Optional.of(aluno));

        // act
        Optional<Aluno> resultado = adapter.findById(id);

        // assert
        assertTrue(resultado.isPresent(), "Deve retornar Optional presente");
        assertSame(aluno, resultado.get(), "Deve retornar a mesma instância retornada pelo JPA");
        verify(jpaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findById deve retornar Optional.empty quando não encontrar")
    void findById_deveRetornarVazioQuandoNaoExistir() {
        // arrange
        Long id = 42L;
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // act
        Optional<Aluno> resultado = adapter.findById(id);

        // assert
        assertFalse(resultado.isPresent(), "Deve retornar Optional vazio");
        verify(jpaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("findByMatricula deve delegar para jpaRepository.findByMatricula_Numero")
    void findByMatricula_deveDelegarCorretamente() {
        // arrange
        String matricula = "M-200";
        Aluno aluno = Aluno.ofMatricula(matricula);
        when(jpaRepository.findByMatricula_Numero(matricula)).thenReturn(Optional.of(aluno));

        // act
        Optional<Aluno> resultado = adapter.findByMatricula(matricula);

        // assert
        assertTrue(resultado.isPresent());
        assertEquals(matricula, resultado.get().getMatricula().getNumero());
        verify(jpaRepository, times(1)).findByMatricula_Numero(matricula);
    }

    @Test
    @DisplayName("save deve delegar para jpaRepository.save e retornar o valor retornado pelo JPA")
    void save_deveDelegarESerRetornado() {
        // arrange
        Aluno aluno = Aluno.ofMatricula("S-300");
        when(jpaRepository.save(any(Aluno.class))).thenAnswer(inv -> inv.getArgument(0));

        // act
        Aluno salvo = adapter.save(aluno);

        // assert
        assertNotNull(salvo);
        assertSame(aluno, salvo, "Deve retornar a mesma instância passada ao JPA (simulação)");
        verify(jpaRepository, times(1)).save(aluno);
    }
}