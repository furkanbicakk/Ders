package hipp;

public class Heap {

    int [] dizi;
    int tane;
   

    public Heap(int N) {

        dizi = new int[N];
        tane = 0;

    }

    void yukaricik(int n) {
        int ata = (n - 1) / 2;

        while (ata >= 0 && dizi[ata] > dizi[n]) {
            int tmp = dizi[ata];
            dizi[ata] = dizi[n];
            dizi[n] = tmp;
            n = ata;
            ata = (n - 1) / 2;
        }
    }

    void son_sil() {
        tane = tane - 1;
    }

    void bassil(int k) {
        
        while (k != 0) {            
        k--;
        int tmp;
        tmp = dizi[0];
        dizi[0] = dizi[tane - 1];
        tane = tane - 1;
        asagin(0);

        
        }

    }

    void asagin(int no) {
        int sol = 2 * no + 1;
        int sag = 2 * no + 2;

        while (sol < tane && dizi[no] > dizi[sol]|| sag < tane && dizi[no] > dizi[sag]) {
            if (sag >= tane || dizi[sol]< dizi[sag]) {

                int ara = dizi[no];
                dizi[no] = dizi[sol];
                dizi[sol] = ara;
                no = sol;
            } else {

                int ara = dizi[no];
                dizi[no] = dizi[sag];
                dizi[sag] = ara;
                no = sag;
            }

            sol = 2 * no + 1;
            sag = 2 * no + 2;

        }

    }

    int arama(int ara) {

        int i;
        for (i = 0; i < tane; i++) {
            if (dizi[i]== ara) {
                return i;
            }
        }
        return -1;
    }

    boolean bosmu() {
        return tane == 0;
    }

    void Ekle(int yeni) {
      

        tane++;
        dizi[tane - 1] = yeni;
        yukaricik(tane - 1);

    }

    void goster() {

        for (int i = 0; i < tane; i++) {
            System.out.println("" + dizi[i]);

        }
    }

    void min_heapmi(int []a) {
        int no=0;
        int sol = 2 * no + 1;
        int sag = 2 * no + 2;
        int sayac = 0;
        for (int i = 0; i < tane; i++) {

            if (sag < tane) {

                if (dizi[no] > dizi[sol]|| dizi[no] > dizi[sol]) {

                } else {
                    sayac++;
                }
            }
        }
        if (sayac == tane) {
            System.out.println("min heap tir");

        } else {
            System.out.println("min hep değildir");
        }
    }

    

   /* void Ekle2(int yeni) {
       

        tane++;
        dizi[tane - 1] = yeni;
        yukaricik2(tane - 1);

    }*/

   /* void asagin2(int no) {
        int sol = 2 * no + 1;
        int sag = 2 * no + 2;

        while (sol < tane && dizi[no]< dizi[sol] || sag < tane && dizi[no]< dizi[sag]) {
            if (sag >= tane || dizi[sol] > dizi[sag]) {

                int ara = dizi[no];
                dizi[no] = dizi[sol];
                dizi[sol] = ara;
                no = sol;
            } else {

                int ara = dizi[no];
                dizi[no] = dizi[sag];
                dizi[sag] = ara;
                no = sag;
            }

            sol = 2 * no + 1;
            sag = 2 * no + 2;

        }

    }*/
    void yukaricik2(int n) {
            int ata = (n - 1) / 2;

            while (ata >= 0 && dizi[ata]< dizi[n]) {
                int tmp = dizi[ata];
                dizi[ata] = dizi[n];
                dizi[n] = tmp;
                n = ata;
                ata = (n - 1) / 2;
            }
        }
    
    void maksa_cevir(int kok) {
        int enb = dizi[0],enbIndis = 0;
        int tmp;
        
        for (int i = 0; i < tane; i++) {
            if (enb< dizi[i]) {
                enb = dizi[i];
                enbIndis = i;
               
            }

        }
        tmp = dizi[0];
        dizi[0] = enb;
        dizi[enbIndis] = tmp;
        
        maxa_cevir1();
    }

    void maxa_cevir1() {
        for (int i = tane - 1; i >= 0; i--) {
            yukaricik2(i);

        }
    }
    void diziEkle(liste a){
        dugum tmp = a.bas;
        while(tmp != null){
            Ekle(tmp.icerik);
            tmp = tmp.ileri;
            
        }
        
    }
}
