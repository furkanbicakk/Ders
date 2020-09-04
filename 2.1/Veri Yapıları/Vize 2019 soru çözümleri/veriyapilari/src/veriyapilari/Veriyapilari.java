
package veriyapilari;
public class Veriyapilari {
 public static BListe kesisim(BListe b1,BListe b2){
     BListe b3=new BListe();
     Eleman tmp1=b1.bas,tmp2=b2.bas;
     while(tmp1!=null && tmp2!=null){
         if(tmp1.icerik==tmp2.icerik){
             Eleman e=new Eleman(tmp1.icerik);
             b3.sonaEkle(e);
             tmp1=tmp1.ileri;
             tmp2=tmp2.ileri;             
         }
         else if(tmp1.icerik<tmp2.icerik)
             tmp1=tmp1.ileri;         
         else tmp2=tmp2.ileri;
     }     
     return b3;
 }
    public static void main(String[] args) {
        BListe b1=new BListe();
        BListe b2=new BListe();
        b1.sonaEkle(new Eleman(3));
        b1.sonaEkle(new  Eleman(5));
        b1.sonaEkle(new Eleman(12));
        b1.sonaEkle(new Eleman(18));
        b2.sonaEkle(new Eleman(3));
        b2.sonaEkle(new  Eleman(4));
        b2.sonaEkle(new Eleman(12));
        b2.sonaEkle(new Eleman(14));
      
        System.out.print("Liste 1:");
       b1.listele();
        System.out.println("");
        b2.listele();
        BListe b4=kesisim(b2, b1);
        b4.listele();
    
    }
    
}
