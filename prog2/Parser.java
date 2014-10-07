class Parser {
  private Scanner scanner;

  public Parser(Scanner s) { scanner = s; }
  
  public Node parseExp() {
    Token toke = scanner.getNextToken();
    return parseExp(toke);
  }
  
  public Node parseExp(Token toke){
      if (toke.getType() == Token.LPAREN){
          return parseRest();
      }
      
      if (toke.getType() == Token.QUOTE)
          return new Cons(new Ident(toke.getName()), parseExp());
       
      if (toke.getType() == Token.TRUE)
          return new BooleanLit(true);
      
      if (toke.getType() == Token.FALSE)
          return new BooleanLit(false);
      
      if (toke.getType() == Token.STRING)
          return new StrLit(toke.getStrVal());
      
      if (toke.getType() == Token.INT){
          return new IntLit(toke.getIntVal());
      }
      
      if (toke.getType() == Token.IDENT){
          return new Ident(toke.getName());
      }
      
      if (toke.getType() == Token.DOT){
          System.out.println("Error");
          return parseExp();
      }
      return parseExp();
  }
  
  public Node parseRest() {
    Token toke = scanner.getNextToken();
    return parseRest(toke);
  }
  
  protected Node parseRest(Token toke){
      if (toke.getType() == Token.QUOTE){
          System.out.println("EOF");
      }
      
      if (toke.getType() == Token.RPAREN){
          return new Nil();
      }
      
      Node a = parseExp(toke);
      //System.out.println("Parser.java, Line 59");
      toke = this.scanner.getNextToken();
      //System.out.println("Parser.java, Line 61");
      
      if(toke.getType() == Token.QUOTE)
          System.out.println("EOF");
      
      Node b;
      if (toke.getType() == Token.DOT){
          toke = this.scanner.getNextToken();
          if (toke.getType() == Token.QUOTE)
              System.out.println("EOF");
          
          b = parseExp(toke);
          
          toke = this.scanner.getNextToken();
          
          if(toke.getType() == Token.QUOTE)
              System.out.println("EOF");
          
          if (toke.getType() != Token.RPAREN){
              System.out.println("Missing RPAREN");
              b.print(2);
              System.out.println("Insert Continuing Error Message Here");
          }
          
          while ((toke.getType() != Token.QUOTE) && (toke.getType() != Token.RPAREN)){
              Node temp = parseExp(toke);
              if (temp == null)
                  return null;
              toke = this.scanner.getNextToken();
              System.out.println("test");
          }
          
          if (toke == null)
              return null;
      }
      else{
          b = parseRest(toke);
          if (b == null)
              return null;
      }
      return new Cons(a,b);
  }
};