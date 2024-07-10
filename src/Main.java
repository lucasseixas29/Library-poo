import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca("Java para estudos.");
        Usuario usuario = null;
        Livro livro = null;

        boolean flag = true;
        while (flag) {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Ver usuário pela matrícula");
            System.out.println("3 - Ver todos os usuários");
            System.out.println("4 - Adicionar Livros");
            System.out.println("5 - Ver todos os livros");
            System.out.println("6 - Adicionar Revistas");
            System.out.println("7 - Ver todas as revistas");
            System.out.println("8 - Pegar um livro emprestado pelo ID");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o seu nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Digie a sua matrícula: ");
                    int matricula = sc.nextInt();
                    sc.nextLine();
                    usuario = new Usuario(nome, matricula);
                    biblioteca.getUsuarios().add(usuario);
                    System.out.println("Usuário: " + usuario.getNome() + ", Matrícula: " + usuario.getMatricula());
                    System.out.println("Usuário cadastrado!");
                    break;

                case 2:
                    boolean encontrado = false;
                    System.out.print("Digite sua matrícula: ");
                    matricula = sc.nextInt();
                    sc.nextLine();
                    if (biblioteca.getUsuarios().isEmpty()) {
                        System.out.println("Não há usuários.");
                        break;
                    }
                    for (Usuario user : biblioteca.getUsuarios()) {
                        if (matricula == user.getMatricula()) {
                            encontrado = true;
                            System.out.println(user);
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Usuário com essa matrícula não encontrado.");
                    }
                    break;

                case 3:
                    if (biblioteca.getUsuarios().isEmpty()) {
                        System.out.println("Não há usuários.");
                        break;
                    }

                    for (Usuario user : biblioteca.getUsuarios()) {
                        if (biblioteca.getUsuarios().isEmpty()) {
                            System.out.println("Não há usuários.");
                            break;
                        }
                        System.out.println(user);
                    }
                    break;
                case 4:
                    System.out.println("Adicione um livro: ");
                    System.out.print("Digite o título do livro: ");
                    String titulo = sc.nextLine();
                    System.out.print("Digite o ano do livro: ");
                    int ano = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o ID do livro: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o autor do livro: ");
                    String autor = sc.nextLine();

                    livro = new Livro(titulo, ano, autor, id);
                    biblioteca.getLivros().add(livro);
                    System.out.println(livro);
                    System.out.println("Livro Cadastrado.");
                    break;
                case 5:
                    if (biblioteca.getLivros().isEmpty()) {
                        System.out.println("Sem livros na biblioteca.");
                        break;
                    }
                    for (Livro lvr : biblioteca.getLivros()) {
                        System.out.println(lvr);
                    }
                    break;
                case 6:
                    System.out.println("Adicione uma Revista: ");
                    System.out.print("Digite o título da revista: ");
                    titulo = sc.nextLine();
                    System.out.print("Digite o ano da revista: ");
                    ano = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Digite o ID da revista: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Digite o número da edição: ");
                    int numeroEdicao = sc.nextInt();
                    sc.nextLine();

                    Revista revista = new Revista(titulo, ano, id, numeroEdicao);
                    biblioteca.getRevistas().add(revista);
                    System.out.println(revista);
                    System.out.println("Revista Cadastrada.");
                    break;

                case 7:
                    if (biblioteca.getRevistas().isEmpty()) {
                        System.out.println("Não há revistas.");
                        break;
                    }
                    for (Revista rvt : biblioteca.getRevistas()) {
                        System.out.println(rvt);
                    }
                    break;
                case 8:
                    if (usuario == null) {
                        System.out.println("Realize o cadastro para pegar um livro emprestado.");
                        break;
                    }
                    System.out.println("Livros disponíveis: ");
                    for (Livro l : biblioteca.getLivros()) {
                        if (l.getIsDisponivel()) {
                            System.out.println(l);
                        }
                    }
                    System.out.println();
                    System.out.println("Digite o id do livro para empréstimo: ");
                    int idLivro = sc.nextInt();
                    sc.nextLine();
                    String livroEscolhido = null;
                    boolean livroEncontrado = false;

                    if (biblioteca.getLivros().isEmpty()) {
                        System.out.println("Não há livros no momento.");
                        break;
                    }
                    for (Livro l : biblioteca.getLivros()) {
                        if (l.getId() == idLivro) {
                            livroEscolhido = l.getTitulo();
                            livroEncontrado = true;
                            break;
                        }
                    }
                    if (!livroEncontrado) {
                        System.out.println("ID do livro não encontrado.");
                        break;
                    }
                    if (usuario.getEmprestimos() >= 2) {
                        System.out.println("Você não pode exceder o limite de 2 livros emprestados.");
                        System.out.println("Você já tem: " + usuario.getEmprestimos() + " livros.");
                        System.out.println();
                        break;
                    }

                    for (Livro l : biblioteca.getEmprestimos()) {
                        if (l.getTitulo().equalsIgnoreCase(livroEscolhido) && !l.getIsDisponivel()) {
                            System.out.println("Livro indisponível para empréstimo, escolha outro por favor.");
                            break;
                        }
                    }

                    if (livro != null) {
                        for (Livro l : biblioteca.getLivros()) {
                            if (l.getId() == idLivro && l.getIsDisponivel()) {
                                biblioteca.addLivroEmprestimos(l);
                                usuario.setEmprestimos(1);
                                l.setIsDisponivel(false);
                                System.out.println("Empréstimo do livro bem sucedido.");
                                break;
                            }
                        }
                        break;
                    }
                    break;
            }
        }
    }
}