package src;

import java.io.IOException;

/**
 * Runs the project with run() method from RosterManager class
 * @author Sofia Juliani, Arnold Nguyen
 */
public class RunProject2 {
    /**
     * static void main method to create a RosterManager object
     * @param args passing in arguments as a String
     */
    public static void main(String[] args) throws IOException {
        new TuitionManager().run();
    }
}
