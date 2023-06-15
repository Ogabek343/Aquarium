package model;

public class Location {
    private int xLocation;
    private int yLocation;
    private int zLocation;

    public Location(int xLocation, int yLocation, int zLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.zLocation = zLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public void setzLocation(int zLocation) {
        this.zLocation = zLocation;
    }

    public int getxLocation() {
        return xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public int getzLocation() {
        return zLocation;
    }

    @Override
    public String toString() {
        return "Location{" +
                "xLocation=" + xLocation +
                ", yLocation=" + yLocation +
                ", zLocation=" + zLocation +
                '}';
    }
}
