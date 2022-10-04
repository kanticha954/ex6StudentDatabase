/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabasewithdbclass;

import java.sql.SQLException;
import java.util.ArrayList;
import utilities.DatabaseDriver;
import utilities.DatabaseHandler;

/**
 *
 * @author user
 */
public class StudentDatabaseWithDBClass {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        String url = "jdbc:derby://localhost:1527/Student";
        String user = "app";
        String passwd = "app";
        /*String driver = "com.mysql.cj.jdbc.Driver";
        //String url="jdbc:mysql://localhost:3306/Student?serverTimezone=UTC";
        String user = "root";
        String passwd = "root";*/
        DatabaseDriver dbDriver = new DatabaseDriver(driver, url, user, passwd);
        DatabaseHandler dbHandler = new DatabaseHandler(dbDriver);
        Student stu1 = new Student(1, "John", 3.45);
        Student stu2 = new Student(2, "Marry", 4.00);
        StudentTable.insertStudent(dbHandler, stu1);
        StudentTable.insertStudent(dbHandler, stu2);
        //Student emp = StudentTable.findStudentById(dbHandler, 1);
        //emp.setName("Jack");
        //emp.setSalary(98765);
        //StudentTable.updateStudent(dbHandler, emp);
        //StudentTable.removeStudent(dbHandler, emp);
        //ArrayList<Student> StudentList = StudentTable.findStudentByName(dbHandler, "Marry");
        ArrayList<Student> StudentList = StudentTable.findAllStudent(dbHandler);
        if (StudentList != null) {
            printAllStudent(StudentList);
        }
        dbHandler.closeConnection();
    }
    
    public static void printAllStudent(ArrayList<Student> StudentList) {
        for(int i = 0; i < StudentList.size(); i++) {
            System.out.print(StudentList.get(i).getId() + " ");
            System.out.print(StudentList.get(i).getName() + " ");
            System.out.println(StudentList.get(i).getGpa() + " ");
        }
        
    }
}
