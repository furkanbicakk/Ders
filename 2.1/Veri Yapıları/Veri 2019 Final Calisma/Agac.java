package agac;

class dugum{
    int icerik;
    dugum sol,sag;
    public dugum(int icerik){
        this.icerik = icerik;
        sol = null;
        sag = null;
    }
}

public class Agac {
    dugum kok;
    public Agac(){
        kok = null;
    }
    public void ekle(dugum yeni){
        if (kok == null){
            kok = yeni;
        }
        else {
            dugum tmp = kok;
            dugum once = null;
            once = tmp;
            while(tmp != null){
            if (yeni.icerik < tmp.icerik){
                tmp = tmp.sol;
            }
            else {
                tmp = tmp.sag;
            }
        }
            if (yeni.icerik < once.icerik){
                once.sol = yeni;
            }
            else {
                once.sag = yeni;
            }
        }
        
        
    }

   
    public static void main(String[] args) {
        
    }
    
}
