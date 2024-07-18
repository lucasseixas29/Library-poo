package Repository;

import entities.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario cadastrarUsuario(String nome, Integer matricula) {
        Usuario usuario = new Usuario(nome, matricula);
        listaUsuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarTodosUsuarios() {
        return listaUsuarios;
    }


}
