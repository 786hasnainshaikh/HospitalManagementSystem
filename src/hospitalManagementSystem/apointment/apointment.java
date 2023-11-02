package hospitalManagementSystem.apointment;

import hospitalManagementSystem.entities.doctor;
import hospitalManagementSystem.entities.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class apointment {

    Connection connection;

    public apointment(Connection connection) {
        this.connection = connection;
    }

    Scanner sc=new Scanner(System.in);
    public  void bookApointment(patient patient, doctor doctor, Connection connection){
        System.out.println("enter the patient id");
        int p_id=sc.nextInt();
        System.out.println("enter doctor id ");
        int doc_id=sc.nextInt();
        System.out.println("enter apointment date '(yyyy-mm-dd) '");
        String apointmentDate= sc.next();

        // first check paitent and doctor are aviailabel in database or not "it will return true"
        if (patient.getPatientById(p_id)  && doctor.getDoctorById(doc_id) ){

            // then check if doctor is available on date or not if doctor is not available in apointments table we store new apointment
              if (checkAvaibility(doc_id, apointmentDate , connection)){

                  try {
                      String apointmentQuery="insert into  apointments( patientId, doctorId, apointmentDate) values (?,?,?)";
                      PreparedStatement pstm=connection.prepareStatement(apointmentQuery);
                      pstm.setInt(1, p_id);
                      pstm.setInt(2, doc_id);
                      pstm.setString(3, apointmentDate);

                      int i = pstm.executeUpdate();

                      if (i>0){
                          // data will be inserted into apointmenst table
                          System.out.println("apointment booked");

                      } else {
                          System.out.println("failed to book apointment");
                      }

                  } catch (SQLException e){
                      e.printStackTrace();
                  }

              }
        }

        else {
            System.out.println("patient or doctor dosn't exist");
        }

    }




   // this function will check the doctor is availabel on particular date or not
    public boolean checkAvaibility(int doctor_id , String apointmentdate , Connection connection){
        //this query return the number of rows in dabase if it return  =>1 means date is not avalable another one booked for that day
        String query="select count(*) from apointments where doctorId=? and apointmentDate=? ";
        try {
             PreparedStatement pstm= connection.prepareStatement(query);
             pstm.setInt(1, doctor_id);
             pstm.setString(2, apointmentdate);

             ResultSet result= pstm.executeQuery();

             if (result.next()){
                 // this line retuen the number of rows
                 int count=result.getInt(1);
                 if (count==0){
                     // if row not availabe menas doctor is available on that day and return true
                     return true;
                 } else {
                     System.out.println("Doctor Apointment is already booked");
                     return false;
                 }

             }


        } catch (SQLException e){
            e.printStackTrace();
        }



     return  false;
    }
}
