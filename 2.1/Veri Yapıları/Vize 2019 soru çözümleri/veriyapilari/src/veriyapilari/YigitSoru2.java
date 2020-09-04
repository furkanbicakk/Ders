
package veriyapilari;
public class YigitSoru2 {
    public static Yigit siralibirlestir(Yigit y1,Yigit y2){
        Yigit y3=new Yigit(20);
        while(!y1.isEmpty()&& !y2.isEmpty()){
            if(y1.peek()==y2.peek()){
                y3.push(y1.pop());
                y2.pop();
            }
            else if(y1.peek()<y2.peek())
                y3.push(y1.pop());
            else y3.push(y2.pop());
          if(y1.isEmpty()) {
              while(!y2.isEmpty()) 
                  y3.push(y2.pop());
          }
          if(y2.isEmpty()) {
              while(!y1.isEmpty()) 
                  y3.push(y1.pop());
          }
        }
        return y3;        
    }
    public static void main(String[] args) {
        Yigit y1=new Yigit(10);
        Yigit y2=new Yigit(8);
        y1.push(10);
        y1.push(8);
        y1.push(5);
        y2.push(9);
        y2.push(7);
        y2.push(5);
        Yigit y3=siralibirlestir(y1, y2);
        while(!y3.isEmpty())
            System.out.println(y3.pop());
    }
}
