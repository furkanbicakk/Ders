/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev3quiz;

/**
 *
 * @author fettah polat
 */
public  class deneme {
    

    
    static int top=0;
    public static int yazdır (int i){
        if (i>4) {
        return 79;
        }
        System.out.println(i);
        top+=i;
      
        yazdır(++i);
          return top;
    }
    public static void main(String[] args) {
        System.out.println( yazdır(0));
    }
}
