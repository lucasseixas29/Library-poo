package entities;

public class Usuario {
    private String nome;
    private Integer matricula;
    private int emprestimos;

    public Usuario() {

    }

    public Usuario(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(int emprestimos) {
        this.emprestimos += emprestimos;
    }

    @Override
    public String toString() {
        return "nome: " + nome + ", matricula: " + matricula + ", emprestimos: " + emprestimos;
    }
}
