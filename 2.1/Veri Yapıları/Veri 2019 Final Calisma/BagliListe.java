package stack;

class Eleman{
    int icerik;
    Eleman ileri;
    public Eleman(int icerik){
        this.icerik = icerik;
        ileri = null;
    }
}

public class BagliListe {
    Eleman ust;
    public BagliListe(){
        ust = null;
    }
    boolean bosmu(){
        return ust == null;
    }
    public void ekle(Eleman yeni){
        if(bosmu()){
            ust = yeni;
        }
        else {
            yeni.ileri = ust;
            ust = yeni;
        }
    }
    Eleman sil(){
        Eleman s = ust;
        if(!bosmu()){
           ust = ust.ileri;
        }
        return s;
    }
    public void enbuyuk(BagliListe x, BagliListe y){
        int eb = x.ust.icerik;
        int sayac = 0;
        while(!x.bosmu()){
            System.out.println(x.ust.icerik);
            //System.out.println(eb);
            if(eb < x.ust.icerik){
               eb = x.ust.icerik;
            }
           
            y.ekle(x.sil());
          //  System.out.println("eklendi");
            
            sayac++;
          
        }
        System.out.println("---------------");
        //System.out.println(sayac);
        while(sayac != 0){
            x.ekle(y.sil());
            
            sayac--;
            System.out.println(x.ust.icerik);
        }
    }
    
    public static void main(String[] args) {
        BagliListe x = new BagliListe();
        BagliListe y = new BagliListe();
        x.ekle(new Eleman(1));
        x.ekle(new Eleman(2));
        x.ekle(new Eleman(3));
        x.ekle(new Eleman(2));
        x.enbuyuk(x, y);
        
    }
    
}
