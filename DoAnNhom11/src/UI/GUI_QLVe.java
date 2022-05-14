package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.LoTrinh_DAO;
import dao.Tour_DAO;
import dao.Ve_DAO;
import entity.KhachHang;
import entity.LoTrinh;
import entity.Tour;
import entity.Ve;

public class GUI_QLVe {
	private JPanel pnQLVe = new JPanel();
	private JLabel lbmaVe, lbmaLT, lbsoNguoi, lbmaKH, lbmaTour,lbgiaVe;
	private JTextField tfmaVe,tfgiaVe,tfTim;
	private JComboBox<String> cbbmaKh=new JComboBox<String>(),
			cbbmaTour=new JComboBox<String>(),cbbSoNguoi,
			cbbmaLT=new JComboBox<String>();
	private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnLuu, btnTim,btnTinh;
	private DefaultTableModel model;
	private JTable table;
	private int edit = 0;
	private Ve_DAO ve_DAO;
	private KhachHang_DAO khachHang_DAO;
	private Tour_DAO tour_DAO;
	private LoTrinh_DAO loTrinh_DAO;

	public GUI_QLVe() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ve_DAO = new Ve_DAO();
		khachHang_DAO = new KhachHang_DAO();
		tour_DAO = new Tour_DAO();
		loTrinh_DAO = new LoTrinh_DAO();
		JPanel pnTitle = new JPanel(), pnCenter = new JPanel();
		JLabel lbTitle = new JLabel("Quản Lí Vé");
		pnTitle.setBackground(Color.white);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		pnTitle.add(lbTitle);
		Font font = new Font("Serif", Font.BOLD, 16);
		String header[] = { "Mã Vé", "Số Người", "Giá Vé","Mã Lộ Trình", "Mã Khách Hàng","Mã Tour"};
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground(new Color(19, 170, 255));
		tableHeader.setFont(font);
		JScrollPane scroll = new JScrollPane(table);
		pnQLVe.setLayout(new BorderLayout());

		pnCenter.setLayout(null);
		int x = 40, y = 10, w = 120, h = 25;
		lbmaVe = new JLabel("Mã vé: ");
		lbmaVe.setFont(font);
		lbmaLT = new JLabel("Mã lộ trình: ");
		lbmaLT.setFont(font);
		lbgiaVe = new JLabel("Giá vé: ");
		lbgiaVe.setFont(font);
		lbsoNguoi = new JLabel("Số người: ");
		lbsoNguoi.setFont(font);
		lbmaKH = new JLabel("Mã khách hàng: ");
		lbmaKH.setFont(font);
		lbmaTour = new JLabel("Mã Tour: ");
		lbmaTour.setFont(font);
		
		
		pnCenter.add(lbmaVe);
		lbmaVe.setBounds(x, y, w, h);
		pnCenter.add(tfmaVe = new JTextField());
		tfmaVe.setBounds(x + 120, y, w + 300, h);
		pnCenter.add(lbsoNguoi);
		lbsoNguoi.setBounds(x + 580, y, w, h);
		String [] soNguoi = {"1","2","4","8","16","32","64"};
		cbbSoNguoi = new JComboBox<String>(soNguoi);
		pnCenter.add(cbbSoNguoi);
		cbbSoNguoi.setBounds(x + 700, y, w + 300, h);

