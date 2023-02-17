package src;

/**
 * Standing enum class for Students that represent the class standing
 * of students dependent on their credits completed.
 * @author Arnold Nguyen, Sofia Juliani
 */
public enum Standing {
    Freshman (0),
    Sophomore (30),
    Junior (60),
    Senior (90);
    private int creditCompleted;

    /**
     * Constructor for Standing as student parameter
     * @param creditCompleted int
     */
    Standing(int creditCompleted)
    {
        this.creditCompleted = creditCompleted;
    }

    /**
     * Returns the standing of a student of given class year
     * @return (specified standing) for student
     */
    public String toString(){
        return "(" + name() + ")";
    }

    /**
     * Gets the lower bound of credits needed for a particular class year
     * @return creditCompleted which represents credits needed for a class year
     */
    public int getCreditLowerBound() {
        return this.creditCompleted;
    }
}
