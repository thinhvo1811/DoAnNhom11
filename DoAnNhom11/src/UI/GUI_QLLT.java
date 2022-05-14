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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import dao.LoTrinh_DAO;
import dao.Tour_DAO;
import dao.Ve_DAO;
import entity.KhachHang;
import entity.LoTrinh;
import entity.Tour;

public class GUI_QLLT {
	private JPanel pnQLLT = new JPanel();
	private JLabel lbmaLT, lbmaTour, lbngayXP, lbngayKT, lbdiemXP,lbdiemDen;
	private JTextField tfmaLT, tfdiemXP, tfdiemDen, tfTim;
	private JDateChooser jdngayXP,jdngayKT;
	private JComboBox<String> cbbmaTour = new JComboBox<String>();
	private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnLuu, btnTim;
	private DefaultTableModel model;
	private JTable table;
	private int edit = 0;
	private LoTrinh_DAO loTrinh_DAO;
	private Tour_DAO tour_DAO;
	private Ve_DAO ve_DAO;
	private GUI_QLTour gui_QLTour = new GUI_QLTour();

	public GUI_QLLT() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		loTrinh_DAO = new LoTrinh_DAO();
		tour_DAO = new Tour_DAO();
		ve_DAO = new Ve_DAO();
		JPanel pnTitle = new JPanel(), pnCenter = new JPanel();
		JLabel lbTitle = new JLabel("Quản Lí Lộ Trình");
		pnTitle.setBackground(Color.white);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		pnTitle.add(lbTitle);
		Font font = new Font("Serif", Font.BOLD, 16);
		String header[] = { "Mã Lộ Trình", "Mã Tour", "Ngày Xuất Phát", "Ngày Kết Thúc", "Điểm Xuất Phát","Điểm Kết Thúc" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground(new Color(19, 170, 255));
		tableHeader.setFont(font);
		JScrollPane scroll = new JScrollPane(table);
		pnQLLT.setLayout(new BorderLayout());

		pnCenter.setLayout(null);
		int x = 40, y = 10, w = 120, h = 25;
		lbmaLT = new JLabel("Mã lộ trình: ");
		lbmaLT.setFont(font);
		lbmaTour = new JLabel("Mã tour: ");
		lbmaTour.setFont(font);
		lbngayXP = new JLabel("Ngày Xuất Phát: ");
		lbngayXP.setFont(font);
		lbngayKT = new JLabel("Ngày Kết Thúc: ");
		lbngayKT.setFont(font);
		lbdiemXP = new JLabel("Điểm Xuất Phát: ");
		lbdiemXP.setFont(font);
		lbdiemDen = new JLabel("Điểm Kết Thúc: ");
		lbdiemDen.setFont(font);
		
		
		pnCenter.add(lbmaLT);
		lbmaLT.setBounds(x, y, w, h);
		pnCenter.add(tfmaLT = new JTextField());
		tfmaLT.setBounds(x + 120, y, w + 300, h);
		pnCenter.add(lbmaTour);
		lbmaTour.setBounds(x + 580, y, w, h);
		gui_QLTour.loadCBBMaTour(cbbmaTour);
		pnCenter.add(cbbmaTour);
		cbbmaTour.setBounds(x + 700, y, w + 300, h);

		pnCenter.add(lbngayXP);
		lbngayXP.setBounds(x, y + 50, w, h);
		pnCenter.add(jdngayXP = new JDateChooser());
		jdngayXP.setBounds(x + 120, y + 50, w + 300, h);
		pnCenter.add(lbngayKT);
		lbngayKT.setBounds(x + 580, y + 50, w, h);
		pnCenter.add(jdngayKT = new JDateChooser());
		jdngayKT.setBounds(x + 700, y + 50, w + 300, h);

		pnCenter.add(lbdiemXP);
		lbdiemXP.setBounds(x, y + 100, w, h);
		pnCenter.add(tfdiemXP = new JTextField());
		tfdiemXP.setBounds(x + 120, y + 100, w + 300, h);
		pnCenter.add(lbdiemDen);
		lbdiemDen.setBounds(x+580, y + 100, w, h);
		pnCenter.add(tfdiemDen = new JTextField());
		tfdiemDen.setBounds(x + 700, y + 100, w + 300, h);

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
		docDuLieuDatabaseVaoTable();
		tfTim.setText("Nhập điểm bạn muốn đến hoặc ngày bạn xuất phát");
		tfTim.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfTim.getText().trim().equals("")) {
					tfTim.setText("Nhập điểm bạn muốn đến hoặc ngày bạn xuất phát");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(tfTim.getText().trim().equals("Nhập điểm bạn muốn đến hoặc ngày bạn xuất phát")) {
					tfTim.setText("");
				}
			}
		});
		
		pnQLLT.add(pnTitle, BorderLayout.NORTH);
		pnQLLT.add(pnCenter);
		jdngayXP.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(jdngayXP.getDate() !=null) {
					Tour t = tour_DAO.getTourTheoma((String)cbbmaTour.getSelectedItem());
					Calendar c = Calendar.getInstance();
					c.setTime(jdngayXP.getDate());
					c.add(Calendar.DATE, t.getSoNgay());      
					jdngayKT.setDate(c.getTime());
				}
			}
		});
		cbbmaTour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jdngayKT.setDate(null);
				jdngayXP.setDate(null);
			}
		});
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit =1;
				if(btnThem.getText().equalsIgnoreCase("Thêm")) {
					moKhoaTextfields(true);
					moKhoaConTrols(false);
					xoaTrangTextfields();
					jdngayKT.setEnabled(false);
					btnThem.setEnabled(true);
					btnLuu.setEnabled(true);
					btnThem.setText("Hủy");
					btnThem.setIcon(new ImageIcon("img/Huy.png"));
				}
				else if(btnThem.getText().equalsIgnoreCase("Hủy")) {
					moKhoaTextfields(false);
					moKhoaConTrols(true);
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
					if(ve_DAO.getVeTheoLoTrinh(tfmaLT.getText()).size()>0) {
						JOptionPane.showMessageDialog(null, "Lộ trình đã được đặt vé k thể xóa");
					}
					else {
						if(JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
							model.removeRow(table.getSelectedRow());
							loTrinh_DAO.delete(tfmaLT.getText());
							xoaTrangTextfields();
							TrangChu.tabQLVe.loadCBBMaLT(TrangChu.tabQLVe.getCBBMaLT());
							JOptionPane.showMessageDialog(null, "Xóa thành công");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn tour cần xóa");
				}
			}
		});
		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xoaTrangTextfields();
			}
		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					edit = 2 ;
					if(btnSua.getText().equalsIgnoreCase("Sửa")) {
						moKhoaTextfields(true);
						tfmaLT.setEnabled(false);
						moKhoaConTrols(false);
						btnLuu.setEnabled(true);
						jdngayKT.setEnabled(false);
						btnSua.setEnabled(true);
						btnSua.setText("Hủy");
						btnSua.setIcon(new ImageIcon("img/Huy.png"));
					}else if(btnSua.getText().equalsIgnoreCase("Hủy")) {
						moKhoaTextfields(false);
						moKhoaConTrols(true);
						btnLuu.setEnabled(false);
						tfmaLT.setEnabled(true);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
					}
				}else {
					JOptionPane.showMessageDialog(null,"Bạn chưa chọn tour cần sửa" );
				}
			}
		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(edit ==1) {
					if(validData()) {
						LoTrinh loTrinh = new LoTrinh(tfmaLT.getText(),new Tour((String) cbbmaTour.getSelectedItem()),
								new Date(jdngayXP.getDate().getTime()),new Date(jdngayKT.getDate().getTime()),
								tfdiemXP.getText(),tfdiemDen.getText());
						if(loTrinh_DAO.getalltbLoTrinh().contains(loTrinh)) {
							JOptionPane.showMessageDialog(null, "K được thêm trùng mã lộ trình");
						}
						else {
							loTrinh_DAO.create(loTrinh);
							docDuLieuDatabaseVaoTable();
							JOptionPane.showMessageDialog(null, "Thêm thành công");
							TrangChu.tabQLVe.loadCBBMaLT(TrangChu.tabQLVe.getCBBMaLT());
							moKhoaConTrols(true);
							moKhoaTextfields(false);
							btnThem.setText("Thêm");
							btnThem.setIcon(new ImageIcon("img/Them.png"));
							btnLuu.setEnabled(false);
							xoaTrangTextfields();
						}
					}
				}else if(edit == 2) {
					if(validData()) {
						loTrinh_DAO.update(new LoTrinh(tfmaLT.getText(),new Tour((String) cbbmaTour.getSelectedItem()),
								new Date(jdngayXP.getDate().getTime()),new Date(jdngayKT.getDate().getTime()),
								tfdiemXP.getText(),tfdiemDen.getText()));
						docDuLieuDatabaseVaoTable();
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						moKhoaTextfields(false);
						moKhoaConTrols(true);
						btnLuu.setEnabled(false);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
						tfmaLT.setEnabled(true);
						xoaTrangTextfields();
					}
				}
			}
		});
		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(loTrinh_DAO.getLoTrinhTheoDiemKT(tfTim.getText()).size()==0 && loTrinh_DAO.getLoTrinhTheoNgayXP(tfTim.getText()).size()==0) {
					JOptionPane.showMessageDialog(null, "K tìm thấy kết quả phù hợp");
				}
				else {
					if(btnTim.getText().equals("Tìm")) {
						model.setRowCount(0);
						if(loTrinh_DAO.getLoTrinhTheoDiemKT(tfTim.getText()).size()>0) {
							ArrayList<LoTrinh> list = loTrinh_DAO.getLoTrinhTheoDiemKT(tfTim.getText());
							for (LoTrinh lt : list) {
								model.addRow(new Object[] {lt.getMaLT(),lt.getMaTour().getMaTour(),lt.getNgayXP(),lt.getNgayKT(),lt.getDiemXP(),lt.getDiemKT()});
							}
						}else if(loTrinh_DAO.getLoTrinhTheoNgayXP(tfTim.getText()).size()>0) {
							ArrayList<LoTrinh> list = loTrinh_DAO.getLoTrinhTheoNgayXP(tfTim.getText());
							for (LoTrinh lt : list) {
								model.addRow(new Object[] {lt.getMaLT(),lt.getMaTour().getMaTour(),lt.getNgayXP(),lt.getNgayKT(),lt.getDiemXP(),lt.getDiemKT()});
							}
						}
						btnTim.setText("Hủy");
						btnTim.setIcon(new ImageIcon("img/Huy.png"));
						JOptionPane.showMessageDialog(null, "Tìm thấy");
					}else {
						btnTim.setText("Tìm");
						tfTim.setText("Nhập điểm bạn muốn đến hoặc ngày bạn xuất phát");
						model.setRowCount(0);
						docDuLieuDatabaseVaoTable();
						btnTim.setIcon(new ImageIcon("img/Tim.png"));
					}
				}
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
				tfmaLT.setText(model.getValueAt(row, 0).toString());
				cbbmaTour.setSelectedItem(model.getValueAt(row, 1).toString());
				jdngayXP.setDate((java.util.Date) model.getValueAt(row, 2));
				jdngayKT.setDate((java.util.Date) model.getValueAt(row, 3));
				tfdiemXP.setText(model.getValueAt(row, 4).toString());
				tfdiemDen.setText(model.getValueAt(row, 5).toString());
			}
		});
	}
	private void docDuLieuDatabaseVaoTable() {
		model.setRowCount(0);
		List<LoTrinh> list = loTrinh_DAO.getalltbLoTrinh();
		for (LoTrinh loTrinh : list) {
			model.addRow(new Object[] {loTrinh.getMaLT(), loTrinh.getMaTour().getMaTour(),
					loTrinh.getNgayXP(), loTrinh.getNgayKT(), loTrinh.getDiemXP(), loTrinh.getDiemKT()});
		}
	}
	private void docDuLieuTimVaoTable(String tuKhoa) {
		if (tuKhoa.equals("")) {
			docDuLieuDatabaseVaoTable();
		} else {
			model.setRowCount(0);
			List<LoTrinh> listDiemKT = loTrinh_DAO.getLoTrinhTheoDiemKT(tuKhoa);
			List<LoTrinh> listNgayXP = loTrinh_DAO.getLoTrinhTheoNgayXP(tuKhoa);
			for (LoTrinh loTrinh : listDiemKT) {
				model.addRow(new Object[] {loTrinh.getMaLT(), loTrinh.getMaTour().getMaTour(),
						loTrinh.getNgayXP(), loTrinh.getNgayKT(), loTrinh.getDiemXP(), loTrinh.getDiemKT()});
			}
			for (LoTrinh loTrinh : listNgayXP) {
				model.addRow(new Object[] {loTrinh.getMaLT(), loTrinh.getMaTour().getMaTour(),
						loTrinh.getNgayXP(), loTrinh.getNgayKT(), loTrinh.getDiemXP(), loTrinh.getDiemKT()});
			}
		}		
	}
	private void moKhoaTextfields(boolean khoa) {
		tfmaLT.setEnabled(khoa);
		cbbmaTour.setEnabled(khoa);
		jdngayXP.setEnabled(khoa);
		jdngayKT.setEnabled(khoa);
		tfdiemXP.setEnabled(khoa);
		tfdiemDen.setEnabled(khoa);
	}
	
	private void xoaTrangTextfields () {
		tfmaLT.setText("");
		cbbmaTour.setSelectedItem(0);
		jdngayXP.setDate(null);
		jdngayKT.setDate(null);
		tfdiemXP.setText("");
		tfdiemDen.setText("");
	}
	
	private void selectedRow(int row) {
		if (row != -1) {
			table.setRowSelectionInterval(row, row);
			table.scrollRectToVisible(table.getCellRect(row, row, true));
		}
	}
	private void moKhoaConTrols(boolean a) {
		btnThem.setEnabled(a);
		btnXoa.setEnabled(a);
		btnSua.setEnabled(a);
		btnXoaTrang.setEnabled(a);
	}
	private boolean validData () {
		String maLT = tfmaLT.getText();
		String diemXP = tfdiemXP.getText();
		String diemKT = tfdiemDen.getText();


		if (!(maLT.length() > 0 && maLT.matches("[A-Z]{2}\\d{1,3}"))) {
			JOptionPane.showMessageDialog(null, "Error: Mã theo mãu: [A-Z]{2}\\d{1,3}");
			return false;
		}
		if (!(diemXP.length() > 0 && diemXP.matches("[0-9A-Za-z|\s|-]{1,}"))) {
			JOptionPane.showMessageDialog(null, "Error: Điểm xuất phát Không Trống và Không chứa ký tự đặc biệt");
			return false;
		}
		if (!(diemKT.length() > 0 && diemKT.matches("[0-9A-Za-z|\s|-]{1,}"))) {
			JOptionPane.showMessageDialog(null, "Error: Điểm đến Không Trống và Không chứa ký tự đặc biệt");
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
	public JComboBox<String> getCBBMaTour(){
		return cbbmaTour;
	}
	public JPanel getTab() {
		return pnQLLT;
	}
}
