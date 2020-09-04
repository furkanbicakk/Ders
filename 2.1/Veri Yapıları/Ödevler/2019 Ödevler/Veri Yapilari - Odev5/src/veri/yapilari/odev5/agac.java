package veri.yapilari.odev5;

import java.util.ArrayList;

class il
{
    int plaka;
    String ilAdi;
    double latitude;
    double longitude;
    
    il sol;
    il sag;
    
    public il(int plaka ,String ilAdi ,double latitude ,double longitude)
    {
        this.plaka = plaka;
        this.ilAdi = ilAdi;
        this.latitude = latitude;
        this.longitude = longitude;
        
        sol = null;
        sag = null;
    }
}

public class agac
{
    
    il kok;
    
    ArrayList<Integer> sayi;
    
    public agac()
    {
        kok = null;
        sayi = new ArrayList<Integer>();
    }
    
    public void ekle(il yeni)
    {
        if(kok == null)
        {
            kok = yeni;
        }
        else
        {
            il tmp = kok;
            il once = null;
            
            while(tmp != null)
            {
                once = tmp;
                
                if(yeni.plaka > tmp.plaka)
                {
                    tmp = tmp.sag;
                }
                else
                {
                    tmp = tmp.sol;
                }
            }
            
            if(yeni.plaka > once.plaka)
            {
                once.sag = yeni;
            }
            else
            {
                once.sol = yeni;
            }
        }
    }
    
    public void sil(int plaka)
    {
        il tmp = kok;
        il once = null;
        
        while(tmp.plaka != plaka)
        {
            once = tmp;
            
            if(plaka > tmp.plaka)
            {
                tmp = tmp.sag;
            }
            else if(plaka < tmp.plaka)
            {
                tmp = tmp.sol;   
            }
        }
        
        if(tmp != null)
        {
            if(tmp.sol == null && tmp.sag == null)
            {
                if(tmp.plaka < once.plaka)
                {
                    once.sol = null;
                }
                else
                {
                    once.sag = null;
                }
            }
            else if(tmp.sol == null && tmp.sag != null)
            {
                if(tmp.plaka < once.plaka)
                {
                    once.sol = tmp.sag;
                }
                else
                {
                    once.sag = tmp.sag;
                }
            }
            else if(tmp.sol != null && tmp.sag == null)
            {
                if(tmp.plaka < once.plaka)
                {
                    once.sol = tmp.sol;
                }
                else
                {
                    once.sag = tmp.sol;
                }
            }
            else
            {
                il tmp2 = tmp.sag;
                il sagEnKucuk = null;
                
                while(tmp2 != null)
                {
                    sagEnKucuk = tmp2;
                    tmp2 = tmp2.sol;
                }
                
                il yedek = sagEnKucuk;
                sil(sagEnKucuk.plaka);
                yedek.sol = tmp.sol;
                yedek.sag = tmp.sag;
                
                if(yedek.plaka > once.plaka)
                {
                    once.sag = yedek;
                }
                else
                {
                    once.sol = yedek;
                }
            }
        }
    }
    
    public void guncelle(il eskiBilgiler, il yeniBilgiler)
    {
        il tmp = kok;
        
        while(tmp.plaka != eskiBilgiler.plaka)
        {
            if(eskiBilgiler.plaka > tmp.plaka)
            {
                tmp = tmp.sag;
            }
            else
            {
                tmp = tmp.sol;
            }
        }
        
        if(yeniBilgiler.plaka > tmp.sag.plaka || yeniBilgiler.plaka < tmp.sol.plaka)
        {
            sil(tmp.plaka);
            ekle(yeniBilgiler);
        }
        if(tmp.plaka == yeniBilgiler.plaka)
        {
            tmp.ilAdi = yeniBilgiler.ilAdi;
            tmp.longitude = yeniBilgiler.longitude;
            tmp.latitude = yeniBilgiler.latitude;
        }
    }
    
    public int yukseklik(il dugum)
    {
        if(dugum == null)
        {
            return 0;
        }
        
        int sol = yukseklik(dugum.sol);
        int sag = yukseklik(dugum.sag);
        
        if(sol > sag)
        {
            return sol + 1;
        }
        else
        {
            return sag + 1;
        }
    }
    
    public il bul(int plaka)
    {
        il sonuc = ara(kok, plaka);
        
        if(sonuc == null)
        {
            return null;
        }
        else
        {
            return sonuc;
        }
    }
    
