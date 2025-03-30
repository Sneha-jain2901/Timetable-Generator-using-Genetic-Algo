import java.util.ArrayList;

public class Department{
        private String name;
        private ArrayList<course> courses;
        public Department(String name,ArrayList<course>courses)
        {
            this.name=name;
            this.courses=courses;
        }
        public String getName(){return name;}
        public ArrayList<course> getCourse(){return courses;}
     }
