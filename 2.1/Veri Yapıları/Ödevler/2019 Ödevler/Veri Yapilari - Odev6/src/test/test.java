package test;

public class test
{
    public static int[] quiz(Heap ilkDizi, Heap ikinciDizi)
    {
        int boyut = ilkDizi.elemanSayisi + ikinciDizi.elemanSayisi;
        
        Heap minHeap = new Heap(boyut);
        
        for (int i = 0; i < ilkDizi.elemanSayisi; i++)
        {
            minHeap.ekle(ilkDizi.dizi[i].no);
        }
        
        for (int i = 0; i < ikinciDizi.elemanSayisi; i++)
        {
            minHeap.ekle(ikinciDizi.dizi[i].no);
        }
        
        int[] sirali = new int[boyut];
        
        for (int i = 0; i < sirali.length; i++) {
            sirali[i] = minHeap.sil().no;
        }
        
        return sirali;
    }
    
    public static void kSil(Heap h, int k)
    {
        h.Listele(h.dizi);
        System.out.println("-----");
        for (int i = 0; i < k; i++)
        {
            h.sil();
        }
        h.Listele(h.dizi);
    }
    
    public static void main(String[] args)
    {
        Heap h1 = new Heap(5);
        Heap h2 = new Heap(5);
        
        h1.ekle(5);
        h1.ekle(10);
        h1.ekle(7);
        h1.ekle(12);
        h1.ekle(3);
        
        h2.ekle(8);
        h2.ekle(9);
        h2.ekle(0);
        h2.ekle(13);
        h2.ekle(4);
        
        kSil(h1, 3);
        
        int[] sira = quiz(h1, h2);
        
        for (int d : sira)
        {
            System.out.print(d + " ");
        }
        System.out.println("");
        
        /*Heap t = new Heap(5);
        t.ekle(7);
        t.ekle(1);
        t.ekle(5);
        t.ekle(2);
        t.ekle(6);
        
        t.Listele(t.dizi);
        t.maxHeapCevir();
        t.Listele(t.dizi);*/
    }
}
