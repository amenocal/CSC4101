class BuiltIn extends Node {
    private Node symbol;
    private int intVal;

    public BuiltIn(Node s)		{ symbol = s; }

    public Node getSymbol()		{ return symbol; }

    // TODO: The method isProcedure() should be defined in
    // class Node to return false. -- DONE
    public boolean isProcedure()	{ return true; }

    public void print(int n) {
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println("#{Built-in Procedure");
	symbol.print(n+3);
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println('}');
    }


    // TODO: The method apply() should be defined in class Node
    // to report an error.  It should be overwritten only in classes
    // BuiltIn and Closure.
    @Override
    public Node apply (Node args) {
        String name = args.getCar().getName();
        Node arg = args.getCdr().getCar();
        
        if(arg == null || arg.isNull())
            arg = new Nil();
        
        Node arg2 = args.getCdr().getCdr();
        if(arg2 == null || arg2.isNull())
            arg2 = new Nil();
        else
            arg2 = arg2.getCar();
        /*****************************************************/
        /*                                                   */
        /*****************************************************/
        if (name.equals("b+")){
                if (arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new IntLit(x+y);
                //return new IntLit((IntLit)arg.getVal());
            }
            else{
                System.out.println("Error in Add Function");
                return new StrLit("");
            }
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/
        else if(name.equals("b-")){
            if (arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new IntLit(x-y);
            }
            else{
                System.out.println("Error in Subtract Function");
                return new StrLit("");
            }            
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/
        else if(name.equals("b/")){
            if (arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new IntLit(x/y);
            }
            else{
                System.out.println("Error Divide Function");
                return new StrLit("");
            }            
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/
        else if(name.equals("b*")){
            if (arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new IntLit(x*y);
            }
            else{
                System.out.println("Error in Multiply Function");
                return new StrLit("");
            }            
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/
        else if(name.equals("b<")){
            if(arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new BooleanLit(x<y);
            }
            else
                System.out.println("Less-Than function");
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/
        else if(name.equals("b>")){
            if(arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new BooleanLit(x>y);
            }
            else
                System.out.println("Greater-Than function");
        }        
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if(name.equals("b=")){
            if(arg.isNumber() && arg2.isNumber()){
                int x = ((IntLit)arg).getVal();
                int y = ((IntLit)arg2).getVal();
                return new BooleanLit(x==y);
            }
            else
                System.out.println("Equals Function");
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("number?")){
            return new BooleanLit(arg.isNumber());
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("eval"))
            return arg;
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("null?"))
            return new BooleanLit(arg.isNull());
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("car")){
            if(arg.isNull())
                return arg;
            return arg.getCar();
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("cdr")){
            if(arg.isNull())
                return arg;
            return arg.getCdr();
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("cons"))
            return new Cons(arg, arg2);
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("pair?"))
            return new BooleanLit(arg.isPair());
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("symbol?"))
            return new BooleanLit(arg.isSymbol());
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("set-car!")){
            arg.setCar(arg2);
            return arg;
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("set-cdr!")){
            arg.setCdr(arg2);
            return arg;
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("procedure?"))
            return new BooleanLit(arg.isProcedure());
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("newline"))
            return new StrLit("\n");
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if (name.equals("eq?")){
            if (arg.isBoolean() && arg2.isBoolean())
                return new BooleanLit(arg.getBool() == arg2.getBool());
            else if (arg.isNumber() && arg2.isNumber())
                return new BooleanLit(((IntLit)arg).getVal() == ((IntLit)arg2).getVal());
            else if (arg.isString() && arg2.isString())
                return new BooleanLit(!(arg.getName().equals(arg2.getName())));
            else if (arg.isSymbol() && arg2.isSymbol())
                return new BooleanLit(!(arg.getName().equals(arg2.getName())));
            else if (arg.isNull() && arg2.isNull())
                return new BooleanLit(true);
            else if (arg.isPair() && arg2.isPair()){
                Node front = new Cons(arg.getCar(), new Cons(arg2.getCar(), new Nil()));
                Node rear = new Cons(arg.getCdr(), new Cons(arg2.getCdr(), new Nil()));
                return new BooleanLit(apply(front).getBool() && apply(rear).getBool());
            }
            return new BooleanLit(false); 
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else if(name.equals("write")){
            arg.print(0);
            return new StrLit("");
        }
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
//        else if(name.equals("interaction-environment"))
//            interaction_environment();
        
        else if(name.equals("apply"))
            return arg.apply(arg2);
        /*****************************************************/
        /*                                                   */
        /*****************************************************/        
        else
            System.out.println("Error :(");
        return new StrLit("");
    }
    
    public Node eval(Node p, Environment env){return this;}
}
