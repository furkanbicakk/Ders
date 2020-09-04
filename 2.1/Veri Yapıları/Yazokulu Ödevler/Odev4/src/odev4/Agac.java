
package odev4;


public class Agac {

    Ogrenci kok;

    Agac() {
        kok = null;
    }

  //-----------------------------------------------------------------------------------------------  

    void agacaEkle(Ogrenci yeni) {//aynı numaralı öğrenci varsa ağaçta eklemiyor
      Ogrenci tmp=agacAra1(yeni.no);
      if(tmp==null){
        Ogrenci once = null;
        Ogrenci x = kok;
     
        while (x != null) {
            once = x;
            if (yeni.no < x.no ) {
                x = x.sol;
            } else {
                x = x.sag;
            }
        }
        
        if (once == null) {
            kok = yeni;
        } else if (yeni.no < once.no ) {
            once.sol = yeni;
        } else if(yeni.no > once.no ){
            once.sag = yeni;
        }
      }else System.out.println("Agacta var");
    }
    //***************************************************************************************************************

    void inorder() {
        inorder(kok);
    }
    void inorder(Ogrenci root) {
        if (root != null) {
            inorder(root.sol);//sol,kök,sağ
            System.out.println("Numarası: "+ root.no+ " İsim: " + root.isim + " Vizesi: " + root.vizenotu + " Finali: "+root.finalnotu);
            inorder(root.sag);
        }
    }
//**********************************************************************************************
    void preorder() {
        preorderRec(kok);
    }

    void preorderRec(Ogrenci root) {
        if (root != null) {
            System.out.println("Numarası: "+ root.no+ " İsim: " + root.isim + " Vizesi: " + root.vizenotu + " Finali: "+root.finalnotu);
            preorderRec(root.sol);
            preorderRec(root.sag);
        }
    }
//**************************----------------------------------------------**************************************
    void postOrder() {
        postOrderRec(kok);
    }

    void postOrderRec(Ogrenci root) {
        if (root != null) {
            postOrderRec(root.sol);
            postOrderRec(root.sag);
            System.out.println("Numarası: "+ root.no+ " İsim: " + root.isim + " Vizesi: " + root.vizenotu + " Finali: "+root.finalnotu);
        }
    }
//********************************************************************************************************
       Ogrenci agacAra1(int eleman) {
        Ogrenci d;
        d = kok;
        while (d != null) {
            if (d.no == eleman) {
             
             return d; 
            } else if (d.no > eleman) {
                d = d.sol;
            } else {
                d = d.sag;
            }
        }
   return null;
    }
    //**********************************************************************************************************
    Ogrenci agacAra(int eleman) {
        Ogrenci d;
        d = kok;
        while (d != null) {
            if (d.no == eleman) {
               System.out.println("Numarası: " + d.no + " İsim: " + d.isim + " Vizesi: " + d.vizenotu + " Finali: " + d.finalnotu);
             return d; 
            } else if (d.no > eleman) {
                d = d.sol;
            } else {
                d = d.sag;
            }
        }
      if(d==null){System.out.println("Bulunmadı");}  return null;
    }
//**********************************************************************************************************
     public void min() {
         Ogrenci minimum=min(kok);
        System.out.println("Minimum numaralı Ogrencinin: Adi:"+minimum.isim+" Numarası: "+minimum.no+" Vize Notu: "+minimum.vizenotu+" Final Notu: "+minimum.finalnotu);
    }
   

    private Ogrenci min(Ogrenci root) {
        Ogrenci sonuc = root;
        while (sonuc.sol!= null) {
            sonuc = sonuc.sol;
        }
        return sonuc;
    }
  //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
    public void max() {
        Ogrenci maxi = max(kok);
        System.out.println("Maximum numaralı Ogrencinin: Adi:" + maxi.isim + " Numarası: " + maxi.no + " Vize Notu: " + maxi.vizenotu + " Final Notu: " + maxi.finalnotu);
    }