    private il ara(il dugum, int plaka)
    {
        if(dugum == null)
        {
            return null;
        }
        else if(dugum.plaka == plaka)
        {
            return dugum;
        }
        else if(plaka > dugum.plaka)
        {
            return ara(dugum.sag, plaka);
        }
        else
        {
            return ara(dugum.sol, plaka);
        }
    }
    
    public void ilBilgileri(il ilBilgi)
    {
        System.out.println(ilBilgi.ilAdi + " " + ilBilgi.plaka + " " + ilBilgi.latitude + " " + ilBilgi.longitude);
    }
    
    public void preorder()
    {
        preorderDolas(kok);
    }
    
    public void inorder()
    {
        inorderDolas(kok);
    }
    
    public void postorder()
    {
        postorderDolas(kok);
    }
    
    private void preorderDolas(il dugum)
    {
        if(dugum == null)
        {
            return;
        }
        
        System.out.print(dugum.plaka + " ");
        preorderDolas(dugum.sol);
        preorderDolas(dugum.sag);
    }
    
    private void inorderDolas(il dugum)
    {
        if(dugum == null)
        {
            return;
        }
        
        inorderDolas(dugum.sol);
        System.out.print(dugum.plaka + " ");
        inorderDolas(dugum.sag);
    }
    
    private void postorderDolas(il dugum)
    {
        if(dugum == null)
        {
            return;
        }
        
        postorderDolas(dugum.sol);
        postorderDolas(dugum.sag);
        System.out.print(dugum.plaka + " ");
    }
    
    public void levelorderDolas()
    {
        ArrayList<il> iller = new ArrayList<il>();
        
        iller.add(kok);
        
        while(!iller.isEmpty())
        {
            il eleman = iller.remove(0);
            System.out.print(eleman.plaka + " ");
            
            if(eleman.sol != null)
            {
                iller.add(eleman.sol);
            }
            if(eleman.sag != null)
            {
                iller.add(eleman.sag);
            }
        }
    }
    
    public int levelorderTopla()
    {
        ArrayList<il> iller = new ArrayList<il>();
        int toplam = 0;
        iller.add(kok);
        
        while(!iller.isEmpty())
        {
            il eleman = iller.remove(0);
            toplam += eleman.plaka;
            
            if(eleman.sol != null)
            {
                iller.add(eleman.sol);
            }
            if(eleman.sag != null)
            {
                iller.add(eleman.sag);
            }
        }
        
        return toplam;
    }
    
    
    public boolean dengeliAgac()
    {
        return agacDengeliMi(kok);
    }
    
    private boolean agacDengeliMi(il dugum)
    {
        if(dugum == null)
        {
            return true;
        }
        
        int fark = yukseklik(dugum.sol) - yukseklik(dugum.sag);
        
        if(fark < -1 || fark > 1)
        {
            return false;
        }
        else
        {
            return agacDengeliMi(dugum.sol) && agacDengeliMi(dugum.sag);
        }
    }
    
    public boolean ikiliAgac()
    {
        ArrayList<il> iller = new ArrayList<il>();
        
        iller.add(kok);
        
        while(!iller.isEmpty())
        {
            il eleman = iller.remove(0);
            
            if(eleman.sol != null)
            {
                if(eleman.plaka <= eleman.sol.plaka)
                {
                    return false;
                }
                
                iller.add(eleman.sol);
            }
            if(eleman.sag != null)
            {
                if(eleman.plaka >= eleman.sag.plaka)
                {
                    return false;
                }
                
                iller.add(eleman.sag);
            }
        }
        
        return true;
    }
    
    public boolean tamIkiliAgac()
    {
        ArrayList<il> iller = new ArrayList<il>();
        iller.add(kok);
        
        while(!iller.isEmpty())
        {
            il eleman = iller.remove(0);
            
            if(eleman.sol != null && eleman.sag == null)
            {
                return false;
            }
            else if(eleman.sag != null && eleman.sol == null)
            {
                return false;
            }
            
            if(eleman.sol != null)
            {
                iller.add(eleman.sol);
            }
            if(eleman.sag != null)
            {
                iller.add(eleman.sag);
            }
        }
        
        return true;
    }
    
    void sirala(il dugum)
    {
        if(dugum == null)
        {
            return;
        }
        
        sirala(dugum.sol);
        sayi.add(dugum.plaka);
        sirala(dugum.sag);
    }
    
    
    void kucukBul(int aranan_indis)
    {
        
        if(aranan_indis > 0 && aranan_indis < 15)
        {
            sirala(kok);
            int bulunan = sayi.get(aranan_indis - 1);
            System.out.println(aranan_indis+ ". kucuk sayi : " + bulunan);
        }
    }
    
}