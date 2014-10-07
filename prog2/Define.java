import java.io.*;

class Define extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor. 
	public Define(Node t){

	}
    void print(Node t, int n, boolean p) {
    	  Printer.printDefine(t, n, p);
    	  }
    
    Node eval(Node p, Environment env){
        Node id = p.getCdr().getCar();
        Node val = p.getCdr().getCdr().getCar();
        
        if (id.isSymbol())
            env.define(id, val);
        else{
            Closure func = new Closure(new Cons(p.getCdr().getCar().getCdr(), p.getCdr().getCdr()),env);
            env.define(id.getCar(),func);
        }
        return new StrLit("no values");
    }
}
