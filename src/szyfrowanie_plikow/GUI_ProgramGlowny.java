package szyfrowanie_plikow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class GUI_ProgramGlowny{

    KolejkaObiektow kolejkaObiektow;
    private String[] path = new String[1];
    private boolean szyfrowanie;
    private int liczbaWatkow;
    KodowaniePlikow[] kodowaniePlikow;
    private int offset;
    private boolean czyFolderWybrany;

    private JLabel czyFolderWybranyLabel = new JLabel();

    private JFrame frame = new JFrame();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();

    private JProgressBar[] progressBars;

    private JSlider slider;

    public GUI_ProgramGlowny() {
        this.path[0] = "";
        this.liczbaWatkow = 3;
        this.offset = 1;
        this.czyFolderWybrany = false;
        this.kodowaniePlikow = new KodowaniePlikow[liczbaWatkow];

        //=====GUI=================================================================================================

        //=====PANELS=====
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

        //=====RADIO=====
        JRadioButton radioButtonSzyfrowanie = new JRadioButton("Szyfrowanie");
        JRadioButton radioButtonDeszyfrowanie = new JRadioButton("Deszyfrowanie");
        ButtonGroup group = new ButtonGroup();
        radioButtonSzyfrowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                szyfrowanie = false;
            }
        });
        radioButtonDeszyfrowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                szyfrowanie = true;
            }
        });

        //=====BUTTONS=====
        JButton dirButton = new JButton("Wyrierz ścieżkę");
        dirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wybierzFolder();

            }

        });

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ee) {

//                radioButtonSzyfrowanie.setEnabled(false);
//                radioButtonDeszyfrowanie.setEnabled(false);
//                slider.setEnabled(false);
//                startButton.setEnabled(false);
//                radioButtonSzyfrowanie.setVisible(true);
//                radioButtonDeszyfrowanie.setVisible(true);
//                slider.setVisible(true);
//                startButton.setVisible(true);

                kolejkaObiektow = pobierzDane();
                szyfrujDane(kolejkaObiektow);
//                startButton.setEnabled(true);
//                radioButtonSzyfrowanie.setEnabled(true);
//                radioButtonDeszyfrowanie.setEnabled(true);
//                slider.setEnabled(true);
//                radioButtonSzyfrowanie.setVisible(true);
//                radioButtonDeszyfrowanie.setVisible(true);
//                slider.setVisible(true);
//                startButton.setVisible(true);

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
        czyFolderWybranyLabel.setText("Nie wybrano folderu.");

        //=====FRAME=====
        frame.setTitle("Szyfrowanie Wielowątkowe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(800, 600);

        //=====PROGRESS_BARS=====
        panel4.setLayout(new GridLayout(liczbaWatkow, 1));
        progressBars = new JProgressBar[liczbaWatkow];

        for (int i = 0; i < liczbaWatkow; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setValue(0);
            progressBars[i].setStringPainted(true);
            panel4.add(progressBars[i]);
        }

        frame.add(panel4, BorderLayout.CENTER);

        frame.setVisible(true);

        //=====SLIDERS=====
        slider = new JSlider(0, 20, 1);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                offset = slider.getValue();

            }
        });

        //=====ADDS=====

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.CENTER);

        panel1.add(titleLabel);

        panel3.add(radioButtonSzyfrowanie);
        panel3.add(radioButtonDeszyfrowanie);
        panel3.add(slider);
        panel3.add(dirButton);
        panel3.add(czyFolderWybranyLabel);
        panel3.add(startButton);

        group.add(radioButtonSzyfrowanie);
        group.add(radioButtonDeszyfrowanie);

        ImageIcon imageIcon = new ImageIcon("src/logo_java.png");
        frame.setIconImage(imageIcon.getImage());

        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();

    }

    public void wybierzFolder() {

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
            czyFolderWybranyLabel.setText("Wybrano folder.");
        }
    }
    public KolejkaObiektow pobierzDane () {

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

        //tworzenie listy obiektów plików
        System.out.println("===kolekcja obiektów plików=== / ILOŚĆ: " + paths.size());
        ArrayList<KodowanyPlik> lista_kodowanych_plikow = new ArrayList<>();
        for(int i = 0; i < paths.size(); i++){
            lista_kodowanych_plikow.add(new KodowanyPlik(paths.get(i), offset));
            System.out.println(lista_kodowanych_plikow.get(i) + " ścieżka: " + lista_kodowanych_plikow.get(i).getPath());
        }

        KolejkaObiektow kolejkaObiektow = new KolejkaObiektow(lista_kodowanych_plikow);
        kolejkaObiektow.wypisz();

        return kolejkaObiektow;

    }
    public void szyfrujDane(KolejkaObiektow kolejkaObiektow) {

//        KodowaniePlikow[] kodowaniePlikow = new KodowaniePlikow[liczbaWatkow];
        for(int i = 0; i < liczbaWatkow; i++){
            kodowaniePlikow[i] = new KodowaniePlikow(kolejkaObiektow, szyfrowanie,progressBars[i]);
            new Thread(kodowaniePlikow[i]).start();
        }

    }

    public boolean getCzyFolderWybrany () {
        return this.czyFolderWybrany;
    }

//    public int getLiczbaPlikow () {
//        return this.liczbaPlikow;
//    }

    public void setFrame (JPanel panel) {
        this.frame.add(panel, BorderLayout.CENTER);
    }

    public void repaint () {
        this.frame.repaint();
    }

    public void revalidate () {
        this.frame.revalidate();
    }

    public void setVisible () {
        this.frame.setVisible(true);
    }

    public void setPanel4 (JProgressBar guiProgressBars) {
        this.panel4.add(guiProgressBars);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI_ProgramGlowny program = new GUI_ProgramGlowny();
                program.frame.setVisible(true);
            }
        });

    }

}