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
        if (state.equalsIgnoreCase("NY")) {
            return "(non-resident)(tri-state:NY)";
        }
        return "(non-resident)(tri-state:CT)";


    }

    @Override
    public String invalidStudent() {
        if (state.equalsIgnoreCase("NY")) {
            return "(Tri-state NY)";
        }
        return "(Tri-state CT)";

    }

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
