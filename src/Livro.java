public class Livro extends ItemAcervo {
    private String autor;

    public Livro(String titulo, int ano, boolean disponivel, int id, String autor) {
        super(titulo, ano, disponivel, id);
        this.autor = autor;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Título: " + getTitulo() + ", Ano: " + getAno() + ", Autor: " + getAutor() + ", ID: " + getId() + ", Status: " +
                (getIsDisponivel() ? "Disponível" : "Indisponível");
    }
}
