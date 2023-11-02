package hospitalManagementSystem.main;

import hospitalManagementSystem.apointment.apointment;
import hospitalManagementSystem.daoConnection.connection;
import hospitalManagementSystem.entities.doctor;
import hospitalManagementSystem.entities.patient;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        patient patient=new patient(connection.getConnection(), scanner);
        doctor doctor = new doctor(connection.getConnection());
        apointment apointment =new apointment(connection.getConnection());


        try {
            while(true){
                main.menu();
                System.out.println("enter the choice");
                int choice = scanner.nextInt();
                switch (choice){
                    case(1):
                        patient.addPatient();
                        System.out.println();
                        break;
                    case (2):
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case (3):
                        System.out.println("enter the patient Id");
                        int patient_id=scanner.nextInt();
                        patient.getPatientById(patient_id);
                        System.out.println();
                        break;
                    case (4):
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case (5):
                        System.out.println("enter the doctor id");
                        int doctor_id=scanner.nextInt();
                        doctor.getDoctorById(doctor_id);
                        System.out.println();
                        break;
                    case(6):
                        apointment.bookApointment(patient, doctor, connection.getConnection());
                        System.out.println();
                        break;

                    default:
                        System.out.println("enter valid entries");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }



    }


    public static void menu(){
        System.out.println("Hospital Management System");
        System.out.println("1. Add patient");
        System.out.println("2. View patient");
        System.out.println("3. view Patient by Id");
        System.out.println("4. view doctors");
        System.out.println("5. view doctor by id");
        System.out.println("6. Book Apointment");
    }
}
