package grupo6.plataforma_cursos.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import grupo6.plataforma_cursos.application.aluno.dto.ConclusaoRequest;
import grupo6.plataforma_cursos.application.aluno.service.RecompensaService;
import grupo6.plataforma_cursos.domain.aluno.entity.Aluno;
import grupo6.plataforma_cursos.domain.aluno.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlunoRepository alunoRepository;

    @MockBean
    private RecompensaService recompensaService;

    @Test
    void getAluno_deveRetornarAluno() throws Exception {
        Aluno aluno = Aluno.ofMatricula("A-100");
        // Simula repository retornando o aluno
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        mockMvc.perform(get("/api/alunos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value("A-100"))
                .andExpect(jsonPath("$.creditosCursos").value(0));
    }

    @Test
    void concluirCurso_deveRetornar3QuandoMediaSuficiente() throws Exception {
        ConclusaoRequest req = new ConclusaoRequest();
        req.setMediaFinal(8.0);

        when(recompensaService.concederCreditosPorConclusao(anyLong(), eq(8.0))).thenReturn(3);

        mockMvc.perform(post("/api/alunos/1/concluir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }
}
