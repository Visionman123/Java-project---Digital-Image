package source_code;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class Main 
{

	  public static void main(String[] args) throws IOException 
	  {
		  BufferedImage OriImage;
		  String PathOri = "C:\\Users\\namng\\Desktop\\Java\\Pre-project\\mona.png";
		  String Path;
		  
		  //opens the original Image
		  OriImage = ImageIO.read(new File (PathOri));	
  
	      // Initializes fitness and Mutation objects and gives the original image to fitness
	      Fitness f = new Fitness(OriImage); 

	      // start of the do-while Loop
	      // checks the image its given on its fitness and afterwards mutates the next version
	      // Path = "C:\\Users\\namng\\Desktop\\Java\\Pre-project\\mona.png";
	  }	  
}
