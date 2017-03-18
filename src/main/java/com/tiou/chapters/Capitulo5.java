package com.tiou.chapters;

import com.tiou.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class Capitulo5 {
    public static void main(String ... args) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("User X", 990));
        usuarios.add(new Usuario("User A1", 150));
        usuarios.add(new Usuario("User B2", 120));
        usuarios.add(new Usuario("User C3", 190));

        Comparator<Usuario> comparator = new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        };

        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));

        Collections.sort(usuarios, comparator);
        usuarios.forEach(usuario -> System.out.println(usuario.getNome()));

        //############
        Comparator<Usuario> lambdaComparator = (usuario1, usuario2) -> usuario1.getNome().compareTo(usuario2.getNome());
        Collections.sort(usuarios, lambdaComparator);

        //##############
        Collections.sort(usuarios, (usuario1, usuario2) -> String.CASE_INSENSITIVE_ORDER.compare(usuario1.getNome(),usuario2.getNome()));
    }
}
