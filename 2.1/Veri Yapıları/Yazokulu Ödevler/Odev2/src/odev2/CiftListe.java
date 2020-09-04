package odev2;



public class CiftListe {

    CiftEleman bas, son;

    public CiftListe() {
        bas =  null; son=null;
    }

    public void SiraliEkle(CiftEleman yeni) {
        CiftEleman tmp = bas;
        CiftEleman once,buyuk,kucuk;
        
        if (bas == null) {
            CiftBasinaEkle(yeni);
        }
        
        if (yeni.ucret < bas.ucret) {
            CiftBasinaEkle(yeni);
        } 
        
        if (yeni.ucret > son.ucret) {
            CiftListeEkleSon(yeni);
        }
        
         if (yeni.ucret > bas.ucret && yeni.ucret< son.ucret) {
            while (tmp.ucret<yeni.ucret) {
                once = tmp; 
                if(once.ucret<yeni.ucret && yeni.ucret<once.ileri.ucret && once!=son && once!=null){
             
                 CiftListOrtaEkle(once,yeni);}
               else if(son==once){return;} tmp = tmp.ileri;
                
            }
            
        }
         

    }
    
  void CiftListOrtaEkle(CiftEleman once, CiftEleman yeni) {
        if (once.ileri != null) {
            yeni.ileri = once.ileri;
            yeni.geri = once;
            once.ileri.geri = yeni;
            once.ileri = yeni;
        } else {
            System.out.println("araya eklenemedi");
        }
    }
  
    boolean siralimi(CiftEleman list) {
        boolean x = true;
        CiftEleman tmp = list;
        while (tmp.ileri != null) {
            if (tmp.ucret < tmp.ileri.ucret) {
                x = true;
            } else {
                x = false;
                return x;
            }
            tmp = tmp.ileri;
        } return x;
    }
    
    void CiftBasinaEkle(CiftEleman yeni) {
        if (son == null) {
            bas = yeni;
            son = yeni;
        } else {
            bas.geri = yeni;
            yeni.ileri = bas;
            bas = yeni;
        }
    }
    
    void print(CiftEleman gelen) {
        CiftEleman tmp = gelen;
        while (tmp != null) {
            System.out.print("isim: " + tmp.isim + " ucret: " + tmp.ucret + " ");
            tmp = tmp.ileri;
        }
        if (son == null) {
            bas = null;
        }
    }
    
    void CiftListeEkleSon(CiftEleman yeni) {
        if (bas == null) {
            son = null;
        } else {
            son.ileri = yeni;
            yeni.geri = son;
            son = yeni;
        }
    }
    
   
    public static void main(String[] args) {
        String b="2000";
        Double ucret=Double.valueOf(b); System.out.println(ucret);
    }

    
  /* public static void main(String[] args) {
        CiftListe ilk = new CiftListe();
        ilk.SiraliEkle(new CiftEleman("fettah", 100));
        ilk.SiraliEkle(new CiftEleman("Abdullah", 20));
        ilk.SiraliEkle(new CiftEleman("Samet", 80));
        ilk.SiraliEkle(new CiftEleman("yunus", 50));
        ilk.SiraliEkle(new CiftEleman("fuat", 70));
        ilk.SiraliEkle(new CiftEleman("ahmet", 40));
        ilk.SiraliEkle(new CiftEleman("salih", 5));
        ilk.SiraliEkle(new CiftEleman("serkan", 10));
        ilk.SiraliEkle(new CiftEleman("ozge", 110));
        ilk.print(ilk.bas);
        System.out.println("\nSıralımı: " + ilk.siralimi(ilk.bas));
    }
*/
    
}
 

