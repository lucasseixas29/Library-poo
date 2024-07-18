package Repository;

import entities.Revista;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RevistaRepository {
    private List<Revista> listaRevistas = new ArrayList<>();
    AtomicInteger COUNTERREVISTA = new AtomicInteger();

    public Revista cadastrarRevista(String titulo, int ano, int numeroEdicao) {
        Revista revista = new Revista(titulo, ano, true, COUNTERREVISTA.incrementAndGet(), numeroEdicao);
        listaRevistas.add(revista);
        return revista;
    }

    public List<Revista> listarTodasRevistas() {
        return listaRevistas;
    }
}
