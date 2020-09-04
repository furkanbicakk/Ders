package veri.yapilari.odev2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ogrenci
{
    int no;
    String ad;
    String soyad;
    int sinif;
    
    ogrenci ogr_ileri;
    
    ders ders_bas;
    ders ders_son;
    
    public ogrenci(int no, String ad, String soyad, int sinif)
    {
        this.no = no;
        this.ad = ad;
        this.soyad = soyad;
        this.sinif = sinif;
        
        ogr_ileri = null;
        ders_bas = null;
        ders_son = null;
    }
    
}

class ders
{
    
    String ders_kodu;
    String ad;
    int sinif;
    
    ders ileri;
    
    public ders(String ders_kodu, String ad, int sinif)
    {
        this.ders_kodu = ders_kodu;
        this.ad = ad;
        this.sinif = sinif;
        
        ileri = null;
    }
    
}

public class bagliListe
{  
    ogrenci bas;
    ogrenci son;
    
    public bagliListe()
    {
        bas = null;
        son = null;
    }
    
    public void ogrenciEkle(File ogrenciDosyasi) throws FileNotFoundException
    {
        Scanner scan = new Scanner(ogrenciDosyasi);
        
        while(scan.hasNext())
        {
            int no = scan.nextInt();
            String ad = scan.next();
            String soyad = scan.next();
            int sinif = scan.nextInt();
            
            listeyeEkle(new ogrenci(no,ad,soyad,sinif));
        }
    }
    
    private void listeyeEkle(ogrenci yeni)
    {
        if(bas == null)
        {
            bas = yeni;
            son = yeni;
        }
        else
        {
            son.ogr_ileri = yeni;
            son = yeni;
        }
    }
    
    public void ogrencileriGoster()
    {
        ogrenci tmp = bas;
        
        System.out.println("Ogrenci Listesi : ");
        
        while(tmp != null)
        {
            System.out.println(tmp.no + " " + tmp.ad + " " + tmp.soyad + " " + tmp.sinif);
            tmp = tmp.ogr_ileri;
        }
    }
    
    public void ogrenciDersEkle(File dersDosyasi) throws FileNotFoundException
    {
        ogrenci tmp = bas;
        Scanner scan;
        
        while(tmp != null)
        {
            scan = new Scanner(dersDosyasi);
            
            while(scan.hasNext())
            {
                String ders_kod = scan.next();
                String ders_adi = scan.next();
                int sinif = scan.nextInt();
                
                if(tmp.sinif == sinif)
                {
                    dersEkle(tmp ,new ders(ders_kod, ders_adi, sinif));
                }
            }
            
            scan.close();
            tmp = tmp.ogr_ileri;
        }
    }
    
    private void dersEkle(ogrenci ogr, ders yeniDers)
    {
        if(ogr.ders_bas == null)
        {
            ogr.ders_bas = yeniDers;
            ogr.ders_son = yeniDers;
        }
        else
        {
            ogr.ders_son.ileri = yeniDers;
            ogr.ders_son = yeniDers;
        }
    }
    
    public void ogrenciDers(ogrenci ogr)
    {
        ogrenci tmp = bas;
        
        while(tmp != null && tmp.no != ogr.no)
        {
            tmp = tmp.ogr_ileri;
        }
        
        if(tmp == null)
        {
            System.out.println("Ogrenci YOK!");
        }
        else
        {
            System.out.println(tmp.ad + " Dersleri : ");
            
            if(tmp.ders_bas == null)
            {
                System.out.println("Ogrencinin Dersi YOK!");
            }
            else
            {
                while(tmp.ders_bas != null)
                {
                    System.out.println(tmp.ders_bas.ders_kodu + " | " + tmp.ders_bas.ad);
                    tmp.ders_bas = tmp.ders_bas.ileri;
                }
            }
        }
    }
    
    public void tumOgrenciDersleri()
    {
        ogrenci tmp = bas;
        ders ders_tmp;
        
        while(tmp != null)
        {
            ders_tmp = tmp.ders_bas;
            System.out.println(tmp.ad + " " + tmp.soyad + " Dersleri : ");
            
            if(ders_tmp == null)
            {
                System.out.println("Ogrenci'nin dersi yok!");
            }
            else
            {
                while(ders_tmp != null)
                {
                    System.out.println(ders_tmp.ders_kodu + " | " + ders_tmp.ad);
                    ders_tmp = ders_tmp.ileri;
                }
            }
            
            System.out.println();
            tmp = tmp.ogr_ileri;
        }
    }
    
    public void enFazlaDersAlan()
    {
        int enYuksek = 0;
        int ders_sayisi = 0;
        
        ogrenci tmp = bas;
        ogrenci eyOgr = null;
        ders ders_tmp;
        
        while(tmp != null)
        {
            ders_tmp = tmp.ders_bas;
            
            while(ders_tmp != null)
            {
                ders_sayisi += 1;
                ders_tmp = ders_tmp.ileri;
            }
            
            System.out.println(tmp.ad + " ders sayisi : " + ders_sayisi);
            
            if(ders_sayisi > enYuksek)
            {
                eyOgr = tmp;
                enYuksek = ders_sayisi;
            }
            
            ders_sayisi = 0;
            tmp = tmp.ogr_ileri;
        }
        
        System.out.println("");
        System.out.println("En fazla ders alan ogrenci : " + eyOgr.ad + " " + eyOgr.soyad + " " + enYuksek);
    }
}
