package src;

/**
 * EnrollStudent class that represents enrolled students.
 * Has various methods to handle EnrollStudent objects like equals and
 * compareTo.
 */
public class EnrollStudent implements Comparable<EnrollStudent> {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * Constructor for EnrollStudent with Profile and creditsEnrolled
     * @param givenProfile Profile object to take in for EnrollStudent
     * @param creditsEnrolled attribute for EnrollStudent object
     */
    public EnrollStudent(Profile givenProfile, int creditsEnrolled){
        this.profile = givenProfile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Another constructor for EnrollStudent for only Profile
     * @param givenProfile Profile object to take in for EnrollStudent
     */
    public EnrollStudent(Profile givenProfile){
        this.profile = givenProfile;
    }

    /**
     * Initialization of EnrollStudent
     * @param studentToEnroll that creates EnrollStudent object
     */
    public EnrollStudent(EnrollStudent studentToEnroll) {
        this.profile = studentToEnroll.profile;
        this.creditsEnrolled = studentToEnroll.creditsEnrolled;
    }

    /**
     * Override of compareTo method that compares EnrollStudent objects
     * @param studentEnrolled the object to be compared.
     * @return 1 if studentEnroll objects are equivalent, -1 if not
     */
    @Override
    public int compareTo(EnrollStudent studentEnrolled) {
        if (this.profile.compareTo(studentEnrolled.profile) > 0)
            return 1;
        else if (this.profile.compareTo(studentEnrolled.profile) < 0)
            return -1;
        return 0;
    }

    /**
     * Determines if two EnrollStudent obj are equal
     * @param obj enrollStudent objects
     * @return true if enrollStudent objs are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EnrollStudent) {
            EnrollStudent studentEnrollObj = (EnrollStudent) obj;
            if (studentEnrollObj.profile.equals(this.profile))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * get Profile attribute for EnrollObject
     * @return profile of the object
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Get the creditEnrolled attribute of EnrollStudent object
     * @return creditsEnrolled attribute of EnrollStudent object
     */
    public int getCreditsEnrolled() {return this.creditsEnrolled;}

    /**
     * Set the credits enrolled of an EnrollStudent
     * @param newCredits that represents new credits to be changed to
     */
    public void setCreditsEnrolled(int newCredits) {
        this.creditsEnrolled = newCredits;
    }

}