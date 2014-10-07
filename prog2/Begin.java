import java.io.*;

class Begin extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Begin(Node t){
		
	}
    void print(Node t, int n, boolean p) {
    	  Printer.printBegin(t, n, p);

    }
    
    Node eval(Node p, Environment env){
        return null;
    }
    
    Node eval_begin(Node p, Environment env){
        return null;
    }
}
