/**
 * @author Toma-Florin Ungureanu
 */

import com.problem.Matching;
import com.problem.Problem;
import com.projects.Application;
import com.projects.Essay;
import com.student.Student;
import com.projects.Application.Language;
import com.projects.Essay.Topic;

import java.time.LocalDate;
import java.time.Month;

public class Main
{
    public static void main(String[] args)
    {
        Student s1 = new Student("S1", 2);
        Student s2 = new Student("S2", 2);
        Student s3 = new Student("S3", 3);
        Student s4 = new Student("S4", 1);
        Student s5 = new Student("S5", 1);

        Application a1 = new Application("A1", LocalDate.of(2019, Month.JUNE, 1),
                Language.JAVA);
        Application a2 = new Application("A2", LocalDate.of(2019, Month.MAY, 27),
                Language.CPLUSPLUS);
        Application a3 = new Application("A3", LocalDate.of(2019, Month.JUNE, 5),
                Language.PYTHON);
        Application a4 = new Application("A4", LocalDate.of(2019, Month.APRIL, 1),
                Language.JAVA);
        Application a5 = new Application("A5", LocalDate.of(2019, Month.JULY, 27),
                Language.CPLUSPLUS);
        Application a6 = new Application("A6", LocalDate.of(2019, Month.MAY, 5),
                Language.PYTHON);
        Essay e2 = new Essay("E2", LocalDate.parse("2019-05-27"), Topic.WEB);
        Essay e1 = new Essay("E1", LocalDate.parse("2019-06-01"), Topic.ALGORITHMS);

        s1.setPreferences(a4, a1, a3);
        s2.setPreferences(a4, a5, a2, e2);
        s3.setPreferences(a4, a5, a2);
        s4.setPreferences(a5, a4, a2);
        s5.setPreferences(a5, a6, a4, a1, a2, a3, e1, e2);


        Problem problem = new Problem();
        problem.setStudents(s1, s2, s3, s4, s5);
        problem.setProjects(a1, a2, a3, e1, e2, a5, a6, a4);
        if (problem.getStudents() != null && problem.getProjects() != null)
        {
            Matching matching = new Matching(problem);
            matching.greedySolve();
            matching.convertToMatrix();
        }

    }
}
