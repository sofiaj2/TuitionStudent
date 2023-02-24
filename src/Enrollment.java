package src;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;
    public void add(EnrollStudent enrollStudent)
    {
        if (!contains(enrollStudent)){
        if (!isEnrollmentFull(enrollStudents)){
            for (int i = 0; i < this.size; i++)
            {
                if (enrollStudents[i] == null)
                {
                    enrollStudents[i] = enrollStudent;
                    return;
                }
            }
        }}
    } //add to the end of array
    //move the last one in the array to replace the deleting index position
    private boolean isEnrollmentFull(EnrollStudent[] enrollStudents) {
        for (int i = 0; i < this.size; i++) {
            if (enrollStudents[i] == null) {
                return false;
            }
        }
        return true;
    }

    public void remove(EnrollStudent enrollStudent)
    {
        boolean studentExists = false;
        // instantiate the variable that will contain the index of the
        // student to remove:
        int nullIndex = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i] != null) {
                if (this.enrollStudents[i].equals(enrollStudent)){
                    studentExists = true;
                    nullIndex = i;
                    this.enrollStudents[i] = null;
                    break;
                }
            }
        }
        for (int i = this.size; i-- > 0;){
            if (this.enrollStudents[i] != null){
                this.enrollStudents[nullIndex] = this.enrollStudents[i];
                this.enrollStudents[i] = null;
                break;
            }
        }
    }

    public boolean contains(EnrollStudent enrollStudent){
        for (int i = 0; i < this.size; i++) {
            if (this.enrollStudents[i] != null) {
                if (this.enrollStudents[i].equals(enrollStudent)){
                    return true;
                }
            }
        }
        return false;
    }

    public void print(){
        for (int i = 0; i < this.size; i++){
            System.out.println(this.enrollStudents[i].getProfile().toString()
                    + ":" + "credits enrolled: "
                    + this.enrollStudents[i].getCreditsEnrolled());
        }
    } //print the array as is without sorting
}