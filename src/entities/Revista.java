package entities;

public class Revista extends ItemAcervo {
    private int numeroEdicao;

    public Revista(String titulo, int ano, boolean disponivel, int id, int numeroEdicao) {
        super(titulo, ano, disponivel, id);
        this.numeroEdicao = numeroEdicao;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    @Override
    public String toString() {
        return "entities.Revista: " + getTitulo() + ", Ano: " + getAno() + ", ID: "
                + getId() + ", Número edição: " + numeroEdicao + ", status: " + (getIsDisponivel() ? "Disponível" : "Indisponível");
    }
}
