package src;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.File;
import java.util.StringTokenizer;
/**
 * Roster Manager class that manages the roster, with various methods
 * such as sorting by certain attributes, performing commands on roster
 * @author Arnold Nguyen, Sofia Juliani
 */
public class TuitionManager {
    /**
     * returns a Major object from a string from Major enum class
     * @param majorSubject represents major input from scanner
     * @return major object to use as major attribute
     */
    private Major returnMajor(String majorSubject) {
        Major studentMajor;
        if (majorSubject.equalsIgnoreCase("CS")) {
            studentMajor = Major.CS;
        } else if (majorSubject.equalsIgnoreCase("ITI")) {
            studentMajor = Major.ITI;
        } else if (majorSubject.equalsIgnoreCase("BAIT")) {
            studentMajor = Major.BAIT;
        } else if (majorSubject.equalsIgnoreCase("EE")) {
            studentMajor = Major.EE;
        } else if (majorSubject.equalsIgnoreCase("MATH")) {
            studentMajor = Major.MATH;
        } else {
            System.out.println("Major code invalid: " + majorSubject);
            return null;
        }
        return studentMajor;
    }

    /**
     * Adds a student to Enroll in the Enrollment list
     * @param scanner object to take in commands
     * @param rutgersRoster roster object to hold students
     * @param dataToken that is read into command for student
     */
    private void commandAdd(Scanner scanner, Roster rutgersRoster, String dataToken) {
        String dataString;
        String[] inputs;
        String firstName;
        String lastName;
        String dateOfBirth ;
        Date studentDate;
        String majorSubject;
        String creditsEnrolled;
        try {
            dataString = scanner.nextLine();
            inputs = dataString.split(" ");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals("")) {
                    throw new NoSuchElementException();
                }
            }
            firstName = inputs[1];
            lastName = inputs[2];
            dateOfBirth = inputs[3];
            studentDate = new Date(dateOfBirth);
            majorSubject = inputs[4];
            creditsEnrolled = inputs[5];
        } catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Missing data in line command.");
            return;
        }
        /*String firstName = inputs[0];
        String lastName = inputs[1];
        String dateOfBirth = inputs[2];
        Date studentDate = new Date(dateOfBirth);
        String majorSubject = inputs[3];
        String creditsEnrolled = inputs[4];
        Student studentToAdd;*/
        Major studentMajor = returnMajor(majorSubject);
        boolean isValidCreditCompleted = isValidCreditsAndDate(studentDate,
                creditsEnrolled, dateOfBirth);
        if (studentMajor != null && studentDate.isValid() && isValidCreditCompleted) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            int credits = Integer.parseInt(creditsEnrolled);
            Student student = createStudentType(dataToken, studentProfile, studentMajor,
                    credits, inputs);
            addRoster(student, rutgersRoster, firstName, lastName, dateOfBirth
            , studentDate);
        }
    }

    /**
     * Student Object to read in to add to Roster
     * @param student object to read in to add to the Roster
     * @param rutgersRoster Roster object to hold the students
     * @param firstName attribute for STudent object
     * @param lastName attribute for STudent object
     * @param dateOfBirth attribute for student object
     * @param studentDate Date object that is from DateOfbirth
     */
    private void addRoster(Student student, Roster rutgersRoster,
                                  String firstName, String lastName,
                                  String dateOfBirth, Date studentDate){
        if (student != null) {
            if (rutgersRoster.contains(student)) {
                System.out.println(firstName + " " + lastName + " " + dateOfBirth
                        + " is already in the roster.");
            } else {
                if (rutgersRoster.add(student)) {
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " added to the roster.");
                } else {
                    System.out.println("DOB invalid: " + studentDate +
                            " younger than 16 years old.");
                }
            }
        }
    }

    /**
     * Creation of an extension of the abstract Student class that can create
     * Resident, nonResident, TriState, and International depending on command
     * @param dataToken String command to initiate
     * @param studentProfile Student profile parameter to create Student
     * @param studentMajor Major object parameter to create Major
     * @param credits taken parameter for creation of student object
     * @param scanner object that reads in commands
     * @return Student extension object (dependent on command)
     */
    private Student createStudentType(String dataToken, Profile studentProfile,
                                      Major studentMajor, int credits, String[] inputs) {
        if (dataToken.equals("AR") || dataToken.equals("R")) {
            return new Resident(studentProfile, studentMajor, credits);
        } else if (dataToken.equals("AT") || dataToken.equals("T")) {
            String state;
            try {
                state = inputs[6];
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Missing the state code.");
                return null;
            }
            if (state.equalsIgnoreCase("CT") || state.equalsIgnoreCase("NY")) {
                return new TriState(studentProfile, studentMajor, credits,
                    state);
            }
            else {
                System.out.println(state + ": Invalid state code.");
                return null;
            }
        } else if (dataToken.equals("AI") || dataToken.equals("I")) {
            boolean studiesAbroad;
            try {
                studiesAbroad = Boolean.parseBoolean(inputs[6]);
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                studiesAbroad = false;
            }
            return new International(studentProfile,
                    studentMajor, credits, studiesAbroad);
        } else if (dataToken.equals("AN") || dataToken.equals("N")) {
            return new NonResident(studentProfile,
                    studentMajor, credits);
        } else
            return null;
    }

    /**
     * Determines if credits and date are valid
     * @param studentDate Date object for Student parameter
     * @param creditsEnrolled credits student currently enrolled in
     * @param dateOfBirth String object
     * @return true if credits and date are valid, false if not.
     */
    private boolean isValidCreditsAndDate(Date studentDate,
                                         String creditsEnrolled,
                                         String dateOfBirth) {
        if (!studentDate.isValid())
            System.out.println("DOB invalid: "
                    + dateOfBirth + " not a valid calendar date!");
        try {
            if (Integer.parseInt(creditsEnrolled) < 0) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return false;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Credits completed invalid: not an integer!");
            return false;
        }
        return true;
    }

    private boolean isValidCreditsAndDateEnroll(Date studentDate,
                                          String creditsEnrolled,
                                          String dateOfBirth) {
        if (!studentDate.isValid())
            System.out.println("DOB invalid: "
                    + dateOfBirth + " not a valid calendar date!");
        try {
            if (Integer.parseInt(creditsEnrolled) < 0) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return false;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Credits enrolled is not an integer.");
            return false;
        }
        return true;
    }

    /**
     * Enrolls a student in the Enrollment list accordingly
     * @param scanner object that reads in command for Student
     * @param rutgersRoster Roster object that holds list of Students
     * @param rutgersEnroll Enrollment list that holds objects
     */
    private void commandEnrollStudent(Scanner scanner,Roster rutgersRoster,Enrollment rutgersEnroll){
        String dataString;
        String[] inputs;
        String firstName;
        String lastName;
        String dateOfBirth;
        Date studentDate;
        String creditsEnrolled;
        try { dataString = scanner.nextLine();
            inputs = dataString.split("\\s++");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals(""))
                    throw new NoSuchElementException();}
            firstName = inputs[1];
            lastName = inputs[2];
            dateOfBirth = inputs[3];
            studentDate = new Date(dateOfBirth);
            creditsEnrolled = inputs[4]; }
        catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Missing data in line command."); return; }
        if (studentDate.isValid() && isValidCreditsAndDateEnroll(studentDate,
                creditsEnrolled, dateOfBirth)) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            int credits = Integer.parseInt(creditsEnrolled);
            EnrollStudent studentToEnroll =
                    new EnrollStudent(studentProfile, credits);
            if (!rutgersRoster.contains(studentProfile)) { //not in roster
                System.out.println("Cannot enroll: " + studentProfile +
                        " is not in the roster.");
            } else {
                Student rosterStudent = rutgersRoster.findStudent(studentProfile);
                if (!rosterStudent.isValid(credits)) { //not valid credits
                    System.out.println(rosterStudent.invalidStudent() + credits +
                            ": invalid credit hours.");
                }
                else {
                    if (!rutgersEnroll.contains(studentToEnroll))
                        rutgersEnroll.add(studentToEnroll);
                    else {rutgersEnroll.updateCredits(studentProfile, credits);}
                    System.out.println(studentToEnroll.getProfile().toString()
                            + " enrolled " + studentToEnroll.getCreditsEnrolled() + " credits"); }
                } }
        }

    }

    /**
     * Drops a student from the enrollment class
     * @param scanner object to read in line commands
     * @param rutgersEnroll Enrollment List for student objects
     */
    private void dropStudent(Scanner scanner, Enrollment rutgersEnroll){
        String dataString;
        String[] inputs;
        String firstName;
        String lastName;
        String dateOfBirth;
        Date studentDate;
        try {
            dataString = scanner.nextLine();
            inputs = dataString.split("\\s++");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals("")) {
                    throw new NoSuchElementException();
                }
            }
            firstName = inputs[1];
            lastName = inputs[2];
            dateOfBirth = inputs[3];
            studentDate = new Date(dateOfBirth);
        }
        catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Missing data in line command.");
            return;
        }

        if (studentDate.isValid()) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            EnrollStudent studentToDrop = new EnrollStudent(studentProfile);
            if (rutgersEnroll.contains(studentToDrop)) {
                rutgersEnroll.remove(studentToDrop);
                System.out.println(studentProfile + " dropped.");
            }
            else {
                System.out.println(studentProfile + " is not enrolled.");
            }
        }
    }

    /**
     * Awards a scholarship to a student in the roster class
     * @param scanner object to read in command
     * @param rutgersRoster object that has a list of students
     */
    private void awardScholarship(Scanner scanner, Roster rutgersRoster, Enrollment rutgersEnroll){
        String dataString;
        String[] inputs;
        String firstName;
        String lastName;
        String dateOfBirth ;
        Date studentDate;
        String scholarshipString;
        try { dataString = scanner.nextLine();
            inputs = dataString.split("\\s++");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals("")) {
                    throw new NoSuchElementException(); } }
            firstName = inputs[1];
            lastName = inputs[2];
            dateOfBirth = inputs[3];
            studentDate = new Date(dateOfBirth);
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            if (!rutgersRoster.contains(studentProfile)){
                System.out.println(studentProfile + " is not in the roster" + "."); return;}
            scholarshipString = inputs[4]; }
        catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Missing data in line command."); return; }
        Profile studentProfile = new Profile(lastName, firstName, studentDate);
        if (studentDate.isValid() && isValidScholarshipAndDate(scholarshipString, studentDate, dateOfBirth)) {
            if (rutgersRoster.contains(studentProfile)){
                Student studentToAward = rutgersRoster.findStudent(studentProfile);
                if (studentToAward.isResident()) { //check if resident
                    Resident residentToAward = (Resident) studentToAward;
                    if (residentToAward.isFullTime(studentEnrollFind.getCreditsEnrolled())) {
                        int scholarship = Integer.parseInt(scholarshipString);
                        residentToAward.setScholarship(scholarship);
                        System.out.println(studentProfile +
                                ": scholarship amount updated.");
                    } else { //not full time
                        System.out.println(" part time student is not " +
                                "eligible for the scholarship.");
                    }
                } else { //not resident
                    System.out.println(studentProfile + " " + studentToAward.invalidStudent() +
                            "is not eligible for the scholarship.");
                }
            } else {System.out.println(studentProfile + " is not in the roster.");}
        }
    }

    /**
     * Determines if scholarship is valid
     * @param scholarshipString
     * @param studentDate
     * @param dateOfBirth
     * @return
     */
    private boolean isValidScholarshipAndDate(String scholarshipString,
                                              Date studentDate,
                                              String dateOfBirth) {
        if (!studentDate.isValid())
            System.out.println("DOB invalid: "
                    + dateOfBirth + " not a valid calendar date!");

        try {
            if (Integer.parseInt(scholarshipString) <= 0 || Integer.parseInt(scholarshipString) > 10000) {
                System.out.println(scholarshipString + ": invalid amount.");
                return false;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Amount is not an integer.");
            return false;
        }
        return true;
    }

    /**
     *
     * @param rutgersEnroll Enrollment list in the
     */
    private void displayEnrollment(Enrollment rutgersEnroll){
        if (rutgersEnroll.isEnrollmentEmpty()) {
            System.out.println("Enrollment is empty!");
        }
        else {
            System.out.println("** Enrollment **");
            rutgersEnroll.print();
            System.out.println("* end of enrollment *");
        }

    }

    /**
     * Display tuition in the Enrollment List order
     * @param rutgersEnroll the Enrollment list for student objects
     * @param rutgersRoster a roster of students to select from
     */
    private void displayTuition(Enrollment rutgersEnroll, Roster rutgersRoster){
        if (rutgersRoster.isRosterEmpty()) {
            System.out.println("Student roster is empty!");
        }
        else {
            System.out.println("** Tuition due **");
            EnrollStudent[] enrollList = rutgersEnroll.getEnrollStudents();
            Student[] rosterList = rutgersRoster.getRoster();
            for (int i = 0; i < rutgersEnroll.getSize(); i++){
                EnrollStudent tempEnrollStudent = enrollList[i];
                if (tempEnrollStudent != null) {
                    DecimalFormat dfLarge = new DecimalFormat( "##,###.00" );
                    DecimalFormat dfSmall = new DecimalFormat( "#,###.00");
                    Student tempStudent = rutgersRoster.findStudent(tempEnrollStudent.getProfile());
                    double tuition =
                            tempStudent.tuitionDue(tempEnrollStudent.getCreditsEnrolled());
                    String formattedTuition;
                    if (tuition >= 10000) {
                        formattedTuition = dfLarge.format(tuition);
                    }
                    else {
                        formattedTuition = dfSmall.format(tuition);
                    }
                    System.out.println(tempStudent.getProfile().toString() +
                            " " + tempStudent.invalidStudent() + "enrolled" +
                            " " +
                            tempEnrollStudent.getCreditsEnrolled() + " credits: " +
                            "tuition " +
                            "due: $" + formattedTuition);
                }
            }
            System.out.println("* end of tuition due *");
        }
    }

    /**
     * SemesterEnd method for Enrollment List and Students
     * @param rutgersRoster Roster object that holds students
     * @param rutgersEnroll Enrollment List object that holds EnrollStudents
     */
    private void semesterEnd(Roster rutgersRoster, Enrollment rutgersEnroll) {
        Student[] rosterList = rutgersRoster.getRoster();
        EnrollStudent[] enrollList = rutgersEnroll.getEnrollStudents();
        for (int i = 0; i < rutgersEnroll.getSize(); i++) {
            EnrollStudent tempEnrollStudent = enrollList[i];
            if (tempEnrollStudent != null) {
                Student tempStudent =
                        rutgersRoster.findStudent(tempEnrollStudent.getProfile());
                tempStudent.updateCredits(enrollList[i].getCreditsEnrolled());
            }
        }
        System.out.println("Credit completed has been updated.");
        System.out.println("** list of students eligible for graduation **");

        for (int i = 0; i < rutgersRoster.getSize(); i++) {
            int creditsForGraduation = 120;
            Student student = rosterList[i];
            if (student != null) {
                if (student.getCreditCompleted() >= creditsForGraduation) {
                    System.out.println(student.getProfile().toString() + " " +
                            student.getMajor().toString() + student.toString()
                            + rutgersRoster.getStanding(student).toString() +
                            student.getClassification());
                }
            }
        }
    }

    /**
     * Read the lines of user input and calls the respective command
     * dependent on the specific data token represented.
     */
    public void run() throws IOException {
        System.out.println("Tuition Manager running...");
        Scanner scanner = new Scanner(System.in);
        Roster rutgersRoster = new Roster();
        Enrollment rutgersEnroll = new Enrollment();
        while (scanner.hasNextLine()) {
            String dataToken = scanner.next();
            if (dataToken.equals("AR") || dataToken.equals("AI") ||
            dataToken.equals("AN") || dataToken.equals("AT")){
                commandAdd(scanner, rutgersRoster, dataToken);
            } else if (dataToken.equals("R")) {
                commandRemove(scanner, rutgersRoster);
            } else if (dataToken.equals("C")) {
                commandChangeMajor(scanner, rutgersRoster);
            } else if (dataToken.equals("L")) {
                listCommand(scanner, rutgersRoster);
            } else if (dataToken.equals("PS")) {
                if (rutgersRoster.isRosterEmpty())
                    System.out.println("Student roster is empty!");
                else { rutgersRoster.printByStanding(); }
            } else if (dataToken.equals("PC")) {
                if (rutgersRoster.isRosterEmpty())
                    System.out.println("Student roster is empty!");
                else { rutgersRoster.printBySchoolMajor(); }
            } else if (dataToken.equals("P")) {
                if (rutgersRoster.isRosterEmpty())
                    System.out.println("Student roster is empty!");
                else { rutgersRoster.print(); }
            } else if (dataToken.equals("Q")) {
                System.out.println("Tuition Manager terminated.");
                break;
            } else if (dataToken.equals("E")){
                commandEnrollStudent(scanner, rutgersRoster, rutgersEnroll);
            } else if (dataToken.equals("D")){
                dropStudent(scanner,rutgersEnroll);
            } else if (dataToken.equals("S")) {
                awardScholarship(scanner, rutgersRoster);
            } else if (dataToken.equals("PT")){
                displayTuition( rutgersEnroll, rutgersRoster);
            } else if (dataToken.equals("PE")){
                displayEnrollment(rutgersEnroll);
            } else if (dataToken.equals("SE")) {
                semesterEnd(rutgersRoster, rutgersEnroll);
            } else if (dataToken.equals("LS")) {
                loadCommand(scanner, rutgersRoster);
            } else {
                System.out.println(dataToken + " is an invalid command!");
            }
        }
    }

    /**
     * Load command method that reads from StudentList
     * @param scanner object to read lines
     * @param rutgersRoster Roster object that holds Students
     */
    private void loadCommand(Scanner scanner, Roster rutgersRoster) throws IOException {
        String dataString;
        String[] inputs;
        String fileName;
        Scanner studentList;
        try {
            dataString = scanner.nextLine();
            inputs = dataString.split(" ");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals("")) {
                    throw new NoSuchElementException();
                }
            }
            fileName = inputs[1];
            studentList = new Scanner(new File (fileName));
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Missing data in line.");
            return;
        }
        while (studentList.hasNextLine()) {
            String dataToken = studentList.nextLine();
            String[] inputsFromFile = dataToken.split(",");
            String typeOfStudent = inputsFromFile[0];
            String firstName = inputsFromFile[1];
            String lastName = inputsFromFile[2];
            String dateOfBirth = inputsFromFile[3];
            Date studentDate = new Date(dateOfBirth);
            String majorSubject = inputsFromFile[4];
            String creditsEnrolled = inputsFromFile[5];
            Student studentToAdd;
            Major studentMajor = returnMajor(majorSubject);
            if (studentMajor != null && studentDate.isValid() && isValidCreditsAndDate(studentDate,
                    creditsEnrolled, dateOfBirth)) {
                Profile studentProfile = new Profile(lastName, firstName, studentDate);
                int credits = Integer.parseInt(creditsEnrolled);
                studentToAdd = createStudentTypeLS(typeOfStudent,
                        studentProfile, studentMajor, credits, inputs);
                if (studentToAdd != null) {
                    if (!rutgersRoster.contains(studentToAdd)) {
                        rutgersRoster.add(studentToAdd);
                    }
                }
            }
        }
        System.out.println("Students loaded to the roster.");
    }

    /**
     * Extracts information from command to remove student from roster
     * @param scanner object to read lines
     * @param rutgersRoster roster object that holds students and size
     */
    private void commandRemove(Scanner scanner, Roster rutgersRoster) {
        String dataString;
        String[] inputs;
        String firstName;
        String lastName;
        String dateOfBirth;
        Date studentDate;
        try {
            dataString = scanner.nextLine();
            inputs = dataString.split("\\s++");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals("")) {
                    throw new NoSuchElementException();
                }
            }
            firstName = inputs[1];
            lastName = inputs[2];
            dateOfBirth = inputs[3];
            studentDate = new Date(dateOfBirth);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Missing data in line command.");
            return;
        }
            Profile studentProfile = new Profile(lastName, firstName, studentDate);

            if (rutgersRoster.remove(studentProfile))
                System.out.println(studentProfile + " removed from the roster.");
            else {
                System.out.println(studentProfile + " is not in the roster.");
            }
    }

    /**
     * Change the major of a student in Roster object
     * @param scanner object that reads in command input
     * @param rutgersRoster holds students in the roster array
     */
    private void commandChangeMajor(Scanner scanner, Roster rutgersRoster) {
        String dataString;
        String[] inputs;
        String firstName;
        String lastName;
        String dateOfBirth ;
        Date studentDate;
        String majorSubject;
        try {
            dataString = scanner.nextLine();
            inputs = dataString.split("\\s+");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals(""))
                    throw new NoSuchElementException();
            }
            firstName = inputs[1];
            lastName = inputs[2];
            dateOfBirth = inputs[3];
            studentDate = new Date(dateOfBirth);
            majorSubject = inputs[4];
        } catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Missing data in line command.");
            return;
        }
        Major studentMajor = returnMajor(majorSubject);
        if (studentMajor != null) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            Student student = rutgersRoster.findStudent(studentProfile);
            boolean studentFound = false;
            if (student != null) {
                studentFound = true;
                student.setMajor(studentMajor);
                System.out.println(studentProfile.toString()
                        + " major changed to " + majorSubject);
            }
            if (!studentFound)
                System.out.println(studentProfile.toString() + " is not in " +
                        "the roster.");
        }
    }

    /**
     * Determines if a school is valid
     * @param school String
     * @return false if school is not one of the valid schools, true otherwise.
     */
    private boolean isValidSchool(String school){
        if (!school.equalsIgnoreCase("SAS")
                && !school.equalsIgnoreCase("SC&I")
                && !school.equalsIgnoreCase("SOE")
                && !school.equalsIgnoreCase("RBS"))
        {
            return false;
        }
        return true;
    }
    /**
     * prints out students by school and sorts within each group by profile
     * @param scanner object to take in command
     * @param rutgersRoster of student objects to access students
     */
    private void listCommand(Scanner scanner, Roster rutgersRoster) {
        String dataString;
        String[] inputs;
        String school;
        try {
            dataString = scanner.nextLine();
            inputs = dataString.split("\\s++");
            for (int i = 1; i < inputs.length; i++) {
                if (inputs[i].equals(""))
                    throw new NoSuchElementException();
            }
            school = inputs[1];
        } catch (ArrayIndexOutOfBoundsException exception){
            System.out.println("Missing data in line command."); return; }
        if (isValidSchool(school)) {
            System.out.println("* Students in " + school + " *");
            Student[] studentArray = rutgersRoster.getRoster();
            for (int i = 0; i < rutgersRoster.getSize() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < rutgersRoster.getSize(); j++) {
                    if (studentArray[j] != null && studentArray[minIndex] != null) {
                        if (studentArray[j].compareTo(studentArray[minIndex]) < 0)
                            minIndex = j;
                        Student swapPosition = studentArray[minIndex];
                        studentArray[minIndex] = studentArray[i];
                        studentArray[i] = swapPosition;
                    }
                }
            }
            for (int k = 0; k < rutgersRoster.getSize(); k++) {
                Student student = studentArray[k];
                if (student != null) {
                    if (student.getSchool().compareToIgnoreCase(school) == 0)
                        System.out.println(student.getProfile().toString() +
                                student.getMajor().toString() + student.toString()
                                + rutgersRoster.getStanding(student).toString());
                }
            }
            System.out.println("* end of list **");
        } else { System.out.println("School doesn't exist: " + school); }
    }
}