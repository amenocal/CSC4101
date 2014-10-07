import java.io.*;

class Let extends Special {
 
    // TODO: Add any fields needed.
	
 
    // TODO: Add an appropriate constructor.
	public Let(Node t){}
    void print(Node t, int n, boolean p) {
    	  Printer.printLet(t, n, p);
    }
    
    Node eval(Node p, Environment env){
        Node args, exp;
        Environment local = new Environment(env);
        args = p.getCdr().getCar();
        exp = p.getCdr().getCdr().getCar();
        args = eval_body(args, local);
        return exp.eval(local);
    }
    
    Node eval_body(Node p, Environment env){
        if (p == null || p.isNull()){
            Node list = new Cons(new Nil(), new Nil());
            return list;
        }
        else{
            Node arg, exp, rest;
            arg = p.getCar().getCar();
            exp = p.getCar().getCdr().getCar();
            rest = p.getCdr();
            
            if (arg.isSymbol()){
                env.define(arg, exp.eval(env));
                return eval_body(rest, env);
            }
            else if(arg.isPair())
                return arg.eval(env);
            else if (arg == null || arg.isNull())
                return new Nil();
        }
        return new Nil();
    }
}
