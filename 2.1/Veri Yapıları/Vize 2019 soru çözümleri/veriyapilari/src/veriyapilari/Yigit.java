package veriyapilari;
class Yigit {   
    int top,N;
    int []a;
    boolean isEmpty() {
        return top ==-1;
    }
    boolean full(){
        return top==N-1;
    }
    Yigit(int N) {
        top = -1;
         a = new int[N]; // Maximum size of Stack
        this.N=N;
    }

    void push(int x) {
        if (!full()) {
            top++;
            a[top] = x;            
        }
    }

    int pop() {
        if (!isEmpty()) {
            top=top-1;            
            int x = a[top+1];
            return x;
        }
        return -1;
    }
    int peek(){
        return a[top];
        
    }
}
