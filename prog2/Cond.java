import java.io.*;

class Cond extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Cond(Node t){

	}
    void print(Node t, int n, boolean p) { 
    	Printer.printCond(t, n, p);
    }
    
    Node eval(Node p, Environment env){
        Node exp = p.getCdr();
        while((!exp.getCar().getCar().eval(env).getBool()) && (!exp.isNull())){
            exp = exp.getCdr();
        }
        if(exp.isNull())
            return new Nil();
        else
            return (exp.getCar().getCdr().getCar().eval(env));
    }
}
