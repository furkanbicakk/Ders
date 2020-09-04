package odev3;
import java.util.concurrent.TimeUnit ;

public class YaziciKuyrukDairesel{

    belge dizi[];
    int bas;
    int son;
    int N;

 
    public YaziciKuyrukDairesel(int N) {
        dizi = new belge[N];
        this.N = N;
        bas = 0;
        son = 0;
   
    }

    boolean kuyrukDolu() {
        return (bas == (son + 1) % N);

    }
    
    boolean kuyrukBos() {
        return (bas == son);
    }
    

   
    
    void kuyrugaEkle(belge yeni) {
        if (!kuyrukDolu()) {
            dizi[son] = yeni;
            son = (son + 1) % N;
        }else System.out.print("kuyruk dolu Ekleme yapamam!! Eklenemeyen Belge: ");

    }
    
    belge kuyrukSil() {
        belge sonuc;
        if (!kuyrukBos()) {
            sonuc = dizi[bas];
            bas = (bas + 1) % N;
            return sonuc;
        }if(kuyrukBos()) {System.out.println("Kuyruk boş Silme işlemi başarısız.");}
        return null;
    }
        
    void Cikart() throws InterruptedException {
         
             belge a = kuyrukSil();
             double sayfabasisaniye=0;
          if(testDosya.dakika*60>=testDosya.sayfa){
             sayfabasisaniye = (testDosya.dakika*60)/(testDosya.sayfa );}//her sayfa kaç saniyede yazdırılır
          if(testDosya.dakika*60<=testDosya.sayfa){sayfabasisaniye =(testDosya.sayfa )/(testDosya.dakika*60);}
            double hiz = a.BelgeSayfaSayisi / sayfabasisaniye;

            TimeUnit.SECONDS.sleep((long) hiz);
            System.out.print("Kaç saniyede Bi Kuyruktan çıkarttı: " + hiz + " sn ");
            System.out.println("kuyruktan Çıkarılan Belgenin: " + "Belge Adı: " + a.BelgeAd + " Belge Boyutu: " + a.BelgeBoyut + " BelgeSayfaSayisi: " + a.BelgeSayfaSayisi + " Ad: " + a.BelgeSahibi.Ad + " KullanıcıAdı: " + a.BelgeSahibi.KullaniciAdi + " Soyadı: " + a.BelgeSahibi.Soyad + " eposta: " + a.BelgeSahibi.eposta);
       

    }

   
}
