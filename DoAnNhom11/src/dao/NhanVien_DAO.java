package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
	}
	public boolean themNhanVien(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("Insert into NhanVien values(?,?,?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getPw());
			stmt.setString(3,nv.getNlpw());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
	}
	public NhanVien getNhanVienTheoMa(String maNV,String pw) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhanVien nv = null;
		String sql = "Select * from NhanVien where maNV = ? and pw = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				nv = new NhanVien(maNV,pw, rs.getString(3));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
}
