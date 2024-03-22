package szyfrowanie_plikow;

import java.util.ArrayList;

public class KolejkaObiektow {

    private ArrayList<KodowanyPlik> kolejka;
    private int currentIndex = 0;

    public KolejkaObiektow(ArrayList<KodowanyPlik> lista_obiektow){
        this.kolejka = new ArrayList<>(lista_obiektow);
    }

    synchronized KodowanyPlik getNextObject(){
        if(currentIndex < kolejka.size()){
            return kolejka.get(currentIndex++);
        }else{
            return null;
        }
    }

    public void wypisz() {
        for (int i = 0; i < kolejka.size(); i++) {
            System.out.println("Element: " + kolejka.get(i));
        }
    }

    public int getSize(){
        return this.kolejka.size();
    }

    public ArrayList<KodowanyPlik> getKolejka() {
        return kolejka;
    }

    public void setKolejka(ArrayList<KodowanyPlik> lista_obiektow) {
        this.kolejka = lista_obiektow;
    }
}
