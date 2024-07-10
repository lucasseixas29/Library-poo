public class Usuario {
    private String nome;
    private int matricula;
    private int emprestimos;

    public Usuario() {

    }

    public Usuario(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public void emprestarItem() {

    }

    public void devolverItem() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
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

    public void consultarEmprestimos() {

    }

    @Override
    public String toString() {
        return "nome: " + nome + ", matricula: " + matricula + ", emprestimos: " + emprestimos;
    }
}
