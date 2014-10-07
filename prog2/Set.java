import java.io.*;

class Set extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Set(Node t){

	}
	
    void print(Node t, int n, boolean p) {
    	Printer.printSet(t, n, p);
    }
    
    Node eval(Node p, Environment env){
        Node id = p.getCdr().getCar();
        Node exp = p.getCdr().getCdr().getCar();
        env.define(id, exp.eval(env));
        return new StrLit("");
    }
}
