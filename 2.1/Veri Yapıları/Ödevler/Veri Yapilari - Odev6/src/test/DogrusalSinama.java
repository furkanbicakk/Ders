package test;

public class DogrusalSinama
{
    eleman[] sayiDizisi;
    int MOD;
    
    public DogrusalSinama(int MOD)
    {
        this.MOD = MOD;
        sayiDizisi = new eleman[MOD];
    }
    
    public void ekle(int tamSayi)
    {
        eleman eklenen = new eleman(tamSayi); 
        int ilkKonum = tamSayi%MOD;
        
        for (int i = ilkKonum; i < MOD+ilkKonum; i++)
        {
            int konum = i%MOD;
            
            if(sayiDizisi[konum] == null)
            {
                sayiDizisi[konum] = eklenen;
                break;
            }
        }
    }
    
    public boolean ara(int tamSayi)
    {
        int baslangic = tamSayi%MOD;
        boolean kontrol = false;
        
        for (int i = baslangic; i < MOD+baslangic; i++)
        {
            int konum = i%MOD;
            
            if(sayiDizisi[konum].icerik == tamSayi)
            {
                kontrol = true;
                break;
            }
        }
        
        if(kontrol)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
