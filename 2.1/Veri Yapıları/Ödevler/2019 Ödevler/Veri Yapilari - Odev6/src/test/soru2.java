package test;

public class soru2
{
    public boolean anagram(String str1, String str2)
    {
        ZincirHash karakterler = new ZincirHash(29);
        
        for (int i = 0; i < str1.length(); i++) 
        {
            if(str1.charAt(i) != ' ')
            {
                karakterler.ekle(str1.charAt(i));
            }
        }
        
        for (int i = 0; i < str2.length(); i++) 
        {
            if(str2.charAt(i) != ' ')
            {
                if(karakterler.ara(str2.charAt(i)))
                {
                    int x = karakterler.indisDon(str2.charAt(i));
                    karakterler.sayiDizisi[x].bastanSil();
                }
                else
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void ciftBul(int[] dizi, int toplam)
    {
        ZincirHash zh = new ZincirHash(dizi.length);
        
        for (int i = 0; i < dizi.length; i++)
        {
            zh.ekle(dizi[i]);
        }
        
        for (int i = 0; i < dizi.length; i++)
        {
            int fark = toplam - dizi[i];
            
            if(zh.ara(fark))
            {
                System.out.println(toplam + " toplamına eşit olan çift : " + dizi[i] + " , " + fark);
                
                zh.sayiDizisi[zh.indisDon(fark)].bastanSil();
                zh.sayiDizisi[zh.indisDon(dizi[i])].bastanSil();
            }
        }
    }
    
    public boolean altKume(int[] ilkDizi, int[] ikinciDizi)
    {
        ZincirHash zh = new ZincirHash(ikinciDizi.length);
        zh.ekle(ikinciDizi);
        boolean altKumedir = true;
        int indis = 0;
        
        while(indis < ilkDizi.length && altKumedir)
        {
            altKumedir = zh.ara(ilkDizi[indis]);
            indis++;
        }
        return altKumedir;
    }
}
