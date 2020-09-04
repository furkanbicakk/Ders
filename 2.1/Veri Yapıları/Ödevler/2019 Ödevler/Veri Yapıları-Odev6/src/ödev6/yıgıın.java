package odev6;

import java.util.HashSet;

class dugum{
    int içerik;

    public dugum(int içerik){
        this.içerik = içerik;
    }
}
public class yıgıın {
    dugum dizi[];
    int es;
    public yıgıın(int N){
        dizi = new dugum[N];
        es = 0;
    }
    
    class zincirDügüm{
        int key, deger;
        
        zincirDügüm ileri;
        
        public zincirDügüm(int key, int deger){
            this.key = key;
            this.deger = deger;
            ileri = null;
            
        }
        
    }
    class zincirHash{
        
        final int diziboyutu = 10;
        zincirDügüm dizi[] = new zincirDügüm[diziboyutu];
        
        int hash(int key){
            return key % diziboyutu;
        }
        
        void ekle(zincirDügüm yeni){
            int hash = hash(yeni.key);
            if (dizi[hash] == null) {
                dizi[hash] = yeni;
            }
            else{
                zincirDügüm tmp = dizi[hash];
                while (tmp.ileri != null) {                    
                    tmp = tmp.ileri;
                }
                
                tmp.ileri = yeni;
            }
        }
        
        void ara(zincirDügüm yeni){
            int hash = hash(yeni.key);
            if (dizi[hash] == yeni){
                System.out.println(yeni.deger);
            }
            else{
                zincirDügüm tmp = dizi[hash];
                
                while (tmp != null) 
                {                    
                    System.out.println(tmp.deger + " ");
                    tmp = tmp.ileri;
                }
                
                System.out.println(yeni.deger);
            }
        }
    }

    boolean yıgınBos(){
        if (es == 0)
            return true;
        else
            return false;
    }
    
    void yerDegistir(int k, int yeni){
        int eski = dizi[k].içerik;
        dizi[k].içerik = yeni;
        if(eski>yeni){
            asagiIn(k);
        }
        else{
            yukarıCik(k);
        }
    }
    
    void yukarıCik(int no){
        int ustdal = (no-1)/2;
        while (ustdal >= 0 && dizi[ustdal].içerik < dizi[no].içerik){
            yerDegistir(ustdal,no);
            no = ustdal;
            ustdal = (no-1)/2;
        }
    }
    void asagiIn(int no){
        int sol = 2*no + 1;
        int sag = 2*no + 2;
        
        while ((sol<es && dizi[no].içerik < dizi[sol].içerik) ||
               (sag<es && dizi[no].içerik < dizi[sag].içerik)) {  
            
            if (sag >= es || dizi[sol].içerik > dizi[sag].içerik) {
                yerDegistir(no, sol);
                no = sol;
            }
            else{
                yerDegistir(no, sag);
                no = sag;
            }
            sol = 2*no + 1;
            sag = 2*no + 2;
        }
    }
    void ekle(dugum yeni){
        es++;
        dizi[es-1] = yeni;
        yukarıCik(es-1);
    }
    
    dugum sil(){
        dugum tmp = dizi[0];
        dizi[0] = dizi[es-1];
        asagiIn(0);
        es--;
        return tmp;
    }
    
      void son_sil() {
        es = es - 1;
    }
      
     int bassil() {

        int tmp;
        tmp = dizi[0].içerik;
        dizi[0] = dizi[es - 1];
        es = es - 1;
        asagiIn(0);
        

        return tmp;

    }  

