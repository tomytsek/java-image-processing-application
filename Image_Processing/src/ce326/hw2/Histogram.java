
package ce326.hw2;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Histogram {
    
    int histogram[] = new int[256];
    int new_luminocity[] = new int[256];
    int height;
    int width;
    String string;
    
    public Histogram(YUVImage img){
        width = img.width;
        height = img.height;
        
      for(int i=0;i<img.height;i++){
          for(int j=0;j<img.width;j++){
              
              histogram[img.pixel[i][j].getY()]++;
          } 
      }
      
          
      
  }  
    
   public String toString(){
       
       StringBuilder builder = new StringBuilder();
       int temp=0;
       
        for(int i=0;i<236;i++){
            builder.append("\n");
            builder.append(String.format("%3d", i));
            builder.append(".(");     
            builder.append(String.format("%4d",histogram[i]));    
            builder.append(")\t"); 
            
            temp = histogram[i];
            
            while(temp>=1000){ 
              builder.append("#");
              temp = temp-1000;
            }
            
            while(temp>=100){
  
              builder.append("$");
              temp = temp-100;
            }
            
            while(temp>=10){
 
              builder.append("@");
              temp = temp-10;
            }
            
            while(temp>=1){ 
              builder.append("*");
              temp = temp-1;
            }
            
        }
        builder.append("\n");
   
       string=builder.toString();
       return string;
   }

   public void toFile(java.io.File file){
       
            if(file.exists()==true){
                try { 
                    PrintWriter writer = new PrintWriter(file);

                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException ex) {}
            }
            else{
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                }
            }
            

            try (FileWriter writer = new FileWriter(file)) {
       
                writer.write(string);
            }
            catch(IOException e){
        }
   
   
   
   }
   public void equalize(){
       
       double probability[] = new double[256];
       double prob_cumul[] = new double[256];
       
       for(int i=0;i<256;i++){
           probability[i] = (double) histogram[i]/(height*width);
        }
        
        double count=0;
 
        for(int i=0;i<256;i++){
            count=0;
            for(int j=0;j<=i;j++){
               count = probability[j]+count;
            }
            prob_cumul[i] = count;
       }
        
        for(int i=0;i<256;i++){
           new_luminocity[i] = (int) (prob_cumul[i]*235);
           
        }
   }
   public short getEqualizedLuminocity(int luminocity){
       short return_luminocity;
       
       return_luminocity = (short) new_luminocity[luminocity] ;
       return return_luminocity;
   }
}
