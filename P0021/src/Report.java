public class Report {
    private String studentName;
    private String courseName;
    private int count;


    public Report(String studentName, String courseName, int count) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.count = count;
    }

    public Report() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void display() {
        System.out.printf("%-20s | %-20s | %-20s\n",
                studentName,  courseName, count);
    }
}