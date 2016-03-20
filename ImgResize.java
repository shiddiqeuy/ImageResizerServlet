package imageresizerws;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
public class ImgResize {
       
         
        public String ImgRsz(String filename,String filepath) throws IOException {
               //TODO write your implementation code here:
              
               try{
                        File inputFile = new File(filepath+filename);
                        String name = inputFile.getName();
                           System.out.println("filename= "+name);
                        String type = new String(name);
                    
                           type.toLowerCase();
                               if(type.endsWith(".jpg")){
                                   type = "JPG";
                               }
                               if(type.endsWith(".png")){
                                   type = "PNG";
                               }
                                
                            if(type.endsWith(".bmp")){
                                type = "BMP";
                                }
                    
                        BufferedImage inputImage = ImageIO.read(inputFile);
                       
                           System.out.println("type file = "+type);
                        BufferedImage thumbNail = shrink(inputImage);
                        System.out.println("berhasil di resize");
                   if(type.endsWith(".jpg")){
              
                       ImageIO.write(thumbNail,"jpg",new File(filepath+ File.separator +name));
                   }
                   
                   if(type.endsWith(".png")){
                 
                       ImageIO.write(thumbNail,"png",new File(filepath+ File.separator +name));
                   }
                        
                        

               }catch(IOException e){
                       System.out.println(e.getMessage());
               }
        
               return "file"+filename+ " berhasil di resize";
           }

    @WebMethod(exclude = true)
    public static BufferedImage shrink(BufferedImage image) {

               int w = 1024;
               int h = 768;

               BufferedImage shrunkImage = new BufferedImage(w, h, image.getType());
               Graphics2D g = shrunkImage.createGraphics();
              
               
               /*
                * increasing image quality when resize
                * 
                */
                       g.setComposite(AlphaComposite.Src);
                       g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                       RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                       g.setRenderingHint(RenderingHints.KEY_RENDERING,
                       RenderingHints.VALUE_RENDER_QUALITY);
                       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                       RenderingHints.VALUE_ANTIALIAS_ON);
               /*
                * resize image quality 
                * 
                */
                       
               g.drawImage(image, 0, 0, w, h, null);
               g.dispose();
                

               return shrunkImage;
           }
        
             
    }

