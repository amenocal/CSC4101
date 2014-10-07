import java.io.*;

class If extends Special {
 
    // TODO: Add any fields needed.
	
 
    // TODO: Add an appropriate constructor.
	public If(Node t){	}
    void print(Node t, int n, boolean p) {
    	  Printer.printIf(t, n, p);
    }
    
    Node eval(Node p, Environment env){
        Node cond, exp;
        cond = p.getCdr().getCar();
        
        if(cond.eval(env).getBool()){
            exp = p.getCdr().getCdr().getCar();
            return exp.eval(env);
        }
        else if(!p.getCdr().getCdr().getCdr().isNull()){
            exp = p.getCdr().getCdr().getCdr().getCar();
        }
        System.out.println("Error");
        return new Nil();
    }
}
