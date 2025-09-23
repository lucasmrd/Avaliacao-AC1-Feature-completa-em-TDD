package grupo6.plataforma_cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import grupo6.plataforma_cursos.entity.Aluno;
import grupo6.plataforma_cursos.service.RecompensaService;
import grupo6.plataforma_cursos.service.RecompensaServiceImpl;


class RecompensaServiceTest {

    private final RecompensaService service = new RecompensaServiceImpl();

    @Test
    @DisplayName("BDD#1: Nota >= 7.0 concede exatamente 3 créditos")
    void deveConceder3CreditosQuandoMediaMaiorOuIgualSete() {
        Aluno aluno = new Aluno("A-001");
        int creditosAntes = aluno.getCreditosCursos();
        double mediaFinal = 7.0;

        int concedidos = service.concederCreditosPorConclusao(aluno, mediaFinal);

        assertEquals(3, concedidos, "Quantidade de créditos concedidos deve ser 3");
        assertEquals(creditosAntes + 3, aluno.getCreditosCursos(),
                "Total de créditos do aluno deve aumentar em 3");
    }

    @Test
    @DisplayName("BDD#2: Nota < 7.0 não concede créditos")
    void naoDeveConcederCreditosQuandoMediaMenorQueSete() {
        Aluno aluno = new Aluno("A-002");
        int creditosAntes = aluno.getCreditosCursos();
        double mediaFinal = 6.5;

        int concedidos = service.concederCreditosPorConclusao(aluno, mediaFinal);

        assertEquals(0, concedidos, "Com nota < 7, nenhum crédito deve ser concedido");
        assertEquals(creditosAntes, aluno.getCreditosCursos(),
                "Total de créditos do aluno deve permanecer inalterado");
    }
    
    @Test
    @DisplayName("BDD#3: Nota 10.0 concede exatamente 3 créditos")
    void deveConceder3CreditosQuandoMediaDez() {
        Aluno aluno = new Aluno("A-003");
        int creditosAntes = aluno.getCreditosCursos();
        double mediaFinal = 10.0;

        int concedidos = service.concederCreditosPorConclusao(aluno, mediaFinal);

        assertEquals(3, concedidos, "Quantidade de créditos concedidos deve ser 3");
        assertEquals(creditosAntes + 3, aluno.getCreditosCursos(),
                "Total de créditos do aluno deve aumentar em 3");
    }
    
    @Test
    @DisplayName("BDD#4: Aluno com créditos existentes recebe mais 3 créditos")
    void deveConceder3CreditosAcumulados() {
        Aluno aluno = new Aluno("A-004");
        aluno.adicionarCreditos(5);
        int creditosAntes = aluno.getCreditosCursos();
        double mediaFinal = 8.0;

        int concedidos = service.concederCreditosPorConclusao(aluno, mediaFinal);

        assertEquals(3, concedidos, "Quantidade de créditos concedidos deve ser 3");
        assertEquals(creditosAntes + 3, aluno.getCreditosCursos(),
                "Total de créditos do aluno deve ser 5+3=8");
    }
    
}
