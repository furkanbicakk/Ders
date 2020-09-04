package veri.yapilari.odev5;

public class VeriYapilariOdev5
{
    public static void main(String[] args)
    {
        il a = new il(21, "Manisa", 38.630554, 27.422222);
        il b = new il(18, "İstanbul", 41.015137, 28.979530);
        il c = new il(44, "Mardin", 37.1883583, 40.5772285);
        il d = new il(17, "Mardin", 37.1883583, 40.5772285);
        il e = new il(20, "Mardin", 37.1883583, 40.5772285);
        il f = new il(43, "Mardin", 37.1883583, 40.5772285);
        il g = new il(15, "Mardin", 37.1883583, 40.5772285);
        il h = new il(19, "Mardin", 37.1883583, 40.5772285);
        il i = new il(40, "Mardin", 37.1883583, 40.5772285);
        il j = new il(9, "Mardin", 37.1883583, 40.5772285);
        il k = new il(3, "Mardin", 37.1883583, 40.5772285);
        il l = new il(10, "Mardin", 37.1883583, 40.5772285);
        il m = new il(1, "Mardin", 37.1883583, 40.5772285);
        il n = new il(12, "Mardin", 37.1883583, 40.5772285);
        
        agac tree = new agac();
        
        tree.ekle(a);
        tree.ekle(b);
        tree.ekle(c);
        tree.ekle(d);
        tree.ekle(e);
        tree.ekle(f);
        tree.ekle(g);
        tree.ekle(h);
        tree.ekle(i);
        tree.ekle(j);
        tree.ekle(k);
        tree.ekle(l);
        tree.ekle(m);
        tree.ekle(n);
        
        
        
        System.out.print("Preorder : ");
        tree.preorder();
        System.out.println();
        
        System.out.print("Inorder : ");
        tree.inorder();
        System.out.println();
        
        System.out.print("Postorder : ");
        tree.postorder();
        System.out.println();
        
        System.out.print("Levelorder : ");
        tree.levelorderDolas();
        System.out.println();
        
        System.out.println("İkili Ağaç Mı? : " + tree.ikiliAgac());
        System.out.println("Tam İkili Ağaç Mı? : " + tree.tamIkiliAgac());
        
        tree.kucukBul(4);
        System.out.println("Level order toplam : " + tree.levelorderTopla());;
    }
}
