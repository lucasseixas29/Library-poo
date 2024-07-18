package Repository;

import entities.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LivroRepository {
    private List<Livro> listaLivros = new ArrayList<>();
    AtomicInteger COUNTERLIVRO = new AtomicInteger();

    public Livro cadastrarLivro(String titulo, int ano, String autor) {
        Livro livro = new Livro(titulo, ano, true, COUNTERLIVRO.incrementAndGet(), autor);
        listaLivros.add(livro);
        return livro;
    }

    public List<Livro> listarTodosLivros() {
        return listaLivros;
    }
}
