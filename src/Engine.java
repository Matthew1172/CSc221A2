import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.Scanner;


public class Engine extends Application {
    private Stage mainStage;
    private Pane mainP;
    private Scene sc1;
    private Scene sc2;


/*
    public static void main(String[] args) {
        launch(args);

    }
 */

    @Override
    public void start(Stage PS) {
        mainStage = PS;
        mainStage.setAlwaysOnTop(true);
        try {

            PS.setTitle("CSc221 Lab 4");
            Pane P = new Pane();
            Pane P2 = new Pane();

            Platform.setImplicitExit(false);

            Canvas CV = addPie1(1200, 900);
            P.getChildren().add(CV);

            Canvas CV2 = addPie2(1200, 900);
            P2.getChildren().add(CV2);

            sc1 = new Scene(P, 1200, 900, MyColor.WHITE.getColor());
            sc2 = new Scene(P2, 1200, 900, MyColor.WHITE.getColor());

            //PS.setScene(SC);
            //PS.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public Canvas addPie1(int cWidth, int cHeight) {
        Canvas CV = new Canvas(cWidth, cHeight);
        GraphicsContext GC = CV.getGraphicsContext2D();

        HistogramAlphaBet h = new HistogramAlphaBet(GC, cWidth, cHeight, 6);
        h.drawConfig();

        //MyPieChart p = new MyPieChart(GC,cWidth,cHeight);
        //p.drawConfig();
        return CV;
    }
    public Canvas addPie2(int cWidth, int cHeight) {
        Canvas CV = new Canvas(cWidth, cHeight);
        GraphicsContext GC = CV.getGraphicsContext2D();

        //HistogramAlphaBet h = new HistogramAlphaBet(GC, cWidth, cHeight, 6);
        //h.drawConfig();

        MyPieChart p = new MyPieChart(GC,cWidth,cHeight);
        p.drawConfig();
        return CV;
    }
}

/*
        MyDatabase db = new MyDatabase();

        //Put method in loop where user can enter a students 'full' record across all tables.
        Scanner sc = new Scanner(System.in);
        String menu;
        boolean flag = false;
        System.out.println("Starting database management system . . .\n");
        while(!flag){
            System.out.println("Enter one of the following opcodes and the letter 'q' to navigate through the system.");
            System.out.println("press 0 to create students table\n" +
                    "press 1 to create courses table\n" +
                    "press 2 to create classes table\n" +
                    "press 3 to drop students table\n" +
                    "press 4 to drop courses table\n" +
                    "press 5 to drop classes table\n" +
                    "press 6 to input students\n" +
                    "press 7 to input courses\n" +
                    "press 8 to input classes\n" +
                    "press 9 to print histogram of top n students in course\n" +
                    "press A to print histogram of all students in course\n" +
                    "press B to exit");
            menu = sc.next();
            //if(menu.equals("q") || menu.equals("Q")) break;
            switch (menu){
                case "0":
                    db.createStudentsTable();
                    break;
                case "1":
                    db.createCoursesTable();
                    break;
                case "2":
                    db.createClassesTable();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    db.userInputStudents();
                    break;
                case "7":
                    db.userInputCourses();
                    break;
                case "8":
                    db.userInputClasses();
                    break;
                case "9":
                    break;
                case "A":
                    break;
                case "B":
                    System.out.println("Exiting . . .");
                    flag = true;
            }
            if(!flag) System.out.println("Finished process. Check results above.\n");
        }

 */
