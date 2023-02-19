package src;

public class TriState extends NonResident {
    private String state;
    private final int newYorkDiscount = 4000, connecticutDiscount =
            5000;
    public TriState(Profile givenProfile, Major givenMajor, int Credits)
    {
        super(givenProfile, givenMajor, Credits);
    }

    private double calculateTuition(int creditsEnrolled){
        if (state.equals("NY")){
            return tuitionDue(creditsEnrolled) - newYorkDiscount;
        }
        else if (state.equals("CT")){
            return tuitionDue(creditsEnrolled) - connecticutDiscount;
        }
        return tuitionDue(creditsEnrolled);
    }
}
