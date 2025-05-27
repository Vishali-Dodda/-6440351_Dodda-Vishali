public class SimpleMath {
    public int add(int a, int b) {
        return a + b;
    }
}

Compile:
javac SimpleMath.java
Inspect the bytecode with javap:
javap -c SimpleMath

Output :
Compiled from "SimpleMath.java"
public class SimpleMath {
  public SimpleMath();
    Code:
       0: aload_0
       1: invokespecial #1 
       4: return

  public int add(int, int);
    Code:
       0: iload_1       
       1: iload_2       
       2: iadd          
       3: ireturn      
}