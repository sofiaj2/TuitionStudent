package src;

/**
 * TriState class that is an extension of NonResident. NonResident class
 * that has methods to handle TriState students like tuitionDue
 * @author Sofia Juliani, Arnold Nguyen
 */
public class TriState extends NonResident {
    private String state;
    private static final double newYorkDiscount = 4000, connecticutDiscount =
            5000;
    public TriState(Profile givenProfile, Major givenMajor, int Credits,
                    String givenState)
    {
        super(givenProfile, givenMajor, Credits);
        this.state = givenState;
    }

    /**
     * TriState student tuition due method
     * @param creditsEnrolled credits of the nonresident
     * @return tuitionDue of a TriState student
     */
    @Override
    public double tuitionDue(int creditsEnrolled){
        if (this.state.equalsIgnoreCase("NY")){
            if (super.isFullTime(creditsEnrolled)) {
                return super.tuitionDue(creditsEnrolled) - newYorkDiscount;
            }
        }
        else if (this.state.equalsIgnoreCase("CT")){
            if (super.isFullTime(creditsEnrolled)) {
                return super.tuitionDue(creditsEnrolled) - connecticutDiscount;
            }
        }
        return super.tuitionDue(creditsEnrolled);
    }

    /**
     * Get the Classification of Student role
     * @return string of non-resident role
     */
    @Override
    public String getClassification() {
        if (state.equalsIgnoreCase("NY")) {
            return "(non-resident)(tri-state:NY)";
        }
        return "(non-resident)(tri-state:CT)";


    }

    /**
     * invalidStudent that checks State
     * @return String of TriState and the State
     */
    @Override
    public String invalidStudent() {
        if (state.equalsIgnoreCase("NY")) {
            return "(Tri-state NY) ";
        }
        return "(Tri-state CT) ";
    }
}
