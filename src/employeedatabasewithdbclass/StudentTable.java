/*
 * To change this tstulate, choose Tools | Tstulates
 * and open the tstulate in the editor.
 */
package employeedatabasewithdbclass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DatabaseHandler;

/**
 *
 * @author sarun
 */
public class StudentTable {
    public static int updateStudent(DatabaseHandler dbHandler, Student stu) {
        String sql = "update Student set name=?, gpa=? where id=?";
        int rowUpdated;
        try {
            rowUpdated = dbHandler.update(sql, stu.getName(), stu.getGpa(), stu.getId());
        }
        catch (SQLException ex ) {
            
            rowUpdated = 0;
        }
        
        return rowUpdated;
    }
     public static int removeStudent(DatabaseHandler dbHandler, Student stu) {
         String sql ="delete from Student where id = ?";
         
         int rowDeleted;
         try {
            rowDeleted = dbHandler.update(sql, stu.getId());
         }
         catch (SQLException ex ) {
             rowDeleted = 0;
         }
        return rowDeleted;
    }
    public static int insertStudent(DatabaseHandler dbHandler, Student stu) {
         String sql = "insert into Student (id, name, gpa)" + 
               " values (?,?,?)";
         
         int rowInserted;
         try {
             rowInserted = dbHandler.update(sql, stu.getId(), stu.getName(), stu.getGpa());
         }
         catch(SQLException ex ) {
             rowInserted = 0;
         }
         return rowInserted;
    } 
     public static Student findStudentById(DatabaseHandler dbHandler, int id) throws SQLException {
        String sql = "select * from Student where id = ?";
        ResultSet rs;
        Student stu = null;
        rs = dbHandler.query(sql, id);
        if (rs.next()) {
           stu = new Student();
           stu.setId(rs.getInt("id"));
           stu.setName(rs.getString("name"));
           stu.setGpa(rs.getDouble("gpa"));
       }
        return stu;
        
    }
    public static ArrayList<Student> findStudentByName(DatabaseHandler dbHandler, String name) throws SQLException {
        String sql = "select * from Student where name = ?";
        ResultSet rs;
        ArrayList<Student> stuList = null;
        rs = dbHandler.query(sql, name);
        stuList = extractStudent(rs);
        return stuList;
        
    } 
    public static ArrayList<Student> findAllStudent(DatabaseHandler dbHandler) throws SQLException {
        String sql = "select * from Student order by id";
        ResultSet rs; 
        ArrayList<Student> stuList = null;
        rs = dbHandler.query(sql);
        stuList = extractStudent(rs);
        return stuList;
    }
    private static ArrayList<Student> extractStudent(ResultSet rs) {
        ArrayList<Student> stuList = new ArrayList<>();
        try {
            while(rs.next()) {
                Student Student = new Student();
                try {
                    Student.setId(rs.getInt("id"));
                    Student.setName(rs.getString("name"));
                    Student.setGpa(rs.getDouble("gpa"));
                } catch (SQLException ex) {
                    Logger.getLogger(StudentTable.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                stuList.add(Student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stuList.size() == 0) {
            stuList = null;
        }
        return stuList;
    }
}
