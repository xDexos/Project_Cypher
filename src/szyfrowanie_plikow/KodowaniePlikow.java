package szyfrowanie_plikow;

public class KodowaniePlikow implements Runnable{

    private KolejkaObiektow kolejka;

    private boolean deszyfracja;

    KodowaniePlikow(KolejkaObiektow kolejka, boolean deszyfracja){
        this.kolejka = kolejka;
        this.deszyfracja = deszyfracja;
    }

    @Override
    public void run() {

        KodowanyPlik kodowanyPlik;

        while((kodowanyPlik = kolejka.getNextObject()) != null){

            if (this.deszyfracja == false){
                kodowanyPlik.szyfrowanie();
            }else{
                kodowanyPlik.deszyfrowanie();
            }


        }

    }

}
