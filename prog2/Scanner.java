// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner {
  private PushbackInputStream in;
  private byte[] buf = new byte[1000];
  //*************************************
  private Token putback = null;  
  //*************************************
  
  
  public Scanner(InputStream i) { in = new PushbackInputStream(i); }
    
  //*************************************
  public void putTokenBack(Token t){
      putback = t;
  }
  //*************************************  
  
  public Token getNextToken() {
    int bite = -1;
    
    if(putback != null){
        Token tmp = putback;
        putback = null; 
        return tmp;
    }
  
	
    // It would be more efficient if we'd maintain our own input buffer
    // and read characters out of that buffer, but reading individual
    // characters from the input stream is easier.
    try {
      bite = in.read();
    } catch (IOException e) {
      System.err.println("We fail: " + e.getMessage());
    }


    if (bite == -1)
      return null;

    char ch = (char) bite;
    
    // Skip white space (including new lines) and comments
    if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r' || ch == '\f')
        return getNextToken();

    if (ch == ';'){
        try{
            while(in.read() != '\n');
        } catch (IOException e){}
        return getNextToken();
    }
    // Special characters
    if (ch == '\'')
      return new Token(Token.QUOTE);
    else if (ch == '(')
      return new Token(Token.LPAREN);
    else if (ch == ')')
      return new Token(Token.RPAREN);
    else if (ch == '.')
      // We ignore the special identifier `...'.
      return new Token(Token.DOT);

    // Boolean constants
    else if (ch == '#') {
      try {
	bite = in.read();
      } catch (IOException e) {
	System.err.println("We fail: " + e.getMessage());
      }

      if (bite == -1) {
	System.err.println("Unexpected EOF following #");
	return null;
      }
      ch = (char) bite;
      if (ch == 't')
	return new Token(Token.TRUE);
      else if (ch == 'f')
	return new Token(Token.FALSE);
      else {
	System.err.println("Illegal character '" + (char) ch + "' following #");
	return getNextToken();
      }
    }

    // String constants
    else if (ch == '"') {
    int counter = 0;
        try{
        bite = in.read();
        while(bite != '"'){
                buf[counter] = (byte) bite;
                bite = in.read();
                ch = (char) bite;
                counter ++;
        }
        buf[counter] = (byte) ch;
    } catch (IOException e){}   
        
    byte[] string = new byte[counter];
    for (int counter2 = 0; counter2 < counter; counter2++)
        string[counter2] = buf[counter2];
    return new StrToken(new String(string));
//    return new StrToken(buf.toString());
    }

    // Integer constants
    else if (ch >= '0' && ch <= '9') {
      int i = ch - '0';
      try{
          bite = in.read();
          ch = (char) bite;
          while (ch >= '0' && ch <= '9'){
              i = i*10 + ch - '0';
              bite = in.read();
              ch = (char) bite;
          }
      } catch(IOException e) {}
      
      try{
          in.unread(ch);
          in.read();
      } catch (IOException e) {}
      return new IntToken(i);
    }

    // Identifiers
    else if ((ch >= 'a' && ch <= 'z') || (ch>= 'A') && (ch <='Z') || ch == '!' || ch == '$' || ch == '%' || ch == '&' || 
            ch == '*' || ch == '+' || ch == '-' || ch == '.' || ch == '/' || ch == ':' || ch == '<' || ch == '=' || ch == '>'||
            ch == '?' || ch == '@' || ch == '^' || ch == '_' || ch == '~'){
        try {
            int counter;
            for (counter = 0; counter < buf.length; counter++){
                    buf[counter] = (byte)ch;
                    bite = in.read();
                    ch= (char)bite;
                    
                    if ((ch >= 'a' && ch <= 'z') || (ch>= 'A') && (ch <='Z') || ch == '!' || ch == '$' || ch == '%' || ch == '&' || 
            ch == '*' || ch == '+' || ch == '-' || ch == '.' || ch == '/' || ch == ':' || ch == '<' || ch == '=' || ch == '>'||
            ch == '?' || ch == '@' || ch == '^' || ch == '_' || ch == '~')
                            continue;
                    else{
                            in.unread((int)ch);
                            break;
                    }
            }

            byte[] notifier = new byte[counter+1];	
            
            for(int counter2 = 0; counter2 <= counter; counter2++){
                    notifier[counter2] = buf[counter2]; 
            }
            
            String stringifier = new String(notifier);
            return new IdentToken(stringifier.toLowerCase());
        }
        
        catch(IOException e) { return getNextToken(); } 
    }

    // Illegal character
    else {
      System.err.println("Illegal input character '" + (char) ch + '\'');
      return getNextToken();
    }
  };
}