    boolean minheapmi(int dizix[]){
        boolean x = true;
        for (int i = 0; i < dizix.length; i++) {
            
            int sol = 2*i+1;
            int sag = 2*i+2;
            
            
            if (sol <= dizix.length-1 && (dizix[i] < dizix[sol]) && sag <= dizix.length-1 && (dizix[i] < dizix[sag])) {
                System.out.println("HEAP");
                  x = true;
            }
            else if(sol >= dizix.length){
                i = dizix.length;
            }
            else{
                x = false;
                i = dizix.length;
            }
        }

         return x;        
    }
    
static void MaxHeapify(int arr[], int i, int n) 
    { 
        int sol = 2*i + 1; 
        int sag = 2*i + 2; 
        int largest = i; 
        
        if (sol < n && arr[sol] > arr[i]){ 
            largest = sol; 
        }
        if (sag < n && arr[sag] > arr[largest]) {
            largest = sag; 
        }
        
        if (largest != i) 
        {  // swap arr[i] and arr[largest] 
            int temp = arr[largest];; 
            arr[largest] = temp; 
            MaxHeapify(arr, largest, n); 
        } 
    } 
   
    
    static void maxheapyap(int arr[], int n){ 
        for (int i = (n-2)/2; i >= 0; i--){ 
            MaxHeapify(arr, i, n); 
        }
       
    } 
   
    
    static void diziyiyaz(int arr[], int byt) 
    { 
        for (int i = 0; i < byt; i++) 
            System.out.print(arr[i]+" "); 
    } 
    
     
    void goster(){
        for (int i = 0; i < dizi.length; i++) {
            System.out.print(dizi[i].içerik);
        }
        System.out.println("");
    }
     
     void goster2(){
        for (int i = 0; i < es; i++) {
            System.out.println("" + dizi[i].içerik);
        }
    }
     
 /*
       void maxa_cevir1() {
        for (int i = es - 1; i >= 0; i--) {
            yukarıCik(es-1);

        }
    }
       
       void maksa_cevir(int kok) {
        int enb = dizi[0].içerik,enbIndis = 0;
        int tmp;
        
        for (int i = 0; i < es; i++) {
            if (enb < dizi[i].içerik) {
                enb = dizi[i].içerik;
                enbIndis = i;
               
            }

        }
        tmp = dizi[0].içerik;
        dizi[0].içerik = enb;
        dizi[enbIndis].içerik = tmp;
        
        maxa_cevir1();
    }
    */
    void quiz(int dizi1[], int dizi2[]){
        
        int diziyeni [] = new int[10];
         
        for (int i = 0; i < 5; i++){
            diziyeni[i] = dizi1[i];
            
        }
        for (int i = 0; i < 5; i++) {
            diziyeni[i+4] = dizi2[i];
        }
        
       // maxheapyap(diziyeni,es);
        for (int i = 0; i < 10; i++) {
            System.out.println(diziyeni[i]);
            
        }
             
        
    }
    
    public static void main(String[] args) {
        dugum d1 = new dugum(4);
        dugum d2 = new dugum(2);
        dugum d3 = new dugum(1);
        dugum d4 = new dugum(5);
        dugum d5 = new dugum(3);
        
        dugum x1 = new dugum(9);
        dugum x2 = new dugum(10);
        dugum x3 = new dugum(6);
        dugum x4 = new dugum(7);
        dugum x5 = new dugum(8);
        

        yıgıın y = new yıgıın(5);
        yıgıın x = new yıgıın(5);
        
        x.ekle(x1);
        x.ekle(x2);
        x.ekle(x3);
        x.ekle(x4);
        x.ekle(x5);
        
        int dizix [] = {9,8,6,7,0};
        
        y.ekle(d1);
        y.ekle(d2);
        y.ekle(d3);
        y.ekle(d4);
        y.ekle(d5);
        
        int diziy [] = {1,4,5,3,2};
        
       x.quiz(dizix, diziy);
     
  
      //y.goster();
     // System.out.println("*****");
     // y.bassil();
     // y.sil();
    //  y.son_sil();
     // y.goster();
       
        int adizi [] = {1,3,5,7,8,9,11,12,13,15,16};
    //  System.out.println(y.minheapmi(adizi));
       
    /*  diziyiyaz(adizi,adizi.length);
        maxheapyap(adizi,adizi.length);
        System.out.println(" ");
        System.out.println("**************************");
        diziyiyaz(adizi,adizi.length);
    */
 
    /*  y.goster2();
        System.out.println(" ");
        y.maksa_cevir(0);
        y.goster2();
   */
  
       
  
    }
}
