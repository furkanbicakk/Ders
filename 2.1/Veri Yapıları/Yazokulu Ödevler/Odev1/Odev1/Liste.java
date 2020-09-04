package Odev1;
public class Liste {
    
    Eleman bas;
    Eleman son;
    public Liste(){
    bas=null;
    son=null;
    
    }
      Eleman TersEkle(Eleman gelen) { 
        Eleman onceki = null; 
        Eleman tmp = gelen; 
       Eleman sonraki = null; 
        while (tmp != null) { 
           sonraki = tmp.ileri; //tmpilerisini sonraki gösterdi
            tmp.ileri = onceki; //oncekiyi tmpilerisi gösterdi
            onceki= tmp; //tmp icerigini oncekine attım
           tmp = sonraki; //sonrakinin iceriğini tmp ye attım
          
        } 
        gelen = onceki; 
        return gelen; 
    } 

void listeBasinaEkle(Eleman yeni){
   if (son == null) {
bas = yeni;
son = yeni;
} else {
yeni.ileri = bas;
bas = yeni;
}
}
void listeSonunaEkle(Eleman yeni){
   if(bas==null)
       bas=yeni;
   else
     son.ileri=yeni;
     son=yeni; 
}
void ListeBasiSil(){
bas=bas.ileri;
if(bas==null)son=null;

}
void listeSonuSil(){
Eleman tmp,once;
tmp=bas; once=null;
while(tmp.ileri!=null){
once=tmp;
tmp=tmp.ileri;}
if(once==null)
    bas=null;
else
    once.ileri=null;
son=once;

}
void listeOrtaSil(Eleman s){
Eleman tmp,elemanonce; tmp=bas; elemanonce=null;
while(tmp!=s){elemanonce=tmp;tmp=tmp.ileri;}elemanonce.ileri=s.ileri;tmp.ileri=null;
}

int basamakSayisi(){
int sayac=0; Eleman tmp=bas;
while(tmp!=null){tmp=tmp.ileri;sayac++;}
return sayac;
}

void okunus(Eleman gelen){
    String a=""; Eleman tmp=gelen;
    while(tmp!=null){ int sayi=tmp.icerik;String b=String.valueOf(sayi);tmp=tmp.ileri;  a+=b;} int sayi=Integer.valueOf(a);
    System.out.println(BasamakAyir(sayi));
 }
  public static String BasamakAyir(int sayi){
  String result = "";
  int i = 1;
  while( sayi != 0 )
  {
   result = result + i + "'ler basamağı: " + durum(sayi % 10 )+ "\n";
   sayi = sayi / 10;
   i *= 10;
  }
  return result;
 }                                                                              
public static String durum(int gelen){
    String a;
 switch(gelen){
     case 1: a="Bir";break;
     case 2: a="İki";break;
     case 3: a="üç";break;
     case 4: a="dört";break;
     case 5: a="beş";break;
     case 6: a="altı";break;
     case 7: a="yedi";break;
     case 8: a="sekiz";break;
     case 9: a="Dokuz";break;
     default:a="sıfır";
 }
return a;
}

void Listeleme(){
Eleman tmp=bas;
while(tmp!=null){
    System.out.print(" "+tmp.icerik);
    tmp=tmp.ileri;
}

}
  void printList(Eleman node) { 
        while (node != null) { 
            System.out.print(node.icerik + " "); 
            node = node.ileri; 
        } 
    } 
  Eleman topla(Eleman bir,Eleman iki){
      Liste ref=new Liste();
  Eleman tmp1=bir; Eleman tmp2=iki; int elde=0;int top=0;int kalan=0;
  while(tmp1!=null){
  top=tmp1.icerik+tmp2.icerik+elde; elde=top/10;kalan=top%10;
  ref.listeSonunaEkle(new Eleman(kalan)); tmp1=tmp1.ileri;tmp2=tmp2.ileri;
  }
  return ref.bas;
  }
  
