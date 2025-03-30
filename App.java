import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class App {
    public static final int population_size = 10;
    public static final double mutation_rate = 0.01;
    public static final double crossover_rate = 0.9;
    public static final int tournament_sel_size = 3;
    public static final int num_elite_schedules = 1;
    private static int scheduleNum = 0, classNum = 0;
    static Data data;
    private static JFrame frame;
    private static JTable table;
    public static void main(String[] args)throws Exception {
        App.data = new Data();
        input inputForm = new input();
        inputForm.setVisible(true);
        
    }

    public static void generateAndDisplay() {
        App app = new App();
        int generationNum[] = {0};
        App.classNum = 1;
       
        frame = new JFrame("Timetable Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createTable();

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("Generation #" + generationNum[0]);
        System.out.print("Schedule#|                        ");
        System.out.print("Classes [dept,class,room,instructor,meeting time]      ");
        System.out.println("                               | Fitness   |Conflicts");
        System.out.println("..............................................................................................................................................");
        geneticAlgo gAlgo = new geneticAlgo(App.data);
        population pp = new population(App.population_size, App.data).sortByFitness();
        pp.getSchedules().forEach(schedule -> System.out.println("   " + App.scheduleNum++ + "     | " + schedule + "  |  " + String.format("%.3f", schedule.getFitness()) + "  |  " + schedule.getConflic()));
        App.printSchedule(pp.getSchedules().get(0), generationNum[0]);

        int i = 0;
        while (1<1000&&pp.getSchedules().get(0).getFitness() != 1.0) {
            System.out.println("> Generation #" + ++generationNum[0]);
            System.out.print(" Schedule # |                                  ");
            System.out.println("Classes [dept, course, room, instructor, meeting-time]                                        |Fitness |Conflicts");
            System.out.println("_____________________________________________________________________________________________________________________________________________");
            pp = geneticAlgo.evolve(pp).sortByFitness();
            App.scheduleNum = 0;
            pp.getSchedules().forEach(schedule -> {
                System.out.println("   " + App.scheduleNum++ + "  |  " + schedule + " | " + String.format("%.5f", schedule.getFitness()) + " | " + schedule.getConflic());
            });
            App.printSchedule(pp.getSchedules().get(0), generationNum[0]);
            App.classNum = 1;
            i++;
        }
        if (pp.getSchedules().get(0).getFitness() == 1.0) {
            //App.printSchedule(schedule, generationNum[0]);
            updateTable(pp.getSchedules().get(0));
        }else System.out.println("Error : could not generate a feasible solution");
    }

    private static void printSchedule(schedule s1, int generation) {
        // Existing printSchedule method remains unchanged
        ArrayList<Class> classes=s1.getClasses();
        System.out.print("\n ");
        System.out.println("Class #| Dept | Course (number, max # of students)  | Room (Capacity) |     Instructor (Id)       | Meeting Time (Id)");
        System.out.println("_____________________________________________________________________________________________________________");
        classes.forEach(x ->{
           int majorIndex =data.getDept().indexOf(x.getDept());
           int coursesIndex =data.getCourses().indexOf(x.getCourse());
           int roomsIndex =data.getRoom().indexOf(x.getRoom());
           int instructorsIndex =data.getInstructor().indexOf(x.getInstructor());
           int meetingTimeIndex= data.getMeetingTime().indexOf(x.getMeetingTime());
           System.out.print(String.format("%1$02d",classNum)+"     | "); 
           System.out.print(String.format("%1$4s", data.getDept().get(majorIndex).getName()) + " | ");
           System.out.print(String.format("%1$21s", data.getCourses().get(coursesIndex).getName()+"("+data.getCourses().get(coursesIndex).getNumber()+","+x.getCourse().getNumofStudents())+")               | ");
           System.out.print(String.format("%1$10s", data.getRoom().get(roomsIndex).getNumber()+ "("+x.getRoom().getSeatCap()) + ")     | ");
           System.out.print(String.format("%1$28s", data.getInstructor().get(instructorsIndex).getName()+"("+data.getInstructor().get(instructorsIndex).getId()+") | "));
           System.out.println(String.format("%1$21s",data.getMeetingTime().get(meetingTimeIndex).getTime()+" ("+data.getMeetingTime().get(meetingTimeIndex).getId()+")"));
           classNum++;
    });
    if(s1.getFitness()==1) System.out.println("\n>Solution found in "+(generation+1)+" generations");
    System.out.println(("___________________________________________________________________________________________________________________________________________________________________"));
    }

    private static void createTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Class #", "Deptartment", "Course(Code)", "Room(Capacity)", "Instructor", "Meeting Time"});
        table = new JTable(model);
        table.setEnabled(false);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setRowHeight(25);
        table.setBackground(new Color(240, 240, 240)); // Set a light background color
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.WHITE);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    
        // Create JScrollPane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
        // Add the scrollPane to the CENTER of the BorderLayout
        frame.add(scrollPane, BorderLayout.CENTER);
        
    }
    

    private static void updateTable(schedule s) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int Num[]={0};
        ArrayList<Class> classes = s.getClasses();
        classes.forEach(x -> {
            int majorIndex = data.getDept().indexOf(x.getDept());
            int coursesIndex = data.getCourses().indexOf(x.getCourse());
            int roomsIndex = data.getRoom().indexOf(x.getRoom());
            int instructorsIndex = data.getInstructor().indexOf(x.getInstructor());
            int meetingTimeIndex = data.getMeetingTime().indexOf(x.getMeetingTime());
    
            model.addRow(new Object[]{
                    String.format("%1$02d", ++Num[0]),
                    data.getDept().get(majorIndex).getName(),
                    data.getCourses().get(coursesIndex).getName() + " (" + data.getCourses().get(coursesIndex).getNumber() + ")",
                    data.getRoom().get(roomsIndex).getNumber() + "(" + x.getRoom().getSeatCap() + ")",
                    data.getInstructor().get(instructorsIndex).getName() + "(" + data.getInstructor().get(instructorsIndex).getId() + ")",
                    data.getMeetingTime().get(meetingTimeIndex).getTime() + " (" + data.getMeetingTime().get(meetingTimeIndex).getId() + ")"
            });
        });
    }
    
}

