public class Revista extends ItemAcervo {
    private int id;
    private int numeroEdicao;


    public Revista(String titulo, int ano, int id, int numeroEdicao) {
        super(titulo, ano);
        this.id = id;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Revista: " + getTitulo() + ", Ano: " + getAno() + ", ID: " + id + ", Número edição: " + numeroEdicao;
    }
}
