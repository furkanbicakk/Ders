
package odev3quiz;

public class kuyruk {
    sayi dizi[];
    int bas;
    int son;
    int N;
    public kuyruk(int N) {
        dizi = new sayi[N];
        this.N = N;
        bas = 0;
        son = 0;
   
    }

    boolean kuyrukDolu() {
        return (bas == (son + 1) % N);

    }
    
    boolean kuyrukBos() {
        return (bas == son);
    }
    

   
    
    void kuyrugaEkle(sayi yeni) {
        if (!kuyrukDolu()) {
            dizi[son] = yeni; System.out.print(""+yeni.sayi);
            son = (son + 1) % N;
        }

    }
    
    sayi kuyrukSil() {
        sayi sonuc;
        if (!kuyrukBos()) {
            sonuc = dizi[bas];
            bas = (bas + 1) % N; System.out.println("");
            return sonuc;
        }
        return null;
    }
    
    public static void main(String[] args) { //43582 //10250 // 00020 //00000
        kuyruk cevirim = new kuyruk(20);
        cevirim.kuyrugaEkle(new sayi(4));
        cevirim.kuyrugaEkle(new sayi(3));
        cevirim.kuyrugaEkle(new sayi(5));
        cevirim.kuyrugaEkle(new sayi(8));
        cevirim.kuyrugaEkle(new sayi(2));
        System.out.println("");
        
        while (true) {

            int a = 3;
            int gelen = cevirim.kuyrukSil().sayi;
            System.out.println("");
            if (a <= gelen) {
                int fark = gelen - a;
                cevirim.kuyrugaEkle(new sayi(fark));}
            
         if(gelen<a) cevirim.kuyrugaEkle(new sayi(0));
            System.out.println("");
            }
        
    
        
        cevirim.bas==cevirim.son+1
        
        
        
        
    }}
    