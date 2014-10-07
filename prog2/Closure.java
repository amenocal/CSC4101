class Closure extends Node {
    private Node fun;		// a lambda expression
    private Environment env;	// the environment in which the function
				// was defined

    public Closure(Node f, Environment e)	{ fun = f;  env = e; }

    public Node getFun()		{ return fun; }
    public Environment getEnv()		{ return env; }

    // TODO: The method isProcedure() should be defined in
    // class Node to return false. --DONE
    public boolean isProcedure()	{ return true; }

    public void print(int n) {
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println("#{Procedure");
	fun.print(n+3);
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println('}');
    }

    // TODO: The method apply() should be defined in class Node
    // to report an error.  It should be overwritten only in classes
    // BuiltIn and Closure.
    @Override
    public Node apply (Node args) {
	Environment env = this.getEnv();
        Node fun = getFun();
        Node symb = fun.getCar();
        fun = fun.getCdr().getCar();
        
        while(!(args == null) && !(args.getCar().isNull())){
            env.define(symb.getCar(), args.getCar()); 
            symb = symb.getCdr();
            args = args.getCdr();
        }
        return fun.eval(env);
    }
  
  public Node eval(Node p, Environment env){
          return this;
  }    
}
