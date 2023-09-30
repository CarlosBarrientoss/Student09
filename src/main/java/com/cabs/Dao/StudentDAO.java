package com.cabs.Dao;

import com.cabs.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.cabs.connection.ConnectionDB.getConnectionBD;

public class StudentDAO {
    public List<Student> listar(){

        List<Student> students = new ArrayList<>();

        PreparedStatement ps;//encapasula la conexion
        ResultSet rs;//guarda la conexion
        Connection con = getConnectionBD();//nos trae el acceso de la conexion
        String sql = "SELECT * FROM student ORDER BY id_student";

        try {
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                Student student = new Student();

                student.setIdStudent(rs.getInt("id_student"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        }catch(Exception ex){
            System.out.println("Ocurrio un error en la consulta: " + ex.getMessage() );
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("Ocurrio un error al cerrar la conexión: " + ex.getMessage());

            }
        }

        return students;
    }

    public boolean searchStudentById2(Student student){

        PreparedStatement ps;
        ResultSet rs;
        //crear conexion
        Connection con = getConnectionBD();
        String sql = "SELECT * FROM student WHERE id_student = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            rs = ps.executeQuery();

            while(rs.next()){
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                return true;
            }
        }catch(Exception ex){
            System.out.println("An error ocurred: " + ex.getMessage());
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("An error ocurred while triying closed conection: " + ex.getMessage());
            }
        }
        
