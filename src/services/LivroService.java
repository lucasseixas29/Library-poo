package services;

import Repository.LivroRepository;
import entities.ItemAcervo;
import entities.Livro;

import java.util.List;
import java.util.stream.Collectors;


public class LivroService {
    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrarLivro(String titulo, Integer ano, String autor) {
        return livroRepository.cadastrarLivro(titulo, ano, autor);
    }

    public List<Livro> listarTodosLivros() {
        if (livroRepository.listarTodosLivros().isEmpty()) {
            System.out.println("Não há livros.");
            return null;
        }
        return livroRepository.listarTodosLivros();
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livroRepository.listarTodosLivros().stream().filter(x -> x.getIsDisponivel()).collect(Collectors.toList());
    }

    public Livro buscarLivroPorId(int idLivro) throws Exception {
        return listarTodosLivros().stream().filter(x -> x.getId() == idLivro)
                .findFirst()
                .orElseThrow(() -> new Exception("Livro não encontrado."));
    }
}
