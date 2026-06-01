
package ce326.hw2;


public class RGBPixel {
    
    byte Bred;
    byte Bgreen;
    byte Bblue;
    
    public RGBPixel(short red, short green, short blue){
        this.Bred =  (byte) (red-128);
        this.Bblue = (byte) (blue-128);
        this.Bgreen = (byte) (green-128);
    } 
    public RGBPixel(RGBPixel pixel){
        this((short)(pixel.Bred+128),(short)(pixel.Bgreen+128),(short)(pixel.Bblue+128));
    }
    public RGBPixel(YUVPixel pixel){
        short C = (short) (pixel.getY()-16);
        short D = (short) (pixel.getU()-128);
        short E = (short) (pixel.getV()-128);
        
        Bred = (byte) (clip(( 298 * C + 409 * E + 128) >> 8)-128);
        Bgreen = (byte) (clip(( 298 * C - 100 * D - 208 * E + 128) >> 8)-128);
        Bblue = (byte) (clip(( 298 * C + 516 * D+ 128) >> 8)-128);
        
    }

    RGBPixel(RGBPixel[][] pixel) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
     public short getRed(){
      
        short red = this.Bred ;     
        red = (short)(red + 128);  
        return red;
    
    }
  
    public short getGreen(){
      
        short green = this.Bgreen;
        green = (short)(green + 128);  
        return green;
    
    }
  
    public short getBlue(){
      
        short blue = this.Bblue;
        blue = (short) (blue + 128);
        return blue;
    
    }
  
    public void setRed(short red){
      
        this.Bred = (byte) (red-128) ;   
    }
  
    public void setGreen(short green){
      
        this.Bgreen = (byte) (green-128) ;
    }
  
    public void setBlue(short blue){
      
        this.Bblue = (byte) (blue-128);
    }
    
    public int getRGB(){
        int RGB_integer=0;
        byte[] byte_array = new byte[]{0,this.Bred,this.Bgreen,this.Bblue};
        
        for (byte b : byte_array) {
            RGB_integer = (RGB_integer << 8) + (b & 0xFF)+128;
        }        
        return RGB_integer;
    }
  
    public void setRGB(int RGB_integer){      
        byte[] byte_array = new byte[Integer.BYTES];
        int length = byte_array.length;
     
        for (int i = 0; i < length; i++) {
            byte_array[length - i - 1] = (byte) (RGB_integer & 0xFF);
            RGB_integer >>= 8;
        }
        this.Bblue = byte_array[3] ;
        this.Bgreen = byte_array[2];
        this.Bred = byte_array[1];
    }
    
    public final void setRGB(short red, short green, short blue){
        setRed(red);
        setBlue(blue);
        setGreen(green);
    }
  
 
    public String toString(){
        short blue = Bblue;
        blue = (short)(blue + 128);
        
        short green = Bgreen;
        green = (short)(green + 128);
        
        short red = Bred ;     
        red = (short)(red + 128);
        
      return red+" "+green+" "+blue;
    }

    private int clip(int i) {
        if(i<0){
            i = 0;
        }
        if(i>255){
            i=255;
        }
        return i ;
    }
}
