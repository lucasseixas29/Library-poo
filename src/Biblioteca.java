import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nomeBiblioteca;

    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Revista> revistas;
    private List<Livro> emprestimos;

    public Biblioteca(String nomeBiblioteca) {
        this.nomeBiblioteca = nomeBiblioteca;
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.revistas = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public List<Livro> getEmprestimos() {
        return emprestimos;
    }

    public void addLivroEmprestimos(Livro livro) {
        emprestimos.add(livro);
    }
}