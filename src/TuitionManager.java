package src;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.DecimalFormat;
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

    private void commandAdd(Scanner scanner, Roster rutgersRoster,
                             Enrollment rutgersEnroll, String dataToken) {
        String firstName;
        String lastName;
        String dateOfBirth;
        String majorSubject;
        String creditsEnrolled;
        Major studentMajor;
        Date studentDate;
        try {
            firstName = scanner.next();
            lastName = scanner.next();
            dateOfBirth = scanner.next();
            majorSubject = scanner.next();
            creditsEnrolled = scanner.next();
            studentMajor = returnMajor(majorSubject);
            studentDate = new Date(dateOfBirth);
        } catch (NoSuchElementException exception) {
            System.out.println("Missing data in line command.");
            return; }
        boolean isValidCreditCompleted = isValidCreditsAndDate(studentDate,
                creditsEnrolled, dateOfBirth);
        if (studentMajor != null && studentDate.isValid() && isValidCreditCompleted) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            int credits = Integer.parseInt(creditsEnrolled);
            Student student = createStudentType(dataToken, studentProfile, studentMajor,
                    credits, scanner);
            addRoster(student, rutgersRoster, firstName, lastName, dateOfBirth
            , studentDate);
        }
    }
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
    private Student createStudentType(String dataToken,
                                      Profile studentProfile,
                                      Major studentMajor,
                                      int credits, Scanner scanner) {
        if (dataToken.equals("AR")) {
            return new Resident(studentProfile, studentMajor, credits);
        } else if (dataToken.equals("AT")) {
            String state;
            try {
                state = scanner.next();
            }
            catch (NoSuchElementException exception) {
                System.out.println("Missing data in line command.");
                return null;
            }
            if (state.equalsIgnoreCase("CT") || state.equalsIgnoreCase("NY")) {
                return new TriState(studentProfile, studentMajor, credits,
                    state);
            }
            else {
                return null;
            }
        } else if (dataToken.equals("AI")) {
            boolean studiesAbroad;
            try {
                studiesAbroad = scanner.nextBoolean();
            }
            catch (NoSuchElementException exception) {
                studiesAbroad = false;
            }
            return new International(studentProfile,
                    studentMajor, credits, studiesAbroad);
        } else if (dataToken.equals("AN")) {
            return new NonResident(studentProfile,
                    studentMajor, credits);
        } else
            return null;
    }

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
    private void commandEnrollStudent(Scanner scanner, Roster rutgersRoster,
                               Enrollment rutgersEnroll){
        String firstName;
        String lastName;
        String dateOfBirth;
        String creditsEnrolled;
        Date studentDate;
        try {
            firstName = scanner.next();
            lastName = scanner.next();
            dateOfBirth = scanner.next();
            creditsEnrolled = scanner.next();
            studentDate = new Date(dateOfBirth);
        }
        catch (NoSuchElementException exception) {
            System.out.println("Missing data in line command.");
            return;
        }
        if (studentDate.isValid() && isValidCreditsAndDate(studentDate,
                creditsEnrolled, dateOfBirth)) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            int credits = Integer.parseInt(creditsEnrolled);
            EnrollStudent studentToEnroll =
                    new EnrollStudent(studentProfile, credits);
            if (!rutgersRoster.contains(studentProfile)) { //not in roster
                System.out.println("Cannot enroll: " + studentProfile.toString() +
                        " is not in the roster.");
            }
            else {
                Student rosterStudent = rutgersRoster.findStudent(studentProfile);
                if (!rosterStudent.isValid(credits)) { //not valid credits
                    System.out.println(rosterStudent.invalidStudent() + credits +
                            ": invalid credit hours.");
                }
                else {
                    if (!rutgersEnroll.contains(studentToEnroll)) {
                        rutgersEnroll.add(studentToEnroll);
                    }
                    else {
                        rutgersEnroll.updateCredits(studentProfile, credits);
                    }
                }
            }
        }

    }

    private void dropStudent(Scanner scanner, Enrollment rutgersEnroll){
        String firstName;
        String lastName;
        String dateOfBirth;
        Date studentDate;

        try {
            firstName = scanner.next();
            lastName = scanner.next();
            dateOfBirth = scanner.next();
            studentDate = new Date(dateOfBirth);
        }
        catch (NoSuchElementException exception) {
            System.out.println("Missing data in line command.");
            return;
        }
        Profile studentProfile = new Profile(lastName, firstName, studentDate);
        EnrollStudent studentToDrop = new EnrollStudent(studentProfile);
        if (rutgersEnroll.contains(studentToDrop)) {
            rutgersEnroll.remove(studentToDrop);
            System.out.println(studentProfile + " dropped");
        }
        else {
            System.out.println(studentProfile + "  is not enrolled.");
        }

    }

    private void awardScholarship(Scanner scanner, Roster rutgersRoster){
        String firstName;
        String lastName;
        String dateOfBirth;
        String scholarshipString;
        Date studentDate;
        try {
            firstName = scanner.next();
            lastName = scanner.next();
            dateOfBirth = scanner.next();
            scholarshipString = scanner.next();
            studentDate = new Date(dateOfBirth);
        } catch (NoSuchElementException exception) {
            System.out.println("Missing data in line command.");
            return;
        }
        Profile studentProfile = new Profile(lastName, firstName, studentDate);
        if (studentDate.isValid() && isValidScholarshipAndDate(scholarshipString, studentDate, dateOfBirth)) {
            if (rutgersRoster.contains(studentProfile)){
                Student studentToAward = rutgersRoster.findStudent(studentProfile);
                if (studentToAward.isResident()) { //check if resident
                    Resident residentToAward = (Resident) studentToAward;
                    if (residentToAward.isFullTime()) {
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
        System.out.println("** Enrollment **");
        rutgersEnroll.print();
        System.out.println("* end of enrollment *");
    }

    /**
     *
     * @param rutgersEnroll the Enrollment list for
     * @param rutgersRoster a roster of students to select from
     */
    private void displayTuition(Enrollment rutgersEnroll,
                               Roster rutgersRoster){
        System.out.println("** Tuition due **");
        EnrollStudent[] enrollList = rutgersEnroll.getEnrollStudents();
        Student[] rosterList = rutgersRoster.getRoster();
        for (int i = 0; i < rutgersEnroll.getSize(); i++){
            EnrollStudent tempEnrollStudent = enrollList[i];
            if (tempEnrollStudent != null) {
                DecimalFormat df = new DecimalFormat( "#.00" );
                Student tempStudent = rutgersRoster.findStudent(tempEnrollStudent.getProfile());
                String formattedTuition = df.format(tempStudent.tuitionDue(tempStudent.getCreditCompleted()));
                System.out.println(tempStudent.getProfile().toString() +
                        " " + tempStudent.invalidStudent() + " enrolled " +
                        tempStudent.getCreditCompleted() + " credits: tuition " +
                        "due: " + formattedTuition);
            }
        }
        System.out.println("* end of tuition due");
    }
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
    public void run() {
        System.out.println("Tuition Manager running...");
        Scanner scanner = new Scanner(System.in);
        Roster rutgersRoster = new Roster();
        Enrollment rutgersEnroll = new Enrollment();
        while (scanner.hasNextLine()) {
            String dataToken = scanner.next();
            if (dataToken.equals("AR") || dataToken.equals("AI") ||
            dataToken.equals("AN") || dataToken.equals("AT")){
                commandAdd(scanner, rutgersRoster, rutgersEnroll, dataToken);
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
            }
            else if (dataToken.equals("S")) {
                awardScholarship(scanner, rutgersRoster);
            }
            else if (dataToken.equals("PT")){
                displayTuition( rutgersEnroll, rutgersRoster);
            }
            else if (dataToken.equals("PE")){
                displayEnrollment(rutgersEnroll);
            }
            else if (dataToken.equals("SE")) {
                semesterEnd(rutgersRoster, rutgersEnroll);
            }
            else {
                System.out.println(dataToken + " is an invalid command!");
            }
        }
    }

    /**
     * Extracts information from command to remove student from roster
     * @param scanner object to read lines
     * @param rutgersRoster roster object that holds students and size
     */
    private void commandRemove(Scanner scanner, Roster rutgersRoster) {
        String firstName = scanner.next();
        String lastName = scanner.next();
        String dateOfBirth = scanner.next();

        Date studentDate = new Date(dateOfBirth);
        Profile studentProfile = new Profile(lastName, firstName, studentDate);

        if(rutgersRoster.remove(studentProfile))
            System.out.println(studentProfile + " removed from the roster.");
        else{
            System.out.println(studentProfile + " is not in the roster.");
        }
    }

    /**
     * Change the major of a student in Roster object
     * @param scanner object that reads in command input
     * @param rutgersRoster holds students in the roster array
     */
    private void commandChangeMajor(Scanner scanner, Roster rutgersRoster) {
        String firstName = scanner.next();
        String lastName = scanner.next();
        String dateOfBirth = scanner.next();
        String majorSubject = scanner.next();
        Date studentDate = new Date(dateOfBirth);

        Major studentMajor = returnMajor(majorSubject);
        if (studentMajor != null) {
            boolean studentFound = false;
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            for (int i = 0; i < rutgersRoster.getSize(); i++) {
                Student[] studentArray = rutgersRoster.getRoster();
                if (studentArray[i] != null) {
                    if ((studentArray[i].getProfile().compareTo(studentProfile)) == 0) {
                        studentArray[i].setMajor(studentMajor);
                        System.out.println(studentProfile.toString()
                                + "major changed to " + majorSubject);
                        studentFound = true;
                        break;
                    }
                }
            }
            if (!studentFound) {
                System.out.println(studentProfile.toString() + "is not in the roster.");
            }
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
        String school = scanner.next();
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
        } else {
            System.out.println("School doesn't exist: " + school);
        }
    }
}