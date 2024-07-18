package services;

import entities.Biblioteca;
import entities.ItemAcervo;

import java.util.List;

public class EmprestimoService {
    private Biblioteca biblioteca;

    public EmprestimoService(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public List<ItemAcervo> listarEmprestimos() {
        return biblioteca.getEmprestimos();
    }

    public void adicionarLivroEmprestimo(int idLivro) throws Exception {
        biblioteca.addLivroEmprestimos(idLivro);
    }

    public boolean removerLivroEmprestimo(int idLivro) {
        return biblioteca.removeLivroEmprestimos(idLivro);
    }

    public void adicionarRevistaEmprestimo(int idRevista) throws Exception {
        biblioteca.addRevistaEmprestimos(idRevista);
    }

    public boolean removerRevistaEmprestimo(int idRevista) {
        return biblioteca.removeRevistaEmprestimos(idRevista);
    }
}
