import Repository.LivroRepository;
import Repository.RevistaRepository;
import Repository.UsuarioRepository;
import entities.*;
import services.EmprestimoService;
import services.LivroService;
import services.RevistaService;
import services.UsuarioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        LivroRepository livroRepository = new LivroRepository();
        LivroService livroService = new LivroService(livroRepository);
        RevistaRepository revistaRepository = new RevistaRepository();
        RevistaService revistaService = new RevistaService(revistaRepository);

        Biblioteca biblioteca = new Biblioteca("Biblioteca Java", livroService, revistaService);
        EmprestimoService emprestimoService = new EmprestimoService(biblioteca);


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
            System.out.println("11 - Devolver a revista pelo ID");
            System.out.println("12 - Sair");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o seu nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Digie a sua matrícula: ");
                    int matricula = sc.nextInt();
                    sc.nextLine();

                    Usuario usuario = usuarioService.cadastrarUsuario(nome, matricula);
                    System.out.println("Usuário: " + usuario.getNome() + ", Matrícula: " + usuario.getMatricula());
                    System.out.println("Usuário cadastrado!");

                    break;
                case 2:
                    try {
                        System.out.print("Digite sua matrícula: ");
                        matricula = sc.nextInt();
                        sc.nextLine();

                        System.out.println(usuarioService.buscarUsuarioPorMatricula(matricula));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    usuarioService.listarTodosUsuarios();
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

                    Livro livro = livroService.cadastrarLivro(titulo, ano, autor);

                    System.out.println(livro);
                    System.out.println("Livro Cadastrado.");
                    break;
                case 5:
                    if (livroService.listarTodosLivros() != null) {
                        for (Livro l : livroService.listarTodosLivros()) {
                            System.out.println(l);
                        }
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

                    Revista revista = revistaService.cadastrarRevista(titulo, ano, numeroEdicao);
                    System.out.println(revista);
                    System.out.println("Revista Cadastrada.");
                    break;
                case 7:
                    if (revistaService.listarTodasRevistas() != null) {
                        for (Revista r : revistaService.listarTodasRevistas()) {
                            System.out.println(r);
                        }
                    }
                    break;
                case 8:
                    try {
                        System.out.print("Digite a matricula do seu usuário: ");
                        matricula = sc.nextInt();
                        sc.nextLine();
                        int livrosDisponiveis = 0;
                        usuario = usuarioService.buscarUsuarioPorMatricula(matricula);
                        if (usuario != null) {
                            System.out.println("Livros disponíveis: ");

                            for (Livro l : livroService.listarLivrosDisponiveis()) {
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

                        }
                        System.out.println();
                        System.out.println("Digite o id do livro para empréstimo: ");
                        int idLivro = sc.nextInt();
                        sc.nextLine();
                        String livroEscolhido = null;
                        boolean livroEncontrado = false;

                        for (Livro l : livroService.listarTodosLivros()) {
                            if (l.getId() == idLivro) {
                                livroEscolhido = l.getTitulo();
                                livroEncontrado = true;
                                break;
                            }
                        }
                        if (!livroEncontrado) {
                            System.out.println("Id do livro não encontrado.");
                            break;
                        }
                        if (usuario.getEmprestimos() >= 2) {
                            System.out.println("Você não pode exceder o limite de 2 livros emprestados.");
                            System.out.println("Você já tem: " + usuario.getEmprestimos() + " livros.");
                            System.out.println();
                            break;
                        }

                        for (ItemAcervo l : emprestimoService.listarEmprestimos()) {
                            if (l.getTitulo().equalsIgnoreCase(livroEscolhido) && !l.getIsDisponivel()) {
                                System.out.println("Livro indisponível para empréstimo, escolha outro por favor.");
                                break;
                            }
                        }
                        livro = livroService.buscarLivroPorId(idLivro);
                        if (livro != null) {
                            if (livro.getIsDisponivel()) {
                                emprestimoService.adicionarLivroEmprestimo(livro.getId());
                                usuario.setEmprestimos(1);
                                livro.setIsDisponivel(false);
                                System.out.println("Empréstimo do livro bem sucedido.");
                                break;
                            }
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 9:
                    try {
                        System.out.print("Digite a sua matrícula: ");
                        matricula = sc.nextInt();
                        sc.nextLine();
                        usuario = usuarioService.buscarUsuarioPorMatricula(matricula);
                        int revistaDisponiveis = 0;
                        if (usuario != null) {

                            System.out.println("Revistas disponíveis: ");
                            for (Revista r : revistaService.listarTodasRevistas()) {
                                if (r.getIsDisponivel()) {
                                    revistaDisponiveis++;
                                    System.out.println(r);
                                }
                            }
                            if (revistaDisponiveis == 0) {
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
                            if (revistaService.listarTodasRevistas().isEmpty()) {
                                System.out.println("Não há revistas no momento.");
                                break;
                            }
                            for (Revista r : revistaService.listarTodasRevistas()) {
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

                            for (ItemAcervo r : emprestimoService.listarEmprestimos()) {
                                if (r.getTitulo().equalsIgnoreCase(revistaEscolhida) && !r.getIsDisponivel()) {
                                    System.out.println("Revista indisponível para empréstimo, escolha outra por favor.");
                                    break;
                                }
                            }
                            revista = revistaService.buscarRevistaPorId(idRevista);
                            if (revista != null) {
                                if (revista.getIsDisponivel()) {
                                    emprestimoService.adicionarRevistaEmprestimo(revista.getId());
                                    usuario.setEmprestimos(1);
                                    revista.setIsDisponivel(false);
                                    System.out.println("Empréstimo da revista bem sucedido.");
                                    break;
                                }
                            }
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:
                    try {
                        System.out.print("Digite sua matrícula: ");
                        matricula = sc.nextInt();
                        sc.nextLine();
                        usuario = usuarioService.buscarUsuarioPorMatricula(matricula);

                        if (emprestimoService.listarEmprestimos().isEmpty() || usuario.getEmprestimos() == 0) {
                            System.out.println("Não há livros para você devolver.");
                            break;
                        }
                        System.out.println("Digite o id do livro que você quer devolver: ");
                        int idLivroDevolvido = sc.nextInt();
                        sc.nextLine();

                        if (emprestimoService.removerLivroEmprestimo(idLivroDevolvido)) {
                            if (usuario != null && usuario.getEmprestimos() > 0) {
                                usuario.setEmprestimos(-1);
                            }
                            livro = livroService.buscarLivroPorId(idLivroDevolvido);
                            livro.setIsDisponivel(true);
                            System.out.println("Livro devolvido com sucesso.");
                            break;
                        } else {
                            System.out.println("Você não pegou este livro emprestado.");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        System.out.print("Digite sua matrícula: ");
                        matricula = sc.nextInt();
                        sc.nextLine();
                        usuario = usuarioService.buscarUsuarioPorMatricula(matricula);

                        if (emprestimoService.listarEmprestimos().isEmpty() || usuario.getEmprestimos() == 0) {
                            System.out.println("Não há revistas para você devolver.");
                            break;
                        }
                        System.out.println("Digite o id da revista que você quer devolver: ");
                        int idRevistaDevolvida = sc.nextInt();
                        sc.nextLine();

                        if (emprestimoService.removerRevistaEmprestimo(idRevistaDevolvida)) {
                            if (usuario != null && usuario.getEmprestimos() > 0) {
                                usuario.setEmprestimos(-1);
                            }
                            revista = revistaService.buscarRevistaPorId(idRevistaDevolvida);
                            revista.setIsDisponivel(true);
                            System.out.println("Revista devolvida com sucesso.");
                            break;
                        } else {
                            System.out.println("Você não pegou esta revista emprestada.");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 12:
                    System.out.println("Saindo...");
                    flag = false;
            }
        }
    }
}