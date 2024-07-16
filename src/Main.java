import entities.*;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca("Java para estudos.");
        Usuario usuario = null;
        Livro livro = null;
        Revista revista = null;

        AtomicInteger COUNTERLIVRO = new AtomicInteger();
        AtomicInteger COUNTERREVISTA = new AtomicInteger();


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
            System.out.println("9 - Pegar uma revista emprestada pelo ID");
            System.out.println("10 - Devolver o livro pelo ID");
            System.out.println("11 - Sair");

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
                    System.out.print("Digite sua matrícula: ");
                    matricula = sc.nextInt();
                    sc.nextLine();
                    if (biblioteca.getUsuarios().isEmpty()) {
                        System.out.println("Não há usuários.");
                        break;
                    }

                    Usuario usuarioEncontrado = biblioteca.getUsuarios().stream()
                            .filter(x -> x.getMatricula().equals(matricula)).findFirst()
                            .orElse(null);

                    if (usuarioEncontrado != null) {
                        System.out.println(usuarioEncontrado);
                        break;
                    } else {
                        System.out.println("Usuário com essa matrícula não encontrado.");
                    }
                    break;

                case 3:
                    if (biblioteca.getUsuarios().isEmpty()) {
                        System.out.println("Não há usuários.");
                        break;
                    }

                    for (Usuario user : biblioteca.getUsuarios()) {
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
                    System.out.print("Digite o autor do livro: ");
                    String autor = sc.nextLine();

                    livro = new Livro(titulo, ano, true, COUNTERLIVRO.incrementAndGet(), autor);
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
                    System.out.print("Digite o número da edição: ");
                    int numeroEdicao = sc.nextInt();
                    sc.nextLine();

                    revista = new Revista(titulo, ano, true, COUNTERREVISTA.incrementAndGet(), numeroEdicao);
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
                    int livrosDisponiveis = 0;
                    for (Livro l : biblioteca.getLivros()) {
                        if (l.getIsDisponivel()) {
                            livrosDisponiveis++;
                            System.out.println(l);
                        }
                    }
                    if (livrosDisponiveis == 0) {
                        System.out.println("0");
                        System.out.println();
                        break;
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

                    for (ItemAcervo l : biblioteca.getEmprestimos()) {
                        if (l.getTitulo().equalsIgnoreCase(livroEscolhido) && !l.getIsDisponivel()) {
                            System.out.println("entities.Livro indisponível para empréstimo, escolha outro por favor.");
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
                case 9:
                    if (usuario == null) {
                        System.out.println("Realize o cadastro para pegar um livro emprestado.");
                        break;
                    }
                    System.out.println("Revistas disponíveis: ");
                    int countRevistasDisponiveis = 0;
                    for (Revista r : biblioteca.getRevistas()) {
                        if (r.getIsDisponivel()) {
                            countRevistasDisponiveis++;
                            System.out.println(r);
                        }
                    }
                    if (countRevistasDisponiveis == 0) {
                        System.out.println('0');
                        System.out.println();
                        break;
                    }
                    System.out.println();
                    System.out.println("Digite o id da revista para empréstimo: ");
                    int idRevista = sc.nextInt();
                    sc.nextLine();
                    String revistaEscolhida = null;
                    boolean revistaEncontrada = false;

                    if (biblioteca.getRevistas().isEmpty()) {
                        System.out.println("Não há revistas no momento.");
                        break;
                    }
                    for (Revista r : biblioteca.getRevistas()) {
                        if (r.getId() == idRevista) {
                            revistaEscolhida = r.getTitulo();
                            revistaEncontrada = true;
                            break;
                        }
                    }
                    if (!revistaEncontrada) {
                        System.out.println("ID da revista não encontrada.");
                        break;
                    }
                    if (usuario.getEmprestimos() >= 2) {
                        System.out.println("Você não pode exceder o limite de 2 empréstimos.");
                        System.out.println("Você já tem: " + usuario.getEmprestimos() + " empréstimos.");
                        System.out.println();
                        break;
                    }

                    for (ItemAcervo r : biblioteca.getEmprestimos()) {
                        if (r.getTitulo().equalsIgnoreCase(revistaEscolhida) && !r.getIsDisponivel()) {
                            System.out.println("entities.Revista indisponível para empréstimo, escolha outra por favor.");
                            break;
                        }
                    }

                    if (revista != null) {
                        for (Revista r : biblioteca.getRevistas()) {
                            if (r.getId() == idRevista && r.getIsDisponivel()) {
                                biblioteca.addRevistaEmprestimos(r);
                                usuario.setEmprestimos(1);
                                r.setIsDisponivel(false);
                                System.out.println("Empréstimo da revista bem sucedido.");
                                break;
                            }
                        }
                        break;
                    }
                    break;
                case 10:
                    if (biblioteca.getEmprestimos().isEmpty() || usuario.getEmprestimos() == 0) {
                        System.out.println("Não há livros para você devolver.");
                        break;
                    }
                    System.out.println("Digite o id do livro que você quer devolver: ");
                    int idLivroDevolvido = sc.nextInt();
                    sc.nextLine();

                    if (biblioteca.removeLivroEmprestimos(idLivroDevolvido)) {
                        if (usuario != null && usuario.getEmprestimos() > 0) {
                            usuario.setEmprestimos(-1);
                        }
                        for (ItemAcervo item : biblioteca.getLivros()) {
                            if (item.getId() == idLivroDevolvido) {
                                item.setIsDisponivel(true);
                                break;
                            }
                        }
                        System.out.println("Livro devolvido com sucesso.");
                        break;
                    } else {
                        System.out.println("Você não pegou este livro emprestado.");
                        break;
                    }

                case 11:
                    System.out.println("Saindo...");
                    flag = false;
            }
        }
    }
}