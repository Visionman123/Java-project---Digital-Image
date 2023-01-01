package group.project;

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
	      //Mutation M = new Mutation(OriImage.getHeight(), OriImage.getWidth());      

	      // start of the do-while Loop
	      // checks the image its given on its fitness and afterwards mutates the next version
	      Path = "C:\\Users\\namng\\Desktop\\Java\\Pre-project\\mona.png";
	      //f.getFitness(Path);
	      //M.Mutate();

	      // end of the do-while loop
	      
	      // for testing only. usually handled by do-while loop
	      // checks the image its given on its fitness and afterwards mutates the next version
	      Path = "C:\\Users\\namng\\Desktop\\Java\\Pre-project\\mona.png";
	      //f.getFitness(Path);
	      //M.Mutate();
	  }	  
}




/*
Meine Main is basicly unn�tig!
in der haut main m�ssen nur die beiden Objekte instanziert werden (Fitness und Mutation)
Fitness baraucht den PFAD f�r das Original bild als argument (string)
danach kann man mir �ber <Fitness name>.getFitness(<Vergleichsbild pfad>) die fitness abrufen
auch hier ist der pfad des vergleichsbild im argument wieder ein string

achtung die klasse die meine sachen aufruft erwartet das " throws IOException " und damit einhergehend 
" import java.io.*; "

*/