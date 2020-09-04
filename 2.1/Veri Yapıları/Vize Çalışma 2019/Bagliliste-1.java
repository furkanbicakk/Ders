
package bagliliste;

class GecenlerList{
    Eleman bas;
    Eleman son;
    public GecenlerList(){
        bas = null;
        son = null;
    }
    
    void SonaEkle(Eleman yeni){
        if (bas == null){
            bas = yeni;
        }
        else {
            son.ileri = yeni;
        }
        son = yeni;
    }
    
    void Listele(){
        Eleman tmp = bas;
        System.out.println("GECENLER...");
        while(tmp != null){
            System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.vize + "\t" + tmp.genel);
            tmp = tmp.ileri;
        }
    }
}

class KalanlarList{
    Eleman bas;
    Eleman son;
    public KalanlarList(){
        bas = null;
        son = null;
    }
    
    void SonaEkle(Eleman yeni){
        if (bas == null){
            bas = yeni;
        }
        else {
            son.ileri = yeni;
        }
        son = yeni;
    }
    
    void Listele(){
        Eleman tmp = bas;
        System.out.println("KALANLAR...");
        while(tmp != null){
            System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.vize + "\t" + tmp.genel);
            tmp = tmp.ileri;
        }
    }
}
       

class Eleman {
    int no,vize,genel;
    String ad;
    Eleman ileri;
    
    Eleman(int no, String ad, int vize, int genel){
        this.no = no;
        this.ad = ad;
        this.vize = vize;
        this.genel = genel;
        ileri = null;
    }
}
    public class Bagliliste{
        Eleman bas;
        Eleman son;
        public Bagliliste(){
            bas = null;
            son = null;
        }
        
        void BasaEkle(Eleman yeni){
            if (son == null){
                son = yeni;
            }
            yeni.ileri = bas;
            bas = yeni;
        }
        void SonaEkle(Eleman yeni){
            if (bas == null){
                bas = yeni;
            }
            else {
                son.ileri = yeni;
            }
            son = yeni;
        }
        void OrtaEkle(Eleman once, Eleman yeni){
            yeni.ileri = once.ileri;
            once.ileri = yeni;
        }
        void BasSil(){
            bas = bas.ileri;
            if (bas == null){
                son = null;
            }
        }
        void SonSil(){
            Eleman tmp, once;
            tmp = bas;
            once = null;
            while (tmp != son){
                once = tmp;
                tmp = tmp.ileri;
            }
            if (once == null){
                bas = null;
            }
            else {
                once.ileri = null;
            }
            son = once;
        }
        void AraSil(Eleman s){
            Eleman tmp, once;
            tmp = bas;
            once = null;
            while (tmp != s){
                once = tmp;
                tmp = tmp.ileri;
            }
            once.ileri = s.ileri;
        }
        void ListeAra(int deger){
            Eleman tmp;
            tmp = bas;
            while(tmp != null){
                if (tmp.vize == deger){
                    System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.vize + "\t" + tmp.genel);
                }
                tmp = tmp.ileri;
            }
            
        }
        void Listele(){
            Eleman tmp = bas;
            while(tmp != null){
                System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.vize + "\t" + tmp.genel);
                tmp = tmp.ileri;
            }
        }
        
        void Karsilastirma(){
            Eleman tmp;
            Eleman eb;
            eb = bas;
            tmp = bas;
            while(tmp != null){
                if (tmp.vize > eb.vize){
                    eb = tmp;
                }
                tmp = tmp.ileri;
            }
            System.out.println(eb.no + "\t" + eb.ad + "\t" + eb.vize + "\t" + eb.genel);
        }
        
        void Ortalama(){
            Eleman tmp;
            tmp = bas;
            double ort;
            GecenlerList gecen = new GecenlerList();
            KalanlarList kalan = new KalanlarList();
                
            
            while (tmp != null){
                String gecme = "Kaldi";
                ort = (tmp.vize*0.4+tmp.genel*0.6);
                if(ort >= 50.0 && tmp.genel >= 50){
                    gecme = "Gecti";
                    gecen.SonaEkle(new Eleman(tmp.no,tmp.ad,tmp.vize,tmp.genel));
                }
                else {
                    kalan.SonaEkle(new Eleman(tmp.no,tmp.ad,tmp.vize,tmp.genel));
                }
              //  System.out.println(tmp.no + "\t" + tmp.ad + "\t" + tmp.vize + "\t" + tmp.genel + "\t" + ort + "\t" + gecme);
                tmp = tmp.ileri;
            }
            gecen.Listele();
            System.out.println("----------------------------------------------");
            kalan.Listele();
        }
        
        
    
    
    public static void main(String[] args) {
        
        Bagliliste x = new Bagliliste();
        
        Eleman a = new Eleman(1,"ali",60,49);
        Eleman a2 = new Eleman(2,"ali",55,50);
        Eleman a3 = new Eleman(3,"ali",23,50);
        Eleman a4 = new Eleman(3,"ali",89,1);
        
        x.BasaEkle(a);
        x.SonaEkle(a3);
        x.OrtaEkle(a, a2);
       // x.ListeAra(53);
        //x.Karsilastirma();
        x.SonaEkle(a4);
        x.Ortalama();
        
        
        
        
    }
    }

