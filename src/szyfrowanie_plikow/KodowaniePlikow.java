package szyfrowanie_plikow;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class KodowaniePlikow extends SzyfrCezara implements Runnable{

    private KolejkaObiektow kolejka;

    private boolean deszyfracja;

    private int progress;
    private JProgressBar progressBar;

    KodowaniePlikow(KolejkaObiektow kolejka, boolean deszyfracja, JProgressBar progressBar){
        this.kolejka = kolejka;
        this.deszyfracja = deszyfracja;
        this.progressBar = progressBar;
    }

    @Override
    public void run() {

        KodowanyPlik kodowanyPlik;

        while((kodowanyPlik = kolejka.getNextObject()) != null){

            kodowanyPlik.setProgressBar(progressBar);
            this.progress = 0;

            if (this.deszyfracja == false) {
                //kodowanyPlik.szyfrowanie();
                //szyfrowanie(kodowanyPlik);

                //System.out.println("Start czytania pliku");
                try {

                    File plik = new File(kodowanyPlik.getPath());

                    long plikRozmiar = plik.length();
                    System.out.println(plikRozmiar + " ROZMIAR ");
                    int czytanyZnak = 0;
                    int czytanieProcent = 0;
                    int c;

                    BufferedReader bReader = new BufferedReader(new FileReader(kodowanyPlik.getPath()));
                    String line;
                    ArrayList<Character> lista_znakow = new ArrayList<>();
                    while ((line = bReader.readLine()) != null) {
                        for (int j = 0; j < line.length(); j++) {
                            lista_znakow.add(line.charAt(j));
                        }
                    }
                    bReader.close();

                    //szyfrowanie cezara
                    for (int i = 0; i < lista_znakow.size(); i++) {
                        //System.out.println(lista_znakow.get(i));

                        int originalAlphabetPosition = lista_znakow.get(i) - 'a';
                        int newAlphabetPosition = (originalAlphabetPosition + kodowanyPlik.getOffset()) % 32;
                        char v = ((char) ('a' + newAlphabetPosition));
                        lista_znakow.set(i, v);
                    }

                    //zapisywanie do pliku
                    BufferedWriter bWriter = null;
                    try {
                        bWriter = new BufferedWriter(new FileWriter(kodowanyPlik.getPath()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for (int i = 0; i < lista_znakow.size(); i++) {
                        bWriter.write(lista_znakow.get(i));

                        czytanyZnak++;
                        int nowyCzytanieProcent = (int) (((double) czytanyZnak / plikRozmiar) * 100);
                        czytanieProcent = nowyCzytanieProcent;
                        this.progress = czytanieProcent;
                        System.out.println("Wątek: " + Thread.currentThread().getName() + "-> Odczytano " + czytanieProcent + "% pliku.");
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                progressBar.setValue(progress);
                                progressBar.setVisible(true);
                            }
                        });
                        try {
                            TimeUnit.MILLISECONDS.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    bWriter.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else{
                //kodowanyPlik.deszyfrowanie();

                //System.out.println("Start czytania pliku");
                try {

                    File plik = new File(kodowanyPlik.getPath());

                    long plikRozmiar = plik.length();
                    System.out.println(plikRozmiar + " ROZMIAR ");
                    int czytanyZnak = 0;
                    int czytanieProcent = 0;
                    int c;

                    BufferedReader bReader = new BufferedReader(new FileReader(kodowanyPlik.getPath()));
                    String line;
                    ArrayList<Character> lista_znakow = new ArrayList<>();
                    while ((line = bReader.readLine()) != null) {
                        for (int j = 0; j < line.length(); j++) {
                            lista_znakow.add(line.charAt(j));
                        }
                    }
                    bReader.close();

                    //deszyfrowanie cezara
                    for (int i = 0; i < lista_znakow.size(); i++) {
                        //System.out.println(lista_znakow.get(i));

                        int originalAlphabetPosition = lista_znakow.get(i) - 'a';
                        int newAlphabetPosition = (originalAlphabetPosition - kodowanyPlik.getOffset()) % 32;
                        char v = ((char) ('a' + newAlphabetPosition));
                        lista_znakow.set(i, v);
                    }

                    //zapisywanie do pliku
                    BufferedWriter bWriter = null;
                    try {
                        bWriter = new BufferedWriter(new FileWriter(kodowanyPlik.getPath()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for (int i = 0; i < lista_znakow.size(); i++) {
                        bWriter.write(lista_znakow.get(i));

                        czytanyZnak++;
                        int nowyCzytanieProcent = (int) (((double) czytanyZnak / plikRozmiar) * 100);
                        czytanieProcent = nowyCzytanieProcent;
                        this.progress = czytanieProcent;
                        System.out.println("Wątek: " + Thread.currentThread().getName() + "-> Odczytano " + czytanieProcent + "% pliku.");
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                progressBar.setValue(progress);
                                progressBar.setVisible(true);
                            }
                        });
                        try {
                            TimeUnit.MILLISECONDS.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    bWriter.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }


        }


    }

    public void szyfrowanie(KodowanyPlik kodowanyPlik){

        //System.out.println("Start czytania pliku");
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(kodowanyPlik.getPath()));
            String line;
            ArrayList<Character> lista_znakow = new ArrayList<>();
            while ((line = bReader.readLine()) != null) {
                for(int j = 0; j < line.length(); j++){
                    lista_znakow.add(line.charAt(j));
                }
            }
            bReader.close();

            //szyfrowanie cezara
            ArrayList<Character> zaszyfrowane_znaki =  szyfowanieCezara(lista_znakow, kodowanyPlik.getOffset());
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(kodowanyPlik.getPath()));
            for (int i = 0; i < zaszyfrowane_znaki.size(); i++){
                bWriter.write(zaszyfrowane_znaki.get(i));

                //aktualizowanie paskow
                float preP = ((float)i+1 / (float)zaszyfrowane_znaki.size());
                //System.out.println("SSS: " + zaszyfrowane_znaki.size());
                //System.out.println("RRR: " + preP);
                this.progress = (int)(preP * 100);
                //System.out.println("PPP: " + progress);
                //System.out.println(this.progress);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar.setValue(progress);
                    }
                });

            }

            bWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
