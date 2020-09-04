package test;

class eleman
{
    int icerik;
    
    eleman ileri;
    public eleman(int icerik)
    {
        this.icerik = icerik;
        ileri = null;
    }
}
class BagliListe
{
    eleman bas;
    eleman son;
    
    public BagliListe()
    {
        bas = null;
        son = null;
    }
    
    public void BasaEkle(int tamSayi)
    {
        eleman eklenen = new eleman(tamSayi);
        
        if(son == null)
        {
            bas = eklenen;
            son = eklenen;
        }
        else
        {
            eklenen.ileri = bas;
            bas = eklenen;
        }
    }
    
    public eleman ara(int tamSayi)
    {
        eleman tmp = bas;
        
        while(tmp != null)
        {
            if(tmp.icerik == tamSayi)
            {
                break;
            }
            
            tmp = tmp.ileri;
        }
        
        return tmp;
    }
    
    public void bastanSil()
    {
        if(bas != null)
        {
            bas = bas.ileri;
        }
    }
    
    public void listele()
    {
        eleman tmp = bas;
        
        while(tmp != null)
        {
            System.out.print(tmp.icerik + " ");
            tmp = tmp.ileri;
        }
    }
}
public class ZincirHash
{
    BagliListe[] sayiDizisi;
    int MOD;
    
    public ZincirHash(int MOD)
    {
        this.MOD = MOD;
        sayiDizisi = new BagliListe[MOD];
        
        for (int i = 0; i < MOD; i++)
        {
            sayiDizisi[i] = new BagliListe();
        }
    }
    
    public void ekle(int tamSayi)
    {
        int sayiMod = tamSayi%MOD;
        sayiDizisi[sayiMod].BasaEkle(tamSayi);
    }
    
    public void ekle(int[] tamSayi)
    {
        for (int i = 0; i < MOD; i++)
        {
            int sayiMod = tamSayi[i]%MOD;
            sayiDizisi[sayiMod].BasaEkle(tamSayi[i]);
        }
    }
    
    public boolean ara(int tamSayi)
    {
        int sayiMod = tamSayi%MOD;
        eleman aranan = sayiDizisi[sayiMod].ara(tamSayi);
        
        if(aranan == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public int indisDon(int indis)
    {
        return indis%MOD;
    }
    
    public void listele()
    {
        for (int i = 0; i < sayiDizisi.length; i++)
        {
            System.out.print(i + ". liste : ");
            sayiDizisi[i].listele();
            System.out.println("");
        }
    }
}
