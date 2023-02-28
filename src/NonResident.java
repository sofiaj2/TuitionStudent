package src;

/**
 * NonResident class that is an extension of student.
 * Has methods to determine if resident, and tuition.
 */
public class NonResident extends Student {
    protected final int nonResidentTuition = 29737;
    protected final int creditHour = 966;

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
        if (creditsEnrolled > creditHourLimit)
            return (nonResidentTuition + universityFee + ((creditsEnrolled -
                    creditHourLimit) * creditHour));
        return nonResidentTuition + universityFee;
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
}
