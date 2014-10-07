public class Printer {
 
    public Printer(){}
    public static void ident(int n){
        if (n < 0){
            n = -n;
        }
        else
            for (int i = 0; i<n; i++)
                System.out.print(" ");
    }
    
    public static void printBoolLit(int n, int boo){
        ident(n);
        if (boo == 1)
            System.out.println("#t");
        else
            System.out.println("#f");
    }
    
    public static void printIntLit(int n, int intLit){
        ident(n);
        System.out.println(intLit);
    }
    
    public static void printStrLit(int n, String strLit){
        ident(n);       
        System.out.println("\""+strLit+"\"");
    }
    
    public static void printIdent(int n , String ident){
        ident(n);
        System.out.println(ident);
    }
    
    public static void printNil(int n, boolean boo){
        ident(n);
        if (boo)
            System.out.println(")");
        else
            System.out.println("()");
    }
    
    public static void printQuote(Node node, int n, boolean boo){
        Node en = Node.getCar(Node.getCdr(node));
        if (en != null){
            ident (n);
            System.out.println("'");
            Node.print(en, -n - 1, false);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printLambda(Node node, int n, boolean boo){
        ident(n);
        if (!boo){
            System.out.println("(LAMBDA");
            Node.print(Node.getCdr(node), n, true);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printBegin(Node node, int n, boolean boo){
        ident(n);
        if (!boo){
            System.out.println("(BEGIN");
            Node.print(Node.getCdr(node), n, true);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printIf(Node node, int n, boolean boo){
        ident(n);
        if(!boo){
            System.out.println("(IF");
            Node.print(Node.getCdr(node), n, boo);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printLet(Node node, int n, boolean boo){
        ident(n);
        if(!boo){
            System.out.println("(LET");
            Node.print(Node.getCdr(node), n, boo);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printCond(Node node, int n, boolean boo){
        ident(n);
        if(!boo){
            System.out.println("(COND");
            Node.print(Node.getCdr(node), n, boo);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printDefine(Node node, int n, boolean boo){
        ident(n);
        if(!boo){
            System.out.println("(DEFINE");
            Node.print(Node.getCdr(node), n, boo);
        }
        else{
            printRegular(node, n, boo);
        }
    }
    
    public static void printSet(Node node, int n, boolean boo){
        ident(n);
        if(!boo){
            System.out.println("(SET!");
            Node.print(Node.getCdr(node), n, boo);
        }
        else{
            printRegular(node, n, boo);
        }    
    }
    
    public static void printRegular(Node node, int n, boolean boo){
        if(!boo){
            ident(n);
            System.out.println("(");
        }
        Node.print(Node.getCar(node), n+2, false);
        Node en = Node.getCdr(node);
        if(Node.isNull(en) || Node.isPair(en)){
            Node.print(en, n, true);
        }
        else{
            ident(n);
            System.out.println(".");
            Node.print(en, n+2, false);
            ident(n);
            System.out.println(")");
        }
    }
}
