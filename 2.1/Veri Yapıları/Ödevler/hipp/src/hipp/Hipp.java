
package hipp;

public class Hipp {

   
    public static void main(String[] args) {
        
       Heap a=new Heap(11);
       Heap b=new Heap(11);
       
        a.Ekle(15);
        a.Ekle(18);
        a.Ekle(5);
        a.Ekle(6);
        a.Ekle(2);
        a.Ekle(3);
        a.Ekle(7);
        a.Ekle(8); 
        a.Ekle(9);
        a.Ekle(4);
        a.Ekle(11);
       
        a.goster();
        System.out.println("***");
       // a.son_sil();
      //  a.goster();
       // System.out.println("****");
        
       a.bassil(3);
       a.goster();
  
  
    
     //a.maksa_cevir(0);
      //a.goster();
    //a.min_heapmi(a.dizi);
     
     
     
  /*   
     //C ŞIKKI
     liste[] l = new liste[3];
     l[0] = new liste();
     l[0].ekle(3);
     l[0].ekle(2);
     l[0].ekle(4);
     
     l[1] = new liste();
     l[1].ekle(6);
     l[1].ekle(5);
     l[0].goster();
     
    liste l1 = new liste();
    l1.birlestir(l);

    l1.goster();
    System.out.println("????????" + l1.uzunluk);
    Heap hh = new Heap(l1.uzunluk);
    hh.diziEkle(l1);
    hh.goster();
*/
    }
    
}
