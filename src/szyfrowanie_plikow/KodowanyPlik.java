package szyfrowanie_plikow;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class KodowanyPlik extends SzyfrCezara {

    private String path;

    private int offset;

    private int progress;
    private JProgressBar progressBar;

    public void szyfrowanie(){

        //System.out.println("Start czytania pliku");
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

            //szyfrowanie cezara
            ArrayList<Character> zaszyfrowane_znaki =  szyfowanieCezara(lista_znakow, this.offset);
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(this.getPath()));
            for (int i = 0; i < zaszyfrowane_znaki.size(); i++){
                bWriter.write(zaszyfrowane_znaki.get(i));

                //aktualizowanie paskow
                float preP = ((float)i+1 / (float)zaszyfrowane_znaki.size());
                //System.out.println("SSS: " + zaszyfrowane_znaki.size());
                //System.out.println("RRR: " + preP);
                this.progress = (int)(preP * 100);
                //System.out.println("PPP: " + progress);
                //System.out.println(this.progress);

            }

            bWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deszyfrowanie(){

        //System.out.println("Start czytania pliku");
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

            //deszyfrowanie Cezara
            ArrayList<Character> zdeszyfowane_znaki =  deszyfrowanieCezara(lista_znakow, this.offset);
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(this.getPath()));
            for (int i = 0; i < zdeszyfowane_znaki.size(); i++){
                bWriter.write(zdeszyfowane_znaki.get(i));
            }
            bWriter.close();

            //testowe wypisaywanie pojedynczych znaków
//            System.out.print("Znaki w pliku: ");
//            for (int j = 0; j < lista_znakow.size(); j++){
//                System.out.print(lista_znakow.get(j) + " ");
//                try {
//                    TimeUnit.MILLISECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            //testowe dopisanie znaku
//            BufferedWriter bWriter = new BufferedWriter(new FileWriter(this.getPath(), true));
//            bWriter.write("#");
//            bWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println();
        //System.out.println("Koniec czytania pliku");

    }

    public KodowanyPlik(String path, int offset){
        this.path = path;
        this.offset = offset;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setProgressBar (JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public int getProgress () {
        return this.progress;
    }

    public int getOffset () {
        return this.offset;
    }

}
