/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceapp;

import controllers.AttendanceController;
//import controllers.AttendanceController_Increment1;
//import controllers.AttendanceController_Increment2;
//import controllers.AttendanceController_Increment3;
//import controllers.AttendanceController_Increment4;
/**
 *
 * @author mga
 */
public class AttendanceApp {

    public static void run() {        
        System.out.println("Attendance App\n" + "==============\n\n");
        
          AttendanceController attendanceController = new AttendanceController();
       
          //AttendanceController_Increment1 attendanceController = new AttendanceController_Increment1();
          //AttendanceController_Increment2 attendanceController = new AttendanceController_Increment2();
          //AttendanceController_Increment3 attendanceController = new AttendanceController_Increment3();
          //AttendanceController_Increment4 attendanceController = new AttendanceController_Increment4();
          
        
        attendanceController.run();
        
        System.out.println("Thank you for using Attendance App. Good bye.\n");        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AttendanceApp attendanceApp = new AttendanceApp();
        attendanceApp.run();
    }
    
}
