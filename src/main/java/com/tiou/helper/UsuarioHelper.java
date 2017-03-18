package com.tiou.helper;

import com.tiou.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class UsuarioHelper {
    public static List<Usuario> getUsuarioList(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("User X 1", 991));
        usuarios.add(new Usuario("User A1 2", 150));
        usuarios.add(new Usuario("User B2 3", 129));
        usuarios.add(new Usuario("User C3 4", 198, true));
        usuarios.add(new Usuario("User B2 5", 127));
        usuarios.add(new Usuario("User C3 6", 196, true));
        usuarios.add(new Usuario("User B2 7", 125));
        usuarios.add(new Usuario("User C3 8", 194));
        usuarios.add(new Usuario("User B2 9", 123, true));
        usuarios.add(new Usuario("User C3 1", 192));
        usuarios.add(new Usuario("d", 191));
        usuarios.add(new Usuario("User X 3", 99));
        usuarios.add(new Usuario("User A1 4", 159));
        usuarios.add(new Usuario("User B2 51", 128));
        usuarios.add(new Usuario("User C3 62", 197));
        usuarios.add(new Usuario("User B2 73", 126));
        usuarios.add(new Usuario("User C3 84", 195));
        usuarios.add(new Usuario("User B2 95", 124));
        usuarios.add(new Usuario("User C3 16", 193));
        usuarios.add(new Usuario("User B2 27", 122));
        usuarios.add(new Usuario("User C3 38", 191));
        usuarios.add(new Usuario(null, 19));
        usuarios.add(new Usuario("User C3 39", 991));
        usuarios.add(new Usuario("User C3 1XX", 192));

        return usuarios;
    }
}
