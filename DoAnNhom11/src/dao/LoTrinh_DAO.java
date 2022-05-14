package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoTrinh;
import entity.Tour;


public class LoTrinh_DAO {
	public LoTrinh_DAO() {
		
	}
	public ArrayList<LoTrinh> getalltbLoTrinh(){
		ArrayList<LoTrinh> dsLT = new ArrayList<LoTrinh>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from LoTrinh";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLT = rs.getString(1);
				Tour tour = new Tour(rs.getString(2));
				Date ngayXP = rs.getDate(3);
				Date ngayKT = rs.getDate(4);
				String diemXP = rs.getString(5);
				String diemKT = rs.getString(6);
				LoTrinh loTrinh = new LoTrinh(maLT, tour, ngayXP, ngayKT, diemXP, diemKT);
				dsLT.add(loTrinh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLT;
	}

	public ArrayList<LoTrinh> getLoTrinhTheoNgayXP(String ngay){
		ArrayList<LoTrinh> dsLT = new ArrayList<LoTrinh>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {		
			
			String sql = "SELECT * FROM LOTRINH WHERE ngayXP LIKE ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, "%"+ngay+"%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLT = rs.getString(1);
				Tour maTour = new Tour(rs.getString(2));
				Date ngayXP = rs.getDate(3);
				Date ngayKT = rs.getDate(4);
				String diemXP = rs.getString(5);
				String diemKT = rs.getString(6);
				
				LoTrinh lt = new LoTrinh(maLT, maTour, ngayXP, ngayKT, diemXP, diemKT);
				dsLT.add(lt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dsLT;
	}
	public ArrayList<LoTrinh> getLoTrinhTheoDiemKT(String diaDiem){
		ArrayList<LoTrinh> dsLT = new ArrayList<LoTrinh>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {		
			
			String sql = "SELECT * FROM LOTRINH WHERE DIEMDEN LIKE ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, "%"+diaDiem+"%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLT = rs.getString(1);
				Tour maTour = new Tour(rs.getString(2));
				Date ngayXP = rs.getDate(3);
				Date ngayKT = rs.getDate(4);
				String diemXP = rs.getString(5);
				String diemKT = rs.getString(6);
				
				LoTrinh lt = new LoTrinh(maLT, maTour, ngayXP, ngayKT, diemXP, diemKT);
				dsLT.add(lt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dsLT;
	}
	public boolean create (LoTrinh loTrinh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LoTrinh values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1,  loTrinh.getMaLT());
			stmt.setString(2, loTrinh.getMaTour().getMaTour());
			stmt.setDate(3, loTrinh.getNgayXP());
			stmt.setDate(4, loTrinh.getNgayKT());
			stmt.setString(5,  loTrinh.getDiemXP());
			stmt.setString(6,  loTrinh.getDiemKT());
			
			
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean update(LoTrinh loTrinh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LoTrinh set maTour=?, ngayXP=?, "
					+"ngayKT=?, diemXP=?, diemden=? "
					+"where maLT=?");
			stmt.setString(1, loTrinh.getMaTour().getMaTour());
			stmt.setDate(2, loTrinh.getNgayXP());
			stmt.setDate(3, loTrinh.getNgayKT());
			stmt.setString(4, loTrinh.getDiemXP());
			stmt.setString(5, loTrinh.getDiemKT());
			
			stmt.setString(6, loTrinh.getMaLT());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean delete(String maLT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LoTrinh where maLT=?");
			stmt.setString(1, maLT);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}
	public ArrayList<LoTrinh> getLTTheoMaTour(String ma){
		ArrayList<LoTrinh> dsLT = new ArrayList<LoTrinh>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {		
			
			String sql = "SELECT * FROM LOTRINH INNER JOIN TOUR ON LOTRINH.maTour = TOUR.maTour WHERE TOUR.maTour like ?";
			statement = con.prepareStatement(sql);
			statement.setString(1,ma);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLT = rs.getString(1);
				Tour maTour = new Tour(rs.getString(2));
				Date ngayXP = rs.getDate(3);
				Date ngayKT = rs.getDate(4);
				String diemXP = rs.getString(5);
				String diemKT = rs.getString(6);
				
				LoTrinh lt = new LoTrinh(maLT, maTour, ngayXP, ngayKT, diemXP, diemKT);
				dsLT.add(lt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return dsLT;
	}
}
