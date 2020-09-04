package odev4;

public class Ogrenci {//numarası, ismi, vize ve final notu

    int no;
    String isim;
    int vizenotu;
    int finalnotu;
    Ogrenci sol, sag;

    Ogrenci(int no, String isim, int vizenotu, int finalnotu) {
        this.no = no;
        this.isim = isim;
        this.vizenotu = vizenotu;
        this.finalnotu = finalnotu;
        sol = null;
        sag = null;
    }
}
