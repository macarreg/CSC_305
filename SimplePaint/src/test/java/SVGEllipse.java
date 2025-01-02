public class SVGEllipse implements SVGShape {
    private int cx, cy, rx, ry;

    public SVGEllipse(int cx, int cy, int rx, int ry) {
        this.cx = cx;
        this.cy = cy;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String toSVG() {
        return String.format("<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"red\" />", cx, cy, rx, ry);
    }

    @Override
    public void setPosition(int x, int y) {
        this.cx = x;
        this.cy = y;
    }

    @Override
    public void setSize(int width, int height) {
        this.rx = width / 2;
        this.ry = height / 2;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public int getRx() {
        return rx;
    }

    public int getRy() {
        return ry;
    }
}

