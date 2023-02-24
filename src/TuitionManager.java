package src;
import java.util.Scanner;
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
     * Reads in command for adding a student to the roster
     * @param scanner object that reads in input
     * @param rutgersRoster roster for Students to be added
     */
    private void commandAdd(Scanner scanner, Roster rutgersRoster) {
        String firstName = scanner.next();
        String lastName = scanner.next();
        String dateOfBirth = scanner.next();
        String majorSubject = scanner.next();
        String creditCompleted = scanner.next();
        Date studentDate = new Date(dateOfBirth);
        Major studentMajor = returnMajor(majorSubject);
        boolean isValidCreditCompleted = true;
        if (!studentDate.isValid())
            System.out.println("DOB invalid: "
                    + dateOfBirth + " not a valid calendar date!");
        try {
            if (Integer.parseInt(creditCompleted) < 0) {
                System.out.println("Credits completed invalid: cannot be negative!");
                isValidCreditCompleted = false;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Credits completed invalid: not an integer!");
            isValidCreditCompleted = false;
        }
        if (studentMajor != null && studentDate.isValid() && isValidCreditCompleted) {
            Profile studentProfile = new Profile(lastName, firstName, studentDate);
            int credits = Integer.parseInt(creditCompleted);
            Student student = new Student(studentProfile, studentMajor, credits);
            if (rutgersRoster.contains(student))
                System.out.println(firstName + " " + lastName + " " + dateOfBirth
                        + " is already in the roster.");
            else {
                if (rutgersRoster.add(student))
                    System.out.println(firstName + " " + lastName + " " +
                            dateOfBirth + " added to the roster.");
                else
                    System.out.println("DOB invalid: " + studentDate +
                            " younger than 16 years old.");
            }
        }
    }

    /**
     * Read the lines of user input and calls the respective command
     * dependent on the specific data token represented.
     */
    public void run() {
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        Roster rutgersRoster = new Roster();
        while (scanner.hasNextLine()) {
            String dataToken = scanner.next();
            if (dataToken.equals("A")) {
                commandAdd(scanner, rutgersRoster);
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
                System.out.println("Roster Manager terminated.");
                break;
            } else {
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
        Student removeStudent = new Student(studentProfile);

        if(rutgersRoster.remove(removeStudent))
            System.out.println(studentProfile.toString() + "removed from the roster.");
        else{
            System.out.println(studentProfile.toString() + "is not in the roster.");
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