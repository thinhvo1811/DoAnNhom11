package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import connectDB.ConnectDB;
import entity.Ve;
import entity.KhachHang;
import entity.LoTrinh;
import entity.Tour;


public class Ve_DAO {
	public  ArrayList<Ve> getAllVe() {
		
		ArrayList<Ve> dsVe = new ArrayList<Ve>();					
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from Ve";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				String maVe = rs.getString(1);
				int soNguoi = rs.getInt(2);
				double giaVe = rs.getDouble(3);
				LoTrinh LT = new LoTrinh(rs.getString(4));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				Tour tour = new Tour(rs.getString(6));
				Ve ve =  new Ve(maVe, LT, giaVe, soNguoi, khachHang, tour);
				dsVe.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVe;
	}
	public  ArrayList<Ve> getVeTheoMaVe(String id) {
		
		ArrayList<Ve> dsVe = new ArrayList<Ve>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
		
			String sql = "Select * from Ve where maVe = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1, id);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				String maVe = rs.getString(1);
				int soNguoi = rs.getInt(2);
				double giaVe = rs.getDouble(3);
				LoTrinh LT = new LoTrinh(rs.getString(4));
				KhachHang khachHang = new KhachHang(rs.getString(5));
				Tour tour = new Tour(rs.getString(6));
				Ve ve =  new Ve(maVe, LT, giaVe, soNguoi, khachHang, tour);
				dsVe.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return dsVe;
	}

	public  void addVe(Ve ve) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
				statement = con.prepareStatement("insert into Ve values(?,?,?,?,?,?)");
				statement.setString(1, ve.getMaVe());
				statement.setInt(2, ve.getSoNguoi());
				statement.setDouble(3, ve.getGiaVe());
				statement.setString(4, ve.getLoTrinh().getMaLT());
				statement.setString(5, ve.getKhachHang().getMaKH());
				statement.setString(6, ve.getTour().getMaTour());
				statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	public  void updateVe(Ve ve) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
				statement = con.prepareStatement("update Ve set soNguoi=?, giaVe=?,maLT=?, maKH=?,maTour=? where maVe=?");
				statement.setInt(1, ve.getSoNguoi());
				statement.setDouble(2, ve.getGiaVe());
				statement.setString(3, ve.getLoTrinh().getMaLT());
				statement.setString(4, ve.getKhachHang().getMaKH());
				statement.setString(5, ve.getTour().getMaTour());
				statement.setString(6, ve.getMaVe());
				statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}	
	public  void deleteVe(String maVe) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		String sql = "delete from Ve where maVe=?";
		try {
				statement = con.prepareStatement(sql);
				statement.setString(1, maVe);
				statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	public ArrayList<Ve> getVeTheoMaKH(String ma){
		ArrayList<Ve> list = new ArrayList<Ve>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		String sql = "SELECT * FROM VE INNER JOIN KHACHHANG ON KHACHHANG.maKH = VE.maKH WHERE KHACHHANG.maKH = ?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1,ma);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String maVe = rs.getString(1);
				int soNguoi = rs.getInt(2);
				double giaVe = rs.getDouble(3);
				LoTrinh lt = new LoTrinh(rs.getString(4));
				Tour t = new Tour(rs.getString(5));
				KhachHang kh = new KhachHang(rs.getString(6));
				Ve ve = new Ve(maVe, lt, giaVe, soNguoi, kh, t);
				list.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<Ve> getVeTheoTour(String ma){
		ArrayList<Ve> list = new ArrayList<Ve>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		String sql = "SELECT * FROM VE INNER JOIN TOUR ON TOUR.maTour = VE.maTour WHERE TOUR.maTour = ?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1,ma);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String maVe = rs.getString(1);
				int soNguoi = rs.getInt(2);
				double giaVe = rs.getDouble(3);
				LoTrinh lt = new LoTrinh(rs.getString(4));
				Tour t = new Tour(rs.getString(5));
				KhachHang kh = new KhachHang(rs.getString(6));
				Ve ve = new Ve(maVe, lt, giaVe, soNguoi, kh, t);
				list.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public ArrayList<Ve> getVeTheoLoTrinh(String ma){
		ArrayList<Ve> list = new ArrayList<Ve>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement st = null;
		String sql = "SELECT * FROM  VE  INNER JOIN LOTRINH ON LOTRINH.maLT = VE.maLT WHERE LOTRINH.maLT = ?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1,ma);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String maVe = rs.getString(1);
				int soNguoi = rs.getInt(2);
				double giaVe = rs.getDouble(3);
				LoTrinh lt = new LoTrinh(rs.getString(4));
				Tour t = new Tour(rs.getString(5));
				KhachHang kh = new KhachHang(rs.getString(6));
				Ve ve = new Ve(maVe, lt, giaVe, soNguoi, kh, t);
				list.add(ve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
