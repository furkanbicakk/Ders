package odev5;
public class Agac {

    DiziElemani[] dizi;
    int kok;
    int tane;
    int N;
    public Agac(int N) {
        dizi = new DiziElemani[N];
        tane=0;
        kok=0;
        this.N=N;
        for(int i=0;i<N;i++){dizi[i]=new DiziElemani(-1,null);}
    
    }
        
    boolean AgacDoluMu(){
    return tane==N;
    }
    
    boolean AgacBosMu(){
    return tane==0;
    }
    
    boolean kont=false;
    void EkleAgac(DiziElemani yeni) {
        if (dizi[kok].Numara == -1) {
            dizi[kok] = yeni;
            kont=true;
            tane++;
        } 
        
     int sol,sag;
     for(int i=0;i<N;i++){
    if(!AgacDoluMu()){
    if(dizi[i].Numara!=-1 && dizi[i].Numara>yeni.Numara && kont==true){
     sol=2*i+1;
     dizi[i].sol=sol;
     if(dizi[sol].Numara==-1){
     dizi[sol]=yeni; tane++; return;}
     i=sol-1;
     }
    
    else if(dizi[i].Numara!=-1 && dizi[i].Numara<yeni.Numara && kont==true){
     sag=2*i+2;
     dizi[i].sag=sag;
     if(dizi[sag].Numara==-1 && dizi[sag].sag==-1){
     dizi[sag]=yeni; tane++; return;}
     
     i=sag-1;
     }
    }
     }
     
     }
          
  

   DiziElemani AgacAra(int gelen) {

        int sol, sag;
        for (int i = 0; i < N; i++) {

            if (dizi[i].Numara == gelen) {

                return dizi[i];
            } else if (dizi[i].Numara > gelen) {
                sol = 2 * i + 1;
                dizi[i].sol = sol;
                if (dizi[sol].Numara == gelen) {
                    return dizi[sol];
                }
                i = sol - 1;
            } else if (dizi[i].Numara < gelen) {
                sag = 2 * i + 2;
                dizi[i].sag = sag;
                if (dizi[sag].Numara == gelen) {
                    return dizi[sag];
                }

                i = sag - 1;
            }
        }

        return null;
    }
 
    
      
      
    
 
    public void inOrder() {
        inorder(0, dizi);

    }

     void inorder(int node, DiziElemani tree[]) {
        if (node < tree.length) {
            if (tree[node] != null) {
                inorder((node + 1) * 2 - 1, tree);
                System.out.println(dizi[node].Numara + " " + dizi[node].İsim);
                inorder((node + 1) * (2 + 1) - 1, tree);
            }
        }

    }
    
     
     void inordor2(int a){
     if(a==-1) return;
     
     inordor2(dizi[a].sol);
     
       System.out.println(""+dizi[a].Numara);
      inordor2(dizi[a].sag);
     
     
     }
    
     void enkucuk(int a){
     
     
     
     }
    public void PreOrder() {

        preorder(0, dizi);

    }

    protected void preorder(int node, DiziElemani tree[]) {
        if (node < tree.length) {
            if (tree[node] != null) {
                System.out.println(dizi[node].Numara + " " + dizi[node].İsim);
                preorder((node + 1) * 2 - 1, tree);
                preorder((node + 1) * (2 + 1) - 1, tree);
            }
        }

    }

    public void PostOrder() {

        postorder(0, dizi);

    }

    protected void postorder(int node, DiziElemani tree[]) {
        if (node < tree.length) {
            if (tree[node] != null) {
                postorder((node + 1) * 2 - 1, tree);
                postorder((node + 1) * (2 + 1) - 1, tree);
                System.out.println(dizi[node].Numara + " " + dizi[node].İsim);

            }
        }

    }
  
  
    
    void LevelOrder() {
        System.out.println("tane: " + tane);
        for (int i = 0; i < N; i++) {
            System.out.println(i + ". indis: " + dizi[i].Numara + " " + dizi[i].İsim);
        }
    }
    
   

    
    public static void main(String[] args) {
        Agac ilk=new Agac(16);
    
        ilk.EkleAgac(new DiziElemani(50,"fettah_can"));
        ilk.EkleAgac(new DiziElemani(19,"samet_aybaba"));
        ilk.EkleAgac(new DiziElemani(70,"garip_kont"));
        ilk.EkleAgac(new DiziElemani(15,"kara kedi"));
        ilk.EkleAgac(new DiziElemani(22,"tucnc"));
        ilk.EkleAgac(new DiziElemani(60,"maykıl"));
        ilk.EkleAgac(new DiziElemani(75,"jackson"));
        ilk.EkleAgac(new DiziElemani(11,"tacer"));
        ilk.EkleAgac(new DiziElemani(16,"maykıl"));
        ilk.EkleAgac(new DiziElemani(21,"jackson"));
        ilk.EkleAgac(new DiziElemani(23,"tuncer"));
        ilk.EkleAgac(new DiziElemani(55,"maykıl"));
        ilk.EkleAgac(new DiziElemani(61,"jackson"));
        ilk.EkleAgac(new DiziElemani(73,"maykıl"));
        ilk.EkleAgac(new DiziElemani(80,"sefkat"));
        ilk.EkleAgac(new DiziElemani(9,"bekir"));
        
      //  DiziElemani aranan=ilk.AgacAra(75);
      // System.out.println("Aranan: "+"Numara: "+aranan.Numara+" isim: "+aranan.İsim);
       ilk.inordor2(0);
                
       // ilk.LevelOrder();
        
       
       //ilk.inOrder();
       //ilk.PreOrder();
      //ilk.PostOrder();
      
   
   /*MaxHeap ref=new MaxHeap(16);
       ref.AgacMaxHeapYerlestir(ilk.dizi);
       ref.LeverOrder();
      //ref.Level();*/
        
   
     
    }
    
    
}
              /*  12
             11    13
               12 14  15
                        16*/

/* boolean tut=false; int al=0;
    void EkleAgac(DiziElemani yeni) {
      
        
          
        if(tut==true){ int t = al;
        if(dizi[t].sol==-1 && dizi[t].Numara > yeni.Numara){ 
             tane++;
                int y = 2 * t + 1;
                dizi[t].sol = y;
                dizi[y] = yeni;}
        else if(dizi[t].sag==-1 && dizi[t].Numara < yeni.Numara){
               tane++;
                int y = 2 * t + 2;
                dizi[t].sag = y;
                dizi[y] = yeni;
        }else  tut=false; 
        
        
        }
        
            if (dizi[tane].Numara == -1 && tut==false) {
            tane++;
            dizi[tane-1] = yeni;
            al=tane-1;
            tut=kontrol(al);
        }    
            
        }

    boolean kontrol(int no) {
      return  (dizi[no].sol==-1 || dizi[no].sag==-1);

    } */