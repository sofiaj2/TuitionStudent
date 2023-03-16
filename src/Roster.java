package src;
import java.util.Calendar;

/**
 * Roster class that maintains Roster object
 * Has methods like find, add, and grow for student management
 * @author Sofia Juliani, Arnold Nguyen
 */
public class Roster {
    private Student[] roster;
    private int size;
    public static final int NOT_FOUND = -1;

    /**
     * Initialization of Roster object
     */
    public Roster() {
        this.size = 4;
        this.roster = new Student[this.size];
    }

    /**
     * Finds a student within the roster and prints out index of student
     * @param student object that represents student to be found
     * @return index of student, with -1 if student is not found
     */
    private int find(Student student) { //search the given student in roster
        for (int i = 0; i < this.size; i++)
            if (this.roster[i].equals(student)) {
                return i;
            }
        return NOT_FOUND;
    }

    /**
     * Find student in the Roster
     * @param profile attribute to use equals to find student
     * @return the student in the roster
     */
    public Student findStudent(Profile profile) {
        for (int i = 0; i < this.size; i++)
            if (this.roster[i] != null) {
                if (this.roster[i].getProfile().equals(profile)) {
                    return this.roster[i];
                }
            }
        return null;
    }

    /**
     * Grows the array capacity of roster when needed by 4
     */
    private void grow() { //increase the array capacity by 4
        int increaseByValue = 4;
        Student[] tempRoster = new Student[this.size + increaseByValue];
        for (int i = 0; i < this.size; i++) {
            Student tempStudent = this.roster[i];
            Profile tempProfile = this.roster[i].getProfile();
            Major tempMajor = this.roster[i].getMajor();
            int tempCredits = this.roster[i].getCreditCompleted();

            if (tempStudent.isResident()) { //copy a resident
                tempRoster[i] = new Resident(tempProfile, tempMajor, tempCredits);
            }
            else {
                String type = tempStudent.getClassification();
                if (type.equals("(non-resident)")) { //copy a non-resident
                    tempRoster[i] = new NonResident(tempProfile, tempMajor, tempCredits);
                }
                else if (type.equals("(non-resident)(tri-state:NY)")) { //tri-state student
                    tempRoster[i] = new TriState(tempProfile, tempMajor,
                            tempCredits, "NY");
                }
                else if (type.equals("(non-resident)(tri-state:CT)")) {
                    tempRoster[i] = new TriState(tempProfile, tempMajor,
                            tempCredits, "CT");
                }
                else if (type.equals("(non-resident)(international:study abroad)")) {
                    tempRoster[i] = new International(tempProfile,
                            tempMajor, tempCredits, true);
                }
                else if (type.equals("(non-resident)(international)")) {
                    tempRoster[i] = new International(tempProfile,
                            tempMajor, tempCredits, false);
                }
            }
        }
        this.roster = tempRoster;
        this.size += increaseByValue;
    }

