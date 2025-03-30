public class Class {
    private int id;
    private Department dept;
    private course course;
    private Instructor instructors;
    private MeetingTime meetingTime;
    private Room room;
    public Class(int id,Department dept,course courses)
    {
        this.id=id;
        this.course=courses;
        this.dept=dept;
    }
    public void setInstructor(Instructor instructors){this.instructors=instructors;}
    public void setMeetingTime(MeetingTime meetingTime){this.meetingTime=meetingTime;}
    public void setRoom(Room room){this.room=room;}
    public int getId(){ return id;}
    public Department getDept(){return dept;}
    public course getCourse(){ return course;}
    public Instructor getInstructor(){return instructors;}
    public MeetingTime getMeetingTime(){ return meetingTime; }
    public Room getRoom(){ return room;}
    //string representation of Class object
    public String toString(){
        return "["+dept.getName()+","+course.getNumber()+","+room.getNumber()+","+instructors.getId()+","+meetingTime.getId()+"]";
    }
    } 
    
    

