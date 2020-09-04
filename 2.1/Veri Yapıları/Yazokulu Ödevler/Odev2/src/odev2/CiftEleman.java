
package odev2;


public class CiftEleman {

    String isim;
    int ucret;
    CiftEleman ileri, geri;

    public CiftEleman(String isim, int ucret) {
        this.isim = isim;
        this.ucret = ucret;
        ileri= null; geri=null;
    }
}
