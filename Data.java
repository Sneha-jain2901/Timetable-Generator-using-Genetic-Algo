import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Data{
    private ArrayList<Room> room;
    private ArrayList<Instructor> instructors;
    private ArrayList<course> courses;
    private ArrayList<Department> dept;
    private ArrayList<MeetingTime> meetingTime;
    private int numberClasses=0;
    public Data() {
        initialize();
    }
    Data initialize(){
        Room r1=new Room("R1", 50);
        Room r2=new Room("R2",50);
        Room r3=new Room("R3",45);
        Room r4=new Room("R4",45);
        Room r5=new Room("R5",50);
        //if(room==null)
        room =new ArrayList<Room>(Arrays.asList(r1,r2,r3,r4,r5));
        //else
        //room.addAll(r1,r2,r3,r4,r5);
        MeetingTime mt1=new MeetingTime("MT1","M-W-F 09:00-10:00");
        MeetingTime mt2=new MeetingTime("MT2","M-W-F 10:00-11:00");
        MeetingTime mt3=new MeetingTime("MT3","T-TH 09:00-10:00");
        MeetingTime mt4=new MeetingTime("MT4","T-TH 11:00-12:00");
        MeetingTime mt5=new MeetingTime("MT5","M-W-F 11:00-12:00");
        MeetingTime mt6=new MeetingTime("MT6","T-TH 12:00-01:00");
        meetingTime=new ArrayList<MeetingTime>(Arrays.asList(mt1,mt2,mt3,mt4,mt5,mt6));
        Instructor in1=new Instructor("T1","Dr Inderdeep Singh");
        Instructor in2=new Instructor("T2","Mr Ashutosh Bhatia");
        Instructor in3=new Instructor("T3","Dr Indu Dhyani");
        Instructor in4=new Instructor("T4","Dr Hemant Pokhriyal");
        Instructor in5=new Instructor("T5","Dr Ramnish Kumar");
        Instructor in6=new Instructor("T6","Dr Sanjay Sharma");
        instructors=new ArrayList<Instructor>(Arrays.asList(in1,in2,in3,in4,in5,in6));
        course c1=new course("C1", "PPE ",new ArrayList<Instructor>(Arrays.asList(in1,in3)), 40);
        course c2=new course("C2", "DSA",new ArrayList<Instructor>(Arrays.asList(in2,in4)) , 30);
        course c3=new course("C3", "CPP",new ArrayList<Instructor>(Arrays.asList(in1,in4)), 45);
        course c4=new course("C4", "TMA",new ArrayList<Instructor>(Arrays.asList(in1,in2)), 40);
        course c5=new course("C5", "PCS",new ArrayList<Instructor>(Arrays.asList(in1,in5)), 25);
        course c6=new course("C6", "TCS",new ArrayList<Instructor>(Arrays.asList(in1,in5)), 35);
        course c7=new course("C7", "TOC",new ArrayList<Instructor>(Arrays.asList(in1,in2)), 45);
        course c8=new course("C8", "EEE",new ArrayList<Instructor>(Arrays.asList(in4,in5)), 50);
        course c9=new course("C9", "CPP",new ArrayList<Instructor>(Arrays.asList(in4,in5)), 50);
        course c10=new course("C10", "RES",new ArrayList<Instructor>(Arrays.asList(in2,in5)), 35);
        course c11=new course("C11", "TPE-301",new ArrayList<Instructor>(Arrays.asList(in5,in6)), 35);
        course c12=new course("C12", "TPE-304",new ArrayList<Instructor>(Arrays.asList(in5,in6)), 45);
        courses=new ArrayList<course>(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12));
        Department d1=new Department("MEE", new ArrayList<course>(Arrays.asList(c1,c4,c9)));
        Department d2=new Department("CSE", new ArrayList<course>(Arrays.asList(c2,c3,c5,c10)));
        Department d3=new Department("EE", new ArrayList<course>(Arrays.asList(c6,c7,c8)));
        Department d4=new Department("PE", new ArrayList<course>(Arrays.asList(c11,c12)));
        dept=new ArrayList<Department>(Arrays.asList(d1,d2,d3,d4));
        dept.forEach(x->numberClasses+=getCourses().size());
        return this;
    }
    public ArrayList<Room>  getRoom(){ return room; }
    public ArrayList<Instructor> getInstructor(){ return instructors; }
    public ArrayList<course> getCourses(){ return courses; }
    public ArrayList<Department> getDept(){ return dept; }
    public ArrayList<MeetingTime> getMeetingTime(){ return meetingTime; }
    public int getNumClasses(){ return this.numberClasses; }
    public void addRoom(Room newRoom) {
        if (room == null) {
            room = new ArrayList<>();
        }
        room.add(newRoom);
    }
    public void addIns(Instructor newRoom) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(newRoom);
    }
    public void addMeeting(MeetingTime newRoom) {
        if (meetingTime == null) {
            meetingTime = new ArrayList<>();
        }
        meetingTime.add(newRoom);
    }
    public void addCourse(String id,String nm,int m) {
        if (courses == null) {
        courses = new ArrayList<>();
    }

    // Selecting two random instructors
    Random random = new Random();
    List<Instructor> randomInstructors = new ArrayList<>(instructors);
    Collections.shuffle(randomInstructors);

    // Creating a course object with the first two instructors
    ArrayList<Instructor> selectedInstructors = new ArrayList<>(randomInstructors.subList(0, 2));
    course newCourse = new course(id, nm, selectedInstructors, m);

    // Adding the new course to the list
    courses.add(newCourse);
    }
    public void addDept(String nm) {
        if (dept == null) {
        dept= new ArrayList<>();
    }

    // Selecting two random instructors
    Random random = new Random();
    List<course> randomInstructors = new ArrayList<>(courses);
    Collections.shuffle(randomInstructors);

    // Creating a course object with the first two instructors
    ArrayList<course> selectedInstructors = new ArrayList<>(randomInstructors.subList(0, 2));
    Department newDept= new Department(nm, selectedInstructors);

    // Adding the new course to the list
    dept.add(newDept);
    }
}
