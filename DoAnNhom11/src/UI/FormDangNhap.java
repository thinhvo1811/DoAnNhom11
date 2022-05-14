package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;

public class FormDangNhap extends JFrame implements ActionListener{
	private JTextField tfTaiKhoan;
	private JPasswordField jpMatKhau;
	private JLabel lbTaiKhoan,lbMatKhau,lbTitle;
	private JButton btDangNhap,btDangKy;
	private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	public FormDangNhap() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Đăng Nhập");
		setSize(600, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel pnNorth = new JPanel();
		lbTitle = new JLabel("Đăng Nhập");
		lbTitle.setFont(new Font("Arial", Font.BOLD, 40));
		lbTitle.setForeground(Color.white);
		pnNorth.add(lbTitle);
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(null);
		int x = 35,y=15,w=100,h=30;
		pnCenter.add(lbTaiKhoan = new JLabel("Tài Khoản: "));
		lbTaiKhoan.setBounds(x, y, w, h);
		pnCenter.add(tfTaiKhoan=new JTextField());
		tfTaiKhoan.setBounds(x+100, y, w+280, h);
		pnCenter.add(lbMatKhau = new JLabel("Mật Khẩu: "));
		lbMatKhau.setBounds(x, y+60, w, h);
		pnCenter.add(jpMatKhau=new JPasswordField());
		jpMatKhau.setBounds(x+100, y+60, w+280, h);
		lbTaiKhoan.setForeground(Color.white);
		lbMatKhau.setForeground(Color.white);
		
		pnCenter.add(btDangKy = new JButton("Tạo Tài Khoản"));
		btDangKy.setBounds(x+150, y+120, w+20, h+10);
		btDangKy.setBackground(Color.white);
		btDangKy.setForeground(new Color(19, 170, 255));
		pnCenter.add(btDangNhap = new JButton("Đăng Nhập"));
		btDangNhap.setBounds(x+300, y+120, w+20, h+10);
		btDangNhap.setBackground(Color.white);
		btDangNhap.setForeground(new Color(19, 170, 255));
		add(pnNorth,BorderLayout.NORTH);
		add(pnCenter,BorderLayout.CENTER);
		pnNorth.setBackground(new Color(19, 170, 255));
		pnCenter.setBackground(new Color(19, 170, 255));
		btDangKy.addActionListener(this);
		btDangNhap.addActionListener(this);
	}
	public static void main(String[] args) {
		new FormDangNhap().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btDangNhap)) {
			String maNV = tfTaiKhoan.getText(),pw = jpMatKhau.getText();
			if(maNV.equals("")||pw.equals("")) {
				JOptionPane.showMessageDialog(this, "Nhập đầy đủ tài khoản mật khẩu");
			}else {
				if(nhanVien_DAO.getNhanVienTheoMa(maNV,pw)!=null) {
					TrangChu t = new TrangChu();
					t.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Tài khoản mật khẩu sai!");
				}
			}
			
		}else if(o.equals(btDangKy)) {
			FormDangKy dk = new FormDangKy();
			dk.setVisible(true);
			dispose();
		}
		
	}
}
