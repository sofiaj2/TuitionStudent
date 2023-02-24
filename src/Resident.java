package src;

public class Resident extends Student {
    private int scholarship;
    private static final int residentTuition = 12536, creditHour = 404;

    public Resident(Profile givenProfile, Major givenMajor,
                    int givenCredits) {
        super(givenProfile, givenMajor, givenCredits);
    }

    public Resident(Profile givenProfile, Major givenMajor,
                    int givenCredits, int givenScholarship) {
        super(givenProfile, givenMajor, givenCredits);
        this.scholarship = givenScholarship;
    }


    public double tuitionDue(int creditsEnrolled) {
        double tuition;
        if (isFullTime(creditsEnrolled)) { //full time student
            tuition = universityFee + residentTuition - scholarship;
            if (creditsEnrolled > creditHourLimit) {
                tuition += creditHour * (creditsEnrolled - creditHourLimit);
            }
        }
        else { //part time student
            tuition = (creditHour * creditsEnrolled)
                    + (percentFullTimeRate * residentTuition);
        }
        return tuition;
    }

    public boolean isResident() {
        return true;
    }

    private boolean isFullTime(int credits) {
        if (credits >= 12)
            return true;
        return false;
    }
}
