import java.util.ArrayList;

public class schedule {
    private ArrayList<Class> classes;
    private int classNum=0;
    private int numConflict=0;
    private boolean isFitnessChanged=true;
    private double fitness=-1;
    private Data data;
    public Data geData(){ return data; }
    public schedule(Data data)
    {
        this.data=data;
        classes=new ArrayList<Class>(data.getNumClasses());
    }
    public schedule initialize()
    {
        /*create a list of Department objects, where each department has a list of courses, and each course has a list of classes.
         also setting various properties for each Class object, such as meeting time, room, and instructor. Additionally, the newly created Class objects are added to a collection called classes
         */
        new ArrayList<Department>(data.getDept()).forEach(dept->{dept.getCourse().forEach(course->{Class newClass=new Class(classNum++,dept,course);newClass.setMeetingTime(data.getMeetingTime().get((int)(data.getMeetingTime().size()*Math.random())));newClass.setRoom(data.getRoom().get((int)(data.getRoom().size()*Math.random())));newClass.setInstructor(course.getInstructors().get((int)(course.getInstructors().size()*Math.random())));classes.add(newClass);});});
        return this;
    }
    public int getConflic(){ return numConflict; }
    public ArrayList<Class> getClasses(){
        isFitnessChanged=true;
        return classes;
    }
    public double getFitness(){
        if(isFitnessChanged==true)
        {
            fitness=calcFitness();
            isFitnessChanged=false;
        }
        return fitness;
    }
    private double calcFitness(){
        numConflict= 0;
        classes.forEach(x->{if(x.getRoom().getSeatCap() <x.getCourse().getNumofStudents()) numConflict++; classes.stream().filter(y-> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> { if(x.getMeetingTime()==y.getMeetingTime() && x.getId() != y.getId()) {if(x.getRoom()== y.getRoom()) numConflict++; if (x.getInstructor()==y.getInstructor()) numConflict++; 
        }
      });
    });
     return 1/(double) (numConflict + 1);
    }
    public String toString(){
        String res=new String();
        for(int x=0;x<classes.size()-1;x++)
         res+=classes.get(x)+",";
         res+=classes.get(classes.size()-1);
         return res;
    }
}
