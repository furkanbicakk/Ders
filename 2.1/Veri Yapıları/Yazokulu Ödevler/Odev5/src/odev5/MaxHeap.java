
package odev5;

public class MaxHeap {
     DiziElemani[] diziMax;
    int tane;
    
    public MaxHeap(int N){
        
        diziMax=new DiziElemani[N];
        tane=0;
        
    }
    
    void yukaricik(int n){
        int ata=(n-1)/2;
        
        
        while (ata>=0 && diziMax[ata].Numara< diziMax[n].Numara){
            DiziElemani ara=diziMax[ata];
            diziMax[ata]=diziMax[n];
            diziMax[n]=ara;
            n=ata;
            ata=(n-1)/2;   
    }}
    
    
    DiziElemani sil(){
        
        DiziElemani tmp ;
        tmp=diziMax[0];
        diziMax[0]=diziMax[tane-1];
        asagin(0);
        tane--;
        return tmp;    
        
    }
     void yerDegistir(int k,int yeni){
         int eski=diziMax[k].Numara;
         diziMax[k].Numara=yeni;
         if(eski>yeni)
             asagin(k);
         else
             yukaricik(k);
   
}
    
   void asagin(int no){
       int sol=2*no+1;
       int sag=2*no+2;
       
       while((sol< tane && diziMax[no].Numara<diziMax[sol].Numara) || (sag< tane && diziMax[no].Numara< diziMax[sag].Numara)){
           if(sag>=tane || diziMax[sol].Numara>diziMax[sag].Numara){
               
              /* DiziElemani ara=diziMax[sol];
               diziMax[no]=diziMax[sol];
               diziMax[no]=ara;
               no=sol;*/
               yerDegistir(no, sol);
              no=sol;
           }
           
           else{
               
          /*   DiziElemani  ara=diziMax[sag];
             diziMax[no]=diziMax[sag];
             diziMax[no]=ara;
             no=sag;    */
               yerDegistir(no, sag);
              no=sag;
           }
               
               sol=2*no+1;
               sag=2*no+2;
       
   }
        
    }
  
   
   int arama (int ara){
       
       int i;
       for(i=0; i<tane; i++)
           if(diziMax[i].Numara==ara) 
               return i;
        return -1;
   }
    
    boolean bosmu(){
        return tane==0;
    }
    
    void  Ekle(DiziElemani yeni){
        tane++;
        diziMax[tane-1]=yeni;
        yukaricik(tane-1);
        
    } 
       void AgacMaxHeapYerlestir(DiziElemani tree[]) {
        
        for (int i = 0; i <diziMax.length; i++) {
            Ekle(tree[i]);
        
        }
    }
    
       void LeverOrder(){
       System.out.println("tane: " + tane);
         for (int i = 0; i <diziMax.length; i++) {
         
             System.out.println(i + ". indis: " + diziMax[i].Numara + " " + diziMax[i].İsim);
         
         }
       }
       
       void Level(){
       for (int i = 0; i <tane; i++) {
       DiziElemani a=sil();
           System.out.println(a.Numara+" "+a.İsim);
       }
       
       }
}
