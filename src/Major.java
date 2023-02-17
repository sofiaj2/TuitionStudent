package src;

/**
 * Enum class for Major that declares all possible Majors for Students
 * @author Arnold Nguyen, Sofia Juliani
 */
public enum Major {
    CS  ("01:198", "SAS"),
    MATH ("01:640", "SAS"),
    EE  ("14:332", "SOE"),
    ITI ("04:547", "SC&I"),
    BAIT ("33:136", "RBS");

    private final String department;
    private final String school;

    /**
     * Major constructor to create Major objects
     * @param department which is string for Major attribute
     * @param school which is string for school object
     */
    Major(String department, String school)
    {
        this.department = department;
        this.school = school;
    }

    /**
     * Constructs a string based on the information of Major object
     * @return the name of major, department, and school
     */
    @Override
    public String toString() {
        return "(" + department + " " + name() + " " +  school + ")";
    }

    /**
     * Getter method that returns the school of a Major
     * @return school String
     */
    public String getSchool(){
        return school;
    }
}

