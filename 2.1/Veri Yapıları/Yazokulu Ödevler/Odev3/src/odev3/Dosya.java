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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dosya extends Thread{
 public  String BelgeAdi = "", Boyutu = "", SayfaSayisi = "", Ad = "", Soyad = "", KullaniciAdi = "", eposta = "";

  
 static int ab;
 
  
     
    public static void main(String[] args) {
       
         Dosya a=new Dosya();
         a.start();
    }
   
    //public static void main(String[] args) {

    
    YaziciKuyrukDairesel ilk=new YaziciKuyrukDairesel(20);
 public void run(){
        File file = new File("d:\\belge.txt");
        try {

            file.createNewFile(); //dosya oluşturuldu
        } catch (IOException e) {

            e.printStackTrace();
        }
             
   
  
      
   System.out.print("Yazıcı Kuruguna Kac Saniyede Bir Belge Gönderilsin: ");

     Scanner hiz=new Scanner(System.in);  int al=hiz.nextInt(); ab=al;
     
    
     
   System.out.print("Yazıcıya gönderileren belge kaç saniye sonra kuruktan çıksın:");
 
  int tut=hiz.nextInt();
  System.out.println("Eklenenler Kuyruğu:");
 hiz.close();
        try {
            Scanner oku = new Scanner(file);
      
    boolean h=false; int sayac=0;
            while (h=oku.hasNextLine()) { sayac++; char t = 0,y=0;
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
                        t=Boyut.charAt(j); y=Boyut.charAt(++j);
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
                 int Boyut=Integer.valueOf(Boyutu);  int SayfaSayi=Integer.valueOf(SayfaSayisi);
                 Thread.sleep(1000*ab); 
                ilk.kuyrugaEkle(new belge(BelgeAdi, Boyut,SayfaSayi, Ad, Soyad, KullaniciAdi, eposta));
                System.out.println(t+"Yazıcıya Gönderilen Belgenin: "+"Belge Adı: "+BelgeAdi+" Belge Boyutu: "+Boyut+" "+t+""+y+" BelgeSayfaSayisi: "+SayfaSayisi+" Ad: "+Ad+" KullanıcıAdı: "+KullaniciAdi+" Soyadı: "+Soyad+" eposta: "+eposta);
           
              
            }
            oku.close();     
    
            if(h==false){
        
                System.out.println("Kuyruktan çıkartılıp yazıcıya Gönderilen Belgeler;");
                for(int i=0;i<sayac;i++){
                Thread.sleep(1000*tut);
                ilk.Cikart();}
           }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (InterruptedException ex) {
         Logger.getLogger(Dosya.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    } 
   
 
   

/*
         public void run(){  
  try {
        
 ilk.kuyrugaEkle(new belge(BelgeAdi, Boyut,SayfaSayi, Ad, Soyad, KullaniciAdi, eposta));
   
     Thread.sleep(1000*ab); 
     
  
     
        } catch (InterruptedException ex) {
            Logger.getLogger(YaziciKuyrukDairesel.class.getName()).log(Level.SEVERE, null, ex);
        }
  
 }
    */
    
}


