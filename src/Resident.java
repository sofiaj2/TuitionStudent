package src;

public class Resident extends Student {
    private double scholarship;
    private static final double residentTuition = 12536, creditHour = 404;

    /**
     * Constructor for the Resident object
     * @param givenProfile
     * @param givenMajor
     * @param givenCredits
     */
    public Resident(Profile givenProfile, Major givenMajor,
                    int givenCredits) {
        super(givenProfile, givenMajor, givenCredits);
        this.scholarship = 0;
    }

    /**
     * Gives the tuition due for a resident of the abstract class Student
     * @param creditsEnrolled for credits the resident is taking
     * @return tuition the Resident owes
     */
    public double tuitionDue(int creditsEnrolled) {
        double tuition;
        if (isFullTime(creditsEnrolled)) { //full time student
            tuition = universityFee + residentTuition - scholarship;
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

    public boolean isResident() {
        return true;
    }

    public boolean isFullTime() {
        if (this.getCreditCompleted() >= 12)
            return true;
        return false;
    }

    public void setScholarship(int givenScholarship) {
        this.scholarship += givenScholarship;
    }

    public String getClassification() {
        return "(resident)";
    }
    public String invalidStudent() {
        return "(Resident) ";
    }
}
