
package ce326.hw2;


public class RGBImage implements ce326.hw2.Image{

    public static int MAX_COLORDEPTH = 225;
    
    int width;
    int height;
    int colordepth;
    
    RGBPixel pixel [][]; 
    
     public RGBImage(){} 
   
     public RGBImage(int width, int height, int colordepth){
        
        this.width = width;
        this.height = height;
        this.colordepth = colordepth;
   
   }
    public RGBImage(RGBImage copyImg){
        this(copyImg.width,copyImg.height,copyImg.colordepth);
        
   }
   
   public RGBImage(YUVImage YUVImg){
        this.height = YUVImg.height;
        this.width = YUVImg.width;
        
        this.pixel = new RGBPixel[this.height][this.width];
        
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                
                pixel[i][j] = new RGBPixel(YUVImg.pixel[i][j]); 
            }
        }  
   } 
    
    int getWidth(){
        return width;
    }
    
    int getHeight(){
        return height;
    }
    
    int getColorDepth(){
        return colordepth;
    }
    
    RGBPixel getPixel(int row, int col){
        return pixel[row][col];
    }
    
    void setPixel(int row, int col,  RGBPixel pixel){
       this.pixel[row][col] = pixel; 
    }
    
    public void grayscale(){
        
        
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                
                
                short gray =  (short) ((pixel[i][j].getRed()*0.3)
                        +(pixel[i][j].getGreen()*0.59)+(pixel[i][j].getBlue()*0.11));
                
               pixel[i][j].setRed(gray);
               pixel[i][j].setGreen(gray);
               pixel[i][j].setBlue(gray);
            }
        }
    }
    
    public void doublesize(){
       
        RGBPixel temp[][] = new RGBPixel[height][width]; 
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                
                temp[i][j] = new RGBPixel((short)0,(short)0,(short)0);
                
                temp[i][j].setRed(pixel[i][j].getRed());
                temp[i][j].setGreen(pixel[i][j].getGreen());
                temp[i][j].setBlue(pixel[i][j].getBlue());
                
            } 
        }
        
        pixel = new RGBPixel[height*2][width*2];   
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
               pixel[2*i][2*j] = temp[i][j]; 
               pixel[2*i+1][2*j] = temp[i][j];
               pixel[2*i][2*j+1] = temp[i][j];
               pixel[2*i+1][2*j+1] = temp[i][j];
            }
        } 
        height = height*2;
        width = width*2;
    }
    
    public void halfsize(){
           
        for(int i=0;i<height/2;i++){
            for(int j=0;j<width/2;j++){
                
            short   avg_red = (short) ((pixel[i*2][j*2].getRed()+pixel[i*2+1][j*2].getRed()+
                    pixel[i*2][j*2+1].getRed()+pixel[i*2+1][j*2+1].getRed())/4);
            
            short   avg_green = (short) ((pixel[i*2][j*2].getGreen()+pixel[i*2+1][j*2].getGreen()+
                    pixel[i*2][j*2+1].getGreen()+pixel[i*2+1][j*2+1].getGreen())/4);
            
            short   avg_blue = (short) ((pixel[i*2][j*2].getBlue()+pixel[i*2+1][j*2].getBlue()+
                    pixel[i*2][j*2+1].getBlue()+pixel[i*2+1][j*2+1].getBlue())/4);
            
            pixel[i][j].setRed(avg_red);
            pixel[i][j].setGreen(avg_green);
            pixel[i][j].setBlue(avg_blue);
            
            }
        }
       
        height = height/2;
        width = width/2;
    }
    
    public void rotateClockwise(){
        
        int temp_width=0;
        int temp_height=0;
       
      RGBPixel temp[][] = new RGBPixel[height][width]; 
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                
                temp[i][j] = new RGBPixel((short)0,(short)0,(short)0);
                
                temp[i][j].setRed(pixel[i][j].getRed());
                temp[i][j].setGreen(pixel[i][j].getGreen());
                temp[i][j].setBlue(pixel[i][j].getBlue());
                
            } 
        }

        pixel = new RGBPixel[width][height];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                pixel[j][i] = temp[height-i-1][j];
            }
        }
        temp_width=width;
        temp_height = height;
        width=temp_height;
        height = temp_width;
    }
 
}
