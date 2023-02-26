package src;
public class EnrollStudent implements Comparable<EnrollStudent> {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent(Profile givenProfile, int creditsEnrolled){
        this.profile = givenProfile;
        this.creditsEnrolled = creditsEnrolled;
    }
    public EnrollStudent(Profile givenProfile){
        this.profile = givenProfile;
    }

    public EnrollStudent(EnrollStudent studentToEnroll) {
        this.profile = studentToEnroll.profile;
        this.creditsEnrolled = studentToEnroll.creditsEnrolled;
    }

    @Override
    public int compareTo(EnrollStudent studentEnrolled) {
        if (this.profile.compareTo(studentEnrolled.profile) > 0)
            return 1;
        else if (this.profile.compareTo(studentEnrolled.profile) < 0)
            return -1;
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EnrollStudent) {
            EnrollStudent studentEnrollObj = (EnrollStudent) obj;
            if (studentEnrollObj.profile.equals(this.profile))
            {
                return true;
            }
        }
        return false;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public int getCreditsEnrolled() {return this.creditsEnrolled;}

    public void setCreditsEnrolled(int newCredits) {
        this.creditsEnrolled = newCredits;
    }

}