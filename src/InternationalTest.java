package src;
import org.junit.Test;
import static org.junit.Assert.*;

public class InternationalTest {

    @Test
    public void test_tuitionDue_nonStudyAbroad(){
        Date StudentDate = new Date("8/17/2003");
        Profile studentProfile = new Profile ("Doe", "John", StudentDate);
        Major studentMajor = Major.CS;
        boolean studiesAbroad = false;
        int credits = 87;
        double tuition;
        int creditsEnrolled = 15;
        Student student = new International(studentProfile,
                studentMajor, credits, studiesAbroad);
        tuition = student.tuitionDue(creditsEnrolled);
        assertTrue(tuition == (2650 + 3268 + 29737));
    }

    @Test
    public void test_tuitionDue_studyAbroad(){
        Date StudentDate = new Date("8/17/2003");
        Profile studentProfile = new Profile ("Doe", "John", StudentDate);
        Major studentMajor = Major.CS;
        boolean studiesAbroad = true;
        int credits = 87;
        double tuition;
        int creditsEnrolled = 15;
        Student student = new International(studentProfile,
                studentMajor, credits, studiesAbroad);
        EnrollStudent enrolledStudent = new EnrollStudent(studentProfile, creditsEnrolled);
        tuition = student.tuitionDue(creditsEnrolled);
        assertTrue(tuition == (2650 + 3268));
    }
}