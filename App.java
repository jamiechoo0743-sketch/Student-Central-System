import java.io.BufferedReader; // for reading the CSV file
import java.io.FileReader; 
import java.io.IOException;    // for handling file I/O exceptions
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {  

        Student[] students = new Student[100]; // maximum 100 students
        int studentCount = 0;

        // === Load csv file ===
        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line = br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) { // Read each line
                String[] parts = line.split(",");

                if (parts.length == 8) {
                    String id = parts[0].trim();
                    String first = parts[1].trim();
                    String last = parts[2].trim();
                    String course = parts[3].trim();
                    int year = Integer.parseInt(parts[4].trim());
                    double cwa = Double.parseDouble(parts[5].trim());
                    String status = parts[6].trim();
                    int credits = Integer.parseInt(parts[7].trim());  //use trim() to remove any leading/trailing spaces

                    // Create Details and Student objects
                    Details d = new Details(course, year, cwa, status, credits);
                    Student s = new Student(id, first, last, d);

                    students[studentCount++] = s; // Store into array
                }
            }
            System.out.println(" CSV file loaded successfully! (" + studentCount + " students found)");

        } catch (IOException e) {
            System.out.println(" Error reading data.csv: " + e.getMessage()); 
        }

        // === Menu ===
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 9) {
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════════════╗");
                System.out.println("║         WELCOME TO STUDENT CENTRAL SYSTEM ^^         ║");
                System.out.println("╠══════════════════════════════════════════════════════╣");
                System.out.println("║ 1 > Add New Student                                  ║");
                System.out.println("║ 2 > Edit Student Details                             ║");
                System.out.println("║ 3 > View All Students                                ║");
                System.out.println("║ 4 > Filter by Course                                 ║");
                System.out.println("║ 5 > Filter by Status                                 ║");
                System.out.println("║ 6 > Show Highest CWA                                 ║");
                System.out.println("║ 7 > Average CWA for Each Course                      ║");
                System.out.println("║ 8 > Credit Analysis                                  ║");
                System.out.println("║ 9 > Exit Program                                     ║");
                System.out.println("╚══════════════════════════════════════════════════════╝");
                System.out.print(" Please enter your choice (1-9): ");

            choice = Integer.parseInt(sc.nextLine()); // interger.parseInt to convert string to int

            switch (choice) {
                case 1:
                    studentCount = addStudent(students, studentCount, sc);
                    break;
                case 2:
                    editStudent(students, studentCount, sc);
                    break;
                case 3:
                    viewAllStudents(students, studentCount);
                    break;
                case 4:
                    filterByCourse(students, studentCount, sc);
                    break;
                case 5:
                    filterByStatus(students, studentCount, sc);
                    break;
                case 6:
                    highestCWA(students, studentCount);
                    break;
                case 7:
                    averageCwaByCourse(students, studentCount);
                    break;
                case 8:
                    creditAnalysis(students, studentCount);
                    break;
                case 9:
                    System.out.println("Exiting....Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        sc.close();
    }

    // Add students function
    public static int addStudent(Student[] students, int studentCount, Scanner sc) {
        System.out.println("=== Add New Student ===");

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter First Name: ");
        String first = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String last = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Year Level: ");
        int year = Integer.parseInt(sc.nextLine());

        System.out.print("Enter CWA: ");
        double cwa = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Status (FT/PT): ");
        String status = sc.nextLine();

        System.out.print("Enter Credits: ");
        int credits = Integer.parseInt(sc.nextLine());

        // Create Details and Student objects
        Details d = new Details(course, year, cwa, status, credits);
        Student newStudent = new Student(id, first, last, d);

        students[studentCount++] = newStudent; // add to array
        System.out.println("Student added successfully!");

        return studentCount; // return the new count
    }







    // Edit students function
    public static void editStudent(Student[] students, int studentCount, Scanner sc) {
        System.out.println("=== Edit Student ===");
        System.out.print("Enter Student ID to edit: ");
        String id = sc.nextLine();

        Student target = null; 
        
        // Find student by ID
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equals(id)) {
                target = students[i]; //save the target student
                System.out.println("Editing student: " + students[i]);
                break;  //loop the array until found
            }
        }
       
        if (target == null){
            System.out.println("Student ID not found.");
            return; // exit if not found
        }
        
        System.out.println("What do you want to edit?");
        System.out.println("1> First Name");
        System.out.println("2> Last Name"); 
        System.out.println("3> Course");
        System.out.println("4> Year Level");
        System.out.println("5> CWA");
        System.out.println("6> Status");
        System.out.println("7> Credits");
        System.out.println("8> Cancel");
        System.out.print("Enter your choice: ");
        int editChoice = Integer.parseInt(sc.nextLine());

        switch (editChoice) {
            case 1:
                System.out.print("Enter new First Name: ");
                String newFirst = sc.nextLine();
                target.setFirstName(newFirst);
                break;
            case 2:
                System.out.print("Enter new Last Name: ");
                String newLast = sc.nextLine();
                target.setLastName(newLast);
                break;
            case 3:
                System.out.print("Enter new Course: ");
                String newCourse = sc.nextLine();
                target.getStudentDetails().setCourse(newCourse);
                break;
            case 4:
                System.out.print("Enter new Year Level: ");
                int newYear = Integer.parseInt(sc.nextLine());
                target.getStudentDetails().setYear(newYear);
                break;
            case 5:
                System.out.print("Enter new CWA: ");
                double newCwa = Double.parseDouble(sc.nextLine());
                target.getStudentDetails().setCwa(newCwa);
                break;
            case 6:
                System.out.print("Enter new Status (FT/PT): ");
                String newStatus = sc.nextLine();
                target.getStudentDetails().setStatus(newStatus);
                break;
            case 7:
                System.out.print("Enter new Credits: ");
                int newCredits = Integer.parseInt(sc.nextLine());
                target.getStudentDetails().setCredits(newCredits);
                break;
            case 8:
                System.out.println("Edit cancelled.");
                return; // exit without changes
            default:
                System.out.println("Invalid choice.");
                return; // exit on invalid choice
        }

        System.out.println("Student details updated successfully!");
    }





    // View all students function
    public static void viewAllStudents(Student[] students, int studentCount) {
        if (studentCount == 0) {
            System.out.println("No students."); // check for no students
        } else {
            for (int i = 0; i < studentCount; i++) {
                System.out.println(students[i]); 
            } // print all students
        }
    }
    
    
    
    
    
    
    // Filter by course function
    public static void filterByCourse(Student[] students, int studentCount, Scanner sc){
        System.out.print("Enter course to filter: ");
        String course = sc.nextLine();

        boolean found = false;
        for (int i = 0;i < studentCount; i++){
            if (students[i].getStudentDetails().getCourse().equals(course)) {
                System.out.println(students[i]);
                found = true;
            } // find students in the specified course and print them
        }
        if (!found) {
            System.out.println("No students found in course: " + course);
            } // if none found, print message
    }





    // Filter by status function
    public static void filterByStatus(Student[] students, int studentCount, Scanner sc){
        System.out.print("Enter status to filter (FT/PT): ");
        String status = sc.nextLine();

        boolean found = false;
        for (int i = 0;i < studentCount; i++){
            if (students[i].getStudentDetails().getStatus().equals(status)) {
                System.out.println(students[i]);
                found = true;
            } // find students with the specified status and print them
        }
        if (!found) {
            System.out.println("No students found with status: " + status);
            } // if none found, print message
    }






    // Highest CWA function
    public static void highestCWA(Student[] students, int studentCount){
        if (studentCount == 0){
            System.out.println("No students yet.");
            return;
        }

        Student top = students[0]; // assume first is highest
        for (int i = 1;i < studentCount;i++){
            if (students[i].getStudentDetails().getCwa() > top.getStudentDetails().getCwa()){
                top = students[i]; // found a new highest
            }
        }
        System.out.println("The student with the highest CWA is: " + top);
    }






    // Average CWA function
    public static void averageCwaByCourse(Student[] students, int studentCount) {
        if (studentCount == 0) {
            System.out.println("No students available.");
        return;
    }

        String[] courses = new String[studentCount]; // to store course names
        int courseCount = 0;

    for (int i = 0; i < studentCount; i++) {
        String course = students[i].getStudentDetails().getCourse();
        boolean exists = false;
        for (int j = 0; j < courseCount; j++) {
            if (courses[j].equalsIgnoreCase(course)) {  // use equalsIgnoreCase to avoid case issues
                exists = true;
                break;
            } // check if course already recorded
        }
        if (!exists) {
            courses[courseCount++] = course; // record new course
        }
    }

    
    for (int i = 0; i < courseCount; i++) {
        String course = courses[i];
        double total = 0;
        int count = 0;

        for (int j = 0; j < studentCount; j++) {
            if (students[j].getStudentDetails().getCourse().equalsIgnoreCase(course)) {
                total += students[j].getStudentDetails().getCwa();
                count++;
            }
        } // calculate total CWA and count for this course

        double avg = (count > 0) ? (total / count) : 0;
        System.out.println("Course: " + course + " | Average CWA: " + String.format("%.2f", avg)); // use String.format to limit to 2 decimal places 
    }
}




    //Credits Analysis function
    public static void creditAnalysis(Student[] students, int studentCount) {
    if (studentCount == 0) {
        System.out.println("No student data available.");
        return;
    } // check for no students

    System.out.println("=== Credit Analysis ===");

    for (int i = 0; i < studentCount; i++) {
        Student s = students[i]; // get each student
        int credits = s.getStudentDetails().getCredits(); // get credits
        String level;

        if (credits < 100) {
            level = "First Year Progress";
        } else if (credits < 200) {
            level = "Second Year Progress";
        } else if (credits < 300) {
            level = "Third Year Progress";
        } else {
            level = "Ready to Graduate";
        } // determine level based on credits

    System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getFirstName() + " " + s.getLastName() + " | Credits: " + credits + " | Status: " + level);
    }
}
}







    








    
