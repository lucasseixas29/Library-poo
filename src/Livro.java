public class Livro extends ItemAcervo {
    private int id;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, int ano, String autor, int id) {
        super(titulo, ano);
        this.autor = autor;
        this.id = id;
        this.disponivel = true;
    }


    public int getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public boolean getIsDisponivel() {
        return disponivel;
    }

    public void setIsDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Título: " + getTitulo() + ", Ano: " + getAno() + ", Autor: " + getAutor() + ", ID: " + getId() + ", Status: " +
                (getIsDisponivel() ? "Disponível" : "Indisponível");
    }
}
