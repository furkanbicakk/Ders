package odev3;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit ;

public class testDosya {
public static String BelgeAdi = "", Boyut = "", SayfaSayisi = "", Ad = "", Soyad = "", KullaniciAdi = "", eposta = "";
public static int dakika,sayfa;

    public static void main(String[] args) throws InterruptedException {
        
        
        int as;
    try (Scanner par = new Scanner(System.in)) {
        System.out.print("kuyruğa Ekleme hızı: ");
        as = par.nextInt();
        System.out.println("Yazıcı kaç dakikada kaç sayfa çıktı verir?: Dakika ve Sayfa Sayısı Girişi yapınız  ");
        System.out.print("Dakika?: ");
        dakika=par.nextInt();  
        System.out.print("SayfaSayisi?: ");
        sayfa=par.nextInt();
    }
        
        File file = new File("d:\\belge.txt");
     
        YaziciKuyrukDairesel yazici1 = new YaziciKuyrukDairesel(20);
        try {
            try (Scanner oku = new Scanner(file)) {
                while (oku.hasNextLine()) {
                    TimeUnit.SECONDS.sleep((long)as);//bekletme nesnesi
                    String str = oku.nextLine();
                    String[] satir = str.split(" ");
                    
                    for (int i = 0; i < satir.length; i++) {
                     
                        if (satir[i].equalsIgnoreCase("BelgeAdı:")) {
                            BelgeAdi = satir[++i];
                        }
                        if (satir[i].equalsIgnoreCase("Boyutu:")) {
                           Boyut= satir[++i];
                         
                        }
                        
                        if (satir[i].equalsIgnoreCase("SayfaSayisi:")) {
                            SayfaSayisi = satir[++i];
                        }
                        if (satir[i].equalsIgnoreCase("Ad:")) {
                            Ad = satir[++i];
                        }
                        if (satir[i].equalsIgnoreCase("Soyad:")) {
                            Soyad = satir[++i];
                        }
                        if (satir[i].equalsIgnoreCase("KullanıcıAdı:")) {
                            KullaniciAdi = satir[++i];
                        }
                        if (satir[i].equalsIgnoreCase("eposta:")) {
                            eposta = satir[++i];
                        }
                    }
                    
                    int SayfaSay = Integer.parseInt(SayfaSayisi);
                    yazici1.kuyrugaEkle(new belge(BelgeAdi, Boyut, SayfaSay, Ad, Soyad, KullaniciAdi, eposta));
                    System.out.println("Kuyruğa Gönderilen Belgenin: " + "Belge Adı: " + BelgeAdi + " Belge Boyutu: " + Boyut + " BelgeSayfaSayisi: " + SayfaSay + " Ad: " + Ad + " KullanıcıAdı: " + KullaniciAdi + " Soyadı: " + Soyad + " eposta: " + eposta);
                    
                } oku.close();
            }

        } catch (FileNotFoundException e) {

          
        } 
        System.out.println("Kuyruktan çıkarma: ");
        while (!yazici1.kuyrukBos()) {
            yazici1.Cikart();
        }

    }
}
        
        
        
    













