import java.io.*;

class Regular extends Special {
 
    // TODO: Add any fields needed.

    
    // TODO: Add an appropriate constructor.

    public Regular(Node t){

	}
    void print(Node t, int n, boolean p) {
    	  Printer.printRegular(t, n, p);
    }
    
    Node eval(Node p, Environment env){
        Node front = p.getCar();
        Node args = evalList(p.getCdr(), env);
        
        while(front.isSymbol()){
            front = env.lookup(front);
        }
        if(front == null || front.isNull()){
            System.out.println("Error, undefined function");
            return new Nil();
        }
        if(front.isProcedure())
            return front.apply(args);
        else
            return front.eval(env).apply(args);
    }
    
    Node evalList(Node p, Environment env){
        if (p == null || p.isNull()){
            Node list = new Cons(new Nil(), new Nil());
            return list;
        }
        else{
            Node arg1 = p.getCar();
            Node rest = p.getCdr();
            
            if (arg1.isSymbol()){
                arg1 = env.lookup(arg1);
            }
            if (arg1 == null || arg1.isNull())
                return new Nil();
            Node list = new Cons(arg1.eval(env), evalList(rest, env));
            return list;
        }
    }
}
