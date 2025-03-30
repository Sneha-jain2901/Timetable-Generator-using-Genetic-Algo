import java.util.ArrayList;
import java.util.stream.IntStream;

public class geneticAlgo {
    private static Data data;
    //constructor
    public geneticAlgo(Data data){
        this.data=data;
    }
    public static population evolve(population pp)
    {
        return mutatePopulation(crossPopulation(pp));  
    }
    //crossover for all schedules in the population
    //tournament selection in order to select two schedules
    static population crossPopulation(population pp)
    {
        population crossPopulation=new population(pp.getSchedules().size(), data);
        IntStream.range(0, App.num_elite_schedules).forEach(x->crossPopulation.getSchedules().set(x,pp.getSchedules().get(x)));
        IntStream.range(App.num_elite_schedules, pp.getSchedules().size()).forEach(x->{
            if(App.crossover_rate>Math.random()){
                schedule s1=selectTournament(pp).sortByFitness().getSchedules().get(0);
                schedule s2=selectTournament(pp).sortByFitness().getSchedules().get(0);
                crossPopulation.getSchedules().set(x,crossOverSchedule(s1, s2));
            }else crossPopulation.getSchedules().set(x, pp.getSchedules().get(x));
        });
        return crossPopulation;
    }
    //this actually does the crossover of two schedules
    static schedule crossOverSchedule(schedule s1,schedule s2)
    {
        schedule crossSchedule=new schedule(data).initialize();
        IntStream.range(0, crossSchedule.getClasses().size()).forEach(x->{
            if(Math.random()>0.5)crossSchedule.getClasses().set(x,s1.getClasses().get(x));
            else crossSchedule.getClasses().set(x, s2.getClasses().get(x));
        });
        return crossSchedule;
    }
    static population mutatePopulation(population pp)
    {
        population mutatePopulation=new population((pp.getSchedules().size()), data);
        ArrayList<schedule> s1=mutatePopulation.getSchedules();
        IntStream.range(0, App.num_elite_schedules).forEach(x->s1.set(x,pp.getSchedules().get(x)));
        IntStream.range(App.num_elite_schedules,pp.getSchedules().size()).forEach(x->{
            s1.set(x,mutateSchedule(pp.getSchedules().get(x)));
        });
        return mutatePopulation;
    }
    static schedule mutateSchedule(schedule mSchedule)
    {
        schedule s1= new schedule(data).initialize();
        IntStream.range(0, mSchedule.getClasses().size()).forEach(x->{
            if(App.mutation_rate>Math.random()) mSchedule.getClasses().set(x,s1.getClasses().get(x));
        });
        return mSchedule;
    }
    static population selectTournament(population pp)
    {
        population tourPopulation= new population(App.tournament_sel_size, data);
        IntStream.range(0, App.tournament_sel_size).forEach(x->{
            tourPopulation.getSchedules().set(x, pp.getSchedules().get((int)Math.random()*pp.getSchedules().size()));
        });
        return tourPopulation;
    }
}
