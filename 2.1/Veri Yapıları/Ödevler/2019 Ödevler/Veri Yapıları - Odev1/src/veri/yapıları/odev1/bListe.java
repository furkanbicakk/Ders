package veri.yapıları.odev1;

class ogrenci
{
    String ad;
    int no;
    int vize;
    int genel;
    
    ogrenci ileri;
    
    public ogrenci(int no, String ad, int vize, int genel)
    {
        this.ad = ad;
        this.no = no;
        this.vize = vize;
        this.genel = genel;
        
        ileri = null;
    }
}

public class bListe{
    ogrenci bas;
    ogrenci son;
    
    bListe gecen, kalan;
    
    public bListe()
    {
        bas = null;
        son = null;
    }
    
    // Ekleme
    public void basaEkle(ogrenci yeni)
    {
        if(bas == null)
        {
            bas = yeni;
            son = yeni;
        }
        else
        {
            yeni.ileri = bas;
            bas = yeni;
        }
    }
    
    public void sonaEkle(ogrenci yeni)
    {
        if(son == null)
        {
            bas = yeni;
            son = yeni;
        }
        else
        {
            son.ileri = yeni;
            son = yeni;
        }
    }
    
    public void arayaEkle(ogrenci yeni, ogrenci sonra)
    {
        ogrenci tmp = bas;
        ogrenci once = null;
        
        while(tmp != sonra)
        {
            once = tmp;
            tmp = tmp.ileri;
        }
        
        if(once == null)
        {
            System.out.println("Eleman bulunamadı!");
        }
        else
        {
            yeni.ileri = sonra;
            once.ileri = yeni;
        }
    }
    
    // Silme
    public void bastanSil()
    {
        if(bas == null)
        {
            System.out.println("Listede eleman yok!");
        }
        else
        {
          bas = bas.ileri;
        }
    }
    
    public void sondanSil()
    {
        ogrenci tmp = bas;
        ogrenci once = null;
        
        while(tmp != son)
        {
            once = tmp;
            tmp = tmp.ileri;
        }
        
        if(once == null)
        {
            bas = null;
            son = null;
        }
        else
        {
            once.ileri = null;
            son = once;
        }
    }
    
    public void elemanSil(ogrenci sil)
    {
        ogrenci tmp = bas;
        ogrenci once = null;
        
        while(tmp != sil)
        {
            once = tmp;
            tmp = tmp.ileri;
        }
        
        if(once == null)
        {
            bas = bas.ileri;
        }
        else
        {
            once.ileri = tmp.ileri;
        }
    }
    
    // Arama
    public void listeGoster()
    {
       ogrenci tmp = bas;
       while(tmp != null)
       {
           System.out.println(tmp.no + " " + tmp.ad + " " + tmp.vize + " " + tmp.genel);
           tmp = tmp.ileri;
       }
    }
    
    public void enYuksekVize()
    {
        ogrenci tmp = bas;
        ogrenci hafiza = null;
        int enYüksek = 0;
        
        while(tmp != null)
        {
            if(tmp.vize > enYüksek)
            {
                enYüksek = tmp.vize;
                hafiza = tmp;
            }
            tmp = tmp.ileri;
        }
        
        System.out.println("En Yüksek Vize Notu : ");
        System.out.println(hafiza.no + " " + hafiza.ad + " " + hafiza.vize + " " + hafiza.genel);
    }
    
    public void ogrenciDurum(){
        gecen = new bListe();
        kalan = new bListe();
        
        ogrenci tmp = bas;
        double ortalama;
        
        while(tmp != null)
        {
            ortalama = ((tmp.vize * 0.4) + (tmp.genel * 0.6));
            
            if(ortalama >= 50 && tmp.genel >= 50)
            {
                gecen.sonaEkle(new ogrenci(tmp.no, tmp.ad, tmp.vize, tmp.genel));
            }
            else
            {
                kalan.sonaEkle(new ogrenci(tmp.no, tmp.ad, tmp.vize, tmp.genel)); 
            }
            
            tmp = tmp.ileri;
        }
        
        System.out.println("------------------ GEÇENLER -------------------");
        gecen.listeGoster();
        
        System.out.println("------------------ KALANLAR -------------------");
        kalan.listeGoster();
    }
    
    public void enYuksekOrtalama()
    {
        ogrenci tmp = bas;
        ogrenci ogr = null;
        double enYuksek = 0;
        double ortalama;
        
        while(tmp != null)
        {
            ortalama = ((tmp.vize * 0.4) + (tmp.genel * 0.6));
            
            if(ortalama > enYuksek)
            {
                enYuksek = ortalama;
                ogr = tmp;
            }
            
            tmp = tmp.ileri;
        }
        
        System.out.println("En Yüksek Ortalama : " + enYuksek);
        System.out.println("En Yüksek Ortalamaya Sahip Öğrenci : " + ogr.no + " " + ogr.ad);
    }
   
}