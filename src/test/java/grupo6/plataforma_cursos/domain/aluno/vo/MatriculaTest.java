package grupo6.plataforma_cursos.domain.aluno.vo;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    @Test
    void of_deveCriarQuandoValida() {
        Matricula m = Matricula.of("X-400");
        assertNotNull(m);
        assertEquals("X-400", m.getNumero());
    }

    @Test
    void of_deveLancarQuandoNulaOuVazia() {
        assertThrows(IllegalArgumentException.class, () -> Matricula.of(null));
        assertThrows(IllegalArgumentException.class, () -> Matricula.of("  "));
    }
}
