package group.project;

import java.awt.*;
import java.util.Random;

public class Polygongen {
    private Random random = new Random();
    public static int POLY_NUM = 50;
    public static int POLY_VERTICES = 12;
    private Polygon polylist[] = new Polygon[POLY_NUM];
    private Color farbe[] = new Color[POLY_NUM];

    private int xPoly[];
    private int yPoly[];

    public Polygongen(int width, int height) {
        xPoly = new int[POLY_VERTICES];
        yPoly = new int[POLY_VERTICES];

        for (int i = 0; i < POLY_NUM; i++) {
            for (int j = 0; j < POLY_VERTICES; j++) {
                xPoly[j] = random.nextInt(width);
                yPoly[j] = random.nextInt(height);
            }
            this.setPolygon(new Polygon(xPoly, yPoly, 5), i);
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), 0);
            this.setColor(color, i);
        }
    }

    public Polygon getPolygon(int i) {
        return polylist[i];
    }

    public void setPolygon(Polygon p, int i) {
        polylist[i] = p;
    }

    public Color getColor(int i) {
        return farbe[i];
    }

    public void setColor(Color c, int i) {
        farbe[i] = c;
    }
}

