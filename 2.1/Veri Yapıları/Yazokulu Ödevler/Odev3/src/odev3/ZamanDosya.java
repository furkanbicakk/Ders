/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author fettah polat
 */
public class ZamanDosya {
     static int say=0,say1=0,h,t;
      public  static String BelgeAdi = "", Boyutu = "", SayfaSayisi = "", Ad = "", Soyad = "", KullaniciAdi = "", eposta = "";

  static  int Boyuti=0; static int SayfaSayi=0;
  

    public static void main(String[] args) throws FileNotFoundException {
          YaziciKuyrukDairesel ilk=new YaziciKuyrukDairesel(20); 
           File file = new File("d:\\belge.txt");
        try {

            file.createNewFile(); //dosya oluşturuldu
        } catch (IOException e) {

            e.printStackTrace();
        }
           Scanner oku1 = new Scanner(file);
        while(oku1.hasNext()){String df = oku1.nextLine();say1++;} oku1.close();
        Scanner hu=new Scanner(System.in); System.out.print("Kuyruğa ekleme hızı: ");
         h=hu.nextInt(); 
         System.out.print("Kuyruktan çıkarma hızı: "); t=hu.nextInt();
         hu.close();
       Scanner oku = new Scanner(file);
        System.out.println("Kuyruğa Eklenenler:");
         Timer myTimer=new Timer();
        TimerTask gorev=new TimerTask() {
             @Override
             
             public void run() {
        if(say==say1){ 
           
           // h=t; ilk.Cikart();}
       
       
       if(say!=say1){ say++;
                  String str = oku.nextLine();//System.out.println(str);
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
             System.out.println("Kuyruğa Gönderilen Belgenin: "+"Belge Adı: "+BelgeAdi+" Belge Boyutu: "+Boyuti+" BelgeSayfaSayisi: "+SayfaSayisi+" Ad: "+Ad+" KullanıcıAdı: "+KullaniciAdi+" Soyadı: "+Soyad+" eposta: "+eposta);
       } if(ilk.kuyrukBos()){myTimer.cancel();}
             } 
         };  
        myTimer.schedule(gorev,0,1000*h);
    
    /*    TimerTask gorev2=new TimerTask() {
              @Override
              public void run() {
                ilk.Cikart();
              }
          };  myTimer.schedule(gorev2,0,1000*t);*/

}
}}