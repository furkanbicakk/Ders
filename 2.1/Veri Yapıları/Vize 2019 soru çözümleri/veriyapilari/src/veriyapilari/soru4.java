
package veriyapilari;

import java.io.File;
import java.util.Scanner;
public class soru4 {
    public static void sil(String data,Bliste2[]yeni){
        Dugum temp=yeni[5].bas,onceki=null;
        if(temp!=null && temp.icerik.equals(data)){
            yeni[5].bas=yeni[5].bas.ileri;
            return;
        }
        while (temp != null && !temp.icerik.equals(data)) 
        { 
            onceki = temp; 
            temp = temp.ileri; 
        }     
        if (temp == null) return; 
        onceki.ileri = temp.ileri;             
    }
    public static void main(String[] args) {
        Bliste2[] yeni=new Bliste2[6];        
        Scanner dosya=null;
        String []harfler={"a","b","c","d","e","v"};
        for (int i = 0; i < yeni.length; i++) {
            yeni[i]=new Bliste2();            
        }
        try {
            dosya=new Scanner(new File("kelime.txt"));
            while(dosya.hasNext()){
                String okunan =dosya.next();
                for (int i = 0; i < harfler.length; i++) {
                    if(okunan.startsWith(harfler[i]))                        
                        yeni[i].sonaEkle(new Dugum(okunan));
                }
            }
            //sil("veri", yeni);
            Dugum tmp=yeni[5].bas;
            while(tmp!=null){
                System.out.println(tmp.icerik);
                tmp=tmp.ileri;
            }
        } catch (Exception e) {
        }
    }
    
}
