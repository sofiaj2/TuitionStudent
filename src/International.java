package src;

public class International extends NonResident{
    private boolean isStudyAbroad;
    public static final int minInternationalCredits = 12,
            maxInternationalCredits = 12;

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
}
