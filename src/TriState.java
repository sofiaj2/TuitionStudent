package src;

public class TriState extends NonResident {
    private String state;
    private final int newYorkDiscount = 4000, connecticutDiscount =
            5000;
    public TriState(Profile givenProfile, Major givenMajor, int Credits)
    {
        super(givenProfile, givenMajor, Credits);
    }

    @Override
    public double tuitionDue(int creditsEnrolled){
        if (state.equalsIgnoreCase("NY")){
            return super.tuitionDue(creditsEnrolled) - newYorkDiscount;
        }
        else if (state.equalsIgnoreCase("CT")){
            return super.tuitionDue(creditsEnrolled) - connecticutDiscount;
        }
        return super.tuitionDue(creditsEnrolled);
    }
}
