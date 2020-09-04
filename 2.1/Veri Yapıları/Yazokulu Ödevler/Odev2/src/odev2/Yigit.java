package odev2;

import java.util.Scanner;

public class Yigit {
    
    Ornek[] dizi;
    int N;
    int ust;

    Yigit(int N) {
        dizi = new Ornek[N];
        this.N = N;
        ust = -1;
    }
    
    Ornek ust() {
        return dizi[ust];
    }
    
    boolean yigitDolu() {
        return (ust == N - 1);
    }
    
    boolean yigitBosmu() {
        if (ust == -1) {
            return true;
        } else {
            return false;
        }
    }
    
        boolean yigitBosmu1() {
        if (ust == N) {
            return true;
        } else {
            return false;
        }
    }
        
          Ornek yigitSil1() {
        if (!yigitBosmu1()) {
            ust++;
            return dizi[ust - 1];
        }
        return null;
    }
                
    void yigitEkle(Ornek yeni,int ust1,int ust2) {
      
        if (ust1<ust2-1) {
            ust++;
            dizi[ust] = yeni;
        }else return;
    }
    
     void yigitEkle1(Ornek yeni,int ust1,int ust2) {
         if(ust1==(ust2-1)){return;}
         else if (ust1<ust2) {
            ust--;
            dizi[ust] = yeni;
        }
    }
    
    Ornek yigitSil() {
        if (!yigitBosmu()) {
            ust--;
            return dizi[ust + 1];
        }
        return null;
    }

        
    void print() { System.out.print("Birinci Yığıt: ");
        int top = ust;
         if (top==-1) {
                System.out.println("Dizi Boş"); 
            } 
        while (top>=0) {
           
                System.out.print(dizi[top--].icerik + " ");
            
        }
    }
    
      void print1() { System.out.print("İkinci Yığıt: ");
        int top = ust;
         if (top==N) {
                System.out.println("Dizi Boş"); 
            } 
        while (top<N) {
           
                System.out.print(dizi[top++].icerik + " ");
            
        }
    }
    
    public static void main(String[] args) {
        Scanner klavye = new Scanner(System.in);
        System.out.print("Dizi Boyutunu Girin: ");
        int n = klavye.nextInt();
        Yigit y = new Yigit(n);              Yigit y1 = new Yigit(n); y1.ust=n;
        y.yigitEkle(new Ornek(1),y.ust,y1.ust);
        y.yigitEkle(new Ornek(2),y.ust,y1.ust);
        y.yigitEkle(new Ornek(3),y.ust,y1.ust);
        y.yigitEkle(new Ornek(4),y.ust,y1.ust);
        y.yigitEkle(new Ornek(5),y.ust,y1.ust); //y.yigitSil();
        System.out.println("ilk yigit ust: "+y.ust().icerik);
        y.print();
        System.out.println("\n"+"----------------------------------------------------");
        
       
        y1.yigitEkle1(new Ornek(6),y.ust,y1.ust);
        y1.yigitEkle1(new Ornek(7),y.ust,y1.ust);
        y1.yigitEkle1(new Ornek(8),y.ust,y1.ust);
        y1.yigitEkle1(new Ornek(9),y.ust,y1.ust);
        y1.yigitEkle1(new Ornek(10),y.ust,y1.ust); // y1.yigitSil1();y1.yigitSil1();y1.yigitSil1();y1.yigitSil1();y1.yigitSil1();
        System.out.println("ikinci yigit ust: "+y1.ust().icerik);
        y1.print1();
    }
}
