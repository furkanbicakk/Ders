package stack;

//Yeni bir stack yapisi olusturuyoruz.
public class Stack {
    int kapasite;
    int []s;             // Dizi seklinde tutacagiz.
    int ust;            // ust adinda bir degisken tutup bununla sinirlarimizi belirleyip/kontrol edecegiz.
    
    public Stack(int kapasite){
        this.kapasite = kapasite;
        s = new int[kapasite]; // disaridan girilen boyutla dizimizi olusturduk.
        ust = -1;  // sinirimizi -1 tutuyoruz ilk basta.
    }
    
    // Yigitimizin dolu olup olmadigini bu sekilde kontrol ediyoruz.
    // Burada amacimiz eger -1=-1 olmadigi surece dolu olacak.
    boolean dolumu(){
        if (ust == kapasite-1){
            return true;
        }
        else {
            return false;
        }
    }
    
    // Eger tum ekleme ve cikarma islemlerinin sonunda ust degiskenimiz hala -1 i gosteriyorsa bostur.
    boolean bosmu(){
        return ust == -1;
    }
    
    // sinirimizi bir arttirip gelen elemani dizinin indisine ekliyoruz.(Eger stack dolu degilse..)
    void push(int x){
        if (!dolumu()){
            ust = ust + 1;
            s[ust] = x;
            System.out.println(x);
        }
    }
    
    // Eger stack dolu ise(bos degilse) siniri azaltip en tepedeki degeri bize gostermesini soyluyoruz.
    int pop(){
        if(!bosmu()){
            ust = ust - 1;
            System.out.println(s[ust+1]); // cikardigi elemani yaziyor
            return s[ust+1];
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int size=5;
        Stack s= new Stack(size);  
        System.out.println("Stack' e eleman ekliyoruz: ");
        s.push(4);
        s.push(9);
        s.push(1);
        s.push(7);
        s.push(3);
        System.out.println("Stack' ten eleman çıkartıyoruz: ");
        
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
    }
    
}
