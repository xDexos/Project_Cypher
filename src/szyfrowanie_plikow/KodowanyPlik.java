package szyfrowanie_plikow;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class KodowanyPlik{

    private String path;

    public void szyfrowanie(){

        System.out.println("Start czytania pliku");
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(this.getPath()));
            String line;
            ArrayList<Character> lista_znakow = new ArrayList<>();
            while ((line = bReader.readLine()) != null) {
                for(int j = 0; j < line.length(); j++){
                    lista_znakow.add(line.charAt(j));
                }
            }
            bReader.close();

            //testowe wypisaywanie pojedynczych znaków
            System.out.print("Znaki w pliku: ");
            for (int j = 0; j < lista_znakow.size(); j++){
                System.out.print(lista_znakow.get(j) + " ");
//                try {
//                    TimeUnit.MILLISECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }

            //testowe dopisanie znaku
//            BufferedWriter bWriter = new BufferedWriter(new FileWriter(this.getPath()));
//            bWriter.write("##");
//            bWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("Koniec czytania pliku");

    }

    public KodowanyPlik(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
