public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent(Profile givenProfile, int creditsEnrolled){
        this.profile = givenProfile;
        this.creditsEnrolled = creditsEnrolled;
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
        if (obj instanceof Profile) {
            Profile profileObj = (Profile) obj; // this code is just copy
            // and pasted, it's not correct
            if (profileObj.lname.compareToIgnoreCase(this.lname) == 0 &&
                    profileObj.fname.compareToIgnoreCase(this.fname) == 0 &&
                    profileObj.dob.compareTo(this.dob) == 0) {
                return true;
            }
        }
        return false;
    }




}