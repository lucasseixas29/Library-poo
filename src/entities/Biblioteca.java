package entities;

import services.LivroService;
import services.RevistaService;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nomeBiblioteca;
    private LivroService livroService;
    private RevistaService revistaService;

    private List<ItemAcervo> emprestimos;

    public Biblioteca(String nomeBiblioteca, LivroService livroService, RevistaService revistaService) {
        this.nomeBiblioteca = nomeBiblioteca;
        this.emprestimos = new ArrayList<>();
        this.livroService = livroService;
        this.revistaService = revistaService;
    }

    public String getNomeBiblioteca() {
        return nomeBiblioteca;
    }

    public List<ItemAcervo> getEmprestimos() {
        return emprestimos;
    }

    public void addLivroEmprestimos(int idLivro) throws Exception {
        Livro livro = livroService.buscarLivroPorId(idLivro);
        if (livro != null) {
            emprestimos.add(livro);
        }
    }

    public boolean removeLivroEmprestimos(int id) {
        return emprestimos.removeIf(x -> x.getId() == id);
    }

    public void addRevistaEmprestimos(int idRevista) throws Exception {
        Revista revista = revistaService.buscarRevistaPorId(idRevista);
        if (revista != null) {
            emprestimos.add(revista);
        }
    }

    public boolean removeRevistaEmprestimos(int id) {
        return emprestimos.removeIf(x -> x.getId() == id);
    }
}
