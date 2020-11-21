package sample;

public class A5Shape {
    private int xCoord;
    private int yCoord;
    private int radius;

    public A5Shape(double centerX, double centerY, double radius) {this(0,0,0);}

    public A5Shape(int xCoord, int yCoord, int radius) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.radius = radius;
    }

    public int getXCoord() { return xCoord;}

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getYCoord() { return yCoord;}

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getRadius() { return radius;}

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
