
package odev3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dosyaİslem{ 
    
 public  static String BelgeAdi = "", Boyutu = "", SayfaSayisi = "", Ad = "", Soyad = "", KullaniciAdi = "", eposta = "";

  static  int Boyuti=0; static int SayfaSayi=0;
 static int ab;
 
  
     static int say=0;
  

    public static void main(String[] args) {
        
     
        File file = new File("d:\\belge.txt");
        try {

            file.createNewFile(); //dosya oluşturuldu
        } catch (IOException e) {

            e.printStackTrace();
        }
             
   
  
      
  /* System.out.print("Yazıcı Kuruguna Kac Saniyede Bir Belge Gönderilsin: ");

     Scanner hiz=new Scanner(System.in);  int al=hiz.nextInt(); ab=al;
     
  System.out.print("Yazıcıya gönderileren belge kaç saniye sonra kuruktan çıksın:");
 
  int tut=hiz.nextInt();
  System.out.println("Eklenenler Kuyruğu:");
 hiz.close();*/
  YaziciKuyrukDairesel ilk=new YaziciKuyrukDairesel(20); 
  
        try {
            Scanner oku = new Scanner(file);
        
        /* while (oku.hasNextLine()) {  say++;
                String str = oku.nextLine();}
oku.close();*/
    Timer myTimer=new Timer();
        TimerTask gorev=new TimerTask() {
            @Override
               public void run() { say++;
               if(say==2)myTimer.cancel();

                
                String str = oku.nextLine();
                String[] satir = str.split(" ");
                

                for (int i = 0; i < satir.length; i++) {
                   // System.out.println(satir[i]);
                    if (satir[i].equalsIgnoreCase("BelgeAdı:")) {
                        BelgeAdi = satir[++i];
                    }
                    if (satir[i].equalsIgnoreCase("Boyutu:")) {
                        String Boyut = satir[++i]; 
              
                        int j = 0; 
                        while (Boyut.charAt(j) != 'M' && Boyut.charAt(j) != 'K') {
                            
                            Boyutu += Boyut.charAt(j);
                            j++;
                        }
                 
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
                 int Boyu=Integer.valueOf(Boyutu);  int SayfaSay=Integer.valueOf(SayfaSayisi);

               Boyuti=Boyu;    SayfaSayi=SayfaSay;
        
                ilk.kuyrugaEkle(new belge(BelgeAdi, Boyuti,SayfaSayi, Ad, Soyad, KullaniciAdi, eposta));  
             System.out.println("Yazıcıya Gönderilen Belgenin: "+"Belge Adı: "+BelgeAdi+" Belge Boyutu: "+Boyuti+" BelgeSayfaSayisi: "+SayfaSayisi+" Ad: "+Ad+" KullanıcıAdı: "+KullaniciAdi+" Soyadı: "+Soyad+" eposta: "+eposta);

               }     
           };
        myTimer.schedule(gorev,0,1000);
 
          oku.close(); 
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } 
     
   
}}