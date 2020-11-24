import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MyDatabase {
    private Connection con;

    MyDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase1?" + "user=matthew&password=1234");
        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void addStudents(ArrayList<MyStudent> s) throws SQLException {
        String sql = "INSERT INTO students (empid, firstname, lastname, email, sex) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            s.forEach(myStudent -> {
                try {
                    insert.setString(1, myStudent.getEmpid());
                    insert.setString(2, myStudent.getFname());
                    insert.setString(3, myStudent.getLname());
                    insert.setString(4, myStudent.getEmail());
                    insert.setString(5, myStudent.getSex());
                    insert.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void addCourses(ArrayList<MyCourse> c) throws SQLException {
        String sql = "INSERT INTO courses (courseid, courseTitle, department) VALUES (?, ?, ?)";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            c.forEach(myCourse -> {
                try {
                    insert.setString(1, myCourse.getCourseid());
                    insert.setString(2, myCourse.getCourseTitle());
                    insert.setString(3, myCourse.getDepartment());
                    insert.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void addClasses(ArrayList<MyClass> c) throws SQLException {
        String sql = "INSERT INTO classes (courseid, empid, sectionNo, year, semester, grade) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            c.forEach(myClass -> {
                try {
                    insert.setString(1, myClass.getCourseid());
                    insert.setString(2, myClass.getEmpid());
                    insert.setString(3, myClass.getSectionNo());
                    insert.setString(4, myClass.getYear());
                    insert.setString(5, myClass.getSemester());
                    insert.setString(6, myClass.getGrade());
                    insert.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void userInputStudents() {
        ArrayList<MyStudent> s = new ArrayList<>();
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String in;

        String empid;
        String fname;
        String lname;
        String email;
        String sex;

        while (!flag) {
            System.out.println("Enter q at anytime to quit. (ALL DATA ENTRY WILL BE LOST!!!)");

            System.out.println("enter student empid (THIS MUST BE UNIQUE. PLEASE CHECK DATABASE BECAUSE IF THERE IS A COLLISION ALL DATA ENTRY WILL BE LOST!!!): ");
            empid = sc.next();
            if (empid.equals("q") || empid.equals("Q")) break;

            System.out.println("enter student first name: ");
            fname = sc.next();
            if (fname.equals("q") || fname.equals("Q")) break;

            System.out.println("enter student last name: ");
            lname = sc.next();
            if (lname.equals("q") || lname.equals("Q")) break;

            System.out.println("enter student email: ");
            email = sc.next();
            if (email.equals("q") || email.equals("Q")) break;

            System.out.println("enter student sex (M, F, U): ");
            sex = sc.next();
            if (sex.equals("q") || sex.equals("Q")) break;

            s.add(new MyStudent(empid, fname, lname, email, sex));

            System.out.println("Enter another student? (Y or N)");
            in = sc.next();
            if (in.equals("n") || in.equals("N")) flag = true;
        }
        if (s.size() > 0) {
            try {
                addStudents(s);
                System.out.println("Successfully added students.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("No students to add!");
        }
    }

    public void userInputCourses() {
        ArrayList<MyCourse> c = new ArrayList<>();
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String in;

        String courseid;
        String courseTitle;
        String department;

        while (!flag) {
            System.out.println("Enter q at anytime to quit. (ALL DATA ENTRY WILL BE LOST!!!)");

            System.out.println("enter course courseid (THIS MUST BE UNIQUE. PLEASE CHECK DATABASE BECAUSE IF THERE IS A COLLISION ALL DATA ENTRY WILL BE LOST!!!): ");
            courseid = sc.next();
            if (courseid.equals("q") || courseid.equals("Q")) break;

            System.out.println("enter course title: ");
            courseTitle = sc.next();
            if (courseTitle.equals("q") || courseTitle.equals("Q")) break;

            System.out.println("enter course department: ");
            department = sc.next();
            if (department.equals("q") || department.equals("Q")) break;

            c.add(new MyCourse(courseid, courseTitle, department));

            System.out.println("Enter another course? (Y or N)");
            in = sc.next();
            if (in.equals("n") || in.equals("N")) flag = true;
        }
        if (c.size() > 0) {
            try {
                addCourses(c);
                System.out.println("Successfully added courses.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("No courses to add!");
        }
    }

    public void userInputClasses() {
        ArrayList<MyClass> c = new ArrayList<>();
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String in;

        String courseid;
        String empid;
        String sectionNo;
        String year;
        String semester;
        String grade;

        while (!flag) {
            System.out.println("Enter q at anytime to quit. (ALL DATA ENTRY WILL BE LOST!!!)");

            System.out.println("enter class courseid (THIS MUST BE UNIQUE KEY THAT ALREADY EXISTS IN COURSES TABLE. " +
                    "PLEASE CHECK DATABASE BECAUSE IF THERE IS A COLLISION OR IT DOES NOT ALREADY EXIST ALL DATA ENTRY WILL BE LOST!!!): ");
            courseid = sc.next();
            if (courseid.equals("q") || courseid.equals("Q")) break;

            System.out.println("enter class empid (THIS MUST BE UNIQUE KEY THAT ALREADY EXISTS IN STUDENTS TABLE. " +
                    "PLEASE CHECK DATABASE BECAUSE IF THERE IS A COLLISION OR IT DOES NOT ALREADY EXIST ALL DATA ENTRY WILL BE LOST!!!): ");
            empid = sc.next();
            if (empid.equals("q") || empid.equals("Q")) break;

            System.out.println("enter class sectionNo (THIS MUST BE UNIQUE. PLEASE CHECK DATABASE BECAUSE IF THERE IS A COLLISION ALL DATA ENTRY WILL BE LOST!!!): ");
            sectionNo = sc.next();
            if (sectionNo.equals("q") || sectionNo.equals("Q")) break;

            System.out.println("enter 4-digit course year (2020, 2019, 2018): ");
            year = sc.next();
            if (year.equals("q") || year.equals("Q")) break;

            System.out.println("enter course semester (FALL, SPRING, WINTER, SUMMER): ");
            semester = sc.next();
            if (semester.equals("q") || semester.equals("Q")) break;

            System.out.println("enter 1-letter course grade for student (A, B, C, D, F, W): ");
            grade = sc.next();
            if (grade.equals("q") || grade.equals("Q")) break;

            c.add(new MyClass(courseid, empid, sectionNo, year, semester, grade));

            System.out.println("Enter another class? (Y or N)");
            in = sc.next();
            if (in.equals("n") || in.equals("N")) flag = true;
        }
        if (c.size() > 0) {
            try {
                addClasses(c);
                System.out.println("Successfully added classes.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("No classes to add!");
        }
    }

    public void updateStudentRecordByEmpid(ArrayList<MyStudent> s) throws SQLException {
        String sql = "UPDATE `students` SET `firstname` = ?, `lastname` = ?, `email` = ?, `sex` = ? WHERE (`empid` = ?)";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            s.forEach(myStudent -> {
                try {
                    insert.setString(1, myStudent.getFname());
                    insert.setString(2, myStudent.getLname());
                    insert.setString(3, myStudent.getEmail());
                    insert.setString(4, myStudent.getSex());
                    insert.setString(5, myStudent.getEmpid());
                    insert.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void updateCourseRecordByCourseid(ArrayList<MyCourse> s) throws SQLException {
        String sql = "UPDATE `courses` SET `courseTitle` = ?, `department` = ? WHERE (`courseid` = ?)";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            s.forEach(myCourse -> {
                try {
                    insert.setString(1, myCourse.getCourseTitle());
                    insert.setString(2, myCourse.getDepartment());
                    insert.setString(3, myCourse.getCourseid());
                    insert.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void updateClassRecordBySectionNo(ArrayList<MyClass> s) throws SQLException {
        String sql = "UPDATE `classes` SET `sectionNo` = ?, `year` = ?, `semester` = ?, `grade` = ? WHERE (`courseid` = ? AND `empid` = ?)";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            s.forEach(myClass -> {
                try {
                    insert.setString(1, myClass.getSectionNo());
                    insert.setString(2, myClass.getYear());
                    insert.setString(3, myClass.getSemester());
                    insert.setString(4, myClass.getGrade());
                    insert.setString(5, myClass.getCourseid());
                    insert.setString(6, myClass.getEmpid());
                    insert.executeUpdate();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void updateStudents() {
        ArrayList<MyStudent> s = new ArrayList<>();
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String in;

        String empid;
        String fname;
        String lname;
        String email;
        String sex;

        while (!flag) {
            System.out.println("Enter q at anytime to quit. If you come across a field you do not wish to update, enter the same information that already exists.");

            System.out.println("enter student empid you want to update (MAKE SURE THIS EMPID EXISTS, OR ELSE ALL DATA ENTRY WILL BE LOST!!!): ");
            empid = sc.next();
            if (empid.equals("q") || empid.equals("Q")) break;

            System.out.println("enter updated student first name: ");
            fname = sc.next();
            if (fname.equals("q") || fname.equals("Q")) break;

            System.out.println("enter updated student last name: ");
            lname = sc.next();
            if (lname.equals("q") || lname.equals("Q")) break;

            System.out.println("enter updated student email: ");
            email = sc.next();
            if (email.equals("q") || email.equals("Q")) break;

            System.out.println("enter updated student sex (M, F, U): ");
            sex = sc.next();
            if (sex.equals("q") || sex.equals("Q")) break;

            s.add(new MyStudent(empid, fname, lname, email, sex));

            System.out.println("Update another student? (Y or N)");
            in = sc.next();
            if (in.equals("n") || in.equals("N")) flag = true;
        }
        if (s.size() > 0) {
            try {
                updateStudentRecordByEmpid(s);
                System.out.println("Successfully updated students.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("No students to update!");
        }
    }

    public void updateCourses() {
        ArrayList<MyCourse> c = new ArrayList<>();
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String in;

        String courseid;
        String courseTitle;
        String department;

        while (!flag) {
            System.out.println("Enter q at anytime to quit. If you come across a field you do not wish to update, enter the same information that already exists.");

            System.out.println("enter the course courseid you want to update (MAKE SURE THIS COURSEID EXISTS, OR ELSE ALL DATA ENTRY WILL BE LOST!!!): ");
            courseid = sc.next();
            if (courseid.equals("q") || courseid.equals("Q")) break;

            System.out.println("enter updated course title: ");
            courseTitle = sc.next();
            if (courseTitle.equals("q") || courseTitle.equals("Q")) break;

            System.out.println("enter updated course department: ");
            department = sc.next();
            if (department.equals("q") || department.equals("Q")) break;

            c.add(new MyCourse(courseid, courseTitle, department));

            System.out.println("Update another course? (Y or N)");
            in = sc.next();
            if (in.equals("n") || in.equals("N")) flag = true;
        }
        if (c.size() > 0) {
            try {
                updateCourseRecordByCourseid(c);
                System.out.println("Successfully updated courses.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("No courses to update!");
        }
    }

    public void updateClasses() {
        ArrayList<MyClass> c = new ArrayList<>();
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String in;

        String courseid;
        String empid;
        String sectionNo;
        String year;
        String semester;
        String grade;

        while (!flag) {
            System.out.println("Enter q at anytime to quit. If you come across a field you do not wish to update, enter the same information that already exists.");

            System.out.println("enter the class' courseid (THIS MUST BE UNIQUE KEY THAT ALREADY EXISTS IN COURSES TABLE. IT CANNOT BE CHANGED." +
                    "PLEASE CHECK DATABASE TO SEE IF THIS COURSEID EXISTS OR ELSE ALL DATA ENTRY WILL BE LOST!!!): ");
            courseid = sc.next();
            if (courseid.equals("q") || courseid.equals("Q")) break;

            System.out.println("enter the class' student empid (THIS MUST BE UNIQUE KEY THAT ALREADY EXISTS IN STUDENTS TABLE. IT CANNOT BE CHANGED" +
                    "PLEASE CHECK DATABASE TO SEE IF THIS EMPID EXISTS OR ELSE ALL DATA ENTRY WILL BE LOST!!!): ");
            empid = sc.next();
            if (empid.equals("q") || empid.equals("Q")) break;

            System.out.println("enter the class' updated sectionNo " +
                    "MAKE SURE THIS SECTIONNO EXISTS, OR ELSE ALL DATA ENTRY WILL BE LOST!!!): ");
            sectionNo = sc.next();
            if (sectionNo.equals("q") || sectionNo.equals("Q")) break;

            System.out.println("enter the updated 4-digit course year (2020, 2019, 2018): ");
            year = sc.next();
            if (year.equals("q") || year.equals("Q")) break;

            System.out.println("enter the updated course semester (FALL, SPRING, WINTER, SUMMER): ");
            semester = sc.next();
            if (semester.equals("q") || semester.equals("Q")) break;

            System.out.println("enter the updated 1-letter course grade for student (A, B, C, D, F, W): ");
            grade = sc.next();
            if (grade.equals("q") || grade.equals("Q")) break;

            c.add(new MyClass(courseid, empid, sectionNo, year, semester, grade));

            System.out.println("Update another class? (Y or N)");
            in = sc.next();
            if (in.equals("n") || in.equals("N")) flag = true;
        }
        if (c.size() > 0) {
            try {
                updateClassRecordBySectionNo(c);
                System.out.println("Successfully updated classes.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("No classes to update!");
        }
    }


    public void createStudentsTable() {
        String sql = "CREATE TABLE `students` (" +
                "`empid` int NOT NULL," +
                "`firstname` varchar(45) NOT NULL," +
                "`lastname` varchar(45) NOT NULL," +
                "`email` varchar(45) NOT NULL," +
                "`sex` varchar(45) NOT NULL," +
                "PRIMARY KEY (`empid`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            insert.execute();
            con.commit();
            System.out.println("Successfully created 'students' table");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void createCoursesTable() {
        String sql = "CREATE TABLE `courses` (" +
                "`courseid` int NOT NULL," +
                "`courseTitle` varchar(45) NOT NULL," +
                "`department` varchar(45) NOT NULL," +
                "PRIMARY KEY (`courseid`)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            insert.execute();
            con.commit();
            System.out.println("Successfully created 'courses' table");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void createClassesTable() {
        String sql = "CREATE TABLE `classes` (" +
                "`sectionNo` int NOT NULL," +
                "`courseid` int NOT NULL," +
                "`empid` int NOT NULL," +
                "`year` year NOT NULL," +
                "`semester` varchar(6) NOT NULL," +
                "`grade` varchar(1) NOT NULL," +
                "KEY `courseid_idx` (`courseid`)," +
                "KEY `empid_idx` (`empid`)," +
                "CONSTRAINT `courseid` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE," +
                "CONSTRAINT `empid` FOREIGN KEY (`empid`) REFERENCES `students` (`empid`) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            insert.execute();
            con.commit();
            System.out.println("Successfully created 'classes' table");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public HashMap<Character, Integer> getAllStudentGradesFromCourseid(String courseid) throws SQLException {
        HashMap<Character, Integer> m = new HashMap<Character, Integer>();

        String sql = "SELECT * FROM classes JOIN students ON classes.empid = students.empid WHERE courseid = ?";

        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            try {
                insert.setString(1, courseid);
                ResultSet r = insert.executeQuery();
                con.commit();
                while (r.next()) {
                    char c = r.getString(6).charAt(0);
                    if (m.containsKey(c)) m.put(c, m.get(c) + 1);
                    else m.put(c, 1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return m;
    }

    public void dropStudentsTable() {
        String sql = "DROP TABLE students";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            insert.execute();
            con.commit();
            System.out.println("Successfully dropped 'students' table");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void dropCoursesTable() {
        String sql = "DROP TABLE courses";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            insert.execute();
            con.commit();
            System.out.println("Successfully dropped 'courses' table");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void dropClassesTable() {
        String sql = "DROP TABLE classes";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            insert.execute();
            con.commit();
            System.out.println("Successfully dropped 'classes' table");
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void dump() {
        dumpStudents();
        dumpCourses();
        dumpClasses();
    }

    public void dumpStudents() {
        String sql = "SELECT * FROM students";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            try {
                ResultSet rs = insert.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                System.out.println("\nSTUDENTS");
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println();
                }
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void dumpCourses() {
        String sql = "SELECT * FROM courses";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            try {
                ResultSet rs = insert.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                System.out.println("\nCOURSES");
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println();
                }
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void dumpClasses() {
        String sql = "SELECT * FROM classes";
        try (PreparedStatement insert = con.prepareStatement(sql)) {
            con.setAutoCommit(false);
            try {
                ResultSet rs = insert.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                System.out.println("\nCLASSES");
                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                    }
                    System.out.println();
                }
                con.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}