        return false;
    }

    public boolean searchStudentById(Student student){

        PreparedStatement ps;
        ResultSet rs;
        //se crea una conexion
        Connection con = getConnectionBD();
        String sql = "SELECT * FROM student WHERE id_student = ?";

        try {
            ps = con.prepareStatement(sql);
            //se establece el parametro id_student de la consulta
            ps.setInt(1, student.getIdStudent());
            rs = ps.executeQuery();//cuando vamos a recuperar informacion de la bd

            while(rs.next()){
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                return true;
            }
        }catch(Exception ex){
            System.out.println("An error occurred in the query: " + ex.getMessage());
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("An error occurred in the query: " + ex.getMessage());
            }
        }

        return false;
    }

    public boolean addStudent2(Student student){

        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnectionBD();
        String sql = "INSERT INTO student(first_name, last_name, phone, email)" + "VALUE(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.execute();
            return true;

        }catch(Exception ex){
            System.out.println("An error ocurred: " + ex.getMessage());
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("An error ocurred while triying closed the connection: " + ex.getMessage());
            }
        }


        return false;
    }
    public boolean addStudent(Student student){

        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnectionBD();
        String sql = "INSERT INTO student(first_name, last_name, phone, email) " + "VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.execute();//When you think about modifying database information
            return true;
        }catch(Exception ex){
            System.out.println("An error ocurred in the query: "+ ex.getMessage());
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                System.out.println("An error ocurred in the query: " + ex.getMessage());
            }
        }
        return false;
    }

    public boolean updateStudent2(Student student){

        PreparedStatement ps;
        Connection con = getConnectionBD();
        String sql = "UPDATE student SET first_name=?, last_name=?, phone=?, email=? WHERE id_student=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getIdStudent());
            ps.execute();

            return true;

        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
    public boolean updateStudent(Student student){

        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnectionBD();
        String sql = "UPDATE student SET first_name=?, last_name=?, phone=?, email=? WHERE id_student=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getIdStudent());
            ps.execute();//When you think about modifying database information
            return true;
        }catch(Exception ex){
            System.out.println("An error ocurred in the query: " + ex.getMessage());
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("An error occurred while trying to close the conection");
            }
        }

        return false;
    }

    public boolean deleteStudent2(Student student){

        PreparedStatement ps;
        Connection con = getConnectionBD();
        String sql = "DELETE FROM student WHERE id_student = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            ps.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        return false;
    }

    public boolean deleteStudent(Student student){

        PreparedStatement ps;
        ResultSet rs;
        //se crea una conexion
        Connection con = getConnectionBD();
        String sql = "DELETE FROM student WHERE id_student = ? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getIdStudent());
            ps.execute();
            return true;
        }catch(Exception ex){
            System.out.println("Error ocurred: " +ex.getMessage());
        }finally {
            try {
                con.close();
            }catch(Exception ex){
                System.out.println("Error corred while triying closet the connection: " + ex.getMessage());
            }
        }


        return false;
    }

    public static void main(String[] args) {
        StudentDAO studentDao = new StudentDAO();

/*
        System.out.println("Add a new student");
        Student objectStudent3 = new Student("Rodrigo", "Goes", "30025698", "rodrigo@gmail.com");
        boolean objectUpdate = studentDao.addStudent2(objectStudent3);
        if(objectUpdate){
            System.out.println("Student add: " + objectStudent3);
        }else{
            System.out.println("Student no add: " + objectStudent3);
        }
*/
/* ADD STUDENT
        System.out.println("Add a new student");
        Student objectStudent = new Student("Julian","Claro","315896563","julian@gmail.com");
        boolean objectFound = studentDao.addStudent(objectStudent);
        if(objectFound){
            System.out.println("Object add: " + objectStudent);
        }else{
            System.out.println("Object not add: " + objectStudent);
        }
*/

        /*LISTAR STUDENT  */
        System.out.println("Student list");
        List<Student> studentList = studentDao.listar();
        studentList.forEach(System.out::println);

        System.out.println();

        Student objectStudent4 = new Student(5,"Pedro","Goes", "30258954", "pedro@gmail.com");
        boolean objectUpdate5 = studentDao.updateStudent2(objectStudent4);
        if(objectUpdate5){
            System.out.println("Student update OK " + objectStudent4);
        }else{
            System.out.println("Student update NO " + objectStudent4);
        }

        /*LISTAR STUDENT  */
        System.out.println("Student list");
        List<Student> studentList2 = studentDao.listar();
        studentList2.forEach(System.out::println);

/*UPDATE STUDENT
        Student objectStudent = new Student(4,"Andres Julian","Claro Vila", "3002565854", "andresjulian@gmail.com");
        boolean objectUpdate = studentDao.updateStudent(objectStudent);
        if(objectUpdate){
            System.out.println("Student update success: " + objectStudent);
        }else{
            System.out.println("Student update no success: " + objectStudent);
        }


        Student objectStudent6 = new Student(2);
        boolean objectDelete = studentDao.deleteStudent(objectStudent6);
        if(objectDelete){
            System.out.println("Student delete OK: " + objectStudent6);
        }else{
            System.out.println("Student delete NO: " + objectStudent6);
        }
*/

/*DELETED STUDENT
        Student objectStudent = new Student(4);
        System.out.println("objectStudent last deleted = " + objectStudent);
        boolean objectDelete = studentDao.deleteStudent(objectStudent);

        if(objectDelete){
            System.out.println("Student delete: " + objectStudent);
        }else{
            System.out.println("Student no deleted: " + objectStudent);
        }
*/
/*LISTAR STUDENT
        System.out.println("Student list");
        List<Student> studentList2 = studentDao.listar();
        studentList2.forEach(System.out::println);
*/
        Student objectStudent2 = new Student(2);
        boolean foundObject2 = studentDao.searchStudentById2(objectStudent2);
        if(foundObject2){
            System.out.println("Studen found: " + objectStudent2);
        }else{
            System.out.println("Studen no found: " + objectStudent2);
        }

        
/* SEARCH BY ID

        Student objectStudent = new Student(2);
        System.out.println("Estudiante antes de la busqueda: " + objectStudent);
        boolean foundStudent = studentDao.searchStudentById(objectStudent);
        var result = foundStudent  ? "Student found: "+objectStudent : "Studen no found: "+objectStudent.getIdStudent();
        System.out.println("result = " + result);
        if (foundStudent){
            System.out.println("Student found: " + objectStudent);
        }else{
            System.out.println("Studen no found: " + objectStudent.getIdStudent());
        }
        */
    }
}
