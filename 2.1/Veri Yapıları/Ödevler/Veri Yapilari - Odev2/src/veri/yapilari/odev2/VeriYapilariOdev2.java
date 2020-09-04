package veri.yapilari.odev2;

import java.io.File;
import java.io.FileNotFoundException;


public class VeriYapilariOdev2
{
    public static void main(String[] args) throws FileNotFoundException
    {
        bagliListe list = new bagliListe();
        
        File ogrenciDosya = new File("ogrenci.txt");
        File dersDosya = new File("ders.txt");
        
        list.ogrenciEkle(ogrenciDosya);
        list.ogrencileriGoster();
        System.out.println();
        
        list.ogrenciDersEkle(dersDosya);
       // list.tumOgrenciDersleri();
        
        list.enFazlaDersAlan();
        
        //list.ogrenciDers(new ogrenci(123, "Ahmet", "Şener", 4));
        
    }
}
