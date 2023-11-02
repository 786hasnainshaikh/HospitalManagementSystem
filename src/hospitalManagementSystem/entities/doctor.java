package hospitalManagementSystem.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class doctor {

    private Connection connection;



    public doctor(Connection connection) {
        this.connection = connection;
    }
    // add patient


    // view patients

    public void viewDoctors(){

        try {
            String query ="select * from doctors";
            PreparedStatement pst =connection.prepareStatement(query);
            ResultSet resultSet=pst.executeQuery();
            System.out.println("Doctors:");
            while (resultSet.next()){

                int doc_id=resultSet.getInt(1);
                String doc_name=resultSet.getString(2);
                String doc_spclization=resultSet.getString(3);

                System.out.println("Doctor ID :"+ doc_id  + "     Doctor Name: " + doc_name  + "     Doctor Specialization: " + doc_spclization);


            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public boolean getDoctorById(int id){
        String query="select * from doctors where  doctorId = ?";
        try {
            PreparedStatement pst= connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()){
                int doctor_id= resultSet.getInt(1);
                String doctor_name= resultSet.getString(2);
                String doctor_specilztion= resultSet.getString(3);
                System.out.println("Doctor id : "+ doctor_id + " "+ "Doctor name: " +" "+ doctor_name +"  "+ "Doctor specialization: "+ doctor_specilztion );
                return true;
            }
            else {
                System.out.println("Doctor not exist");
                return false;
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return false;
    }

}
