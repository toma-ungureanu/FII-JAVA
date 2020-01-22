package graph;

import drawings.NodeShape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * @author Toma-Florin Ungureanu
 */
public class Vertex extends Ellipse2D.Double
{
    private double xCoord;
    private double yCoord;
    private double radius = 30;

    public Vertex(double xCoord, double yCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public void setCoords(double xCoord, double yCoord)
    {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public double getXCoord()
    {
        return xCoord;
    }

    public double getYCoord()
    {
        return yCoord;
    }
}
