package stackcalisma;

class Eleman{
    Eleman ileri;
    int icerik;
    public Eleman(int icerik){
        this.icerik = icerik;
        ileri = null;
    }
}

public class Calisma1 {
    int kapasite = 0;
    Eleman ust;
    Eleman bas;
    Eleman son;
    public Calisma1(){
        ust = null;
        bas = null;
        son = null;
    }
    
    public boolean bosmu(){
        return ust == null;
    }
    public boolean kbosmu(){
        return bas == null;
    }
    
    public void ekle(Eleman yeni) {
        if (kapasite >= 20) {
            System.out.println("SINIRA ULASTINIZ.");
        } else {
            if (bosmu()) {
                ust = yeni;
            } else {
                yeni.ileri = ust;
                ust = yeni;
            }
        }
        kapasite++;
    }
    public void kuyrukEkle(Eleman yeni){
        if(bosmu()){
            bas = yeni;
            son = yeni;
        }
        else{
            son.ileri = yeni;
            son = yeni;
        }
       // System.out.println("Eklenenler : " + yeni.icerik);
    }
    
    
    Eleman sil(){
        kapasite--;
        Eleman s = ust;
        if(!bosmu()){
            ust = ust.ileri;
        }
        return s;
    }
    public void bul(Calisma1 x, Calisma1 y){
        int enb = x.ust.icerik;
        int sayac = 0;
        while(!x.bosmu()){
            if(enb < x.ust.icerik){
                enb = x.ust.icerik;
            }
            y.ekle(x.sil());
            sayac++;
        }
        while(sayac != 0){
            if(y.ust.icerik != enb){
                x.ekle(y.sil());
               // System.out.println(x.ust.icerik);
            }
            else{
                System.out.println(y.ust.icerik);
                y.sil();
            }
            sayac--;
        }
    }
    public void itekle(Calisma1 x, Calisma1 y, Calisma1 z){
        int sayac = 0;
        int sayac2 = 0;
        while(!x.bosmu()){
            z.ekle(x.sil());
            sayac++;
        }
        while(sayac2 != sayac/2){
            y.ekle(z.sil());
            System.out.println(y.ust.icerik);
            sayac2++;
        }
    }
    public void yigitaktar(Calisma1 x, Calisma1 k, Calisma1 z){
        int sayac = 0;
        int sayac2 = 0;
        while(!x.bosmu()){
            z.ekle(x.sil());
            sayac++;
        }
        while(sayac2 != sayac/2){
            k.kuyrukEkle(z.sil());
          //  System.out.println(k.son.icerik);
            sayac2++;
        }
        
    }
    public void ikiekle(Calisma1 x, Calisma1 k1, Calisma1 k2){
        while(!x.bosmu()){
            System.out.println("tek eklendi");
            k1.kuyrukEkle(x.sil());
            System.out.println("-------------");
            System.out.println("cift eklendi");
            k2.kuyrukEkle(x.sil());
            System.out.println("-------------");
        }
    }

    
    public static void main(String[] args) {
        Calisma1 x = new Calisma1();
        Calisma1 y = new Calisma1();
        Calisma1 z = new Calisma1();
        x.ekle(new Eleman(11));
        x.ekle(new Eleman(2));
        x.ekle(new Eleman(10));
        x.ekle(new Eleman(4));
        x.ekle(new Eleman(51));
        x.ekle(new Eleman(80));
        x.ekle(new Eleman(80));
        //x.bul(x, y);
      //  x.itekle(x, y, z);
     // x.yigitaktar(x, y, z);
     x.ikiekle(x, y, z);
    }
    
}
