package kuyruk;

class Eleman{
    int icerik;
    Eleman ileri;
    public Eleman(int icerik){
        this.icerik = icerik;
        ileri = null;
    }
}

public class Kuyruk {
    Eleman bas;
    Eleman son;
    int sayac = 0;
    public Kuyruk(){
        bas = null;
        son = null;
    }
    public boolean bosmu(){
        return bas == null;
    }
    public void ekle(Eleman yeni){
        if (bosmu()){
            bas = yeni;
            son = yeni;
        }
        else{
            son.ileri = yeni;
            son = yeni;
        }
        System.out.println(yeni.icerik);
        sayac++;
    }
    Eleman sil(){
       // System.out.println("silinen eleman " + bas.icerik);
        Eleman s = bas;
        if(!bosmu()){
        bas = bas.ileri;
        }
        if(bas == null){
            son = null;
        }
        return s;
        
    }
     public void aktar(Kuyruk x, Kuyruk y){
         int yarisi = sayac/2;
         while(yarisi !=0 ){
             y.ekle(x.sil());
             yarisi--;
             
         }
     }
     public void siraliekle(Eleman yeni, Kuyruk x , Kuyruk y){
         int sayac = 0;
         int syc = 0;
         while(!bosmu()){
             if(yeni.icerik < x.bas.icerik && sayac !=1){
                 y.ekle(yeni);
                 sayac++;
             }
             else{
                 y.ekle(x.sil());
             }
             syc++;
         }
         sayac = 0;
         System.out.println("---------");
         while(syc != 0){
             x.ekle(y.sil());
             syc--;
         }
         
     }
   
    public static void main(String[] args) {
        Kuyruk x = new Kuyruk();
        Kuyruk y = new Kuyruk();
        x.ekle(new Eleman(3));
        x.ekle(new Eleman(8));
        x.ekle(new Eleman(12));
        x.ekle(new Eleman(15));
        //x.aktar(x, y);
        System.out.println("-------------------");
        x.siraliekle(new Eleman(13), x, y);
        System.out.println("-------------------");
        x.siraliekle(new Eleman(5), x, y);
       
       
    }
    
}
