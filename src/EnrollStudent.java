public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * Determines if two Profile objects are equal to eachother.
     * @param profile obj to compare
     * @return true if the Profile objects are equal, false if not.
     */

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