import javafx.scene.canvas.GraphicsContext;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
        //readFile();
        readDatabase();
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
        for (HashMap.Entry<Character, Integer> entry : m.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            f.put(key, (double) value * (360/(double)sum));
        }

        //Sort HashMap f
        f = sortByValue(f);

        HashMap<Character, Double> ms = new HashMap<Character, Double>();
        for(int i = 0; i < this.n; ++i) {
            char mxk = '*';
            double mxv = 0;
            for (HashMap.Entry<Character, Double> entry : f.entrySet()) {
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

        ms = sortByValue(ms);

        //Print HashMap f
        //f.forEach((key, value) -> System.out.println(key + " " + value));

        double angle = 90;
        int counter = 0;
        double pieHeight = y;
        double pieWidth = x;
        if (x > y) pieWidth = x - (x - y);
        else pieHeight = y - (y - x);
        MyPoint pieRef = new MyPoint((pieHeight / 4), (pieWidth / 4));
        for (HashMap.Entry<Character, Double> entry : ms.entrySet()) {
            Character key = entry.getKey();
            Double value = entry.getValue();
            String text = key + " , " + (value * (sum / 360)) / sum;
            MyArc a = new MyArc(pieRef, cs.get(counter), pieHeight / 2, pieWidth / 2, angle, -value);
            a.draw(GC);
            double tx = (pieWidth / 4) * Math.cos((angle - (value/2))*(Math.PI/180.0));
            double ty = (pieHeight / 4) * Math.sin((angle - (value/2))*(Math.PI/180.0));
            GC.setStroke(MyColor.BLACK.getColor());
            GC.strokeText(text, (pieRef.getX() * 2) + tx, (pieRef.getY() * 2) - ty);
            if(a.getStartAngle() + a.getLength() <= 0) angle = 360 + (angle + a.getLength());
            else angle = a.getStartAngle() + a.getLength();
            counter++;
        }
    }

    public void readFile(){
        java.io.File file = new java.io.File("C:\\Users\\pecko\\IdeaProjects\\CSc221A2\\assets\\alice.txt");
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String n = input.next();
                for(int i = 0; i < n.length(); ++i) {
                    char c = Character.toLowerCase(n.charAt(i));
                    if (Character.isLetter(c)) {
                        if (m.containsKey(c)) m.put(c, m.get(c) + 1);
                        else m.put(c, 1);
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readDatabase(){

        /*
        System.out.println("Starting create all tables . . .\n");
        db.createStudentsTable();
        db.createCoursesTable();
        db.createClassesTable();
        System.out.println("Finished with creating all tables. Check results above.\nStarting data entry . . .\n");
        db.userInputStudents();
        db.userInputCourses();
        db.userInputClasses();
        System.out.println("Finished data entry process. Check results above.");
         */

        //db.userInputStudents();

        /*
        ArrayList<MyStudent> s = new ArrayList<>();
        MyStudent s1 = new MyStudent("23916861", "matthew", "pecko", "pecko.matthew@gmail.com", "male");
        MyStudent s2 = new MyStudent("23916869", "jane", "doe", "jane.doe@gmail.com", "female");
        s.add(s1);
        s.add(s2);

        try {
            db.addStudents(s);
            //db.loadAllStudents();

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }


    public static HashMap<Character, Double> sortByValue(HashMap<Character, Double> hm)
    {
        LinkedList<HashMap.Entry<Character, Double> > list = new LinkedList<>(hm.entrySet());
        list.sort(HashMap.Entry.comparingByValue());
        HashMap<Character, Double> temp = new LinkedHashMap<>();
        for (HashMap.Entry<Character, Double> aa : list) temp.put(aa.getKey(), aa.getValue());
        return temp;
    }
}
