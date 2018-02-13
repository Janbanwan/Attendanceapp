package controllers;

import helpers.InputHelper;
import java.util.Calendar;
import static java.util.Calendar.getInstance;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static model.Swipe.formatSwipeDateTime;
import static model.SwipeWithEnums.SwipeDateTimeComparator;
import model.SwipeType;
import model.SwipeWithEnums;
import model.VisitorSwipeWithEnums;
import repositories.RepositoryGeneric;

/**
 *
 * @author mga
 */
public class AttendanceController {
    private final RepositoryGeneric<SwipeWithEnums> repository;
    
    /**
     *
     */
        
    public AttendanceController() {
        InputHelper inputHelper = new InputHelper();
        char c = inputHelper.readCharacter("Load an already existing Attendance File (Y/N)?","YN");
        if (c == 'Y' || c == 'y') {
            String fileName = inputHelper.readString("Enter filename");               
            this.repository = new RepositoryGeneric(fileName);
            SwipeWithEnums.setLastIdUsed((int) repository.getItems().stream().count());  
        }
        else {
            this.repository = new RepositoryGeneric();
            //addSwipes();
            //SwipeWithEnums.setLastIdUsed((int) repository.getItems().stream().count());  
        }
    }
   
    /**
     *
     */
    public void run() {
        boolean finished = false;
        InputHelper ih = new InputHelper();
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    repository.getItems().stream().map(e->e).forEach(e-> System.out.println(e));
                    break;
                case 'C': 
                    List<SwipeWithEnums> name = repository.getItems().stream().sorted(SwipeDateTimeComparator).collect(Collectors.toList());
                    name.forEach(e-> System.out.println(e));
                    break;                                                                
                case 'D': 
                    String c = ih.readString("Enter Card ID");
                    System.out.println("Number of swipes for ID "+ c+": "+repository.getItems().stream().filter(e-> e.getCardId().equals(c)).count());
                    System.out.println("Last Swipe for ID "+c+": "+repository.getItems().stream().sorted(SwipeDateTimeComparator).filter(e -> e.getCardId().equals(c))
                            .map(e->formatSwipeDateTime(e.getSwipeDateTime())).findFirst().orElse("No such ID present in system"));
                    break;
                case 'E':
                    SwipeType sType = chooseSwipeType();
                    repository.getItems().stream().filter(e -> e.getSwipeType().equals(sType)).map(e -> e).forEach(str -> System.out.println(str));         
                    break;
                case 'Q': 
                    String fileName = ih.readString("Enter filename");          
                    Pattern p = Pattern.compile("[^A-Za-z0-9]");
                    Matcher m = p.matcher(fileName);
                    
                    boolean t = m.find();
                    if(t==true){
                        System.out.println("No special characters allowed");
                        break;
                    }else{
                        repository.store(fileName);
                        finished = true;
                    }

            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");     
        System.out.println("\n"
                + "E. List Swipes by Swipe type");
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDEQ");
    }    
    
    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");     
        
