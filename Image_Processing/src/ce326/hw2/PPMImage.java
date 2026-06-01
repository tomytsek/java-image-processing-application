
package ce326.hw2;

import java.io.*;   
import java.io.IOException;
import java.util.Scanner;


public class PPMImage extends RGBImage{
    
    StringBuilder builder = new StringBuilder();
    
    public PPMImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException{
        
        if(file.exists()){
            
            if(!file.getName().endsWith(".ppm")){
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
                            super.width = Integer.parseInt(sc.next());
                            super.height = Integer.parseInt(sc.next());
                            super.colordepth = Integer.parseInt(sc.next());
                            super.pixel = new RGBPixel[super.height][super.width];
                            break;
                        }
                        i++;
                    }
                    int w=0,j=0;
                    while (sc.hasNext()) {
                   
                    
                        if(j==super.width){
                            j=0;
                            w++;
                        }
                    
                        super.pixel[w][j]=new RGBPixel( Short.parseShort(sc.next()),
                           Short.parseShort(sc.next()),Short.parseShort(sc.next()));  
                        j++;
                       
                    }
                    sc.close(); 
                }
                try( BufferedReader br = new BufferedReader(new FileReader(file))){
                    String str;
                
                    while ((str = br.readLine()) != null){
                        builder.append(str);
                    }  
                }
                catch (IOException e) {
                }
            }
        }
        else{
            throw  new FileNotFoundException("File does not exist!");
        }   
}
    
    public PPMImage(RGBImage img){
       
       super.height = img.height ;
       super.width =  img.width ;
       super.colordepth =  img.colordepth ;
       super.pixel = new RGBPixel[super.height][super.width];
       
       for(int i=0;i<super.height;i++){
           for(int j=0;j<super.width;j++){
                super.pixel[i][j] = img.pixel[i][j];
           }
       }   
              
    }
    public PPMImage(YUVImage img){
       super(img);
    }
    
    public String toString(){
        
        String str=builder.toString();

        return str;
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
                writer.write("P3\n"+super.width+" "+super.height+"\n"+super.colordepth+"\n");
                
                for(int i=0;i<height;i++){
                    for(int j=0;j<width;j++){
                        
                    writer.write(super.pixel[i][j].toString()+"\n");
                    }
                }
                
                
            }
            catch(IOException e){
        }
    }
}
