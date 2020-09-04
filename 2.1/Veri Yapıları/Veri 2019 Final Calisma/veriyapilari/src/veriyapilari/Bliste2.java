/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapilari;

class Dugum {

    String icerik;
    Dugum ileri;

    Dugum(String icerik) {
        this.icerik = icerik;
        ileri = null;
    }
}
public class Bliste2 {

    Dugum bas;
    Dugum son;

    public Bliste2() {
        bas = null;
        son = null;
    }

    void sonaEkle(Dugum yeni) {
        if (bas == null) {
            son = yeni;
            bas = yeni;
        } else {
            son.ileri = yeni;
            son = yeni;
        }
    }
    void listele(){
        Dugum tmp=bas;
        while(tmp!=null){
            System.out.print(tmp.icerik+"-->");
            tmp=tmp.ileri;
        }
        System.out.println("");
       
    }
}
