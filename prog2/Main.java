// Main.java -- the main program

import java.io.*;
import java.util.*;

public class Main {
    // Array of token names used for debugging the scanner
    private static final String TokenName[] = {
	"QUOTE",			// '
	"LPAREN",			// (
	"RPAREN",			// )
	"DOT",				// .
	"TRUE",				// #t
	"FALSE",			// #f
	"INT",				// integer constant
	"STRING",			// string constant
	"IDENT"				// identifier
    };

    public static void main (String argv[]) {
    	
	// create scanner that reads from standard input
	Scanner scanner = new Scanner(System.in);


	if (argv.length > 1) {
	    System.err.println("Usage: java Main [-d]");
	    System.exit(1);
	}
	
	// if commandline option -d is provided, debug the scanner
	if (argv.length == 1 && argv[0].equals("-d")) {
	    // debug scanner
	    Token tok = scanner.getNextToken();
	    while (tok != null) {
		int tt = tok.getType();
		System.out.print(TokenName[tt]);
		if (tt == Token.INT)
		    System.out.println(", intVal = " + tok.getIntVal());
		else if (tt == Token.STRING)
		    System.out.println(", strVal = " + tok.getStrVal());
		else if (tt == Token.IDENT)
		    System.out.println(", name = " + tok.getName());
		else
		    System.out.println();

		tok = scanner.getNextToken();
	    }
	    System.exit(0);
	}
	
	// Create parser
	Parser parser = new Parser(scanner);
	Node root;
	
	// Parse and pretty-print each input expression
	
          Environment builtin_env = new Environment();
          Ident id;
          id = new Ident("b+");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("car");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("symbol?");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("number?");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("b/");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("b-");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("b*");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("b=");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("b<");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("cdr");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("cons");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("set-car!");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("set-cdr!");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("null?");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("pair?");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("eq?");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("procedure?");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("read");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("write");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("display");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("newline");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("eval");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("apply");
          builtin_env.define(id, new BuiltIn(id));
          id = new Ident("interaction-environment");
          builtin_env.define(id, new BuiltIn(id));
          Environment env = new Environment(builtin_env);
          BuiltIn x = new BuiltIn(env);        
        
        System.out.print("Welcome...\n>");
	root = parser.parseExp();
	int i = 0;
        while (root != null) {
	    //root.print(0); --this was the old statement..it just printed
        x.apply(root).eval(env).print(i);
            //root.eval(root).print(2);
		System.out.print(">");
	    root = parser.parseExp();  
	}
	System.exit(0);
    }
    	
}
