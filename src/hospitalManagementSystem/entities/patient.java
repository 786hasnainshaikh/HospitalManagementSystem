package hospitalManagementSystem.entities;




import hospitalManagementSystem.daoConnection.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {
    Connection connection;

    private Scanner sc;

    public patient() {
    }

    public patient(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }
    // add patient
    public void addPatient(){
        System.out.println("enter patient name");
        String name=sc.next();
        System.out.println("enter patient age");
        int age=sc.nextInt();
        System.out.println("enter patient gender");
        String gender =sc.next();


        try {
            String query="insert into patients (patientName, patientAge, patientGeder) values (?,?,?)";
            PreparedStatement pst =connection.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, gender);

            int resutl = pst.executeUpdate();
            if (resutl>0){
                System.out.println("data inserted");
            } else {
                System.out.println("failed to add patient");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    // view patients

    public void viewPatients(){

        try {
            String query ="select * from patients";
            PreparedStatement pst =connection.prepareStatement(query);
            ResultSet resultSet=pst.executeQuery();
            System.out.println("patients:");
            while (resultSet.next()){
                int p_id= resultSet.getInt(1);
                String p_name=resultSet.getString(2);
                String p_age=resultSet.getString(3);
                String p_gender=resultSet.getString(4);

                System.out.println("patient Id : " + p_id + " patient Name: "+ p_name + " patient Age: "+ p_age +" patient Gender " + p_gender);

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // view patient by id
    public boolean getPatientById(int id){

        patient patient=new patient();

        String query="select * from patients where  patientId = ?";

        try {
            PreparedStatement pst= connection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()){
               int p_id= resultSet.getInt(1);
               String p_name= resultSet.getString(2);
               String p_age= resultSet.getString(3);
               String p_gender= resultSet.getString(4);

                System.out.println("patient Id : " + p_id + " patient Name: "+ p_name + " patient Age: "+ p_age +" patient Gender " + p_gender);
                return true;
            }
            else {
                System.out.println("patient not exist");
                return false;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
         return false;
    }





}
