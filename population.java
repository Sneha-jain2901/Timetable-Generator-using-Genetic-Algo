//population of schedules

import java.util.ArrayList;
import java.util.stream.IntStream;

public class population {
    private ArrayList<schedule> schedules;
    public population(int size, Data data)
    {
        schedules= new ArrayList<schedule>(size);
        IntStream.range(0, size).forEach(x->schedules.add(new schedule(data).initialize()));
    }
    public ArrayList<schedule> getSchedules(){ return this.schedules;}
    public population sortByFitness()
    {
        schedules.sort((schedule1,schedule2)->{
            int res=0;
            if(schedule1.getFitness()>schedule2.getFitness()) res=-1;
            else if(schedule1.getFitness()<schedule2.getFitness()) res=1;
            return res;
        });
        return this;
    }
}
