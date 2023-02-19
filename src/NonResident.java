package src;

public class NonResident extends Student {
    protected final int nonResidentTuition = 29737;
    protected final int creditHour = 966;
    public NonResident(Profile givenProfile, Major givenMajor, int Credits)
    {
        super(givenProfile, givenMajor, Credits);
    }
    public boolean isResident() {
        return false;
    }

    public double tuitionDue(int creditsEnrolled){
        if (creditsEnrolled > creditHourLimit)
            return (nonResidentTuition + universityFee + ((creditsEnrolled -
                    creditHourLimit) * creditHour));
        return nonResidentTuition + universityFee;
    }



}
