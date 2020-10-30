import javafx.scene.paint.Color;

public enum MyColor {
    RED(255,0,0,255), BLUE(0,0,255,255),
    LIME(0,255,0,255), CYAN(0,255,255,255),
    GREEN(0,128,0,255), GREY(128,128,128,255),
    MAGENTA(255,0,255,255), PURPLE(128,0,128,255),
    VIOLET(148,0,211,255), YELLOW(255,255,0,255),
    WHITE(255,255,255,255), BLACK(0,0,0,255),
    HOTPINK(255,105,180,255), BUSINESS(1, 107, 169, 255),
    GOOGLERED(234, 67, 53, 255), COLOR16(19,111,19, 255),
    COLOR17(129,11,199, 255), COLOR18(49,111,169, 255),
    COLOR19(99,11,19, 255), COLOR20(19,11,129, 255),
    COLOR21(219,124,69, 255), COLOR22(39,33,199, 255),
    COLOR23(19,211,99, 255), COLOR24(149,11,55, 255),
    COLOR25(111,222,119, 255), COLOR26(219,211,10, 255),
    COLOR27(0,222,119, 255), COLOR28(9,100,210, 255);

    private int r;
    private int g;
    private int b;
    private int a;
    private int argb;

    MyColor(int r, int g, int b, int a){
        setR(r);
        setG(g);
        setB(b);
        setA(a);
        setArgb(r, g, b, a);
    }

    public void setR(int r) { if(r >= 0 && r <= 255) this.r = r; }
    public void setG(int g) { if(g >= 0 && g <= 255) this.g = g; }
    public void setB(int b) { if(b >= 0 && b <= 255) this.b = b; }
    public void setA(int a) { if(a >= 0 && a <= 255) this.a = a; }
    public void setArgb(int r, int g, int b, int a) {
        this.argb = (a << 24) & 0xFF000000 |
                (r << 16) & 0x00FF0000 |
                (g << 8) & 0x0000FF00 |
                b;
    }

    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
    public int getA() { return a; }
    public int getArgb() { return argb; }

    @Override
    public String toString() {
        String h = getHexColor();
        return h + "[r=" + this.getR() + ",g=" + this.getG() + ",b=" + this.getB() + "]";
    }

    public Color getColor(){
        return new Color((double) getR()/255, (double) getG()/255, (double) getB()/255, (double) getA()/255);
    }

    public String getHexColor(){
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
