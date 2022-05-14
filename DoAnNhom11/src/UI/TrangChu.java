package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

public class TrangChu extends JFrame{
	private GUI_QLKH tabQLKH = new GUI_QLKH();
	public static GUI_QLVe tabQLVe = new GUI_QLVe();
	private GUI_QLTour tabQLTour = new GUI_QLTour();
	public static GUI_QLLT tabQLLT = new GUI_QLLT();
	public TrangChu() {
		setTitle("");
		setSize(1450,900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		Font font = new Font("Serif", Font.BOLD, 16);
		
		JPanel pnNorth = new JPanel();
		JLabel lbTitel = new JLabel("Quản Lí Du Lịch");
		lbTitel.setFont(new Font("Serif",Font.BOLD,30));
		lbTitel.setForeground(Color.white);
		pnNorth.setBackground(new Color(19, 170, 255));
		pnNorth.add(lbTitel);
		add(pnNorth,BorderLayout.NORTH);
		
		JLabel lb = new JLabel();
		ImageIcon bg = new ImageIcon("img/trangchu.jpg");
		Image i = bg.getImage().getScaledInstance(1200, 400, DO_NOTHING_ON_CLOSE);
		ImageIcon b = new ImageIcon(i);
		lb.setIcon(b);
		lb.setLayout(new FlowLayout());
		JLabel lbtravel = new JLabel("Travel");
		lbtravel.setFont(new Font("Serif", Font.BOLD, 80));
		lbtravel.setForeground(Color.white);
		lb.add(lbtravel);
		
		JPanel pntour = new JPanel();
		JLabel lbt = new JLabel("Một số tour nổi bật");
		lbt.setFont(font);
		pntour.add(lbt);
		
		JPanel pnt1 = new JPanel();
		
		JLabel lbt1 = new JLabel();
		ImageIcon ii1 = new ImageIcon("img/nb1.jpg");
		Image i1 = ii1.getImage().getScaledInstance(300, 300, DO_NOTHING_ON_CLOSE);
		ImageIcon iii1 = new ImageIcon(i1);
		lbt1.setIcon(iii1);
		
		JLabel lbt2 = new JLabel();
		ImageIcon ii2 = new ImageIcon("img/nb2.jpg");
		Image i2 = ii2.getImage().getScaledInstance(300, 300, DO_NOTHING_ON_CLOSE);
		ImageIcon iii2 = new ImageIcon(i2);
		lbt2.setIcon(iii2);
		
		JLabel lbt3 = new JLabel();
		ImageIcon ii3 = new ImageIcon("img/nb3.jpg");
		Image i3 = ii3.getImage().getScaledInstance(300, 300, DO_NOTHING_ON_CLOSE);
		ImageIcon iii3 = new ImageIcon(i3);
		lbt3.setIcon(iii3);
		
		JLabel lbt4 = new JLabel();
		ImageIcon ii4 = new ImageIcon("img/nb4.jpg");
		Image i4 = ii4.getImage().getScaledInstance(300, 300, DO_NOTHING_ON_CLOSE);
		ImageIcon iii4 = new ImageIcon(i4);
		lbt4.setIcon(iii4);
		
		pnt1.setLayout(new GridLayout(1, 4,5,5));
		pnt1.add(lbt1);
		pnt1.add(lbt2);
		pnt1.add(lbt3);
		pnt1.add(lbt4);
		
		JPanel pnTen = new JPanel(),
				pnTen1 = new JPanel(),pnTen2 = new JPanel(),pnTen3 = new JPanel(),pnTen4 = new JPanel();
		JLabel lbTen1 = new JLabel("Đà Nẵng"),lbTen2= new JLabel("Phú Quốc"),lbTen3=new JLabel("Nha Trang"),
				lbTen4 = new JLabel("Đà Lạt");
		
		
		
		pnTen1.setPreferredSize(new Dimension(300, 50));
		pnTen2.setPreferredSize(new Dimension(300, 50));
		pnTen3.setPreferredSize(new Dimension(300, 50));
		pnTen4.setPreferredSize(new Dimension(300, 50));
		pnTen1.add(lbTen1);pnTen2.add(lbTen2);pnTen3.add(lbTen3);pnTen4.add(lbTen4);
		pnTen.setLayout(new GridLayout(1,4,5,0));
		pnTen.add(pnTen1);pnTen.add(pnTen2);pnTen.add(pnTen3);pnTen.add(pnTen4);
		
		
		pnTen1.setBackground(new Color(19, 170, 255));
		pnTen2.setBackground(new Color(19, 170, 255));
		pnTen3.setBackground(new Color(19, 170, 255));
		pnTen4.setBackground(new Color(19, 170, 255));
		
		lbTen1.setForeground(Color.white);
		lbTen2.setForeground(Color.white);
		lbTen3.setForeground(Color.white);
		lbTen4.setForeground(Color.white);
		lbTen1.setFont(font);
		lbTen2.setFont(font);
		lbTen3.setFont(font);
		lbTen4.setFont(font);
		
		
		JPanel pn = new JPanel();
		pn.add(lb);
		pn.add(lbt);
		pn.add(pnt1);
		pn.add(pnTen,BorderLayout.SOUTH);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(new Color(238, 204, 237));
		tabbedPane.setForeground(new Color(251, 86, 131));
		tabbedPane.setFont(new Font("Serif",Font.BOLD,20));
		tabbedPane.addTab("Trang Chủ",new ImageIcon("img/home.png"), pn);
		tabbedPane.addTab("Quản Lí Vé",new ImageIcon("img/ve.png"),tabQLVe.getTab());
		tabbedPane.addTab("Quản Lí Tour",new ImageIcon("img/tour.png"), tabQLTour.getTab());
		tabbedPane.addTab("Quản Lí Lộ Trình",new ImageIcon("img/lotrinh.png"), tabQLLT.getTab());
		tabbedPane.addTab("Quản Lí Khách Hàng",new ImageIcon("img/khachhang.png"),tabQLKH.getTab());
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		add(tabbedPane);
		
		JMenuBar jMenuBar = new JMenuBar();
		JMenu jmenuHeThong = new JMenu("Hệ Thống");
		JMenuItem jmiDangXuat = new JMenuItem("Đăng Xuất");
		jmiDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_DOWN_MASK));
		jmiDangXuat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FormDangNhap().setVisible(true);
		        dispose();
			}
		});
		JMenuItem jmiKetThuc = new JMenuItem("Kết thúc");
		jmiKetThuc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK));
		jmiKetThuc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMenuItem jmiTrangChu = new JMenuItem("Trang Chủ");
		jmiTrangChu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_DOWN_MASK));
		jmiTrangChu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		jmenuHeThong.add(jmiTrangChu);
		jmenuHeThong.addSeparator();
		jmenuHeThong.add(jmiDangXuat);
		jmenuHeThong.addSeparator();
		jmenuHeThong.add(jmiKetThuc);
		
		
		
		JMenu jMenuQuanLi = new JMenu("Quản Lí");
		JMenuItem jmiQVe = new JMenuItem("Quản Lí Vé");
		jmiQVe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_DOWN_MASK));
		jmiQVe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		jMenuQuanLi.add(jmiQVe);
		jMenuQuanLi.addSeparator();
		JMenuItem jmiQLTour = new JMenuItem("Quản Lí Tour");
		jmiQLTour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_DOWN_MASK));
		jmiQLTour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		jMenuQuanLi.add(jmiQLTour);
		jMenuQuanLi.addSeparator();
		JMenuItem jmiQLLT = new JMenuItem("Quản Lí Lộ Trình");
		jmiQLLT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_DOWN_MASK));
		jmiQLLT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		jMenuQuanLi.add(jmiQLLT);
		jMenuQuanLi.addSeparator();
		JMenuItem jmiQLKH = new JMenuItem("Quản Lí Khách Hàng");
		jmiQLKH.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.CTRL_DOWN_MASK));
		jmiQLKH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		jMenuQuanLi.add(jmiQLKH);
		
		jMenuBar.add(jmenuHeThong);
		jMenuBar.add(jMenuQuanLi);
		setJMenuBar(jMenuBar);
		
		
		
	}
	public static void main(String[] args) {
		new FormDangNhap().setVisible(true);
//		new TrangChu().setVisible(true);
	}
}
