package src;

/**
 * International class that is an extension of NonResident. Handles
 * various methods such as determining if an International is valid.
 */
public class International extends NonResident{
    private boolean isStudyAbroad;
    private static final int minInternationalCredits = 12;
    private static final int maxInternationalCredits = 12;
    private static final double healthInsurance = 2650;

    /**
     * Constructor for the International object
     * @param givenProfile parameter for International obj
     * @param givenMajor major attribute for international
     * @param Credits taken attribute for international
     * @param studiesAbroad determines if international in study abroad
     */
    public International(Profile givenProfile, Major givenMajor,
                         int Credits, boolean studiesAbroad){
        super(givenProfile, givenMajor, Credits);
        this.isStudyAbroad = studiesAbroad;
    }

    /**
     * Determines if an International student is valid through credits
     * @param creditEnrolled credits that International student is taking
     * @return
     */
    @Override
    public boolean isValid(int creditEnrolled){ //override Student isValid
        // max number that study abroad international student is 12
        if (this.isStudyAbroad) {
            if (creditEnrolled >= minCredit
                    && creditEnrolled <= maxInternationalCredits)
                return true;
            else
                return false;
        }
        else {
            if (creditEnrolled >= minInternationalCredits
                    && creditEnrolled <= maxCredit)
                return true;
            else
                return false;
        }
    }

    /**
     * Calculates the tuitionDue for an International
     * @param creditsEnrolled credits of the nonresident
     * @return the tuition due for an International object
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if (this.isStudyAbroad) {
            return universityFee + healthInsurance;
        }
        else {
            return super.tuitionDue(creditsEnrolled) + healthInsurance;
        }
    }

    /**
     * Returns a String of the classification of an International
     * @return string of classification for International
     */
    @Override
    public String getClassification() {
        if (this.isStudyAbroad) {
            return "(non-resident)(international:study abroad)";
        }
        return "(non-resident)(international)";

    }

    /**
     * Another classification String method for International student
     * @return String for International object
     */
    @Override
    public String invalidStudent() {
        if (this.isStudyAbroad) {
            return "(International studentstudy abroad) ";
        }
        return "(International student) ";
    }

}
