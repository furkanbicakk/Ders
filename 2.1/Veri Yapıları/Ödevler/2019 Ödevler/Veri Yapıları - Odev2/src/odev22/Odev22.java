package odev22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


    class Ders {
    int dkod,dsinif;
    String dad;
    Ders ileri2;
    

    Ders(int dkod, String dad, int dsinif){
        this.dkod = dkod;
        this.dad = dad;
        this.dsinif = dsinif;
        ileri2 = null;
        
    }
}

class Ogrenci {
    int no,sinif;
    String ad, soyad, dersadi;
    Ogrenci ileri;
    
    Ogrenci(int no, String ad, String soyad, int sinif){
        this.no = no;
        this.ad = ad;
        this.sinif = sinif;
        this.soyad = soyad;
        
        ileri = null;

    }

    Ogrenci(int no, String ad, String soyad, int sinif, String dersadi){
        this.no = no;
        this.ad = ad;
        this.sinif = sinif;
        this.soyad = soyad;
        this.dersadi = dersadi;
        ileri = null;

    }
}
    public class Odev22{
    Ogrenci bas;
    Ogrenci son;
    Ders bas2;
    Ders son2;
    
        public Odev22(){
            bas = null;
            son = null;
            bas2 = null;
            son2 = null;
        }
        
    void Yerlestir(Ogrenci yeni){
            if (bas == null){
                bas = yeni;
            }
            else {
                son.ileri = yeni;
            }
            son = yeni;
        }
    void Listele(){
            Ogrenci tmp = bas;
            
            while(tmp != null){
                if(tmp.dersadi == ""){
                tmp.dersadi = " Ders Almiyor";
            }
                System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.soyad + "\t" + tmp.sinif);
                tmp = tmp.ileri;
            }
        }
    void DersListele(){
            Ogrenci tmp = bas;
            
            while(tmp != null){
                if(tmp.dersadi == ""){
                tmp.dersadi = " Ders Almiyor";
            }
                System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.soyad + "\t" + tmp.sinif + "\t" + tmp.dersadi);
                tmp = tmp.ileri;
            }
        }
    void ListeAra(int deger){
            Ogrenci tmp;
            tmp = bas;
            while(tmp != null){
                if (tmp.no == deger){
                    System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.soyad + "\t" + tmp.dersadi);
                }
                tmp = tmp.ileri;
            }
            
        }
        void Yerlestir2(Ders yeni){
            if (bas2 == null){
                bas2 = yeni;
            }
            else {
                son2.ileri2 = yeni;
            }
            son2 = yeni;
        }
    void Listele2(){
            Ders tmp = bas2;
            while(tmp != null){
                System.out.println(tmp.dkod + "\t" + tmp.dad + "\t" + tmp.dsinif);
                tmp = tmp.ileri2;
            }
        }
    
    void Oku(File f1) throws FileNotFoundException{
        Scanner oku1 = new Scanner(f1);
        while(oku1.hasNext()){
            int no = oku1.nextInt();
            String ad = oku1.next();
            String soyad = oku1.next();
            int sinif = oku1.nextInt();
            Ogrenci o = new Ogrenci(no,ad,soyad,sinif);
            Yerlestir(o);
        }
    }
    
    void Oku2(File f1, File f2) throws FileNotFoundException{
        Scanner oku1 = new Scanner(f1);
        Scanner oku2 = new Scanner(f2);
        while(oku1.hasNext()){
            int no = oku1.nextInt();
            String ad = oku1.next();
            String soyad = oku1.next();
            int sinif = oku1.nextInt();
            String s = "";
            while(oku2.hasNext()){
                int dkod = oku2.nextInt();
                String dad = oku2.next();
                int dsinif = oku2.nextInt();
                if (sinif == dsinif){
                    s += " " + dad;
                }             
            }
                    oku2 = new Scanner(f2);
                    Ogrenci o = new Ogrenci(no,ad,soyad,sinif,s);
                    Yerlestir(o);                    
        }
        
        while(oku2.hasNext()){
                int dkod = oku2.nextInt();
                String dad = oku2.next();
                int dsinif = oku2.nextInt();
                Ders d = new Ders(dkod,dad,dsinif);
                Yerlestir2(d);
  

            }
    }

    
    public static void main(String[] args) throws FileNotFoundException {
        
        Odev22 x = new Odev22();
        Odev22 y = new Odev22();
        File f1 = new File("ogrenci.txt");
        File f2 = new File("ders.txt");
       // x.Oku(f1);
       
        y.Oku2(f1,f2);
        
       // y.Listele2();
        
        //x.Listele();
       // y.DersListele();
      
        y.ListeAra(1);
        
    }
    
}
