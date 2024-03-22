package szyfrowanie_plikow;

import java.util.ArrayList;

public class SzyfrCezara {

    public ArrayList<Character> szyfowanieCezara(ArrayList<Character> lista_znakow, int offset){

        ArrayList<Character> znaki_do_szyfrowania = new ArrayList<>(lista_znakow);

        for (int i = 0; i < znaki_do_szyfrowania.size(); i++){

            if (znaki_do_szyfrowania.get(i) != ' '){

                int originalAlphabetPosition = znaki_do_szyfrowania.get(i) - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 32;
                char c = ((char) ('a' + newAlphabetPosition));
                znaki_do_szyfrowania.set(i, c);
            }

        }

        return znaki_do_szyfrowania;

    }

    public ArrayList<Character> deszyfrowanieCezara(ArrayList<Character> lista_znakow, int offset){

        ArrayList<Character> znaki_do_szyfrowania = new ArrayList<>(lista_znakow);

        //deszyfrowanie Cezara
        for (int i = 0; i < znaki_do_szyfrowania.size(); i++){

            if (znaki_do_szyfrowania.get(i) != ' '){

                int originalAlphabetPosition = znaki_do_szyfrowania.get(i) - 'a';
                int newAlphabetPosition = (originalAlphabetPosition - offset) % 32;
                char c = ((char) ('a' + newAlphabetPosition));
                znaki_do_szyfrowania.set(i, c);
            }

        }

        return znaki_do_szyfrowania;

    }

}