    private Ogrenci max(Ogrenci root) {
        Ogrenci sonuc = root;
        while (sonuc.sag != null) {
            sonuc = sonuc.sag;
        }
        return sonuc;
    }
  //----------------------------------------------------------------------------------------------------------  
    public boolean delete(int Silinecek) {//parent=ebeveyn tmp=şimdiki   //recursive değil
        Ogrenci parent= kok;
        Ogrenci tmp = kok;
        boolean isLeftChild = false;
        while (tmp.no != Silinecek) {//silinecek eleman simdikinin numarasına eşit değilse döngüye devam et
            parent = tmp; //şimdiki düğümü ebeveyn de tut kopyasını
            if (tmp.no > Silinecek) {//şimdikinin numarası büyük ise silinecek olan değerden
                isLeftChild = true; //eyer if sartı doğruysa silinecek olan ogrenci şimdikinin solundadır, o zaman sol çocuktur deyip true diyoruz
                tmp = tmp.sol;//yukarda zaten şimdiki ebeveynde tutuluyor, simdiki ye kendisinin solunu atıp şimdi sol çocuğu gösterdi
            } else {//değilse silinen tmp ten büyük olduğu için sağ cocuk olur
                isLeftChild = false;//sol çocuk değil
                tmp = tmp.sag;//şimdiki artık sağ çocuk oldu
            }
            if (tmp == null) { //eyer şimdiki boş ise yani kok boş ise agaç yoktur
                return false;
            }
        }
        //eğer buradaysam, düğümü bulduk
        //Eğer silinecek düğümün hiç çocuğu yoksa
        if (tmp.sol == null && tmp.sag == null) {//şimdiki elemanın sağ ve solu boş değilse şart true
            if (tmp == kok) {//yani yukardaki hiç bir şart sağlanmamış, zaten basta currente=kok demiştim 
                kok = null;//demekki currentin ne sağına gitmiş nede soluna o zaman ağaç botur demektir.
            }
            if (isLeftChild == true) {//eyer sol cocuk ise 
                parent.sol = null;// ebeveyn nin sol çocuğunu sil
            } else {
                parent.sag= null;//değil ise ebeveyn nin sag cocugunu sil
            } 
        }
        
//Eğer silinecek düğümün sadece bir çocuğu varsa
        else if (tmp.sag == null) {
            if (tmp == kok) {
                kok = tmp.sol;
            } else if (isLeftChild) {
                parent.sol = tmp.sol;
            } else {
                parent.sol= tmp.sag;
            }
        } else if (tmp.sol == null) {
            if (tmp == kok) {
                kok = tmp.sag;
            } else if (isLeftChild) {
                parent.sol = tmp.sag;
            } else {
                parent.sag= tmp.sag;
            }
        } else if (tmp.sol != null && tmp.sag != null) {

            //şimdi sağ alt ağacın minimum elemanını bulduk
            Ogrenci successor = getSuccessor(tmp);
            if (tmp == kok) {
                kok = successor;
            } else if (isLeftChild) {
                parent.sol = successor;
            } else {
                parent.sag = successor;
            }
            successor.sol = tmp.sol;
        }
        return true;
    }
//***************************************************************************************************	
	public Ogrenci getSuccessor(Ogrenci deleleNode){ //silenenin yerine geçecek 
		Ogrenci successsor =null;
		Ogrenci successsorParent =null;
		Ogrenci current = deleleNode.sag;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.sol;
		}
		//halefi doğru çocuğa sahip olup olmadığını kontrol edin, emin çocuğa sahip olamaz
		// eğer doğru çocuğa sahipse, onu halefinin Ebeveynine ekleyin.
                //halefi Ebeveyn
		if(successsor!=deleleNode.sag){
			successsorParent.sol = successsor.sag;
			successsor.sag = deleleNode.sag;
		}
		return successsor;}
 //************************************************************************************************       
        int height(Ogrenci root){ 
            if (root == null) return 0; 
            else{/* her bir alt ağacın yüksekliğini hesaplama */
            int lheight = height(root.sol); 
            int rheight = height(root.sag); 
            /* büyük olanı kullan */
            if (lheight > rheight) 
                 return(lheight+1); 
            else return(rheight+1);}}
//*****************************************************************************************
        void printLevelOrder(){ //seviye seviye dolaşmadır.
            Ogrenci current=kok;
            int h = height(current); 
            for (int i=1; i<=h; i++){ 
            printGivenLevel(current, i);
            System.out.println(" seviye "+i);}} 
      
            void printGivenLevel(Ogrenci root, int level){
             if (root == null) 
             return; 
            if (level == 1){ 
          // nesne.Ekle(root.no,root.ad,root.notu); //kuyruga ekleme kısmı
               System.out.print(root.no+" "); }
            else if (level > 1){ 
             printGivenLevel(root.sol, level-1); 
             printGivenLevel(root.sag, level-1);
            
            }}
 //************************************************************************************************    
      /*  void printLevelOrder1() {//kuruk ile seviye seviye yazdırma
        Queue<Node> queue = new LinkedList<Ogrenci>();
        queue.add(root);
        while (!queue.isEmpty()) {

            Node tempNode = queue.poll();
            System.out.print(tempNode.data + " ");

   
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

           
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
            */
            
  //********************************************************************************************          
    void deleteKey(int key) {
        kok = deleteRec(kok, key);
    }

    /* A recursive function to insert a new key in BST */
    Ogrenci deleteRec(Ogrenci root, int key) {
        /* Base Case: If the tree is empty */
        if (root == null) {
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.no) {
            root.sol = deleteRec(root.sag, key);
        } else if (key > root.no) {
            root.sag = deleteRec(root.sag, key);
        } // if key is same as root's key, then This is the node 
        // to be deleted 
        else {
            // node with only one child or no child 
            if (root.sol == null) {
                return root.sag;
            } else if (root.sag == null) {
                return root.sol;
            }

            // node with two children: Get the inorder successor (smallest 
            // in the right subtree) 
            root.no = minValue(root.sag);

            // Delete the inorder successor 
            root.sag = deleteRec(root.sag, root.no);
        }

        return root;
    }
