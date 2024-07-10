public class Revista extends ItemAcervo {
    private int numeroEdicao;

    public Revista(String titulo, int ano, boolean disponivel, int id, int numeroEdicao) {
        super(titulo, ano, disponivel, id);
        this.numeroEdicao = numeroEdicao;
    }

    @Override
    public int getAno() {
        return super.getAno();
    }

    @Override
    public String getTitulo() {
        return super.getTitulo();
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "Revista: " + getTitulo() + ", Ano: " + getAno() + ", ID: "
                + getId() + ", Número edição: " + numeroEdicao + ", status: " + (getIsDisponivel() ? "Disponível" : "Indisponível");
    }
}