    void polindrom(Eleman gelen){ Eleman tmp=gelen;
        Liste ref=new Liste();
        ref.bas=ref.TersEkle(tmp);
        Eleman tmp1=ref.bas;
        while(tmp!=null){ if(tmp.icerik==tmp1.icerik){System.out.println("polindrom");} else System.out.println("polindrom değil");tmp=tmp.ileri;tmp1=tmp1.ileri; }
    
    }
    void polindrom1(Eleman gelen){ 
    String a=""; Eleman tmp=gelen;
    while(tmp!=null){ int sayi=tmp.icerik;String b=String.valueOf(sayi);tmp=tmp.ileri;  a+=b;}  int b= Integer.valueOf(a); 
    int palindrom = b;     
    int ilkdeger = palindrom;        
        int length = String.valueOf(palindrom).length();
         
        int kalan,deger=0;
        for (int i = 0; i<length; i++){
             
            kalan = palindrom % 10;
            deger = deger * 10 + kalan;
            palindrom = palindrom /10;
        }
         
        if(deger == ilkdeger){
            System.out.println(deger + " Palindrom Sayidir.");
        }else{
            System.out.println(deger + " Palindrom Sayi Degildir.");
        }
         
    }
    
  

public static void main(String[] args) {
   Liste list=new Liste();
   Eleman bes=new Eleman(5);
    
   list.listeSonunaEkle(new Eleman(1));
   list.listeSonunaEkle(new Eleman(2));
   list.listeSonunaEkle(new Eleman(3));
   list.listeSonunaEkle(new Eleman(4));
   list.listeSonunaEkle(bes);
   list.listeSonunaEkle(new Eleman(6));
   list.listeSonunaEkle(new Eleman(7));
   System.out.println("sona ekleme");
   list.printList(list.bas);System.out.println("");
  // System.out.println("Baştan Ekleme: ");
    
           
   System.out.println("Tersten Ekleme");
   list.bas=list.TersEkle(list.bas);
   list.printList(list.bas);System.out.println("");
   
    System.out.println("liste basından silme:");  
    list.ListeBasiSil();
    list.printList(list.bas);System.out.println("");
    
    System.out.println("liste sonu Sil:");
     list.listeSonuSil();
    list.printList(list.bas);System.out.println("");
    
    System.out.println("liste orta sil:");
    list.listeOrtaSil(bes);
    list.printList(list.bas);System.out.println("");
    
    System.out.println("Basamak Sayisini bulma:");
    System.out.println("Sayinin Basamak Sayisi: "+list.basamakSayisi()+"\n");
    System.out.println("bağlı listede tutulan sayıyı String olarak döndüren metotlarını yazınız?");
    list.okunus(list.bas);
 
    System.out.println("------------------");
    
    System.out.println("Kendisine parametre olarak iki bağlı liste alan ve iki bağlı listenin tuttuğu sayıların toplamını yine bir bağlı listede döndüren metodu yazınız?\n");
    Liste bir=new Liste(); Liste iki=new Liste(); bir.listeSonunaEkle(new Eleman(6));bir.listeSonunaEkle(new Eleman(1));bir.listeSonunaEkle(new Eleman(7));
    iki.listeSonunaEkle(new Eleman(2));iki.listeSonunaEkle(new Eleman(9));iki.listeSonunaEkle(new Eleman(5));
    System.out.print("sona ekleme birinci liste: ");
   bir.printList(bir.bas);  System.out.print(" Sona Ekleme İkinci Liste: "); iki.printList(iki.bas);  System.out.println("");
   bir.bas=bir.TersEkle(bir.bas);iki.bas=iki.TersEkle(iki.bas);System.out.print("Sona Ekleme Birinci liste ters: ");bir.printList(bir.bas);
    System.out.print("Sona Ekleme İkinci Liste: "); iki.printList(iki.bas);System.out.println("");
    Liste uc=new Liste();
    uc.bas=uc.topla(bir.bas,iki.bas);uc.bas=uc.TersEkle(uc.bas);System.out.print("toplam sonuc: ");uc.printList(uc.bas);System.out.println("\n");
    System.out.println("Kendisine parametre olarak bir bağlı liste alan ve bağlı listenin tuttuğu sayının palindrom olup olmadığını döndüren metodu yazınız?\n");
    
    Liste pol=new Liste(); pol.listeSonunaEkle(new Eleman(7)); pol.listeSonunaEkle(new Eleman(3));pol.listeSonunaEkle(new Eleman(2));pol.listeSonunaEkle(new Eleman(3));pol.listeSonunaEkle(new Eleman(7));
    pol.polindrom(pol.bas);
   System.out.println("");// pol.polindrom1(pol.bas);
}}

