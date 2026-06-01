
package ce326.hw2;

public class YUVPixel {
    
    private short Y;
    private short U;
    private short V;
    
    public YUVPixel(short Y, short U, short V){
        this.Y = Y;
        this.U = U;
        this.V = V;
    }
    
    public YUVPixel(YUVPixel pixel){
        this(pixel.Y,pixel.U,pixel.V);
    }
    
    public YUVPixel(RGBPixel pixel){
        this.Y = (short) (( (  66 * pixel.getRed() + 129 * pixel.getGreen()
                +  25 * pixel.getBlue() + 128) >> 8) +  16);
        this.U = (short) (( ( -38 * pixel.getRed() -  74 * pixel.getGreen()
                + 112 * pixel.getBlue() + 128) >> 8) + 128);
        this.V = (short) (( ( 112 * pixel.getRed() -  94 * pixel.getGreen()
                -  18 *  pixel.getBlue() + 128) >> 8) + 128);
    
    }
    
    short getY(){
        
        return Y;
    }
    
    short getU(){
        
        return U;
    }
    
    short getV(){
        
        return V;
    }
    
    void setY(short Y){
        this.Y = Y;
    }
    
    void setU(short U){
        this.U = U;
    }
    
    void setV(short V){
        this.V = V;
    }
}