		pnCenter.add(lbmaKH);
		lbmaKH.setBounds(x, y + 50, w, h);
		loadCBBMaKH(cbbmaKh);
		pnCenter.add(cbbmaKh);
		cbbmaKh.setBounds(x + 120, y + 50, w + 300, h);
		pnCenter.add(lbmaTour);
		lbmaTour.setBounds(x + 580, y + 50, w, h);
		loadCBBMaTour(cbbmaTour);
		cbbmaTour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cbbmaLT.removeAllItems();
				for (LoTrinh lt : loTrinh_DAO.getLTTheoMaTour((String)cbbmaTour.getSelectedItem())) {
					cbbmaLT.addItem(lt.getMaLT());
				}
			}
		});
		pnCenter.add(cbbmaTour);
		cbbmaTour.setBounds(x + 700, y + 50, w + 300, h);
		pnCenter.add(lbmaLT);
		lbmaLT.setBounds(x, y + 100, w, h);
		loadCBBMaLT(cbbmaLT);
		pnCenter.add(cbbmaLT);
		cbbmaLT.setBounds(x + 120, y + 100, w + 300, h);
		pnCenter.add(lbgiaVe);
		lbgiaVe.setBounds(x + 580, y + 100, w, h);
		pnCenter.add(tfgiaVe = new JTextField());
		tfgiaVe.setBounds(x + 700, y + 100, w + 250, h);
		pnCenter.add(btnTinh = new JButton(new ImageIcon("img/Tinh.png")));
		btnTinh.setBounds(x+1070, y+100, 50, h);
		btnTinh.setBackground(new Color(19, 170, 255));
		tfgiaVe.setEnabled(false);
		pnCenter.add(scroll);
		scroll.setBounds(x, y + 150, w + 1000, h + 300);

		pnCenter.add(btnThem = new JButton("Thêm", new ImageIcon("img/Them.png")));
		btnThem.setBounds(x + 100, y + 620, 150, h+10);
		pnCenter.add(btnSua = new JButton("Sửa", new ImageIcon("img/Sua.png")));
		btnSua.setBounds(x + 300, y + 620, 150, h+10);
		pnCenter.add(btnXoa = new JButton("Xóa", new ImageIcon("img/Xoa.png")));
		btnXoa.setBounds(x + 500, y + 620, 150, h+10);
		pnCenter.add(btnXoaTrang = new JButton("Xóa Trắng", new ImageIcon("img/XoaTrang.png")));
		btnXoaTrang.setBounds(x + 700, y + 620, 150, h+10);
		pnCenter.add(btnLuu = new JButton("Lưu", new ImageIcon("img/Luu.png")));
		pnCenter.add(tfTim = new JTextField());
		tfTim.setBounds(x + 100, y + 530, w + 630, h+10);
		pnCenter.add(btnTim = new JButton("Tìm", new ImageIcon("img/Tim.png")));
		btnTim.setBounds(x + 900, y + 530, 150, h+10);

		btnLuu.setBounds(x + 900, y + 620, 150, h+10);
		btnThem.setBackground(new Color(19, 170, 255));
		btnThem.setForeground(Color.white);
		btnThem.setFont(font);
		btnSua.setBackground(new Color(19, 170, 255));
		btnSua.setForeground(Color.white);
		btnSua.setFont(font);
		btnLuu.setBackground(new Color(19, 170, 255));
		btnLuu.setForeground(Color.white);
		btnLuu.setFont(font);
		btnTim.setBackground(new Color(19, 170, 255));
		btnTim.setForeground(Color.white);
		btnTim.setFont(font);
		btnXoa.setBackground(new Color(19, 170, 255));
		btnXoa.setForeground(Color.white);
		btnXoa.setFont(font);
		btnXoaTrang.setBackground(new Color(19, 170, 255));
		btnXoaTrang.setForeground(Color.white);
		btnXoaTrang.setFont(font);
		btnLuu.setEnabled(false);
		moKhoaTextfields(false);
		DocDuLieuDatabaseVaoTable();
		
		tfTim.setText("Nhập vào mã vé muốn tìm");
		tfTim.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfTim.getText().trim().equals("")) {
					tfTim.setText("Nhập vào mã vé muốn tìm");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(tfTim.getText().trim().equals("Nhập vào mã vé muốn tìm")) {
					tfTim.setText("");
				}
			}
		});
		
		
		pnQLVe.add(pnTitle, BorderLayout.NORTH);
		pnQLVe.add(pnCenter);
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit =1;
				if(btnThem.getText().equalsIgnoreCase("Thêm")) {
					moKhoaTextfields(true);
					moKhoaControls(false);
					btnThem.setEnabled(true);
					btnLuu.setEnabled(true);
					btnThem.setText("Hủy");
					xoaRongTextfields();
					btnThem.setIcon(new ImageIcon("img/Huy.png"));
				}
				else if(btnThem.getText().equalsIgnoreCase("Hủy")) {
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnThem.setEnabled(true);
					btnLuu.setEnabled(false);
					btnThem.setText("Thêm");
					btnThem.setIcon(new ImageIcon("img/Them.png"));
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					if(JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
						model.removeRow(table.getSelectedRow());
						ve_DAO.deleteVe(tfmaVe.getText());
						xoaRongTextfields();
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn vé cần xóa");
				}
			}
		});
		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xoaRongTextfields();
			}
		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					edit = 2 ;
					if(btnSua.getText().equalsIgnoreCase("Sửa")) {
						moKhoaTextfields(true);
						tfmaVe.setEnabled(false);
						moKhoaControls(false);
						btnLuu.setEnabled(true);
						btnSua.setEnabled(true);
						btnSua.setText("Hủy");
						btnSua.setIcon(new ImageIcon("img/Huy.png"));
					}else if(btnSua.getText().equalsIgnoreCase("Hủy")) {
						moKhoaTextfields(false);
						moKhoaControls(true);
						btnLuu.setEnabled(false);
						tfmaVe.setEnabled(true);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
					}
				}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn vé cần sửa" );
				}
			}
		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(edit ==1) {
					if(validData()) {
						Ve ve = new Ve(tfmaVe.getText(),new LoTrinh((String)cbbmaLT.getSelectedItem()),
								Double.parseDouble(tfgiaVe.getText()),Integer.parseInt((String)cbbSoNguoi.getSelectedItem()),
								new KhachHang((String)cbbmaKh.getSelectedItem()),
								new Tour((String)cbbmaTour.getSelectedItem()));
						if(ve_DAO.getAllVe().contains(ve)) {
							JOptionPane.showMessageDialog(null, "K được thêm trùng mã vé");
						}
						else {
							ve_DAO.addVe(ve);
							DocDuLieuDatabaseVaoTable();
							JOptionPane.showMessageDialog(null, "Thêm thành công");
							moKhoaControls(true);
							moKhoaTextfields(false);
							btnThem.setText("Thêm");
							btnThem.setIcon(new ImageIcon("img/Them.png"));
							btnLuu.setEnabled(false);
							xoaRongTextfields();
						}
					}
				}else if(edit == 2) {
					if(validData()) {
						ve_DAO.updateVe(new Ve(tfmaVe.getText(),new LoTrinh((String)cbbmaLT.getSelectedItem()),
								Double.parseDouble(tfgiaVe.getText()),Integer.parseInt((String)cbbSoNguoi.getSelectedItem()),
								new KhachHang((String)cbbmaKh.getSelectedItem()),
								new Tour((String)cbbmaTour.getSelectedItem())));
						DocDuLieuDatabaseVaoTable();
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						moKhoaTextfields(false);
						moKhoaControls(true);
						btnLuu.setEnabled(false);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
						tfmaVe.setEnabled(true);
						xoaRongTextfields();
					}
				}
			}
		});
		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ve_DAO.getVeTheoMaVe(tfTim.getText()).size()==0) {
					JOptionPane.showMessageDialog(null, "K tìm thấy kết quả phù hợp");
				}
				else {
					if(btnTim.getText().equals("Tìm")) {
						model.setRowCount(0);
						ArrayList<Ve> list = ve_DAO.getVeTheoMaVe(tfTim.getText());
			 			for (Ve ve : list) {
							model.addRow(new Object[] {ve.getMaVe(),ve.getSoNguoi(),ve.getGiaVe(),
									ve.getLoTrinh().getMaLT(),ve.getKhachHang().getMaKH(),ve.getTour().getMaTour()});
						}
						btnTim.setText("Hủy");
						btnTim.setIcon(new ImageIcon("img/Huy.png"));
						JOptionPane.showMessageDialog(null, "Tìm thấy");
					}
					else {
						btnTim.setText("Tìm");
						tfTim.setText("Nhập vào mã vé muốn tìm");
						model.setRowCount(0);
						DocDuLieuDatabaseVaoTable();
						btnTim.setIcon(new ImageIcon("img/Tim.png"));
					}
				}
			}
		});
		btnTinh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double giaTour = tour_DAO.getTourTheoma((String)cbbmaTour.getSelectedItem()).getGiaTour();
				int soNguoi = Integer.parseInt((String)cbbSoNguoi.getSelectedItem());
				double tinhTien = giaTour*soNguoi;
				tfgiaVe.setText(tinhTien+"");
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				tfmaVe.setText(model.getValueAt(row, 0).toString());
				cbbSoNguoi.setSelectedItem(model.getValueAt(row, 1).toString());
				tfgiaVe.setText(model.getValueAt(row, 2).toString());
				cbbmaLT.setSelectedItem(model.getValueAt(row, 3).toString());
				cbbmaKh.setSelectedItem(model.getValueAt(row, 4).toString());
				cbbmaTour.setSelectedItem(model.getValueAt(row, 5).toString());
			}
		});
	}
	public void DocDuLieuDatabaseVaoTable() {
		model.setRowCount(0);
		List<Ve> list = ve_DAO.getAllVe();
				for (Ve ve : list) {
					Object [] row = {ve.getMaVe(),ve.getSoNguoi(),ve.getGiaVe(),ve.getLoTrinh().getMaLT(),
							ve.getKhachHang().getMaKH(),ve.getTour().getMaTour()};
					model.addRow(row);
				}
	}
	
	private void moKhoaControls(boolean b) {
		btnThem.setEnabled(b);
		btnSua.setEnabled(b);
		btnXoaTrang.setEnabled(b);
		btnXoa.setEnabled(b);
	}

	private void moKhoaTextfields(boolean b) {
		tfmaVe.setEnabled(b);
		tfgiaVe.setEnabled(b);
		cbbmaKh.setEnabled(b);
		cbbmaLT.setEnabled(b);
		cbbSoNguoi.setEnabled(b);
		cbbmaTour.setEnabled(b);
	}
	
	private void xoaRongTextfields() {
		tfmaVe.setText("");
		tfgiaVe.setText("");
	}

	private void selectedRow(int row) {
		if(row != -1) {
			table.setRowSelectionInterval(row, row);
			table.scrollRectToVisible(table.getCellRect(row, row, true));
		}
	}
	
	private boolean validData() {
		String ma = tfmaVe.getText().trim(), gia = tfgiaVe.getText().trim(); 

		if (!(ma.length() > 0 && ma.matches("VE\\d{1,3}"))) {
			JOptionPane.showMessageDialog(null, "Error: Mã vé theo mẫu: VE + Dãy gồm 1->3 chữ số");
			tfmaVe.requestFocus();;
			return false;
		}
		if(gia.equals("")) {
			JOptionPane.showMessageDialog(null, "Bạn phải tính tiền trước khi thêm");
			return false;
		}
		if(cbbmaLT.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "Tour này chưa có lộ trình");
			return false;
		}
		if(cbbmaTour.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "Chưa có tour");
			return false;
		}
		if(cbbmaKh.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "Chưa có khách hàng");
			return false;
		}
		return true;
	}
	public void loadCBBMaTour(JComboBox<String> c) {
		c.removeAllItems();
		for (Tour tour : tour_DAO.getAllTour()) {
			c.addItem(tour.getMaTour());
		}
	}
	public JComboBox<String> getCBBMaKH(){
		return cbbmaKh;
	}
	public void loadCBBMaKH(JComboBox<String> c) {
		c.removeAllItems();
		for (KhachHang kh : khachHang_DAO.getAllKhachHang()) {
			c.addItem(kh.getMaKH());
		}
	}
	public JComboBox<String> getCBBMaLT(){
		return cbbmaLT;
	}
	public void loadCBBMaLT(JComboBox<String> c) {
		c.removeAllItems();
		for (LoTrinh lt : loTrinh_DAO.getalltbLoTrinh()) {
			c.addItem(lt.getMaLT());
		}
	}
	public JComboBox<String> getCBBMaTour(){
		return cbbmaTour;
	}
	public JPanel getTab() {
		return pnQLVe;
	}
}
