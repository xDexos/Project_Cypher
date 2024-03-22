package szyfrowanie_plikow;

import java.util.ArrayList;

public class ProgramGlowny {

    public static void main(String[] args) {

        //ścieżka testowa: C:/Projects/IntelliJProjects/Project_Cypher/TestPlikiDoSzyfrowania

        PobieraniePlikow pobieraniePlikow = new PobieraniePlikow("C:/Projects/IntelliJProjects/Project_Cypher/TestPlikiDoSzyfrowania");
        Thread watekPobieranie = new Thread(pobieraniePlikow);

        //startowanie wątku pobierającego ścieżki do plików
        watekPobieranie.start();

        try {
            watekPobieranie.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> paths = new ArrayList<>(pobieraniePlikow.getPaths());

        System.out.println("===kolekcja String ścieżek do plików===");
        for(int i = 0; i < paths.size(); i++){
            System.out.println(paths.get(i));
        }

        //tworzenie listy obiektów plików
        System.out.println("===kolekcja obiektów plików=== / ILOŚĆ: " + paths.size());
        ArrayList<KodowanyPlik> lista_kodowanych_plikow = new ArrayList<>();
        for(int i = 0; i < paths.size(); i++){
            lista_kodowanych_plikow.add(new KodowanyPlik(paths.get(i)));
            System.out.println(lista_kodowanych_plikow.get(i) + " ścieżka: " + lista_kodowanych_plikow.get(i).getPath());
        }

        KolejkaObiektow kolejkaObiektow = new KolejkaObiektow(lista_kodowanych_plikow);
        kolejkaObiektow.wypisz();

        KodowaniePlikow kodowaniePlikow1 = new KodowaniePlikow(kolejkaObiektow);
        Thread watekKodowanie1 = new Thread(kodowaniePlikow1);
        KodowaniePlikow kodowaniePlikow2 = new KodowaniePlikow(kolejkaObiektow);
        Thread watekKodowanie2 = new Thread(kodowaniePlikow2);
        KodowaniePlikow kodowaniePlikow3 = new KodowaniePlikow(kolejkaObiektow);
        Thread watekKodowanie3 = new Thread(kodowaniePlikow3);
        watekKodowanie1.start();
        watekKodowanie2.start();
        watekKodowanie3.start();

    }

}
