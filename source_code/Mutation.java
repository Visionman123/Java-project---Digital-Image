package source_code;

import java.awt.*;
import java.util.Random;


public class Mutation {
    private static Random random = new Random();
    private int height;                            //height of the original image
    private int width;                            //width of the original image
    private Polygongen imagePolygongen;
    private int mutationProperty;                    //rng choice of the omponent
    private int chosenPolygonIndex;
    private int valueBeforeMutation;
    private int mutatedPointIndex;

    public Mutation(Dimension dimension, Polygongen polygongen) {
        this.width = dimension.width;
        this.height = dimension.height;
        this.imagePolygongen = polygongen;
    }

    //method to mutate a polygon
    public void mutate() {
        chosenPolygonIndex = -1;
        valueBeforeMutation = 0;
        mutatedPointIndex = -1;

        chosenPolygonIndex = getRandomPolygonIndex();
        Polygon polygon = imagePolygongen.getPolygon(chosenPolygonIndex);
        Color polygonColor = imagePolygongen.getColor(chosenPolygonIndex);
        // mutation property tells what polygon property is going to be mutated
        // 0 - red
        // 1 - blue
        // 2 - green
        // 3 - alpha (transparency)
        // 4 - change polygon random X coordinate
        // 5 - change polygon random Y coordinate
        mutationProperty = random.nextInt(6);

        if (mutationProperty == 0) {
            valueBeforeMutation = polygonColor.getRed();
            int red = random.nextInt(256);
            Color color = new Color(red, polygonColor.getGreen(), polygonColor.getBlue(), polygonColor.getAlpha());
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 1) {
            valueBeforeMutation = polygonColor.getGreen();
            int green = random.nextInt(256);
            Color color = new Color(polygonColor.getRed(), green, polygonColor.getBlue(), polygonColor.getAlpha());
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 2) {
            valueBeforeMutation = polygonColor.getBlue();
            int blue = random.nextInt(256);
            Color color = new Color(polygonColor.getRed(), polygonColor.getGreen(), blue, polygonColor.getAlpha());
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 3) {
            valueBeforeMutation = polygonColor.getAlpha();
            int alpha = random.nextInt(256);
            Color color = new Color(polygonColor.getRed(), polygonColor.getGreen(), polygonColor.getBlue(), alpha);
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 4) {
            mutatedPointIndex = random.nextInt(polygon.xpoints.length);
            valueBeforeMutation = polygon.xpoints[mutatedPointIndex];
            int newXPointValue = random.nextInt(width);
            polygon.xpoints[mutatedPointIndex] = newXPointValue;
            polygon.invalidate();
        } else if (mutationProperty == 5) {
            mutatedPointIndex = random.nextInt(polygon.ypoints.length);
            valueBeforeMutation = polygon.ypoints[mutatedPointIndex];
            int newYPointValue = random.nextInt(height);
            polygon.ypoints[mutatedPointIndex] = newYPointValue;
            polygon.invalidate();
        }
    }

    public void undoLastMutation() {
        if (mutationProperty == 0) {
            Color polygonColor = imagePolygongen.getColor(chosenPolygonIndex);
            Color color = new Color(valueBeforeMutation, polygonColor.getGreen(), polygonColor.getBlue(), polygonColor.getAlpha());
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 1) {
            Color polygonColor = imagePolygongen.getColor(chosenPolygonIndex);
            Color color = new Color(polygonColor.getRed(), valueBeforeMutation, polygonColor.getBlue(), polygonColor.getAlpha());
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 2) {
            Color polygonColor = imagePolygongen.getColor(chosenPolygonIndex);
            Color color = new Color(polygonColor.getRed(), polygonColor.getGreen(), valueBeforeMutation, polygonColor.getAlpha());
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 3) {
            Color polygonColor = imagePolygongen.getColor(chosenPolygonIndex);
            Color color = new Color(polygonColor.getRed(), polygonColor.getGreen(), polygonColor.getBlue(), valueBeforeMutation);
            imagePolygongen.setColor(color, chosenPolygonIndex);
        } else if (mutationProperty == 4) {
            Polygon polygon = imagePolygongen.getPolygon(chosenPolygonIndex);
            polygon.xpoints[mutatedPointIndex] = valueBeforeMutation;
        } else if (mutationProperty == 5) {
            Polygon polygon = imagePolygongen.getPolygon(chosenPolygonIndex);
            polygon.ypoints[mutatedPointIndex] = valueBeforeMutation;
        }
    }

    private int getRandomPolygonIndex() {
        return random.nextInt(Polygongen.POLY_NUM);
    }
}
