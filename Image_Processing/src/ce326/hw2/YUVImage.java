
package ce326.hw2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class YUVImage {
    
    int width;
    int height;
    YUVPixel pixel[][];
    
    public YUVImage(int width, int height){
        this.width = width;
        this.height = height;
      
        
        this.pixel = new YUVPixel[this.height][this.width];
        
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){ 
                this.pixel[i][j] = new YUVPixel((short)16,(short)128,(short)128);
            }
        } 
    }
    
    public YUVImage(YUVImage copyImg){
        this(copyImg.width,copyImg.height);
    
    }
    
    public YUVImage(RGBImage RGBImg){
        this.height = RGBImg.height;
        this.width = RGBImg.width;
        
        this.pixel = new YUVPixel[this.height][this.width];
        
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                
                pixel[i][j] = new YUVPixel(RGBImg.pixel[i][j]);
            }
        }  
    }
    
    StringBuilder builder = new StringBuilder();
    
    public YUVImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException{
    
        if(file.exists()){
            
            if(!file.getName().endsWith(".yuv")){
                throw new UnsupportedFileFormatException("Unsupported File Format!");
                
            }
            else{
                try (Scanner sc = new Scanner(file)) {

                    int i=0;
                    while (sc.hasNext()) {  
                    
                        if(i==0){
                           sc.next();
                        }
                       
                        if(i==1){
                            this.width = Integer.parseInt(sc.next());
                            this.height = Integer.parseInt(sc.next());
                            this.pixel = new YUVPixel[this.height][this.width];
                            break;
                        }
                        i++;
                    }
                    int w=0,j=0;
                    while (sc.hasNext()) {
                   
                    
                        if(j==width){
                            j=0;
                            w++;
                        }
                    
                        pixel[w][j]=new YUVPixel( Short.parseShort(sc.next()),
                           Short.parseShort(sc.next()),Short.parseShort(sc.next()));
   
                        j++;
                    }
                    sc.close();   
                }
                BufferedReader br = new BufferedReader(new FileReader(file));
                    String str;
                
                try {
                    while ((str = br.readLine()) != null){
                        builder.append(str);
                    }  
                } 
                catch (IOException ex) {}
            }
        }
        else{
            throw  new FileNotFoundException("File does not exist!");
        }   
    }
    
    public String toString(){
        String S = builder.toString();
        
            return S;
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
                } catch (IOException ex) {}
            }
        
            StringBuilder data = new StringBuilder();  
            
            try (FileWriter writer = new FileWriter(file)) {
                
                writer.write("YUV3\n"+width+" "+height+"\n");
                
                for(int i=0;i<height;i++){
                    for(int j=0;j<width;j++){
                        
                    writer.write(pixel[i][j].getY()+" "+pixel[i][j].getU()+" "
                            +pixel[i][j].getV()+"\n");
                    }
                }
    
            }
            catch(IOException e){
        }
    }
    
    public void equalize(){ 
       YUVImage his_image = new YUVImage(this.width,this.height);
       
        his_image.pixel = new YUVPixel[this.height][this.width];
        
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                
              his_image.pixel[i][j] = this.pixel[i][j];  
            }
        }

        Histogram histogram = new Histogram(his_image);
        
        histogram.equalize();
        
          for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
            
            this.pixel[i][j].setY(histogram.getEqualizedLuminocity(his_image.pixel[i][j].getY()));
            }
          }
       
    }
}
