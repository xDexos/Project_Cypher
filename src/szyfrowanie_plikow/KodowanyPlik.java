package szyfrowanie_plikow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KodowanyPlik{

    private String path;
    private boolean czy_wolny;
    private boolean czy_zaszyfrowany;

    public KodowanyPlik(String path){
        this.path = path;
        this.czy_wolny = true;
        this.czy_zaszyfrowany = false;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCzy_wolny() {
        return czy_wolny;
    }

    public void setCzy_wolny(boolean czy_wolny) {
        this.czy_wolny = czy_wolny;
    }

    public boolean isCzy_zaszyfrowany() {
        return czy_zaszyfrowany;
    }

    public void setCzy_zaszyfrowany(boolean czy_zaszyfrowany) {
        this.czy_zaszyfrowany = czy_zaszyfrowany;
    }

}
