package src;
/**
 * Profile class that holds information about name and date of birth
 * Has methods to compare Profile objects such as compareTo() and equals()
 * @author Sofia Juliani, Arnold Nguyen
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    /**
     * Constructor for Profile object creation
     * @param last name for Profile object
     * @param first name for Profile object
     * @param birthdate Date object for Profile object
     */
    public Profile(String last, String first, Date birthdate) {
        this.lname = last;
        this.fname = first;
        this.dob = birthdate;
    }

    /**
     * Determines if two Profile objects are equal to eachother.
     * @param obj to compare
     * @return true if the Profile objects are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profileObj = (Profile) obj;
            if (profileObj.lname.compareToIgnoreCase(this.lname) == 0 &&
                    profileObj.fname.compareToIgnoreCase(this.fname) == 0 &&
                    profileObj.dob.compareTo(this.dob) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares two profiles to each other progressively by last name, first
     * name and date of birth.
     * @param profile the object to be compared.
     * @return 1 if first profile is greater, 0 if the same, and -1 if less
     */
    @Override
    public int compareTo(Profile profile) {
        if (this.lname.compareToIgnoreCase(profile.lname) > 0)
            return 1;
        else if (this.lname.compareToIgnoreCase(profile.lname) < 0)
            return -1;
        else { //the last names are equal, so compare first names
            if (this.fname.compareToIgnoreCase(profile.fname) > 0)
                return 1;
            else if (this.fname.compareToIgnoreCase(profile.fname) < 0)
                return -1;
            else { //the first names are equal, so compare dates of birth
                if (this.dob.compareTo(profile.dob) > 0) {
                    return 1;
                }
                else if (this.dob.compareTo(profile.dob) < 0) {
                    return -1;
                }

            }
        }
        return 0;
    }

    /**
     * Getter method that gets date of birth from Profile
     * @return date of birth from profile as Date object
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * toString method that prints out information of Profile
     * @return first name, last name, and date of birth of student
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }

}
