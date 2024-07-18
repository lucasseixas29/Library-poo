package services;

import Repository.RevistaRepository;
import entities.Livro;
import entities.Revista;

import java.util.List;


public class RevistaService {
    private RevistaRepository revistaRepository;

    public RevistaService(RevistaRepository revistaReposiory) {
        this.revistaRepository = revistaReposiory;
    }

    public Revista cadastrarRevista(String titulo, int ano, int numeroEdicao) {
        return revistaRepository.cadastrarRevista(titulo, ano, numeroEdicao);
    }

    public List<Revista> listarTodasRevistas() {
        if (revistaRepository.listarTodasRevistas().isEmpty()) {
            System.out.println("Não há revistas.");
            return null;
        }
        return revistaRepository.listarTodasRevistas();
    }

    public Revista buscarRevistaPorId(int idRevista) throws Exception {
        return listarTodasRevistas().stream().filter(x -> x.getId() == idRevista)
                .findFirst()
                .orElseThrow(() -> new Exception("Livro não encontrado."));
    }
}
