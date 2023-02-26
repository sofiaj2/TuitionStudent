package src;

public class TriState extends NonResident {
    private String state;
    private static final int newYorkDiscount = 4000, connecticutDiscount =
            5000;
    public TriState(Profile givenProfile, Major givenMajor, int Credits,
                    String givenState)
    {
        super(givenProfile, givenMajor, Credits);
        this.state = givenState;
    }

    @Override
    public String getClassification() {
        return "(non-resident)(tri-state)";
    }

    @Override
    public String invalidStudent() { return"(TriState) "; }

    @Override
    public double tuitionDue(int creditsEnrolled){
        if (this.state.equalsIgnoreCase("NY")){
            return super.tuitionDue(creditsEnrolled) - newYorkDiscount;
        }
        else if (this.state.equalsIgnoreCase("CT")){
            return super.tuitionDue(creditsEnrolled) - connecticutDiscount;
        }
        return super.tuitionDue(creditsEnrolled);
    }

    public String getState() {
        return this.state;
    }
}