    /**
     * Roster method that adds a student to the roster. Checks if
     * the student is in the roster already.
     * @param student object to be added to the roster
     * @return true if student has been added successfully, false if not
     */
    public boolean add(Student student) {
        Date studentDob = student.getProfile().getDob();
        Date today = new Date();
        int minimumAge = 16;
        int youngestYear = today.getYear() - minimumAge;
        int todayMonth = today.getMonth();
        int todayDay = today.getDay();
        Date youngestDob = new Date(todayMonth + "/" + todayDay + "/" + youngestYear);
        if (studentDob.compareTo(youngestDob) == 1) {
            return false;
        }
        //check if we need to grow roster array:
        boolean isFull = isRosterFull(this.roster);
        if (isFull)
        {
            this.grow();
        }
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i] == null) {
                this.roster[i] = student;
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether the roster is full
     * @param rosterInstance is an array of Students
     * @return returns true if the roster is full, false otherwise
     */
    private boolean isRosterFull(Student[] rosterInstance) {
        for (int i = 0; i < this.size; i++) {
            if (rosterInstance[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the roster is empty
     * @return true if the roster is empty, false otherwise
     */
    public boolean isRosterEmpty() {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes selected student from the roster. Checks if student is in the
     * roster.
     * @param student object to be removed from the roster
     * @return true if student has been successfully removed, false if not.
     */
    public boolean remove(Student student) { //maintain the order after remove
        boolean studentExists = false;
        // instantiate the variable that will contain the index of the
        // student to remove:
        int nullIndex = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i] != null) {
                if (this.roster[i].getProfile().equals(student.getProfile())) {
                    studentExists = true;
                    nullIndex = i;
                    this.roster[i] = null;
                    break;
                }
            }
        }
        if (studentExists && nullIndex > -1) {
            for (int i = nullIndex; i < this.size - 1; i++) {
                this.roster[i] = this.roster[i + 1];
            }
            return true;
        }
        return false;
    }

    public boolean remove(Profile profile) { //maintain the order after remove
        boolean studentExists = false;
        // instantiate the variable that will contain the index of the
        // student to remove:
        int nullIndex = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i] != null) {
                if (this.roster[i].getProfile().equals(profile)) {
                    studentExists = true;
                    nullIndex = i;
                    this.roster[i] = null;
                    break;
                }
            }
        }
        if (studentExists && nullIndex > -1) {
            for (int i = nullIndex; i < this.size - 1; i++) {
                this.roster[i] = this.roster[i + 1];
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if student is in the roster class
     * @param student object to be found in roster
     * @return true if student is found in the roster, false otherwise.
     */
    public boolean contains(Student student) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i] != null) {
                if (this.roster[i].equals(student)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(Profile profile) {
        for (int i = 0; i < this.size; i++) {
            if (this.roster[i] != null) {
                if (this.roster[i].getProfile().equals(profile)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Print students in the roster, sorted by profile.
     */
    public void print() {
        System.out.println("** Student roster sorted by last name, first " +
                "name, DOB **");
        for (int i = 0; i < this.size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < this.size; j++) {
                if (this.roster[j] != null && this.roster[minIndex] != null) {

                    if (this.roster[j].compareTo(this.roster[minIndex]) < 0)
                        minIndex = j;
                }
            }
            if (this.roster[i] != null && this.roster[minIndex] != null) {
                Student swapPosition = this.roster[minIndex];
                this.roster[minIndex] = this.roster[i];
                this.roster[i] = swapPosition;
            }
        }
        for (int k = 0; k < this.size; k++) {
            Student student = this.roster[k];
            if (student != null)
                System.out.println(student.getProfile().toString() + " " +
                        student.getMajor().toString() + student.toString()
                        + getStanding(student).toString()
                        + student.getClassification());
        }
        System.out.println("* end of roster *");
    }

    /**
     * Prints the roster object by sorting by school and then major if same
     * school. Uses compareTo to sort as part of selection sort.
     */
    public void printBySchoolMajor() { //print roster sorted by school, major
        System.out.println("** Student roster sorted by school, major **");
        for (int i = 0; i < this.size - 1; i++)
        {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (this.roster[j] != null && this.roster[minIndex] != null) {
                    if (this.roster[j].getSchool().toString().compareTo
                            (this.roster[minIndex].getSchool().toString()) < 0)
                        minIndex = j;
                    else if (this.roster[j].getSchool().toString().compareTo
                            (this.roster[minIndex].getSchool().toString()) == 0) {
                        if (this.roster[j].getMajor().toString().compareTo
                                (this.roster[minIndex].getMajor().toString()) < 0)
                            minIndex = j;
                    }
                }
            }
            if (this.roster[i] != null && this.roster[minIndex] != null) {
                Student swapPosition = this.roster[minIndex];
                this.roster[minIndex] = this.roster[i];
                this.roster[i] = swapPosition;
            }
        }
        for (int k = 0; k < this.size; k++) {
            Student student = this.roster[k];
            if (student != null)
                System.out.println(student.getProfile().toString() + " " +
                        student.getMajor().toString() + student.toString()
                        + getStanding(student).toString()
                        + student.getClassification());
        }
        System.out.println("* end of roster *");
    }

    /**
     * prints roster sorted by standing by using compareTo method
     */
    public void printByStanding() { //print roster sorted by standing
        System.out.println("** Student roster sorted by standing **");
        for (int i = 0; i < this.size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < this.size; j++) {
                if (this.roster[j] != null && this.roster[minIndex] != null) {
                    if (getStanding(this.roster[j]).toString().compareTo
                            (getStanding(this.roster[minIndex]).toString()) < 0)
                        minIndex = j;
                }
            }

            if (this.roster[i] != null && this.roster[minIndex] != null) {
                Student swapPosition = this.roster[minIndex];
                this.roster[minIndex] = this.roster[i];
                this.roster[i] = swapPosition;
            }
        }

        for (int k = 0; k < this.size; k++) {
            Student student = this.roster[k];
            if (student != null)
                System.out.println(student.getProfile().toString() + " " +
                        student.getMajor().toString() + student.toString()
                        + getStanding(student).toString()
                        + student.getClassification());
        }
        System.out.println("* end of roster *");
    }

    /**
     * Getter method for Student object outputs standing for a Student
     * @param student object to obtain standing
     * @return standing for Student object
     */
    public Standing getStanding(Student student)
    {
        Standing freshman = Standing.Freshman;
        Standing sophomore = Standing.Sophomore;
        Standing junior = Standing.Junior;
        Standing senior = Standing.Senior;
        int numCredits = student.getCreditCompleted();
        if (numCredits < sophomore.getCreditLowerBound()) {
            return freshman;
        } else if (numCredits < junior.getCreditLowerBound()) {
            return sophomore;
        } else if (numCredits < senior.getCreditLowerBound()) {
            return junior;
        }
        return senior;
    }

    /**
     * Gets size attribute from Roster
     * @return size attribute which is an int
     */
    public int getSize() {
        return this.size;
    }

    /**
     * gets roster array of students
     * @return roster array carrying student objects
     */
    public Student[] getRoster() {
        return this.roster;
    }

}