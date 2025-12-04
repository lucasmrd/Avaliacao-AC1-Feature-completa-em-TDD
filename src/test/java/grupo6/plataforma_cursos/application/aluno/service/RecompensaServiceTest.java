package grupo6.plataforma_cursos.application.aluno.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import grupo6.plataforma_cursos.domain.aluno.repository.AlunoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class RecompensaServiceTest {

    private AlunoRepository alunoRepository;
    private RecompensaServiceImpl service;

    @BeforeEach
    void setup() {
        alunoRepository = mock(AlunoRepository.class);
        service = new RecompensaServiceImpl(alunoRepository);
    }

    @Test
    @DisplayName("BDD#1: Nota >= 7.0 concede exatamente 3 créditos")
    void deveConceder3CreditosQuandoMediaMaiorOuIgualSete() {
        Aluno aluno = Aluno.ofMatricula("A-001");
        Long alunoId = 1L;

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(inv -> inv.getArgument(0));

        int concedidos = service.concederCreditosPorConclusao(alunoId, 7.0);

        assertEquals(3, concedidos, "Quantidade de créditos concedidos deve ser 3");
        assertEquals(3, aluno.getCreditosCursos(), "Total de créditos do aluno deve aumentar em 3");
        verify(alunoRepository, times(1)).findById(alunoId);
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    @DisplayName("BDD#2: Nota < 7.0 não concede créditos")
    void naoDeveConcederCreditosQuandoMediaMenorQueSete() {
        Aluno aluno = Aluno.ofMatricula("A-002");
        Long alunoId = 2L;

        // NÃO stubbamos findById porque o serviço não deve chamá-lo quando a média < 7
        int concedidos = service.concederCreditosPorConclusao(alunoId, 6.5);

        assertEquals(0, concedidos, "Com nota < 7, nenhum crédito deve ser concedido");
        assertEquals(0, aluno.getCreditosCursos(), "Total de créditos do aluno deve permanecer inalterado");

        // Verifica explicitamente que NÃO houve interação com o repositório
        verify(alunoRepository, never()).findById(anyLong());
        verify(alunoRepository, never()).save(any());
    }

    @Test
    @DisplayName("BDD#3: Nota 10.0 concede exatamente 3 créditos")
    void deveConceder3CreditosQuandoMediaDez() {
        Aluno aluno = Aluno.ofMatricula("A-003");
        Long alunoId = 3L;

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(inv -> inv.getArgument(0));

        int concedidos = service.concederCreditosPorConclusao(alunoId, 10.0);

        assertEquals(3, concedidos);
        assertEquals(3, aluno.getCreditosCursos());
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    @DisplayName("BDD#4: Aluno com créditos existentes recebe mais 3 créditos")
    void deveConceder3CreditosAcumulados() {
        Aluno aluno = Aluno.ofMatricula("A-004");
        aluno.adicionarCreditos(5);
        Long alunoId = 4L;

        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(inv -> inv.getArgument(0));

        int concedidos = service.concederCreditosPorConclusao(alunoId, 8.0);

        assertEquals(3, concedidos);
        assertEquals(8, aluno.getCreditosCursos());
        verify(alunoRepository, times(1)).save(aluno);
    }
}