import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class TestEngine {
    public static void main(String[] args) {

        MyDatabase db = new MyDatabase();
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
                    "press B to modify student by empid\n" +
                    "press C to modify course by courseid\n" +
                    "press D to modify class by sectionNo\n" +
                    "press E to dump all tables to terminal\n" +
                    "press F to exit");
            menu = sc.next();
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
                    db.dropStudentsTable();
                    break;
                case "4":
                    db.dropCoursesTable();
                    break;
                case "5":
                    db.dropClassesTable();
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
                    Platform.startup(() -> {
                        try {
                            Stage PS = new Stage();

                            PS.setTitle("CSc221 Lab 4");
                            Pane P = new Pane();

                            //Platform.setImplicitExit(false);

                            Canvas CV = new Canvas(1200, 900);
                            GraphicsContext GC = CV.getGraphicsContext2D();

                            HistogramAlphaBet h = new HistogramAlphaBet(GC, 1200, 900, 2, db);
                            h.drawConfig();


                            P.getChildren().add(CV);
                            P.setOnMouseClicked(event -> {
                                PS.hide();
                                Platform.exit();
                            });
                            Scene SC = new Scene(P, 1200, 900, MyColor.WHITE.getColor());
                            PS.setScene(SC);

                            PS.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    });
                    break;
                case "A":
                    Platform.startup(() -> {
                        try {
                            Stage PS = new Stage();

                            PS.setTitle("CSc221 Lab 4");
                            Pane P = new Pane();

                            //Platform.setImplicitExit(false);

                            Canvas CV = new Canvas(1200, 900);
                            GraphicsContext GC = CV.getGraphicsContext2D();

                            MyPieChart h = new MyPieChart(GC, 1200, 900, db);
                            h.drawConfig();


                            P.getChildren().add(CV);
                            P.setOnMouseClicked(event -> {
                                PS.hide();
                                Platform.exit();
                            });
                            Scene SC = new Scene(P, 1200, 900, MyColor.WHITE.getColor());
                            PS.setScene(SC);

                            PS.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    });
                    break;
                case "B":
                    db.updateStudents();
                    break;
                case "C":
                    db.updateCourses();
                    break;
                case "D":
                    db.updateClasses();
                    break;
                case "E":
                    db.dump();
                    break;
                case "F":
                    System.out.println("Exiting . . .");
                    Platform.exit();
                    flag = true;
            }
            if(!flag) System.out.println("\nFinished process. Check results above.\n");
        }

    }
}
