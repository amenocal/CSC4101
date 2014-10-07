import java.io.*;
class IntLit extends Node {
  private int intVal;

  public IntLit(int i) { intVal = i; }

  public void print(int n) {
  	Printer.printIntLit(n, intVal);
  }

  public boolean isNumber() { return true; }
  
  public int getVal(){
      return intVal;
  }
  
  public Node eval(Node p, Environment env){return this;}

}
