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

    @Override
    public int hashCode(){
        int hashcode = 0;
        hashcode = (int) x*20;
        hashcode += toString().hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof MyPoint)) return false;
        MyPoint c = (MyPoint) o;
        return Double.compare(x, c.x) == 0 && Double.compare(y, c.y) == 0;
    }
}
