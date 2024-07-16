public abstract class ItemAcervo {
    private String titulo;
    private int ano;
    private boolean disponivel;
    private int id;

    public ItemAcervo(String titulo, int ano, boolean disponivel, int id) {
        this.titulo = titulo;
        this.ano = ano;
        this.disponivel = true;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean getIsDisponivel() {
        return disponivel;
    }

    public void setIsDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getId() {
        return id;
    }
}
