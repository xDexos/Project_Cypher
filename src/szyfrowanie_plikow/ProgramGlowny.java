package szyfrowanie_plikow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ProgramGlowny{

    public static void main(String[] args) {

        //=========================================================================================================

//        //ścieżka testowa: C:/Projects/IntelliJProjects/Project_Cypher/TestPlikiDoSzyfrowania
//        String path = "C:\\Projects\\IntelliJProjects\\Project_Cypher\\TestPlikiDoSzyfrowania";
//
//        PobieraniePlikow pobieraniePlikow = new PobieraniePlikow(path);
//        Thread watekPobieranie = new Thread(pobieraniePlikow);
//
//        //startowanie wątku pobierającego ścieżki do plików
//        watekPobieranie.start();
//
//        try {
//            watekPobieranie.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        ArrayList<String> paths = new ArrayList<>(pobieraniePlikow.getPaths());
//
//        System.out.println("===kolekcja String ścieżek do plików===");
//        for(int i = 0; i < paths.size(); i++){
//            System.out.println(paths.get(i));
//        }
//
//        //offset dla szyfrowania Cezara
//        int offset = 1;
//
//        //tworzenie listy obiektów plików
//        System.out.println("===kolekcja obiektów plików=== / ILOŚĆ: " + paths.size());
//        ArrayList<KodowanyPlik> lista_kodowanych_plikow = new ArrayList<>();
//        for(int i = 0; i < paths.size(); i++){
//            lista_kodowanych_plikow.add(new KodowanyPlik(paths.get(i), offset));
//            System.out.println(lista_kodowanych_plikow.get(i) + " ścieżka: " + lista_kodowanych_plikow.get(i).getPath());
//        }
//
//        KolejkaObiektow kolejkaObiektow = new KolejkaObiektow(lista_kodowanych_plikow);
//        kolejkaObiektow.wypisz();
//
//        //zmienne dla wątków
//        int liczbaWatkow = 3;
//        boolean szyfrowanie = true;
//
//        KodowaniePlikow[] kodowaniePlikow = new KodowaniePlikow[liczbaWatkow];
//        for(int i = 0; i < liczbaWatkow; i++){
//            kodowaniePlikow[i] = new KodowaniePlikow(kolejkaObiektow, szyfrowanie);
//            new Thread(kodowaniePlikow[i]).start();
//        }

        //=====GUI=================================================================================================

        //=====PANELS=====

        final String[] path = new String[1];
        path[0] = "";
        int liczbaWatkow = 3;
        final int[] liczbaPlikow = new int[1];
        final boolean[] szyfrowanie = new boolean[1];
        int offset = 1;

        JFrame frame = new JFrame();
        final JProgressBar[][] progressBars = new JProgressBar[1][1];

        JLabel czyFolderWybrany = new JLabel();

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setBackground(Color.DARK_GRAY);
        panel2.setBackground(Color.DARK_GRAY);
        panel3.setBackground(Color.LIGHT_GRAY);
        panel4.setBackground(Color.GRAY);

        panel1.setPreferredSize(new Dimension(100, 100));
        panel2.setPreferredSize(new Dimension(100, 50));
        panel3.setPreferredSize(new Dimension(250, 100));
        panel4.setPreferredSize(new Dimension(100, 100));

        panel1.setLayout(new GridBagLayout());
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new GridLayout(liczbaWatkow, 1));

        //=====RADIO=====

        JRadioButton radioButtonSzyfrowanie = new JRadioButton("Szyfrowanie");
        JRadioButton radioButtonDeszyfrowanie = new JRadioButton("Deszyfrowanie");
        ButtonGroup group = new ButtonGroup();
        radioButtonSzyfrowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                szyfrowanie[0] = true;
            }
        });
        radioButtonDeszyfrowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                szyfrowanie[0] = false;
            }
        });

        //=====BUTTONS=====

        JButton dirButton = new JButton("Wyrierz ścieżkę");
        dirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //=====FILE_CHOOSE=====
                //tworzenie instancji FileChooser
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

                //ustawienie wyboru tylko katalogów
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // Wyświetlanie okna dialogowego wyboru folderów
                int result = fileChooser.showOpenDialog(panel3);

                // Sprawdzanie, czy użytkownik wybrał folder
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Pobieranie wybranej ścieżki do folderu
                    path[0] = fileChooser.getSelectedFile().getPath();

                    // Wyświetlanie ścieżki do folderu
                    System.out.println("Wybrana ścieżka do folderu: " + path[0]);
                }
                if (Objects.equals(path[0], "")){
                    System.out.println("Brak ścieżki do folderu");
                }else{
                    czyFolderWybrany.setText("Wybrano folder.");
                }

            }

        });

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) {

                //ścieżka testowa: C:/Projects/IntelliJProjects/Project_Cypher/TestPlikiDoSzyfrowania
                //String path = "C:\\Projects\\IntelliJProjects\\Project_Cypher\\TestPlikiDoSzyfrowania";

                PobieraniePlikow pobieraniePlikow = new PobieraniePlikow(path[0]);
                Thread watekPobieranie = new Thread(pobieraniePlikow);

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

                //offset dla szyfrowania Cezara
                //int offset = 1;

                //tworzenie listy obiektów plików
                System.out.println("===kolekcja obiektów plików=== / ILOŚĆ: " + paths.size());
                ArrayList<KodowanyPlik> lista_kodowanych_plikow = new ArrayList<>();
                for(int i = 0; i < paths.size(); i++){
                    lista_kodowanych_plikow.add(new KodowanyPlik(paths.get(i), offset));
                    System.out.println(lista_kodowanych_plikow.get(i) + " ścieżka: " + lista_kodowanych_plikow.get(i).getPath());
                }

                KolejkaObiektow kolejkaObiektow = new KolejkaObiektow(lista_kodowanych_plikow);
                kolejkaObiektow.wypisz();
                liczbaPlikow[0] = kolejkaObiektow.getSize();

                //zmienne dla wątków
//                int liczbaWatkow = 3;
//                boolean szyfrowanie = false;

                KodowaniePlikow[] kodowaniePlikow = new KodowaniePlikow[liczbaWatkow];
                for(int i = 0; i < liczbaWatkow; i++){
                    kodowaniePlikow[i] = new KodowaniePlikow(kolejkaObiektow, szyfrowanie[0]);
                    new Thread(kodowaniePlikow[i]).start();
                }

            }
        });

        //=====LABELS=====

        JLabel titleLabel = new JLabel("PROGRAM SZYFRUJĄCY WIELOWĄTKOWO");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);
        Font titleFont = new Font("Times New Roman", Font.BOLD, 24);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);

        //JLabel czyFolderWybrany = new JLabel();
        czyFolderWybrany.setText("Nie wybrano folderu.");

        //=====PROGRESS_BARS=====


        //=====FRAME=====

//        JFrame frame = new JFrame();
        frame.setTitle("Szyfrowanie Wielowątkowe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(800, 600);

        //=====ADDS=====

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.CENTER);

        panel1.add(titleLabel);

        panel3.add(radioButtonSzyfrowanie);
        panel3.add(radioButtonDeszyfrowanie);
        panel3.add(dirButton);
        panel3.add(czyFolderWybrany);
        panel3.add(startButton);

        group.add(radioButtonSzyfrowanie);
        group.add(radioButtonDeszyfrowanie);

        ImageIcon imageIcon = new ImageIcon("src/logo_java.png");
        frame.setIconImage(imageIcon.getImage());

        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();

    }

}
