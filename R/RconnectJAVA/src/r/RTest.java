package r;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTest {

   public static void main(String[] args) {
      RConnection rconn=null;
      try {
      rconn=new RConnection("70.12.114.130");
      
      }catch(RserveException e){
         System.out.println("R Connection Error");
            
      }
      System.out.println("ok");
      try {
         rconn.setStringEncoding("utf8");

         rconn.eval("source('C:/rproject/day08/r1.R',encoding='UTF-8')");
         int a = 100;
         int b = 87;
         double data = rconn.eval("r1("+a+","+b+")").asDouble();
         
         System.out.println(data);
      }catch(Exception e) {
         e.printStackTrace();
      }
      
   }

}