public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private Details studentDetails;

    // Constructor
    public Student(String studentId, String firstName, String lastName, Details studentDetails) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentDetails = studentDetails; 
    }

    public String getStudentId() { 
        return studentId; 
    }
    public void setStudentId(String studentId) { 
        this.studentId = studentId; 
    }

    public String getFirstName() { 
        return firstName; 
    }
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }

    public String getLastName() { 
        return lastName; 
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }

    public Details getStudentDetails() { 
        return studentDetails; 
    }
    public void setStudentDetails(Details studentDetails) { 
        this.studentDetails = studentDetails; 
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + firstName + " " + lastName + 
               ", " + studentDetails.toString();
    }
}