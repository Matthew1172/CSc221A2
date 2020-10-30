import javafx.scene.canvas.GraphicsContext;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.LinkedHashMap;

public class HistogramAlphaBet{
    private HashMap<Character, Integer> m = new HashMap<Character, Integer>();
    private GraphicsContext GC;
    private double x;
    private double y;
    private int n;

    HistogramAlphaBet(GraphicsContext GC, double x, double y, int n){
        this.GC = GC;
        this.x = x;
        this.y = y;
        this.n = n;
        readFile();
    }

    public void drawConfig(){
        //Create ArrayList and assign 26 MyColors
        ArrayList<MyColor> cs = new ArrayList<MyColor>(26);
        cs.add(MyColor.RED);
        cs.add(MyColor.BLUE);
        cs.add(MyColor.LIME);
        cs.add(MyColor.CYAN);
        cs.add(MyColor.GREEN);
        cs.add(MyColor.GREY);
        cs.add(MyColor.MAGENTA);
        cs.add(MyColor.PURPLE);
        cs.add(MyColor.VIOLET);
        cs.add(MyColor.YELLOW);
        cs.add(MyColor.HOTPINK);
        cs.add(MyColor.BUSINESS);
        cs.add(MyColor.GOOGLERED);
        cs.add(MyColor.COLOR16);
        cs.add(MyColor.COLOR17);
        cs.add(MyColor.COLOR18);
        cs.add(MyColor.COLOR19);
        cs.add(MyColor.COLOR20);
        cs.add(MyColor.COLOR21);
        cs.add(MyColor.COLOR22);
        cs.add(MyColor.COLOR23);
        cs.add(MyColor.COLOR24);
        cs.add(MyColor.COLOR25);
        cs.add(MyColor.COLOR26);
        cs.add(MyColor.COLOR27);
        cs.add(MyColor.COLOR28);

        //Create a HashMap f that will store all letters as keys and their degrees as values
        HashMap<Character, Double> f = new HashMap<Character, Double>();

        //Print the HashMap of letters and their freq count
        //m.forEach((key, value) -> System.out.println(key + " " + value));

        //Assign the total number of letters to sum
        final int sum = m.values().stream().mapToInt(Integer::intValue).sum();

        //Put all the letters and their degrees into HashMap f
        for (Map.Entry<Character, Integer> entry : m.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            f.put(key, (double) value * (360/(double)sum));
        }

        //Sort HashMap f
        f = sortByValueDouble(f);

        HashMap<Character, Double> ms = new HashMap<Character, Double>();
        for(int i = 0; i < this.n; ++i) {
            char mxk = '*';
            double mxv = 0;
            for (Map.Entry<Character, Double> entry : f.entrySet()) {
                Character key = entry.getKey();
                Double value = entry.getValue();
                if (mxv < value){
                    mxv = value;
                    mxk = key;
                }
            }
            ms.put(mxk, mxv);
            f.remove(mxk, mxv);
        }


        //ms.forEach((key, value) -> System.out.println(key + " " + value));

        final double sum2 = ms.values().stream().mapToDouble(Double::doubleValue).sum();
        ms.put('*', 360 - sum2);

        ms = sortByValueDouble(ms);

        //Print HashMap f
        //f.forEach((key, value) -> System.out.println(key + " " + value));

        double p = 90;
        int counter = 0;
        for (Map.Entry<Character, Double> entry : ms.entrySet()) {
            Character key = entry.getKey();
            Double value = entry.getValue();
            String text = key + " , " + value;
            MyArc a = new MyArc(new MyPoint(0, 0), cs.get(counter), y, x - 300, p, -value);
            a.draw(GC);
            p = a.getStartAngle() + a.getLength();
            GC.setStroke(cs.get(counter).getColor());
            GC.strokeText(text, x-200, 50 + (counter * 25));
            counter++;
        }
    }

    public void readFile(){
        java.io.File file = new java.io.File("C:\\Users\\18453\\Desktop\\CSc221A2\\assets\\alice.txt");
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                char c = Character.toLowerCase(input.next().charAt(0));
                if(Character.isLetter(c)) {
                    if (m.containsKey(c)) m.put(c, m.get(c) + 1);
                    else m.put(c, 1);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Character, Double> sortByValueDouble(HashMap<Character, Double> hm)
    {
        List<Map.Entry<Character, Double> > list = new LinkedList<>(hm.entrySet());
        list.sort(Map.Entry.comparingByValue());
        HashMap<Character, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<Character, Double> aa : list) temp.put(aa.getKey(), aa.getValue());
        return temp;
    }
}
