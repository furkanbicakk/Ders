package odev6;
import java.util.HashSet;

class zincirdugum{
    int anahtar, deger;
    zincirdugum ileri;
    
    zincirdugum(int anahtar, int deger){
        this.anahtar=anahtar;
        this.deger=deger;
        ileri=null;    
    }    
}

class zincirhash{
    final int diziuzunlugu=10;
    zincirdugum dizi[] = new zincirdugum[diziuzunlugu];
    
    int hash (int anahtar){
        return anahtar%diziuzunlugu;
    }
    
    void ekle(zincirdugum yeni){
        int hash = hash(yeni.anahtar);
        
        if (dizi[hash] == null){
            dizi[hash]=yeni;
        }
        else {
            zincirdugum tmp= dizi[hash];
            while(tmp.ileri != null){
                tmp=tmp.ileri;
        }
            tmp.ileri=yeni;        
        }
    }
    
    
    void ara(zincirdugum yeni){
        
        int hash = hash(yeni.anahtar);
        
        if (dizi[hash] == yeni) {
            System.out.println(yeni.deger);
        }
        else{
            zincirdugum tmp = dizi[hash];
            while(tmp != null){
                System.out.println(tmp.deger+ " ");
                tmp=tmp.ileri;
            }
            System.out.println(yeni.deger);
        }
    }
}

public class hash {
    
    static void esitmi(int dizi[], int n){ //dizideki iki sayı toplamı 
        for (int i = 0; i < dizi.length; i++) {
            for (int j = i+1; j < dizi.length; j++) {
                if (dizi[i]+dizi[j] == n) {
                    System.out.println("Toplma var ve sayılar : " + dizi[i] + "," + dizi[j]);
                }
            }
        }
        
    }
    
    static boolean anagram(String str1, String str2){   
        if (str1.length() != str2.length()){  
            return false;  
        }
        
        int deger = 0;  
        
        for (int i = 0; i < str1.length(); i++){  
            deger = deger ^ str1.charAt(i);  
            deger = deger ^ str2.charAt(i);  
        }  
      return deger == 0;  
    }  
    
    static boolean altKume(int dizi1[], int dizi2[], int d1, int d2){ 
        HashSet<Integer> hset= new HashSet<>(); 
          
        for(int i = 0; i < d1; i++){ 
            if(!hset.contains(dizi1[i])){ 
                hset.add(dizi1[i]); 
            }
        } 
               
        for(int i = 0; i < d2; i++) { 
            if(!hset.contains(dizi2[i])) 
                return false; 
        } 
        return true; 
    } 
     
     
     public static void main(String[] args) {
         
        zincirhash x = new zincirhash();
        x.ekle(new zincirdugum (3,10));
        x.ekle(new zincirdugum (1,60));
        x.ekle(new zincirdugum (2,60));
        x.ekle(new zincirdugum (3,20));
        x.ekle(new zincirdugum (3,80));
        x.ara(new zincirdugum(3,20));
        
        int adizi [] = {1,3,5,7,8,9,11,12,13,15,16};

        esitmi(adizi,20);

        System.out.println(anagram("silah","halis"));
        
        int arr1[] = {25, 1, 13, 21, 5, 8}; 
        int arr2[] = {25, 5, 8, 1}; 
        
        System.out.println(altKume(arr1, arr2, arr1.length, arr2.length));
    }
}
