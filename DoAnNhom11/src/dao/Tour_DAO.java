package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Tour;

public class Tour_DAO {
	public  Tour_DAO(){
	}
	public ArrayList<Tour> getAllTour(){
		ArrayList<Tour> dstour = new ArrayList<Tour>();
		try {
			ConnectDB.getInstance();
			Connection connection=ConnectDB.getConnection();
			
			String sql="select * from TOUR";
			Statement statement=connection.createStatement();
			
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
				String maTour=rs.getString(1);
				String tenTour=rs.getString(2);
				int soNgay= rs.getInt(3);
				double giaTour=rs.getDouble(4);
				Tour tour=new Tour(maTour, tenTour, soNgay, giaTour);
				dstour.add(tour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dstour;
	}
	public boolean AddTour(Tour tour) {
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into TOUR values(?, ?, ?, ?)");
			stmt.setString(1,tour.getMaTour());
			stmt.setString(2, tour.getTenTour());
			stmt.setInt(3, tour.getSoNgay());
			stmt.setDouble(4, tour.getGiaTour());
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean updateTour(Tour tour) {
		ConnectDB.getInstance();
		new ConnectDB();
		Connection con=ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update TOUR set tenTour= ?, soNgay= ?, giaTour= ? where maTour=?");
			
			stmt.setString(1, tour.getTenTour());
			stmt.setInt(2, tour.getSoNgay());
			stmt.setDouble(3, tour.getGiaTour());
			stmt.setString(4,tour.getMaTour());
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	public void deleteTour(String maTour){
		ConnectDB.getInstance();
		new ConnectDB();
		Connection con=ConnectDB.getConnection();
		PreparedStatement stmt=null;
		String sql = "Delete from TOUR where maTour= ?";
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maTour);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public Tour getTourTheoma(String maTour){
		Tour tour = null;
		ConnectDB.getInstance();
		Connection con=ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="Select * from TOUR where maTour=?";
			statement=con.prepareStatement(sql);
			statement.setString(1,maTour);
			
			ResultSet rs=statement.executeQuery();
			
			while (rs.next()) {
				String ma=rs.getString(1);
				String tenTour=rs.getString(2);
				int soNgay= rs.getInt(3);
				double giaTour=rs.getDouble(4);
				tour=new Tour(ma, tenTour, soNgay, giaTour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tour;
	}
}
