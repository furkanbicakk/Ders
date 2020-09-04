package odev3;

class Kisi {

    String Ad;
    String Soyad;
    String KullaniciAdi;
    String eposta;

    Kisi(String Ad, String Soyad, String KullaniciAdi, String eposta) {
        this.Ad = Ad;
        this.Soyad = Soyad;
        this.KullaniciAdi = KullaniciAdi;
        this.eposta = eposta;
        
    }

}

public class belge {

    String BelgeAd;
    String BelgeBoyut;
    int BelgeSayfaSayisi;
    Kisi BelgeSahibi;

    public belge(String BelgeAd, String BelgeBoyut, int BelgeSayfaSayisi, String Ad, String Soyad, String KullaniciAdi, String eposta) {
        this.BelgeAd = BelgeAd;
        this.BelgeBoyut = BelgeBoyut;
        this.BelgeSayfaSayisi = BelgeSayfaSayisi;

        Kisi yeni = new Kisi(Ad, Soyad, KullaniciAdi, eposta);
        BelgeSahibi = yeni;

    }

    
}
