package src;

/**
 * NonResident class that is an extension of student.
 * Has methods to determine if resident, and tuition.
 */
public class NonResident extends Student {
    protected final double nonResidentTuition = 29737;
    protected final double creditHour = 966;

    /**
     * Constructor for NonResident with super
     * @param givenProfile Profile object for NonResident
     * @param givenMajor Major object for NonResident constructor
     * @param Credits parameter for NonResident constructor
     */
    public NonResident(Profile givenProfile, Major givenMajor, int Credits)
    {
        super(givenProfile, givenMajor, Credits);
    }

    /**
     * Determines if student is a resident or not (clearly not)
     * @return false since nonresident obj
     */
    public boolean isResident() {
        return false;
    }

    /**
     * Determines the tuition for a nonresident
     * @param creditsEnrolled credits of the nonresident
     * @return the tuition due for the student
     */
    public double tuitionDue(int creditsEnrolled){
        double tuition;
        if (isFullTime(creditsEnrolled)) { //full time student
            tuition = universityFee + nonResidentTuition;
            if (creditsEnrolled > creditHourLimit) {
                tuition =
                        (creditHour * (creditsEnrolled - creditHourLimit)) + tuition;
            }
        }
        else { //part time student
            tuition = (creditHour * creditsEnrolled)
                    + (percentFullTimeRate * universityFee);
        }
        return tuition;
    }

    /**
     * Returns the classification of the nonResident
     * @return "(non-resident)" as String
     */
    public String getClassification() {
        return "(non-resident)";
    }

    /**
     * Invalid student determiner, returns special (Non-Resident)
     * @return String (Non-Resident)
     */
    public String invalidStudent() {
        return "(Non-Resident) ";
    }

    public boolean isFullTime(int creditsEnrolled) {
        if (creditsEnrolled >= 12)
            return true;
        return false;
    }
}
