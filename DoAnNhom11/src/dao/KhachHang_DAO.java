package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	public  ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();		
				
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();	
			
			String sql = "Select * from KHACHHANG";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
						String maKH = rs.getString(1);
						String TenKH = rs.getString(2);
						String Email = rs.getString(3);
						String SoDT = rs.getString(4);
						String diaChi = rs.getString(5);
						KhachHang kh = new KhachHang(maKH, TenKH, Email, SoDT, diaChi);
						dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}
	
	
	
	public ArrayList<KhachHang> getTheoMaKhachHang(String ma) {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from KHACHHANG where maKH like ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, "%"+ma+"%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString(1);
				String tenKH = rs.getString(2);
				String email = rs.getString(3);
				String soDT = rs.getString(4);
				String diaChi = rs.getString(5);
				KhachHang kh = new KhachHang(maKH, tenKH, email, soDT, diaChi);
				dskh.add(kh);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dskh;
	}
	
	
	public boolean deleteKH (String makh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			String sql = "delete from KHACHHANG where maKH = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, makh);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	
	public boolean createKH(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into KHACHHANG values(?, ?, ? ,? , ?)");
			stmt.setString(1,  kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(3, kh.getEmail());
			stmt.setString(4, kh.getSdt());
			stmt.setString(5, kh.getDiachi());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return n>0;
	}
	
	//update
	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update KHACHHANG set tenKH = ?, email =?, sdt=?, diaChi=? where maKH = ?");
			
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getEmail());
			stmt.setString(3, kh.getSdt());
			stmt.setString(4, kh.getDiachi());
			stmt.setString(5,  kh.getMaKH());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	public boolean deleteALLKH () {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete KHACHHANG");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
}
