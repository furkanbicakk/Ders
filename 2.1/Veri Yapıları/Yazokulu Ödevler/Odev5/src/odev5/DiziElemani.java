package odev5;
//Numara:int|isim:String|sol:int|sag 
/*Yukarıdaki tanımlamaya göre dizi’nin her bir elemanı numara ve isim adında bir veri alanı ve sol ile sag çocukları bulunmaktadır. 
Eğer düğümün çocuğu yok ise sol ve sag değeri -1 değerini alır. 
Çocuk var ise sağ veya sol çocuğun indis numarası yer alır. Sol çocuk=2*i+1, sağ çocuk=2*i+2 şeklinde hesaplanır.*/
public class DiziElemani {

    int Numara;
    String İsim;
    int sag;
    int sol;

    public DiziElemani(int Numara, String İsim) {
        this.Numara = Numara;
        this.İsim = İsim;
        this.sag = -1;
        this.sol = -1;
    }

}
/*if (dizi[tane - 1].Numara != -1) {
            if (dizi[tane - 1].Numara != -1 && dizi[tane - 1].Numara > yeni.Numara) {
                int t = tane - 1;
                int y = 2 * t + 1;
                dizi[t].sol = y;
                dizi[y] = yeni;
            }
            if (dizi[tane - 1].Numara != -1 && dizi[tane - 1].Numara < yeni.Numara) {
                int t = tane - 1;
                int y = 2 * t + 2;
                dizi[t].sag = y;
                dizi[y] = yeni;
            }
        }*/