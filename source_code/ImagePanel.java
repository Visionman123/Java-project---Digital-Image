package group.project;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private Polygongen polygongen;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Polygongen getPolygongen() {
        return polygongen;
    }

    public void setPolygongen(Polygongen polygongen) {
        this.polygongen = polygongen;
    }

    public void createImageFrom(BufferedImage bufferedImage)
    {
        this.image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.polygongen = new Polygongen(bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    private void clearImage()
    {
        Graphics2D g2d = image.createGraphics();
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
    }

    private void drawPolygons(){
        Graphics2D graphics = image.createGraphics();
        for (int i = 0; i < Polygongen.POLY_NUM; i++) {
            Polygon p = polygongen.getPolygon(i);
            if (p != null) {
                graphics.setColor(polygongen.getColor(i));
                graphics.fillPolygon(polygongen.getPolygon(i));
            }
        }
    }

    public void renderPolygons() {
        clearImage();
        drawPolygons();
    }
}