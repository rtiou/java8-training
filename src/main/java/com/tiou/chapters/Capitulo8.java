package com.tiou.chapters;

import com.tiou.entity.Usuario;
import com.tiou.helper.UsuarioHelper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by ronaldo on 11/03/2017.
 */
public class Capitulo8 {

    public static void main(String ...args) throws IOException {
        List<Usuario> usuarios = UsuarioHelper.getUsuarioList();
        //peek(usuarios);
        //iterator(usuarios);
        //streamIlimitado();
        files();
    }

    public static void peek(List<Usuario> usuarios){
        usuarios
                .stream()
                .filter(usuario -> usuario.getPontos() < 100)
                .peek(System.out::println)
                .findAny();
    }

    public static void iterator(List<Usuario> usuarios) {
        usuarios
                .stream()
                .iterator()
                .forEachRemaining(usuario -> {
                    usuario.tornarModerador();
                    usuario.setPontos(usuario.getPontos() + 1);
                });
        usuarios.forEach(System.out::println);
    }

    public static void streamIlimitado(){
        Random random = new Random(0);
        IntStream stream = IntStream.generate(() -> random.nextInt());

        List<Integer> list = stream
                .limit(100)
                .boxed()
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    public static void files() throws IOException{
            Files.list(Paths.get("./src/main/java/com/tiou/casaDoCodigo"))
                    .filter(path -> path.toString().endsWith(".java"))
                    .flatMap(path -> lines(path))
                    .flatMapToInt((String s) -> s.chars())
                    .forEachOrdered(System.out::println);
    }

    static Stream<String> lines (Path p) {
        try {
            return Files.lines(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

