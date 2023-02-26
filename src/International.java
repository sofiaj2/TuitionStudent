package src;

public class International extends NonResident{
    private boolean isStudyAbroad;
    private static final int minInternationalCredits = 12;
    private static final int maxInternationalCredits = 12;
    private static final int healthInsurance = 2650;

    public International(Profile givenProfile, Major givenMajor,
                         int Credits, boolean studiesAbroad){
        super(givenProfile, givenMajor, Credits);
        this.isStudyAbroad = studiesAbroad;
    }
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

    @Override
    public double tuitionDue(int creditsEnrolled) {
        if (this.isStudyAbroad) {
            return universityFee + healthInsurance;
        }
        else {
            return super.tuitionDue(creditsEnrolled) + healthInsurance;
        }
    }

    @Override
    public String getClassification() {
        if (this.isStudyAbroad) {
            return "(non-resident)(international:study abroad)";
        }
        return "(non-resident)(international)";

    }

    @Override
    public String invalidStudent() {
        if (this.isStudyAbroad) {
            return "(International studentstudy abroad) ";
        }
        return "(International student)";
    }

}
