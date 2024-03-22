package szyfrowanie_plikow;

public class KodowaniePlikow implements Runnable{

    private KolejkaObiektow kolejka;

    KodowaniePlikow(KolejkaObiektow kolejka){
        this.kolejka = kolejka;
    }

    @Override
    public void run() {

        KodowanyPlik kodowanyPlik;

        while((kodowanyPlik = kolejka.getNextObject()) != null){
            kodowanyPlik.szyfrowanie();
        }

    }

}
