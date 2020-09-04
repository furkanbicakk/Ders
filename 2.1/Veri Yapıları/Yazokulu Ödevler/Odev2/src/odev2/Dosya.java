package odev2;


import java.io.*;
import java.util.Scanner;
public class Dosya{ 
    
public static void main(String[] args) throws FileNotFoundException, IOException{
String path = "d:\\odemeler.txt";
File dosya= new File(path);     
Scanner oku=new Scanner(dosya);

CiftListe ilk = new CiftListe();

    while (oku.hasNext()) {
      
        String str = oku.nextLine();
    
        String[] arrOfStr = str.split(" ");
      
        String tut = "";
        for (String a : arrOfStr) {
if (a.charAt(0) == '0' || a.charAt(0) == '1' || a.charAt(0) == '2' || a.charAt(0) == '3' || a.charAt(0) == '4' || a.charAt(0) == '5' || a.charAt(0) == '6' || a.charAt(0) == '7' || a.charAt(0) == '8' || a.charAt(0) == '9') {
                tut = a;
            }
        }

        int i = 0;   String b = "";
        while (tut.charAt(i) != 'T') {
            b += tut.charAt(i);
            i++;
           
        }
        String ad="";
        if(b!=null){
         ad = str.substring(0, 6);}
    
        //System.out.println(ad);System.out.println(b);
      if(b!=""){
          int ucret=Integer.valueOf(b); 
 ilk.SiraliEkle(new CiftEleman(ad, ucret));}
    }
oku.close();

      
     
        ilk.print(ilk.bas);
        System.out.println("\nSıralımı: " + ilk.siralimi(ilk.bas));



    



}}