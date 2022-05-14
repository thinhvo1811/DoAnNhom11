package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.NhanVien;

public class FormDangKy extends JFrame implements ActionListener{
	private JTextField tfTaiKhoan;
	private JPasswordField jpMatKhau,jpNLMatKhau;
	private JLabel lbTaiKhoan,lbMatKhau,lbNLMatKhau,lbTitle;
	private JButton btDangNhap,btDangKy;
	private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
	public FormDangKy() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Đăng Ký");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel pnNorth = new JPanel();
		lbTitle = new JLabel("Đăng Ký");
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
		
		pnCenter.add(lbNLMatKhau = new JLabel("Nhập Lại MK: "));
		lbNLMatKhau.setBounds(x, y+120, w, h);
		pnCenter.add(jpNLMatKhau=new JPasswordField());
		jpNLMatKhau.setBounds(x+100, y+120, w+280, h);
		
		lbTaiKhoan.setForeground(Color.white);
		lbMatKhau.setForeground(Color.white);
		lbNLMatKhau.setForeground(Color.white);
		
		
		pnCenter.add(btDangNhap = new JButton("Đăng Nhập"));
		btDangNhap.setBackground(Color.white);
		btDangNhap.setBounds(x+150, y+180, w+20, h+10);
		btDangNhap.setForeground(new Color(19, 170, 255));
		pnCenter.add(btDangKy = new JButton("Đăng Ký"));
		btDangKy.setBounds(x+300, y+180, w+20, h+10);
		btDangKy.setBackground(Color.white);
		btDangKy.setForeground(new Color(19, 170, 255));
		add(pnNorth,BorderLayout.NORTH);
		add(pnCenter,BorderLayout.CENTER);
		pnNorth.setBackground(new Color(19, 170, 255));
		pnCenter.setBackground(new Color(19, 170, 255));
		btDangKy.addActionListener(this);
		btDangNhap.addActionListener(this);
	}
	public static void main(String[] args) {
		new FormDangKy().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btDangKy)) {
			String maNV = tfTaiKhoan.getText(),pw = jpMatKhau.getText(),nlpw = jpNLMatKhau.getText();
			if(maNV.equals("")||pw.equals("")||nlpw.equals("")) {
				JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin");
			}else {
				if(JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng ký không","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					if(!pw.equals(nlpw)) {
						JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu phải giống nhau");
					}else {
						NhanVien nv = new NhanVien(maNV, pw, nlpw);
							if(nhanVien_DAO.themNhanVien(nv)) {
								JOptionPane.showMessageDialog(this, "Thêm thành công");
								tfTaiKhoan.setText("");
								jpMatKhau.setText("");
								jpNLMatKhau.setText("");
							}
							else {
								JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại");
							}
					}
				}
			}
		}
		else if(o.equals(btDangNhap)) {
			FormDangNhap dn = new FormDangNhap();
			dn.setVisible(true);
			this.dispose();
		}
	}
}
