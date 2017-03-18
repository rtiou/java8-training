package com.tiou.chapters;

import com.tiou.interfaces.Validator;

/**
 * Created by ronaldo on 06/03/2017.
 */
public class Capitulo3 {
    public static void main(String ... args){
        Validator<String> validadorCEP = valor -> valor.matches("[0-9]{5}-[0-9]{3}");

        System.out.println(validadorCEP.valida("sdfg"));
        System.out.println(validadorCEP.valida("04001-099"));

        Runnable o = () -> {
            System.out.println("How am I? What lambda?");
        };

        System.out.println(o);
        System.out.println(o.getClass());

        //################
        int number = 5;
        new Thread(() -> {
            System.out.println(number);
        }).start();
    }
}
