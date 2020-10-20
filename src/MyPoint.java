import javafx.scene.canvas.GraphicsContext;

public class MyPoint {
    private double x, y;
    public MyPoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){ return this.x; }
    public double getY(){ return this.y; }
    @Override
    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
}
