package src;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public Enrollment() {
        this.size = 4;
        this.enrollStudents = new EnrollStudent[this.size];
    }

    public void add(EnrollStudent enrollStudent) { //add to the end of array
        if (isEnrollmentFull(this.enrollStudents)) {
            this.grow();
        }

        if (!contains(enrollStudent)) { //add
            // enrollStudent for the first time
            if (!isEnrollmentFull(enrollStudents)) {
                for (int i = 0; i < this.size; i++) {
                    if (enrollStudents[i] == null) {
                        enrollStudents[i] = enrollStudent;
                        return;
                    }
                }
            }
        }

    }

    //move the last one in the array to replace the deleting index position
    private boolean isEnrollmentFull(EnrollStudent[] enrollStudentsInstance) {
        for (int i = 0; i < this.size; i++) {
            if (enrollStudentsInstance[i] == null) {
                return false;
            }
        }
        return true;
    }

    private void grow() {
        int increaseByValue = 4;
        EnrollStudent[] tempEnrollment =
                new EnrollStudent[this.size + increaseByValue];
        for (int i = 0; i < this.size; i++) {
            tempEnrollment[i] = new EnrollStudent(this.enrollStudents[i]);
        }
        this.enrollStudents = tempEnrollment;
        this.size += increaseByValue;
    }

    public void remove(EnrollStudent enrollStudent) {
        boolean studentExists = false;
        // instantiate the variable that will contain the index of the
        // student to remove:
        int nullIndex = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i] != null) {
                if (this.enrollStudents[i].equals(enrollStudent)) {
                    studentExists = true;
                    nullIndex = i;
                    this.enrollStudents[i] = null;
                    break;
                }
            }
        }
        for (int i = this.size; i-- > 0; ) {
            if (this.enrollStudents[i] != null) {
                this.enrollStudents[nullIndex] = this.enrollStudents[i];
                this.enrollStudents[i] = null;
                break;
            }
        }
    }

    public boolean contains(EnrollStudent enrollStudent) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i] != null) {
                if (this.enrollStudents[i].equals(enrollStudent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public EnrollStudent find(EnrollStudent enrollStudent) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i] != null) {
                if (this.enrollStudents[i].equals(enrollStudent)) {
                    return this.enrollStudents[i];
                }
            }
        }
        return null;
    }
    public void print(){
        for (int i = 0; i < this.size; i++){
            System.out.println(this.enrollStudents[i].getProfile().toString()
                    + ":" + "credits enrolled: "
                    + this.enrollStudents[i].getCreditsEnrolled());
        }
    } //print the array as is without sorting

    public void updateCredits(Profile studentProfile, int credits) {
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i] != null) {
                if (this.enrollStudents[i].getProfile().equals(studentProfile)){
                    this.enrollStudents[i].setCreditsEnrolled(credits);
                }
            }
        }
    }
}