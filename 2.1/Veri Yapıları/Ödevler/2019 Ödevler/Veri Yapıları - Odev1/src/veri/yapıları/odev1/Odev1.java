package veri.yapıları.odev1;

public class Odev1
{
    public static void main(String[] args)
    {
        bListe list = new bListe();
        
        ogrenci ali = new ogrenci(17, "Ali", 30 ,60);
        ogrenci veli = new ogrenci(25, "Veli", 20 ,50);
        ogrenci ayse = new ogrenci(23, "Ayse", 35 ,63);
        ogrenci mehmet = new ogrenci(12, "Mehmet", 75, 80);
       
        
        System.out.println("------------------EKLEME------------------");
        
        list.basaEkle(mehmet);
        list.basaEkle(ali);
        list.sonaEkle(ayse);
        list.arayaEkle(veli, ayse);
        
        System.out.println("LİSTE : ");
        list.listeGoster();
        
        System.out.println("********************************************");
        list.enYuksekVize();
        
        
       /* System.out.println("------------------SİLME------------------");
        
        list.bastanSil();
        list.sondanSil();
        list.elemanSil(veli);
        
        list.listeGoster();*/
        
        
        list.ogrenciDurum();
        
        list.enYuksekOrtalama();
        
        
        
    }
}