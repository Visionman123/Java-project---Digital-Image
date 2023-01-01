package source_code;

import java.awt.image.BufferedImage;

public class Fitness {
    private static BufferedImage OriImage;            //the original Image
    private BufferedImage CompImage;        //the Image that gets compared
    private double deltaRed = 0;                //the difference in Red between the two versions of a Pixel
    private double deltaGreen = 0;            //the difference in Green between the two versions of a Pixel
    private double deltaBlue = 0;                //the difference in Blue between the two versions of a Pixel
    private double pixleFitness = 0;            //the Fitness coefficient for the individual Pixel
    private double fitness = 0;                //the Fitness coefficient for the current Image
    private static double OldFitness = 0;            //the Fitness coefficient for the old best Image
    private boolean better = false;            //used to tell if the result is better

    private int imageWidth;
    private int imageHeight;

    public Fitness(BufferedImage Ori) {
        OriImage = Ori;
        imageWidth = Ori.getWidth();
        imageHeight = Ori.getHeight();
    }

    // compares the image to the original and calculates the difference in %
    protected boolean getFitness(BufferedImage bufferedImage) {
        CompImage = bufferedImage;
        pixleFitness = 0;
        fitness = 0;
        better = false;

        //goes through each pixle
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                // gets the originals ARGB as an int and extracts the colors
                int[] ori_argb = OriImage.getRaster().getPixel(x, y, new int[4]);
                int ori_red = ori_argb[0];
                int ori_green = ori_argb[1];
                int ori_blue = ori_argb[2];
                // same with the comapritive image

                int[] comp_argb = CompImage.getRaster().getPixel(x, y, new int[4]);
                int comp_red = comp_argb[0];
                int comp_green = comp_argb[1];
                int comp_blue = comp_argb[2];

                // calculated the difference between the colors
                deltaRed = Math.abs(comp_red - ori_red);
                deltaGreen = Math.abs(comp_green - ori_green);
                deltaBlue = Math.abs(comp_blue - ori_blue);

                // calculates the difference for the pixle overall
                pixleFitness = deltaRed + deltaGreen + deltaBlue;

                // calculates overall fitness (lower is better)
                fitness += pixleFitness;

            }
        }

        // converts it into percentile value
        fitness = 100 * (1 - fitness / (OriImage.getWidth() * OriImage.getHeight() * 3 * 255));

        // checks if the result is better then the old optimum
        if (fitness > OldFitness) {
            // sets the new optimum
            OldFitness = fitness;
            //gives the fitness value to the GUI
            GUI.setFitnessVal(fitness);
            //gives the Polygongen the info that its a new optimum
            better = true;
        }
        return better;
    }
}
