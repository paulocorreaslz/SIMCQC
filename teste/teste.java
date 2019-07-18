package teste;

import java.io.FileInputStream;   
import java.io.IOException;   
import java.util.Properties;   
  
public class teste{   
   public static void main(String[] args) {   
  
       // Read properties file.   
       Properties properties = new Properties();   
       try {   
         properties.load(new FileInputStream("configuration"));   
         String string = properties.getProperty("irox");   
         System.out.println("Valor do campo: " + string);   
         String string2 = properties.getProperty("destilador");
         System.out.println("Valor do campo: " + string2);   
         
         
        properties.getProperty("irox");
       } catch (IOException e) {   
           System.out.println("Erro ao ler arquivo." + e.getMessage());   
       }   
   }   
}  