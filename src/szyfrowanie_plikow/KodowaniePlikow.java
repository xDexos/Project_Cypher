package szyfrowanie_plikow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KodowaniePlikow implements Runnable{

    private ArrayList<KodowanyPlik> lista_kodowanych_plikow;

    public KodowaniePlikow (ArrayList<KodowanyPlik> lista_kodowanych_plikow){
        this.lista_kodowanych_plikow = lista_kodowanych_plikow;
    }

    @Override
    public void run() {

        for (int i = 0; i < lista_kodowanych_plikow.size(); i++){
            if (lista_kodowanych_plikow.get(i).isCzy_wolny() == true && lista_kodowanych_plikow.get(i).isCzy_zaszyfrowany() == false){
                System.out.println("Start czytania pliku");
                lista_kodowanych_plikow.get(i).setCzy_wolny(false);
                lista_kodowanych_plikow.get(i).setCzy_zaszyfrowany(true);
                try {
                    BufferedReader bReader = new BufferedReader(new FileReader(lista_kodowanych_plikow.get(i).getPath()));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    bReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Koniec czytania pliku");
            }
        }

    }
}
