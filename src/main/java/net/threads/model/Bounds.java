package net.threads.model;

public class Bounds {
    private final int height;
    private final int width;

    public Bounds(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Bounds) {
            Bounds other = (Bounds)obj;
            result = this.height == other.height && this.width == other.width;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return height * 13 + width;
    }

    @Override
    public String toString() {
        return String.format("Height: %1$d Width: %2$d", height, width);
    }
}
