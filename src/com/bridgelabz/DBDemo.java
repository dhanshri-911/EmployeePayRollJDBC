package com.bridgelabz;

import java.sql.*;
import java.util.Enumeration;

public class DBDemo {
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/Payroll_Service?useSSL=false";
        String userName = "root";
        String password = "admin00";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the calsspath!",e);
        }

        try {
            System.out.println("Connecting to database: " + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful!!!!!" + con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listDrivers();
        System.out.println("");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee_payroll");
        System.out.println("Before Update");
        display(resultSet);
        System.out.println("After Update");
        String updatequery = "update employee_payroll set Basic_Pay = ? where name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(updatequery);
        preparedStatement.setInt(1,3000000);
        preparedStatement.setString(2,"Terisa");
        preparedStatement.executeUpdate();
        String updatedquery = "select * from employee_payroll where name = ?";
        PreparedStatement preparedStatement1 = con.prepareStatement(updatedquery);
        ResultSet resultSet1 = preparedStatement1.executeQuery("select * from employee_payroll");
        display(resultSet1);
    }
    static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("   " + driverClass.getClass().getName());
        }
    }
        private static void  display(ResultSet resultSet) throws SQLException {
            while (resultSet.next()){
                System.out.print(resultSet.getInt("id")+" ");
                System.out.print(resultSet.getString("name")+" ");
                System.out.print(resultSet.getString("department")+" ");
                System.out.print(resultSet.getLong("phone")+" ");
                System.out.print(resultSet.getString("address")+" ");
                System.out.print(resultSet.getString("gender")+" ");
                System.out.print(resultSet.getFloat("Basic_pay")+" ");
                System.out.print(resultSet.getFloat("deductions")+" ");
                System.out.print(resultSet.getFloat("Taxable_pay")+" ");
                System.out.print(resultSet.getFloat("IncomeTax")+" ");
                System.out.print(resultSet.getFloat("Net_pay")+" ");
                System.out.print(resultSet.getDate("start")+" ");
                System.out.println("");
            }
    }
}
