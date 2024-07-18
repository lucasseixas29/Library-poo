package services;

import Repository.UsuarioRepository;
import entities.Usuario;


public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario cadastrarUsuario(String nome, Integer matricula) {
        Usuario usuario = new Usuario(nome, matricula);
        usuarioRepository.cadastrarUsuario(nome, matricula);
        return usuario;
    }

    public Usuario buscarUsuarioPorMatricula(Integer matricula) throws Exception {
        return usuarioRepository.listarTodosUsuarios().stream()
                .filter(x -> x.getMatricula().equals(matricula)).findFirst()
                .orElseThrow(() -> new Exception("Usuário com essa matrícula não encontrado."));
    }

    public void listarTodosUsuarios() {
        if (usuarioRepository.listarTodosUsuarios().isEmpty()) {
            System.out.println("Não há usuários.");
        }
        for (Usuario user : usuarioRepository.listarTodosUsuarios()) {
            System.out.println(user);
        }
    }
}
