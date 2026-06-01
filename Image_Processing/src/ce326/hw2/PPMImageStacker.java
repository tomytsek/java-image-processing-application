
        
package ce326.hw2;
import java.io.*;

import java.io.FileNotFoundException;
import java.util.AbstractList;
import java.util.ArrayList;

public class PPMImageStacker {
    PPMImage image;
    AbstractList<File> list;
    int [][] r  ;
    int [][] g;
    int [][] b  ;
    
    public PPMImageStacker(java.io.File dir) throws FileNotFoundException, UnsupportedFileFormatException{
        this.list = new ArrayList<>();
        if(dir.exists()==false){
            throw new FileNotFoundException("[ERROR] Directory "+dir.getName()+" does not exist!");
        }
        else{
            if(dir.isDirectory()==false){
                throw new FileNotFoundException("[ERROR] "+dir.getName()+" is not a directory!");
            }
            File[] files = dir.listFiles();
             
            for(int i=0;i<files.length;i++){
                
                if(files[i].getName().endsWith(".directory")){
                        i++;
                }
                if(files[i].isFile()){
                    if(!files[i].getName().endsWith(".ppm")){   
                        throw new UnsupportedFileFormatException(); 
                    }
                    else{
                            list.add(files[i]);
   
                    }
                }  
            }
            PPMImage first = new PPMImage(list.get(0));
            this.image = first;
            this.image.height = first.height;
            this.image.width = first.width; 
            this.image.colordepth = first.colordepth;
            
            r  = new int[image.height][image.width] ;
            g  = new int[image.height][image.width] ;
            b  = new int[image.height][image.width] ;
        }
    }

    
    public void stack() throws FileNotFoundException, UnsupportedFileFormatException{
  
        for(int i=0;i<list.size();i++){
            PPMImage help = new PPMImage(list.get(i));
            
            for(int j=0;j<image.height;j++){
                for(int w=0;w<image.width;w++){
                    
                    r[j][w]= (int) (r[j][w]+ help.getPixel(j, w).getRed());
                    g[j][w]= (int) (g[j][w]+ help.getPixel(j, w).getGreen());
                    b[j][w]= (int) (b[j][w]+ help.getPixel(j, w).getBlue());
                       
                }
            } 
        }
          
        for(int j=0;j<image.height;j++){
            for(int w=0;w<image.width;w++){
                
                r[j][w] = r[j][w]/list.size();   
                g[j][w] = g[j][w]/list.size();
                b[j][w] = b[j][w]/list.size();
               
                image.getPixel(j, w).setRed((short)r[j][w]);  
                image.getPixel(j, w).setGreen((short)g[j][w]);
                image.getPixel(j, w).setBlue((short) b[j][w]);
   
            }
        }
    }
    
    public PPMImage getStackedImage() throws FileNotFoundException, UnsupportedFileFormatException{

        return image;
    }
    
}
