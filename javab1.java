package Connectivity;

import java.sql.*;
import java.util.Scanner;

public class javab1 {

 
    static String url = "jdbc:mysql://localhost:8080/trafficdb?useSSL=false&serverTimezone=UTC";
    static String username = "root";
    static String password = "Sakshi@1234";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Traffic Resolution System ");
            System.out.println("1. Add Driver");
            System.out.println("2. Add Vehicle");
            System.out.println("3. Add Violation");
            System.out.println("4. Update Fine Status");
            System.out.println("5. View Reports");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1: addDrivers();
                break;
                case 2: addVehicles(); 
                break;
                case 3: addViolations(); 
                break;
                case 4: updateFines();
                break;
                case 5: viewReports();
                break;
            }
        } 
        while(choice != 6);
        sc.close();
    }
  
	public static void addDrivers() {
	    try (Connection con = DriverManager.getConnection(url, username, password);
	         Scanner sc = new Scanner(System.in)) {

	        System.out.print("Enter Driver ID: ");
	        int driverId = sc.nextInt();
	        sc.nextLine(); 

	        System.out.print("Enter Name: ");
	        String name = sc.nextLine();

	        System.out.print("Enter License No: ");
	        String licenseNo = sc.nextLine();

	        System.out.print("Enter Address: ");
	        String address = sc.nextLine();

	       
	        String sql = "INSERT INTO Drivers (DriverId, Name, LicenseNo, Address) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, driverId);
	        ps.setString(2, name);
	        ps.setString(3, licenseNo);
	        ps.setString(4, address);

	        ps.executeUpdate();
	        System.out.println("Driver added successfully!");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    public static void addVehicles() {
        try (Connection con = DriverManager.getConnection(url, username, password);
             Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter Vehicle id: ");
            int vehicleid = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter RegNo: ");
            String regno = sc.nextLine();
          
            System.out.print("Enter DriverID: ");
            int Driverid = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Vehicle Type: ");
            String Type = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO vehicles(vehicleid, regno, driverid, type) VALUES(?,?,?,?)");
            ps.setInt(1, vehicleid);
            ps.setString(2, regno);
            ps.setInt(3, Driverid);
            ps.setString(4, Type);
            ps.executeUpdate();
            System.out.println("Vehicle added successfully!");
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
  
    public static void addViolations() {
        try (Connection con = DriverManager.getConnection(url, username, password);
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter violation id: ");
            int violationId = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Enter VehicleID: ");
            int vehicleId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Violation Type (e.g., Speeding): ");
            String type = sc.nextLine();

            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

           
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO violations (violationid, vehicleid, type, date) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, violationId);
            ps.setInt(2, vehicleId);
            ps.setString(3, type);
            ps.setString(4, date);

            int rows = ps.executeUpdate(); 

            if (rows > 0) {
               
                PreparedStatement finePs = con.prepareStatement(
                    "INSERT INTO Fines (violationid, amount, status) VALUES (?, ?, ?)"
                );
                finePs.setInt(1, violationId); 
                finePs.setDouble(2, 500.00);
                finePs.setString(3, "Unpaid");

                finePs.executeUpdate();
                System.out.println("Violation added and fine generated!");
            } else {
                System.out.println("Failed to add violation.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static void updateFines() {
        try (Connection con = DriverManager.getConnection(url, username, password);
             Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter FineID to update: ");
            int fineId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new status (Paid/Unpaid): ");
            String status = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE Fines SET status=? WHERE fineid=?");
            ps.setString(1, status);
            ps.setInt(2, fineId);
            int rows = ps.executeUpdate();
            if(rows > 0) {
                System.out.println("Fine status updated successfully!");
            } else {
                System.out.println("FineID not found.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

   
   public static void viewReports() {
	    try (Connection con = DriverManager.getConnection(url, username, password);
	         Statement st = con.createStatement()) {

	        String query = "SELECT f.fineid, v.type, v.date, f.amount, f.status " +
	                       "FROM Fines f JOIN Violations v ON f.violationid = v.violationid";

	        ResultSet rs = st.executeQuery(query); // retrieve the data from db

	        System.out.println("---- Traffic Reports ----");
	        while (rs.next()) {
	            int fineId = rs.getInt("fineid");
	            String type = rs.getString("type");
	            String date = rs.getString("date"); 
	            double amount = rs.getDouble("amount");
	            String status = rs.getString("status");

	            System.out.println("FineID: " + fineId +
	                               ", Violation: " + type +
	                               ", Date: " + date +
	                               ", Amount: " + amount +
	                               ", Status: " + status);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
   }
}