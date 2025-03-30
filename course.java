import java.util.ArrayList;

public class course {
    private String number=null;
    private String name=null;
    private int maxNumberofStudents;
    private ArrayList<Instructor> instructors;
    public course(String number, String name, ArrayList<Instructor> instructors ,int maxNumberofStudents)
    {
        this.number=number;
        this.name=name;
        this.instructors=instructors;
        this.maxNumberofStudents=maxNumberofStudents;
    }
    public String getNumber(){
        return number;
    }
    public String getName(){
        return name;
    }
    public ArrayList<Instructor> getInstructors()
    {
        return instructors;
    }
    public int getNumofStudents()
    {
        return maxNumberofStudents;
    }
    public String toString()
    {
        return name;
    }
}
