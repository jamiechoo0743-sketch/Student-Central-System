public class Details {
    private String course;
    private int year;
    private double cwa;
    private String status;
    private int credits;

    public Details(String course, int year, double cwa, String status, int credits) {
        this.course = course;
        this.year = year;
        this.cwa = cwa;
        this.status = status;
        this.credits = credits;
    }

    public String getCourse() 
    { 
        return course; 
    }
    public void setCourse(String course) 
    { this.course = course; }

    public int getYear() 
    { 
        return year; 
    }
    public void setYear(int year) 
    { 
        this.year = year; 
    }

    public double getCwa() 
    { 
        return cwa; 
    }
    public void setCwa(double cwa) 
    { 
        this.cwa = cwa; 
    }

    public String getStatus() 
    { 
        return status; 
    }
    public void setStatus(String status) 
    { 
        this.status = status; 
    }

    public int getCredits() 
    { 
        return credits; 
    }
    public void setCredits(int credits) 
    { 
        this.credits = credits; 
    }

    @Override
    public String toString() {
        return "Course: " + course + ", Year: " + year + 
               ", CWA: " + cwa + ", Status: " + status + 
               ", Credits: " + credits;
    }
}
