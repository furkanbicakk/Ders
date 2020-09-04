package test;

class dugum
{
    int no;
    
    public dugum(int no)
    {
        this.no = no;
    }
}

public class Heap
{
    dugum[] dizi;
    int elemanSayisi;
    
    public Heap(int diziBoyut)
    {
        dizi = new dugum[diziBoyut];
        elemanSayisi = 0;
    }
    
    public boolean bosMu()
    {
        return (elemanSayisi == 0);
    }
    
    public void ekle(int tamSayi)
    {
        dugum eklenen = new dugum(tamSayi);
        dizi[elemanSayisi] = eklenen;
        basamakCik(elemanSayisi);
        elemanSayisi++;
    }
    
    public dugum sil()
    {
        dugum silinen = dizi[0];
        
        dizi[0] = dizi[elemanSayisi-1];
        elemanSayisi--;
        basamakIn(0);
        
        return silinen;
    }
    
    private void basamakCik(int indis)
    {
        int ata = (indis - 1) / 2;
        
        while(ata >= 0 && dizi[ata].no > dizi[indis].no)
        {
            yerDegistir(ata, indis);
            indis = ata;
            ata = (indis - 1) / 2;
        }
    }
    
    private void basamakIn(int indis)
    {
        int solCocuk = ((2*indis)+1);
        int sagCocuk = ((2*indis)+2);
        
        while((solCocuk < elemanSayisi && dizi[indis].no > dizi[solCocuk].no) || (sagCocuk < elemanSayisi && dizi[indis].no > dizi[sagCocuk].no))
        {
            if(sagCocuk >= elemanSayisi || dizi[solCocuk].no < dizi[sagCocuk].no)
            {
                yerDegistir(indis, solCocuk);
                indis = solCocuk;
            }
            else
            {
                yerDegistir(indis, sagCocuk);
                indis = sagCocuk;
            }
            
            solCocuk = ((2*indis)+1);
            sagCocuk = ((2*indis)+2);
        }
    }
    
    private void yerDegistir(int indis ,int cocuk)
    {
        dugum tmp = dizi[cocuk];
        dizi[cocuk] = dizi[indis];
        dizi[indis] = tmp;
    }
    
    public boolean minHeapMi(int[] liste)
    {
        int i = 0;
        int solCocuk = (2*i) + 1;
        int sagCocuk = (2*i) + 2;
        
        while(i < liste.length)
        {
            if((solCocuk < liste.length) && liste[i] > liste[solCocuk])
            {
                return false;
            }
            if((sagCocuk < liste.length) && liste[i] > liste[sagCocuk])
            {
                return false;
            }
            
            i++;
            solCocuk = (2*i) + 1;
            sagCocuk = (2*i) + 2;
        }
        
        return true;
    }
    
    private int elemanBul(int[]... diziler)
    {
        int sayac = 0;
        
        for (int i = 0; i < diziler.length; i++)
        {
            for (int j = 0; j < diziler[i].length; j++)
            {
                sayac++;
            }
        }
        
        return sayac;
    }
    
    void siraliListeler(int[]... diziler)
    {
        Heap minHeap = new Heap(elemanBul(diziler));
        
        for (int i = 0; i < diziler.length; i++)
        {
            for (int j = 0; j < diziler[i].length; j++)
            {
                minHeap.ekle(diziler[i][j]);
            }
        }
        
        dugum[] dizi = new dugum[minHeap.elemanSayisi];
        dugum eleman;
        
        for (int i = 0; i < dizi.length; i++)
        {
            eleman = minHeap.sil();
            dizi[i] = eleman;
        }
    }
    
    private void max(int indis)
    {
        int solCocuk = ((2*indis)+1);
        int sagCocuk = ((2*indis)+2);
        
        while((solCocuk < elemanSayisi && dizi[indis].no < dizi[solCocuk].no) || (sagCocuk < elemanSayisi && dizi[indis].no < dizi[sagCocuk].no))
        {
            if(sagCocuk >= elemanSayisi || dizi[solCocuk].no > dizi[sagCocuk].no)
            {
                yerDegistir(indis, solCocuk);
                indis = solCocuk;
            }
            else
            {
                yerDegistir(indis, sagCocuk);
                indis = sagCocuk;
            }
            
            solCocuk = ((2*indis)+1);
            sagCocuk = ((2*indis)+2);
        }
    }
    
   
    
    public void maxHeapCevir()
    {
        for (int i = (elemanSayisi-2)/2; i >= 0; i--)
        {
            max(i);
        }
    }
    
    public void Listele(dugum[] liste)
    {
        for (int i = 0; i < elemanSayisi; i++)
        {
            System.out.print(liste[i].no + " ");
        }
        
        System.out.println();
    }
}
