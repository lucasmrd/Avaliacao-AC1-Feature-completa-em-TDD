package grupo6.plataforma_cursos.web.controller;

import grupo6.plataforma_cursos.application.aluno.dto.AlunoDTO;
import grupo6.plataforma_cursos.application.aluno.dto.ConclusaoRequest;
import grupo6.plataforma_cursos.application.aluno.mapper.AlunoMapper;
import grupo6.plataforma_cursos.application.aluno.service.RecompensaService;
import grupo6.plataforma_cursos.domain.aluno.repository.AlunoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {


    private final AlunoRepository alunoRepository;
    private final RecompensaService recompensaService;


    public AlunoController(AlunoRepository alunoRepository, RecompensaService recompensaService) {
        this.alunoRepository = alunoRepository;
        this.recompensaService = recompensaService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAluno(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(AlunoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/{id}/concluir")
    public ResponseEntity<Integer> concluirCurso(@PathVariable Long id, @RequestBody ConclusaoRequest request) {
        int concedidos = recompensaService.concederCreditosPorConclusao(id, request.getMediaFinal());
        return ResponseEntity.ok(concedidos);
    }
}