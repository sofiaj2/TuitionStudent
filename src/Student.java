package src;

/**
 * Student class that initializes Student objects
 * with Profile object, Major object and creditCompleted.
 * Has various student methods like compareTo
 * @author Sofia Juliani, Arnold Nguyen
 */
public abstract class  Student implements Comparable<Student> {
    private Profile profile;
    private Major major;
    private int creditCompleted;
    protected static final int universityFee = 3268;
    protected static final int minCredit = 3, maxCredit = 24;
    protected static final int creditHourLimit = 16;
    protected static final double percentFullTimeRate = 0.8;

    /**
     * Constructor class for Student object
     * @param givenProfile A profile object for the student
     * @param givenMajor A major object to create student
     * @param givenCredits Credit Completed object
     */
    public Student(Profile givenProfile, Major givenMajor, int givenCredits) {
        this.profile = givenProfile;
        this.major = givenMajor;
        this.creditCompleted = givenCredits;
    }

    /**
     * Extracts information attributes from Student Object
     * @param student object
     */
    public Student(Student student) {
        this.profile = student.profile;
        this.major = student.major;
        this.creditCompleted = student.creditCompleted;
    }

    /**
     * Profile only constructor for Student
     * @param givenProfile is Profile attribute
     */
    public Student(Profile givenProfile) {
        this.profile = givenProfile;
    }

    /**
     * Override equals method
     * @param obj for Student obj
     * @return true if the 2 Students have the same major, profile,
     * and credits completed, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student studentObj = (Student) obj;
            if (studentObj.major.compareTo(this.major) == 0){
                if (studentObj.profile.compareTo(this.profile) == 0){
                    if (studentObj.creditCompleted == this.creditCompleted){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Getter method to getMajor from Student object
     * @return attribute
     */
    public Major getMajor(){
        return this.major;
    }

    /**
     * Getter method to get school from Student object
     * @return school attribute
     */
    public String getSchool(){
        return this.major.getSchool();
    }

    /**
     * Getter method to get profile from Student object
     * @return profile attribute
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Getter method for creditCompleted
     * @return creditCompleted attribute
     */
    public int getCreditCompleted(){
        return this.creditCompleted;
    }

    /**
     * Setter method for Major
     * @param newMajor to change major attribute
     */
    public void setMajor(Major newMajor) {
        this.major = newMajor;
    }

    /**
     * Compares students
     * @param student object to compare student
     * @return 0 if same profile, 1 if this.profile greater, -1 if less
     */
    @Override
    public int compareTo(Student student) {
        if (this.profile.compareTo(student.profile) > 0)
            return 1;
        else if (this.profile.compareTo(student.profile) < 0)
            return -1;
        return 0;
    }

    /**
     * Override toString method
     * @return String representation of Student object
     */
    @Override
    public String toString() {
        return " credits completed: " + this.creditCompleted + " ";
    }

    public boolean isValid(int creditEnrolled){
        if (creditEnrolled >= minCredit && creditEnrolled <= maxCredit)
            return true;
        else
            return false;
    }

    public void updateCredits(int creditsToAdd) {
        this.creditCompleted += creditsToAdd;
    }

    public abstract double tuitionDue(int creditsEnrolled);

    public abstract boolean isResident();

    public abstract String invalidStudent();

    public abstract String getClassification();

    /**
     * Testbed main for Student class test cases for Student methods
     * @param args array of characters passed to main
     */
    public static void main(String[] args) {
        //TEST CASE 1: compare 2 students with the same attributes, should print 0
        Date d1 = new Date("1/1/2002");
        Major m1 = Major.CS;
        Profile p1 = new Profile("computer", "angela", d1);
        Student s1 = new Student(p1, m1, 30);

        Date d2 = new Date("1/1/2002");
        Major m2 = Major.CS;
        Profile p2 = new Profile("Computer", "Angela", d2);
        Student s2 = new Student(p2, m2, 30);

        System.out.println(s1.compareTo(s2)); //prints 0, equal objects

        //TEST CASE 2: compare 2 students with the different attributes, should print -1
        Date d3 = new Date("1/1/2002");
        Major m3 = Major.CS;
        Profile p3 = new Profile("computer", "angela", d3);
        Student s3 = new Student(p3, m3, 30);

        Date d4 = new Date("1/1/2002");
        Major m4 = Major.CS;
        Profile p4 = new Profile("Computer", "Sabrina", d4);
        Student s4 = new Student(p4, m4, 30);

        System.out.println(s3.compareTo(s4)); //prints -1, fname is different

        //TEST CASE 3: compare 2 students with the different attributes, should print 1
        Date d5 = new Date("5/1/2002");
        Major m5 = Major.CS;
        Profile p5 = new Profile("computer", "angela", d5);
        Student s5 = new Student(p5, m5, 30);

        Date d6 = new Date("1/1/2002");
        Major m6 = Major.CS;
        Profile p6 = new Profile("Computer", "Angela", d6);
        Student s6 = new Student(p6, m6, 30);

        System.out.println(s5.compareTo(s6)); //prints 1, dob is different
        System.out.println(s5.getMajor().toString());
    }
}
