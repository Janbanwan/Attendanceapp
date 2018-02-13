package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mga
 */
public class InputHelper {
    private final Scanner reader;
    
    /**
     *
     */
    public InputHelper() {
        reader = new Scanner(System.in);
    }

    // Read Character

    /**
     *
     * @param prompt
     * @return
     */
        public char readCharacter(String prompt) {
            boolean good;
            System.out.println(prompt + ": ");

        do{
            String h = reader.nextLine();
            h.replaceAll("//s", "");
            good = false;
            if(h.length()>1||h.equals("")){
                System.out.println("Wrong Input, try again");
            }else{
                good = true;
                return h.charAt(0);
                
            }
            
        }while(!good);
        
        return 'g';
    }    
    
    // Read Character - set of valid values

    /**
     *
     * @param prompt
     * @param validCharacters
     * @return
     */
        public char readCharacter(String prompt, String validCharacters) {
            boolean goodd;
            boolean inputError;   
            char inputText = 0;
            do{
                goodd = false;
                System.out.println(prompt + ": ");
                String in = reader.nextLine();
                in.replaceAll("//s","");
                if(in.length()>1||in.equals("")){
                    System.out.println("Wrong Input, try again");
                }else{
                    //do {
                    //    inputError = false;             
                        inputText = Character.toUpperCase(in.charAt(0));
                        if (validCharacters.indexOf(inputText) == -1) {
                            inputError = true;
                            System.out.println("Character out of range: please re-enter: "); 
                        }
                        else{
                            goodd=true;
                            return inputText;
                        }
                    //}while (inputError);

                }
    
            
            }while(!goodd);
    
        return inputText;
    }     
    
    // Read String

    /**
     *
     * @param prompt
     * @return
     */
        public String readString(String prompt) {
            boolean gooddd;
            
            do{
                gooddd = false;
                System.out.println(prompt + ": ");
                String gh = reader.nextLine();
                gh.replaceAll("//s", "");
                if(gh.length()>64||gh.equals("")){
                    System.out.println("Invalid input try again");
                }else{
                    return gh;
                }
            
            }while(!gooddd);
        
            return "lol";
    }
    
    // Read Int

    /**
     *
     * @param prompt
     * @param max
     * @param min
     * @return
     */
        public int readInt(String prompt, int max, int min) {
        int inputNumber = 0;
        boolean inputError;
        do {
            inputError = false;                
            System.out.println(prompt + ": ");

            try {
                inputNumber = Integer.parseInt(reader.nextLine());
                if (inputNumber < min || inputNumber > max) {
                    inputError = true;
                    System.out.println("Number out of range: please re-enter\n");                        
                }
            } catch (NumberFormatException e) {
                inputError = true;
                System.out.println("Not a valid number: please re-enter: ");
            }
        } while (inputError);
        return inputNumber;
    } 
    
    // Read Int

    /**
     *
     * @param prompt
     * @return
     */
        public int readInt(String prompt) {
        int inputNumber = 0;
        boolean inputError;
        do {
            inputError = false;                
            System.out.println(prompt + ": ");

            try {
                inputNumber = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                inputError = true;
                System.out.println("Not a valid number: please re-enter: ");
            }
        } while (inputError);
        return inputNumber;
    }     
    
    // Read Date

    /**
     *
     * @param prompt
     * @param format
     * @return
     */
        public Calendar readDate(String prompt, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateInString = readString(prompt);
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(InputHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);  
        return calendar;
    }
        
}