//----------------------------------------------------------------------------------------    
    int minValue(Ogrenci root) {
        int minv = root.no;
        while (root.sol != null) {
            minv = root.sol.no;
            root = root.sol;
        }
        return minv;
    }
//------------------------+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++      
    boolean isBalanced(Ogrenci node) {//Dengeli olup olmadığını kontrol etme
        int lh;
        /* for height of left subtree */

        int rh;
        /* for height of right subtree */

 /* If tree is empty then return true */
        if (node == null) {
            return true;
        }

        /* Get the height of left and right sub trees */
        lh = height(node.sol);
        rh = height(node.sag);

        if (Math.abs(lh - rh) <= 1 && isBalanced(node.sol) && isBalanced(node.sag)) {
            return true;
        }

        /* If we reach here then tree is not height-balanced */
        return false;
    }
   //******************************************************************************* 
    void display(){
                   System.out.println("       50");
                   System.out.println("   30      70");
                   System.out.println("20   40 60    80");
       }
 //*******************************************************************************************    
    void printKDistant(Ogrenci node, int k) {//hangi seviyede elemanları istiyorsan yazdırma
        if (node == null) {
            return;
        }
        if (k == 1) {
           System.out.println("Ogrencinin: Adi:"+node.isim+" Numarası: "+node.no+" Vize Notu: "+node.vizenotu+" Final Notu: "+node.finalnotu);
            return;
        } else {
            printKDistant(node.sol, k - 1);
            printKDistant(node.sag, k - 1);
        }
    }
   //---------------------------------------------------------------------------------- 
    boolean isBST() {
        return isBSTUtil(kok, Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
  
    /* Returns true if the given tree is a BST and its 
      values are >= min and <= max. */
    boolean isBSTUtil(Ogrenci node, int min, int max) 
    { 
        /* an empty tree is BST */
        if (node == null) 
            return true; 
  
        /* false if this node violates the min/max constraints */
        if (node.no < min || node.no > max) 
            return false; 
  
        /* otherwise check the subtrees recursively 
        tightening the min/max constraints */
        // Allow only distinct values 
        return (isBSTUtil(node.sol, min, node.no-1) && 
                isBSTUtil(node.sag, node.no+1, max)); 
    } 
   //****************************************************************************************** 
    int getLeafCount() {//yaprak sayısını bulma
        return getLeafCount(kok);
    }

    int getLeafCount(Ogrenci node) {
        if (node == null) {
            return 0;
        }
        if (node.sol == null && node.sag == null) {
            return 1;
        } else {
            return getLeafCount(node.sol) + getLeafCount(node.sag);
        }
    }
//************************************************************************************************
    static int sum=0;

     void leafSum(Ogrenci root) {//yaprakalrın toplamı
        if (root == null) {
            return;
        }

        // add root data to sum if  
        // root is a leaf node 
        if (root.sol == null && root.sag == null) {
            sum += root.no;
        }

        // propagate recursively in left 
        // and right subtree 
        leafSum(root.sol);
        leafSum(root.sag);
    }
//************************************************************************************
     
    public static void main(String[] args) {
        Agac tree = new Agac();

        /* BST
           50
        /     \
       30      70
      /  \    /  \
    20   40  60   80 */
        tree.agacaEkle(new Ogrenci(50, "fettah", 50, 60));
        tree.agacaEkle(new Ogrenci(30, "ahmet", 70, 10));
        tree.agacaEkle(new Ogrenci(20, "veli", 30, 77));
        tree.agacaEkle(new Ogrenci(40, "şerafettin", 50, 80));
        tree.agacaEkle(new Ogrenci(70, "cübbeli", 20, 60));
        tree.agacaEkle(new Ogrenci(60, "şemsettin", 40, 20));
        tree.agacaEkle(new Ogrenci(80, "maykıl", 90, 50));
        tree.agacaEkle(new Ogrenci(50, "maykıl", 90, 50));
        tree.agacaEkle(new Ogrenci(80, "maykıl", 90, 50));
        tree.agacaEkle(new Ogrenci(110, "maykıl", 90, 50));
      
     
        //tree.display();

         // tree.inorder();//sol,kök,sağ
        //tree.preorder();//kök,sol,sağ
        //tree.postOrder();//sol,sağ,kök
        //tree.agacAra(20);
        //tree.min();
        //tree.max();
       // tree.delete(20); tree.inorder();
         tree.printLevelOrder(); // seviye seviye gezme direk gösterir
       //tree.printKDistant(tree.kok,3);//hangi seviyeyi yazdırmak istersen
       
    /*
       
       
         if(tree.isBalanced(tree.kok)) 
            System.out.println("Agac dengeli"); 
        else
            System.out.println("Agac dengel değil");*/
         
         
        // tree.deleteKey(80);    tree.inorder();//sol,kök,sağ //öz yenilemeli silme
        
       // System.out.println("Ağacın yaprak sayısı : "+ tree.getLeafCount());

     
       // tree.leafSum(tree.kok); System.out.println(sum); 
    }
}
