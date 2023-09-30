package com.cabs.presentation;

import com.cabs.Dao.StudentDAO;
import com.cabs.domain.Student;

import java.util.List;
import java.util.Scanner;

public class SystemStudentApp {
    public static void main(String[] args) {

        Scanner consola = new Scanner(System.in);
        StudentDAO studentDao = new StudentDAO();

        boolean exit = false;

        while(!exit){
            showMenu();
            try {
                exit = excuteOption(consola, studentDao);
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }


    }

    private static void showMenu(){
        System.out.println("""
        **** Student List ****
        [1] Listar Estudiante
        [2] Buscar Estudiante
        [3] Agregar Estudiante
        [4] Modificar Estudiante
        [5] Eliminar Estudiante
        [6] Salir
        
        Selecciona una opnción ?
                """);
    }

    private static boolean excuteOption(Scanner consola, StudentDAO studentDao){
        int option = Integer.parseInt(consola.nextLine());
        boolean exit = false;

        switch (option){
            case 1 -> {
                /*LISTAR STUDENT */
                System.out.println("Student list");
                List<Student> studentList = studentDao.listar();
                studentList.forEach(System.out::println);
            }
            case 2 ->{
                /*BUSCAR STUDENT */
                System.out.println("Ingrese el id a buscar: ");
                int id_student = Integer.parseInt(consola.nextLine());
                Student objectStudent2 = new Student(id_student);
                boolean foundObject2 = studentDao.searchStudentById2(objectStudent2);
                if(foundObject2){
                    System.out.println("Studen found: " + objectStudent2);
                }else{
                    System.out.println("Studen no found: " + objectStudent2);
                }
            }
            case 3 -> {
                addStudent(consola, studentDao);
            }
            case 4 -> {
                updateStudent(consola, studentDao);
            }
        }

        return false;
    }

    private static void addStudent(Scanner consola, StudentDAO studentDao){
        System.out.println("Add a new student");

        System.out.println("Ingresa el nombre");
        String nombre = consola.nextLine();
        System.out.println("Ingresa el apellido");
        String apellido = consola.nextLine();
        System.out.println("Ingresa el telefono");
        String telefono = consola.nextLine();
        System.out.println("Ingresa el correo");
        String correo = consola.nextLine();

        Student objectStudent = new Student(nombre, apellido, telefono, correo);
        boolean objectFound = studentDao.addStudent(objectStudent);
        if(objectFound){
            System.out.println("Object add: " + objectStudent);
        }else{
            System.out.println("Object not add: " + objectStudent);
        }
    }

    private static void updateStudent(Scanner consola, StudentDAO studentDao){
        System.out.println("Ingresa el ID del estudiante");
        int id_student = Integer.parseInt(consola.nextLine());

        System.out.println("Ingresa el nombre");
        String nombre = consola.nextLine();
        System.out.println("Ingresa el apellido");
        String apellido = consola.nextLine();
        System.out.println("Ingresa el telefono");
        String telefono = consola.nextLine();
        System.out.println("Ingresa el correo");
        String correo = consola.nextLine();

        Student objectStudent = new Student(id_student, nombre, apellido, telefono, correo);
        boolean objectUpdate = studentDao.updateStudent(objectStudent);
        if(objectUpdate){
            System.out.println("Student update success: " + objectStudent);
        }else{
            System.out.println("Student update no success: " + objectStudent);
        }
    }
}