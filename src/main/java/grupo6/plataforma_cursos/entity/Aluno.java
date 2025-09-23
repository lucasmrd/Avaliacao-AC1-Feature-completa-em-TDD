package grupo6.plataforma_cursos.entity;

public class Aluno {
	
    private String matricula;
    private int creditosCursos;

    public Aluno(String matricula) {
        this.matricula = matricula;
        this.creditosCursos = 0;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getCreditosCursos() {
        return creditosCursos;
    }

    public void adicionarCreditos(int quantidade) {
        this.creditosCursos += quantidade;
    }
}
