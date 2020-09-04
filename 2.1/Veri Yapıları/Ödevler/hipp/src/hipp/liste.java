
package hipp;


public class liste {
    dugum bas;
    dugum son;
    int uzunluk = 0;
    
    public liste(){
        bas=null;
        son=null;
    }
    
    void birlestir(liste[] l1){
        for (liste nesne : l1) {
            if (nesne == null) 
                break;
            if(bas == null){
                bas = nesne.bas;
                son = nesne.son;
                uzunluk = nesne.uzunluk;
            }else{
                son.ileri = nesne.bas;
                son = nesne.son;
                uzunluk = uzunluk + nesne.uzunluk;
            }
        }
        
    }
     public void ekle(int icerik) {
        dugum yeni = new dugum(icerik);
        if (bas == null && son == null) {
            bas = yeni;
            son = yeni;
            uzunluk++;
        } else {
            son.ileri = yeni;
            son = yeni;
            uzunluk++;
        }
    }
     public void goster() {
        dugum tampon = bas;
        while (tampon != null) {
            System.out.print(" "+tampon.icerik);
            tampon = tampon.ileri;
        }
    }
}
