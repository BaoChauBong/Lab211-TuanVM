
import java.util.ArrayList;
import java.util.Collections;

public class Manager {

    GetInputData getInputData = new GetInputData();
    Validation validation = new Validation();

    /**
     * Method use display menu
     */
    public void displayMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT");
        System.out.println("        1. Create");
        System.out.println("        2. Find and Sort");
        System.out.println("        3. Update/Delete");
        System.out.println("        4. Report");
        System.out.println("        5. Exit");
        System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program).");
    }

    /**
     * Method use display list student
     *
     * @param students
     */
    public void display(ArrayList<Student> students) {
        System.out.printf("%-20s | %-20s | %-20s\n",
                "Student Name", "Semester", "Course Name");
        // access over the elements in the list
        for (Student student : students) {
            student.display();
        }
    }

    public void displayAll(ArrayList<Student> students) {
        System.out.printf("%-20s |%-20s | %-20s | %-20s\n",
                "ID", "Student Name", "Semester", "Course Name");
        // access over the elements in the list
        for (Student student : students) {
            student.displayAll();
        }
    }

    /**
     * Method is use create student into list
     *
     * @param list
     */
    void createStudent(ArrayList<Student> list) {
        System.out.println("--------------Create----------------");
        while (true) {
            String id = getInputData.getID("Enter id: ");
            Student student = getStudentById(list, id);
            String studentName = "";
            while (student != null) {
                student.setId(id);
                System.out.println("Student with id " + id + " existed");
                //check the user's choice whether to use this id
                if (!validation.isInputYes("Do you want to use this Student (Y/N): ")) {
                    id = getInputData.getID("Enter id: ");
                    student = getStudentById(list, id);
                } else {
                    studentName = student.getStudentName();
                    break;
                }
            }
            // check student == null --> get student name
            if (student == null) {
                studentName = getInputData.getStringInput("Enter student name: ");
            }
            String semester = getInputData.getSemester("Enter semester: ");
            String courseName = getInputData.getCourseName("Enter Course Name: ");
            //loop until user input not duplicate
            while (!validation.isDuplicate(list, id, semester, courseName)) {
                System.err.println("DUPLICATE!!!");
                semester = getInputData.getSemester("Enter semester: ");
                courseName = getInputData.getCourseName("Enter Course Name:");
                validation.isDuplicate(list, id, semester, courseName);
            }
            list.add(new Student(id, studentName, semester, courseName));
            System.out.println("CREAT STUDENT SUCCESSFULLY");
            // if number of students greater than 10 ask user continue or not
            if (list.size() >= 10) {
                if (!validation.isInputYes("Do you want to continue (Y/N)?:")) {
                    return;
                }
            }

        }
    }

    void findAndSortDisplayByName(ArrayList<Student> list) {
        // check list empty
        if (list.isEmpty()) {
            System.out.println("List empty!!! ");
            return;
        }
        String keyWord = getInputData.getStringInput("Enter the value to search: ");
        ArrayList<Student> listMatching = findStudentByName(list, keyWord);
        // check listMatching empty
        if (listMatching.isEmpty()) {
            System.err.println("Not exist.");
        } else {
            Collections.sort(listMatching);
            display(listMatching);
        }
    }

    void updateOrDelete(ArrayList<Student> list) {
        // check list empty
        if (list.isEmpty()) {
            System.out.println("  Please enter student first.");
            return;
        }
        String id = getInputData.getID("Enter ID need update/delete: ");
        while (!validation.isIdExists(list, id)) {
            System.out.println("ID not found, please enter agian.");
            id = getInputData.getID("Enter ID need update/delete: ");
            validation.isIdExists(list, id);
        }
        ArrayList<Student> studentListFindByID = getListStudentById(list, id);

        System.out.printf("%-20s | %-20s | %-20s | %-20s\n",
                "STT", "Student Name", "Semester", "Course Name");
        // access each element in the student list find by id
        for (int i = 0; i < studentListFindByID.size(); i++) {
            System.out.printf("%-20s | %-20s | %-20s | %-20s\n",
                    i + 1, studentListFindByID.get(i).getStudentName(),
                    studentListFindByID.get(i).getSemester(), studentListFindByID.get(i).getCourseName());
        }
        boolean choice = validation.isInputUD("Do you want to update (U) or delete (D) student: ");
        if (choice) {
            updateStudent(list, studentListFindByID);
        } else {
            deleteStudent(list, studentListFindByID);
        }

    }

    private void updateStudent(ArrayList<Student> list, ArrayList<Student> studentsMatching) {
        int choice = getInputData.getIntInput(1, studentsMatching.size(), "Enter number of student: ");
        Student student = studentsMatching.get(choice - 1);
        // student null-->return 
        if (student == null) {
            return;
        }
        String idUpdate = "";
        idUpdate = getInputData.getID("Enter ID: ");
        Student studentUpdate = getStudentById(list, idUpdate);
        // student # null --> set studentname  
        if (studentUpdate != null) {
            student.setStudentName(studentUpdate.getStudentName());
        } else {
            String studentName = getInputData.getStringInput("Enter student name: ");
            student.setStudentName(studentName);
        }
        student.setId(idUpdate);
        String semester = "";
        semester = getInputData.getSemester("Enter semester: ");
        String courseName = "";
        courseName = getInputData.getCourseName("Enter Course Name: ");
        // Loop until user input not duplicate
        while (!validation.isDuplicate(list,idUpdate , semester, courseName)) {
            System.err.println("DUPLICATE!!!");
            semester = getInputData.getSemester("Enter semester: ");
            courseName = getInputData.getCourseName("Enter Course Name: ");
            validation.isDuplicate(list, student.getId(), semester, courseName);
        }
        student.setSemester(semester);
        student.setCourseName(courseName);
        System.out.println("Update Success !!!");
    }

    private void deleteStudent(ArrayList<Student> list, ArrayList<Student> studentsMatching) {
        int choice = getInputData.getIntInput(1, studentsMatching.size(), "Enter number of student: ");
        Student student = studentsMatching.get(choice - 1);
        int idDelete = getIndexStudentDelete(list, student);
        list.remove(idDelete);
        System.out.println("Remove Success !!!");
    }

    void report(ArrayList<Student> list) {
        ArrayList<Report> reportList = new ArrayList<>();
        // access each element in the list 
        for (Student student : list) {
            Report report = new Report();
            report.setStudentName(student.getStudentName());
            report.setCourseName(student.getCourseName());
            // check report list is empty
            if (reportList.isEmpty()) {
                report.setCount(1);
                reportList.add(report);
                continue;
            }
            boolean check = false;
            // access each element contained in the report list
            for (Report report1 : reportList) {
                // check the name in the report list with the name in the student list equals
                if (report1.getStudentName().equalsIgnoreCase(student.getStudentName())) {
                    check = true;
                    break;
                }
            }
     
            if (!check) {
                report.setCount(1);
                reportList.add(report);
                continue;
            }
            //  access each element contained in the report list
            for (int i = 0; i < reportList.size(); i++) {
                Report reportIndex = reportList.get(i);
                //  check the name in the report list with the name in the student list equals 
                if (reportIndex.getStudentName().equalsIgnoreCase(student.getStudentName())) {
                    //  check the CourseName in the report list with the CourseName in the student list equals
                    if (reportIndex.getCourseName().equalsIgnoreCase(student.getCourseName())) {
                        report.setCount(reportIndex.getCount() + 1);
                        reportList.set(i, report);
                        break;
                    } else {
                        report.setCount(1);
                        reportList.add(report);
                        break;
                    }

                }
            }
        }
        displayReport(reportList);
    }

    private void displayReport(ArrayList<Report> reportList) {
        //  //  access each element contained in the report list
        for (Report report : reportList) {
            report.display();
        }
    }

    private Student getStudentById(ArrayList<Student> list, String id) {
        // access over the elements in the list
        for (Student student : list) {
            //compare the input id with the id in the list is there any duplicate
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    private ArrayList<Student> findStudentByName(ArrayList<Student> list, String keyWord) {
        ArrayList<Student> listMatching = new ArrayList<>();
        //  access each element contained in list
        for (Student student : list) {
            // check student name contains input keyword
            if (student.getStudentName().toLowerCase().contains(keyWord.toLowerCase())) {
                listMatching.add(student);
            }
        }
        return listMatching;
    }

    private ArrayList<Student> getListStudentById(ArrayList<Student> list, String id) {
        ArrayList<Student> studentList = new ArrayList<>();
        //  access each element contained in list
        for (Student student : list) {
            // Check the input id is equal to the id in the list
            if (student.getId().equalsIgnoreCase(id)) {
                studentList.add(student);
            }
        }
        return studentList;
    }

    private int getIndexStudentDelete(ArrayList<Student> list, Student student) {
        // access each element contained in list
        for (int i = 0; i <= list.size(); i++) {
            if (list.get(i).equals(student)) {
                return i;
            }
        }
        return -1;
    }

}
