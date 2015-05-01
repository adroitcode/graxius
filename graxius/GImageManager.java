
package graxius;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GImageManager {
    
    
    ArrayList<GImage> imageList = new ArrayList<GImage>();
    ArrayList<GGif> gifList = new ArrayList<GGif>();
    
//    BufferedImage shipOneSelectImg;
//    BufferedImage shipTwoSelectImg;
//    BufferedImage shipThreeSelectImg;
//    BufferedImage shipFourSelectImg;
//    BufferedImage fireLaserImg;
//    BufferedImage shockLaserImg;
//    BufferedImage iceLaserImg;
//    BufferedImage bgImg;
//    BufferedImage iceShipImg;
//    BufferedImage [] shipFrames = new BufferedImage[72];
//    File iceShipFile;
    
//    public GImageManager(URL passedShipOneSelectURL,URL passedShipTwoSelectURL,URL passedShipThreeSelectURL,
//            URL passedShipFourSelectImgURL, URL passedFireLaserURL,URL passedShockLaserURL,URL passedIceLaserURL,URL passedBGURL,URL passedIceShipURL){
    public GImageManager(){
//        iceShipFile = new File(passedIceShipURL.toString(),"flying.gif");
//        try{
//            
//            shipOneSelectImg = ImageIO.read(passedShipOneSelectURL);
//            shipTwoSelectImg = ImageIO.read(passedShipTwoSelectURL);
//            shipThreeSelectImg = ImageIO.read(passedShipThreeSelectURL);
//            shipFourSelectImg = ImageIO.read(passedShipFourSelectImgURL);
//            fireLaserImg = ImageIO.read(passedFireLaserURL);
//            shockLaserImg = ImageIO.read(passedShockLaserURL);
//            iceLaserImg = ImageIO.read(passedIceLaserURL);
//            bgImg = ImageIO.read(passedBGURL);
//            iceShipImg = ImageIO.read(passedIceShipURL);
//            frames(passedIceShipURL);
//        }catch(Exception e){
//            System.out.println("Error while loading images");
//        } 
        
    }
    
    public void addImage(String name, URL imageURL){
        try{
            imageList.add(new GImage(name,ImageIO.read(imageURL)));
        }catch(Exception e){
            System.out.println("Error creating new gImage");
        }
    }
    
    public void addGif(String name,URL gifURL){
        gifList.add(new GGif(name,gifURL));
    }
    
    
    public BufferedImage getImage(String name){
        BufferedImage image = null;
        for(int x=0;x<imageList.size();x++){
            if(imageList.get(x).name.equals(name)){
                image = imageList.get(x).getImage();
                break;
            }
        }     
        return image;
    }
    
    public GImage getImageObject(String name){
        for(int x=0;x<imageList.size();x++){
            if(imageList.get(x).name.equals(name)){
                return imageList.get(x);
            }
        }     
        return null; 
    }
    
    public BufferedImage getFrame(String name, int index){
        for(int x=0;x<gifList.size();x++){
            if(gifList.get(x).name.equals(name)){
                return gifList.get(x).getFrame(index);
            }
        }
        return null;
    }
    
    
    
    
    private class GImage {
        
        String name;
        BufferedImage image;
        public GImage(String name,BufferedImage image){
            this.name = name;
            this.image = image;
        }
        
        public String getName(){
            return this.name;
        }
        
        public BufferedImage getImage(){
            return this.image;
        }
    }
    
    
    private class GGif{
        
        String name;
        BufferedImage[] frames; // = new BufferedImage[72];
        
        public GGif(String name,URL gifURL){
            this.name = name;
            try{
                initFrames(gifURL);
            }catch(IOException e){}
            
        }
        
        public String getName(){
            return this.name;
        }
        
        
        public BufferedImage getFrame(int index){
            return this.frames[index];
        }
        public BufferedImage[] getFrames(){
            return frames;
        }
        
        private void initFrames(URL passedIceShipURL) throws IOException{
            //"C:" + File.separator + "java-tutorial"+ File.separator + "Test" + File.separator +"anyfile.txt"
            File input = new File("C:/Users/Alex/Documents/NetBeansProjects/Graxius/build/classes/Images/transShip.gif");
            System.out.print(input.isFile());
            // or Object input = new FileInputStream("animated.gif");
            ImageInputStream stream = ImageIO.createImageInputStream(input);
            Iterator readers = ImageIO.getImageReaders(stream);
            if (!readers.hasNext())
            throw new RuntimeException("no image reader found");
            ImageReader reader = (ImageReader) readers.next();
            reader.setInput(stream); // don't omit this line!
            int n = reader.getNumImages(true); // don't use false!
            this.frames = new BufferedImage[n];
            System.out.println("numImages = " + n);
            for (int i = 0; i < n; i++) {
                BufferedImage image = reader.read(i);
                frames[i] = image;
                System.out.println("image[" + i + "] = " + image);  
            }
            frames[71] = frames[70];
            stream.close();
        }
        
    }
    
    
    
    
    AffineTransformOp op;
    
    
    
    public BufferedImage rotate(BufferedImage image, int degrees){  
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(degrees),image.getWidth()/2, image.getHeight()/2);
        op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage newImage = op.filter(image, null);
        return newImage;
    }
    
    
    
    
    
    
    
    
    
    private Image TransformGreenToTransparency(BufferedImage image){
        ImageFilter filter = new RGBImageFilter(){
            public final int filterRGB(int x, int y, int rgb){
                return (rgb << 8) & 0x00ff00;
            }
        };
        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
