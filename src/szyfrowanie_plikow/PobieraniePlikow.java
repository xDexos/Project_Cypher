package szyfrowanie_plikow;

import java.io.File;
import java.util.ArrayList;

public class PobieraniePlikow implements Runnable{

    private String path;
    private ArrayList<String> paths = new ArrayList<>();

    public PobieraniePlikow(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        System.out.println("Start wątku pobierania plików");
        File dir = new File(path); //ścieżka do folderu z plikami do zaszyfrowania
        File[] files = dir.listFiles(); //pobieranie wszystkich plików z folderu
        for(int i = 0; i < files.length; i++){
            paths.add(i, files[i].toString());
        }
        System.out.println("Koniec wątku pobierania plików");
    }

    public ArrayList<String> getPaths(){
        return this.paths;
    }
}