        InputHelper ih = new InputHelper();
        String room = "General";
        char choi = ih.readCharacter("Is your swipe a visitor swipe? (Y/N)","YN");
        if(choi=='Y'|| choi=='y'){
            String cardId = ih.readString("Enter card ID");
            String visitorName = ih.readString("Enter Visitor name");
            String visitorComp = ih.readString("Enter Visitor Company");
            SwipeWithEnums sw = new VisitorSwipeWithEnums(cardId,room,SwipeType.VISITORSWIPE,visitorName,visitorComp);
            repository.add(sw);
            
        } else {
            String cardId = ih.readString("Enter card ID");
            char st = ih.readCharacter("Is your swipe a student or teacher swipe? S for student, T for teacher (S/T)");
            if(st=='s'|| st=='S'){
                SwipeWithEnums sw = new SwipeWithEnums(cardId,room,SwipeType.STUDENTSWIPE);
                repository.add(sw);  
            }else {
                SwipeWithEnums sw = new SwipeWithEnums(cardId,room,SwipeType.TEACHERSWIPE);
                repository.add(sw); 
            }

        }
        
    }
    
    private <X, Y> void processElementsWithFunction(Iterable<X> source, Predicate<X> tester, 
                                                    Function<X, Y> mapper, Consumer<Y> process) {
        
        for (X x : source) {
            if (tester.test(x)) {
                Y data = mapper.apply(x);
                process.accept(data);
            } 
        }       
    }    
    
    private SwipeType chooseSwipeType(){
        InputHelper ih = new InputHelper();
        char kk = ih.readCharacter("Do you want to display Student (S), Teacher (T) or Visitor (V) swipes? (S/T/V)", "STV");
        switch (kk) {
            case 's':
            case 'S':
                return SwipeType.STUDENTSWIPE;
            case 't':
            case 'T':
                return SwipeType.TEACHERSWIPE;
            default:
                return SwipeType.VISITORSWIPE;
        }
    }
    
    private void addSwipes(){
        
        Calendar calendar = getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 13);
        calendar.set(Calendar.HOUR, 7);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2015);
        
        Calendar calendar2 = getInstance();
        calendar2.set(Calendar.SECOND, 15);
        calendar2.set(Calendar.MINUTE, 24);
        calendar2.set(Calendar.HOUR, 4);
        calendar2.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar2.set(Calendar.DAY_OF_MONTH, 22);
        calendar2.set(Calendar.YEAR, 2013);
        
        Calendar calendar3 = getInstance();
        calendar3.set(Calendar.SECOND, 0);
        calendar3.set(Calendar.MINUTE, 13);
        calendar3.set(Calendar.HOUR, 7);
        calendar3.set(Calendar.MONTH, Calendar.JUNE);
        calendar3.set(Calendar.DAY_OF_MONTH, 1);
        calendar3.set(Calendar.YEAR, 2012);
        
        Calendar calendar4 = getInstance();
        calendar4.set(Calendar.SECOND, 0);
        calendar4.set(Calendar.MINUTE, 13);
        calendar4.set(Calendar.MONTH, Calendar.JANUARY);
        calendar4.set(Calendar.DAY_OF_MONTH, 9);
        calendar4.set(Calendar.YEAR, 2009);
        
        Calendar calendar5 = getInstance();
        calendar5.set(Calendar.SECOND, 12);
        calendar5.set(Calendar.MINUTE, 13);
        calendar5.set(Calendar.MONTH, Calendar.JANUARY);
        calendar5.set(Calendar.DAY_OF_MONTH, 9);
        calendar5.set(Calendar.YEAR, 2017);
        
        Calendar calendar6 = getInstance();
        calendar6.set(Calendar.SECOND, 50);
        calendar6.set(Calendar.MINUTE, 13);
        calendar6.set(Calendar.MONTH, Calendar.AUGUST);
        calendar6.set(Calendar.DAY_OF_MONTH, 28);
        calendar6.set(Calendar.YEAR, 2015);
        
        Calendar calendar7 = getInstance();
        calendar7.set(Calendar.SECOND, 0);
        calendar7.set(Calendar.MINUTE, 13);
        calendar7.set(Calendar.MONTH, Calendar.MAY);
        calendar7.set(Calendar.DAY_OF_MONTH, 9);
        calendar7.set(Calendar.YEAR, 2015);
        
        Calendar calendar8 = getInstance();
        calendar8.set(Calendar.SECOND, 1);
        calendar8.set(Calendar.MINUTE, 13);
        calendar8.set(Calendar.MONTH, Calendar.MAY);
        calendar8.set(Calendar.DAY_OF_MONTH, 9);
        calendar8.set(Calendar.YEAR, 2015);
        
        Calendar calendar9 = getInstance();
        calendar9.set(Calendar.SECOND, 0);
        calendar9.set(Calendar.MINUTE, 13);
        calendar9.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar9.set(Calendar.DAY_OF_MONTH, 14);
        calendar9.set(Calendar.YEAR, 2011);
        
        Calendar calendar10 = getInstance();
        calendar10.set(Calendar.SECOND, 45);
        calendar10.set(Calendar.MINUTE, 45);
        calendar10.set(Calendar.MONTH, Calendar.JANUARY);
        calendar10.set(Calendar.DAY_OF_MONTH, 16);
        calendar10.set(Calendar.YEAR, 2013);
        
        Calendar calendar11 = getInstance();
        calendar11.set(Calendar.SECOND, 0);
        calendar11.set(Calendar.MINUTE, 13);
        calendar11.set(Calendar.MONTH, Calendar.JANUARY);
        calendar11.set(Calendar.DAY_OF_MONTH, 9);
        calendar11.set(Calendar.YEAR, 2014);

        
        SwipeWithEnums sw1 = new SwipeWithEnums(1,"CiD","Kitchen",SwipeType.STUDENTSWIPE,calendar);
        repository.add(sw1);
        SwipeWithEnums sw2 = new SwipeWithEnums(2,"CiD","Kitchen",SwipeType.STUDENTSWIPE,calendar2);
        repository.add(sw2);
        SwipeWithEnums sw3 = new SwipeWithEnums(3,"CiD","Kitchen",SwipeType.STUDENTSWIPE,calendar3);
        repository.add(sw3);
        SwipeWithEnums sw4 = new SwipeWithEnums(4,"CiD","Kitchen",SwipeType.STUDENTSWIPE,calendar4);
        repository.add(sw4);
        SwipeWithEnums sw5 = new SwipeWithEnums(5,"CiD","Kitchen",SwipeType.STUDENTSWIPE,calendar5);
        repository.add(sw5);
        SwipeWithEnums sw6 = new SwipeWithEnums(6,"CiD","Kitchen",SwipeType.STUDENTSWIPE,calendar6);
        repository.add(sw6);
        SwipeWithEnums sw7 = new SwipeWithEnums(7,"CiD","Kitchen",SwipeType.TEACHERSWIPE,calendar7);
        repository.add(sw7);
        SwipeWithEnums sw8 = new VisitorSwipeWithEnums(8,"CiD","Kitchen",SwipeType.VISITORSWIPE,calendar8,"John","Anyman");
        repository.add(sw8);
        SwipeWithEnums sw9 = new VisitorSwipeWithEnums(9,"Dd","Kitchen",SwipeType.VISITORSWIPE,calendar9,"John","Anyman");
        repository.add(sw9);
        SwipeWithEnums sw10 = new VisitorSwipeWithEnums(10,"dd","Kitchen",SwipeType.VISITORSWIPE,calendar10,"John","Anyman");
        repository.add(sw10);
        SwipeWithEnums sw11 = new VisitorSwipeWithEnums(11,"dd","Kitchen",SwipeType.VISITORSWIPE,calendar11,"John","Anyman");
        repository.add(sw11);
    }

}
