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

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.LoTrinh_DAO;
import dao.Tour_DAO;
import dao.Ve_DAO;
import entity.Tour;

public class GUI_QLTour {
	private JPanel pnQLTour = new JPanel();
	private JLabel lbmaTour, lbtenTour, lbsoNgay, lbgiaTour;
	private JTextField tfmaTour, tftenTour, tfsoNgay, tfgiaTour,tfTim;
	private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnLuu, btnTim;
	private DefaultTableModel model;
	private JTable table;
	private int edit = 0;
	private Tour_DAO tour_DAO;
	private LoTrinh_DAO loTrinh_DAO;
	private Ve_DAO ve_DAO;
	public GUI_QLTour() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tour_DAO = new Tour_DAO();
		loTrinh_DAO = new LoTrinh_DAO();
		ve_DAO =  new Ve_DAO();
		JPanel pnTitle = new JPanel(), pnCenter = new JPanel();
		JLabel lbTitle = new JLabel("Quản Lí Tour");
		pnTitle.setBackground(Color.white);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		pnTitle.add(lbTitle);
		Font font = new Font("Serif", Font.BOLD, 16);
		String header[] = { "Mã Tour", "Tên Tour", "Số Ngày", "Giá Tour" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground(new Color(19, 170, 255));
		tableHeader.setFont(font);
		JScrollPane scroll = new JScrollPane(table);
		pnQLTour.setLayout(new BorderLayout());

		pnCenter.setLayout(null);
		int x = 40, y = 10, w = 120, h = 25;
		lbmaTour = new JLabel("Mã tour: ");
		lbmaTour.setFont(font);
		lbgiaTour = new JLabel("Giá tour: ");
		lbgiaTour.setFont(font);
		lbsoNgay = new JLabel("Số ngày: ");
		lbsoNgay.setFont(font);
		lbtenTour = new JLabel("Tên tour: ");
		lbtenTour.setFont(font);

		pnCenter.add(lbmaTour);
		lbmaTour.setBounds(x, y, w + 300, h);
		lbmaTour.setBounds(x, y, w, h);
		pnCenter.add(tfmaTour = new JTextField());
		tfmaTour.setBounds(x + 120, y, w + 880, h);
		
		pnCenter.add(lbtenTour);
		lbtenTour.setBounds(x , y+50, w, h);
		pnCenter.add(tftenTour = new JTextField());
		tftenTour.setBounds(x +120, y+50, w + 880, h);
		
		pnCenter.add(lbsoNgay);
		lbsoNgay.setBounds(x, y + 100, w, h);
		pnCenter.add(tfsoNgay = new JTextField());
		tfsoNgay.setBounds(x + 120, y + 100, w + 300, h);
		pnCenter.add(lbgiaTour);
		lbgiaTour.setBounds(x + 580, y + 100, w, h);
		pnCenter.add(tfgiaTour = new JTextField());
		tfgiaTour.setBounds(x + 700, y + 100, w + 300, h);


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
		moKhoaTextField(false);
		docDuLieuVaoBang();
		
		tfTim.setText("Nhập vào mã tour muốn tìm");
		tfTim.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfTim.getText().trim().equals("")) {
					tfTim.setText("Nhập vào mã tour muốn tìm");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(tfTim.getText().trim().equals("Nhập vào mã tour muốn tìm")) {
					tfTim.setText("");
				}
			}
		});
		
		pnQLTour.add(pnTitle, BorderLayout.NORTH);
		pnQLTour.add(pnCenter);
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit =1;
				if(btnThem.getText().equalsIgnoreCase("Thêm")) {
					moKhoaTextField(true);
					moKhoaConTrols(false);
					btnThem.setEnabled(true);
					btnLuu.setEnabled(true);
					btnThem.setText("Hủy");
					xoaTrang();
					btnThem.setIcon(new ImageIcon("img/Huy.png"));
				}
				else if(btnThem.getText().equalsIgnoreCase("Hủy")) {
					moKhoaTextField(false);
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
					if(loTrinh_DAO.getLTTheoMaTour(tfmaTour.getText()).size()>0) {
						JOptionPane.showMessageDialog(null, "Tour đã có lộ trình k thể xóa");
					}
					else if(ve_DAO.getVeTheoTour(tfmaTour.getText()).size()>0) {
						JOptionPane.showMessageDialog(null, "Tour đã được đặt vé k thể xóa");
					}
					else {
						if(JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không","Cảnh báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
							model.removeRow(table.getSelectedRow());
							tour_DAO.deleteTour(tfmaTour.getText());
							xoaTrang();
							TrangChu.tabQLLT.loadCBBMaTour(TrangChu.tabQLLT.getCBBMaTour());
							TrangChu.tabQLVe.loadCBBMaTour(TrangChu.tabQLVe.getCBBMaTour());
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
				xoaTrang();
			}
		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					edit = 2 ;
					if(btnSua.getText().equalsIgnoreCase("Sửa")) {
						moKhoaTextField(true);
						tfmaTour.setEnabled(false);
						moKhoaConTrols(false);
						btnLuu.setEnabled(true);
						btnSua.setEnabled(true);
						btnSua.setText("Hủy");
						btnSua.setIcon(new ImageIcon("img/Huy.png"));
					}else if(btnSua.getText().equalsIgnoreCase("Hủy")) {
						moKhoaTextField(false);
						moKhoaConTrols(true);
						btnLuu.setEnabled(false);
						tfmaTour.setEnabled(true);
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
						Tour tour = new Tour(tfmaTour.getText(),tftenTour.getText(),
								Integer.parseInt(tfsoNgay.getText()),
								Double.parseDouble(tfgiaTour.getText()));
						if(tour_DAO.getAllTour().contains(tour)) {
							JOptionPane.showMessageDialog(null, "K được thêm trùng tour");
						}
						else {
							tour_DAO.AddTour(tour);
							docDuLieuVaoBang();
							JOptionPane.showMessageDialog(null, "Thêm thành công");
							TrangChu.tabQLLT.loadCBBMaTour(TrangChu.tabQLLT.getCBBMaTour());
							TrangChu.tabQLVe.loadCBBMaTour(TrangChu.tabQLVe.getCBBMaTour());
							moKhoaConTrols(true);
							moKhoaTextField(false);
							btnThem.setText("Thêm");
							btnThem.setIcon(new ImageIcon("img/Them.png"));
							btnLuu.setEnabled(false);
							xoaTrang();
						}
					}
				}else if(edit == 2) {
					if(validData()) {
						tour_DAO.updateTour(new Tour(tfmaTour.getText(), tftenTour.getText(), 
								Integer.parseInt(tfsoNgay.getText()),Double.parseDouble(tfgiaTour.getText())));
						docDuLieuVaoBang();
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						moKhoaTextField(false);
						moKhoaConTrols(true);
						btnLuu.setEnabled(false);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
						tfmaTour.setEnabled(true);
						xoaTrang();
					}
				}
			}
		});
		btnTim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tour_DAO.getTourTheoma(tfTim.getText())==null) {
					JOptionPane.showMessageDialog(null, "K tìm thấy kết quả phù hợp");
				}
				else {
					if(btnTim.getText().equals("Tìm")) {
						model.setRowCount(0);
						DocDuLieuTimVaoBang(tfTim.getText());
						btnTim.setText("Hủy");
						btnTim.setIcon(new ImageIcon("img/Huy.png"));
						JOptionPane.showMessageDialog(null, "Tìm thấy");
					}
					else {
						btnTim.setText("Tìm");
						tfTim.setText("Nhập vào mã tour muốn tìm");
						model.setRowCount(0);
						docDuLieuVaoBang();
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
				tfmaTour.setText(model.getValueAt(row, 0).toString());
				tftenTour.setText(model.getValueAt(row, 1).toString());
				tfsoNgay.setText(model.getValueAt(row, 2).toString());
				tfgiaTour.setText(model.getValueAt(row, 3).toString());
			}
		});
	}
	private boolean validData() {
		try {
			if (tfmaTour.getText().equals("") || tftenTour.getText().equals("")||tfsoNgay.getText().equals("")||tfgiaTour.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				return false;
			}
			String maTour = tfmaTour.getText();
			String tenTour = tftenTour.getText();
			int soNgay = Integer.parseInt(tfsoNgay.getText().toString());
			double giaTour = Double.parseDouble(tfgiaTour.getText().toString());
			 if(!maTour.matches("^T\\d{1,8}")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mã Tour theo định dạng: [Txxxxxxxx] x là số");
				tfmaTour.requestFocus();
				return false;
			}	
			else if(!tenTour.matches("[a-zA-Z ]+\\-[a-zA-Z ]+")|| !tenTour.matches("[a-zA-Z ]+\\-[a-zA-Z ]+") ){
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tên Tour theo định dạng: [X-X] X là chữ");
				return false;
			}
			else if(soNgay<0){
				JOptionPane.showMessageDialog(null, "Số ngày phải là số nguyên dương");
				return false;
			}
			else if(giaTour<0){
				JOptionPane.showMessageDialog(null, "Giá Tour phải là số nguyên dương");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Số ngày và giá tour phải nhập số");
			return false;
		}
		return true;
	}
	private void selectedRow(int row) {
		if (row != -1) {
			table.setRowSelectionInterval(row, row);
			table.scrollRectToVisible(table.getCellRect(row, row, true));
		}
	}

	public void docDuLieuVaoBang() {
		model.setRowCount(0);
		List<Tour> list = tour_DAO.getAllTour();
		for (Tour tour : list) {
			model.addRow(new Object[] { tour.getMaTour(),
					tour.getTenTour(), tour.getSoNgay(), tour.getGiaTour() });
		}
	}
	private void DocDuLieuTimVaoBang(String maTour){
			model.setRowCount(0);
			Tour tour = tour_DAO.getTourTheoma(maTour);
			model.addRow(new Object[] { tour.getMaTour(),
				tour.getTenTour(), tour.getSoNgay(), tour.getGiaTour() });
	}
	private void moKhoaTextField(boolean a) {
		tfmaTour.setEditable(a);
		tftenTour.setEditable(a);
		tfsoNgay.setEditable(a);
		tfgiaTour.setEditable(a);
	}
	private void moKhoaConTrols(boolean a) {
		btnThem.setEnabled(a);
		btnXoa.setEnabled(a);
		btnSua.setEnabled(a);
		btnXoaTrang.setEnabled(a);
	}
	private void xoaTrang() {
		tfmaTour.setText("");
		tftenTour.setText("");
		tfsoNgay.setText("");
		tfgiaTour.setText("");
		tfmaTour.requestFocus();
	}
	public void loadCBBMaTour(JComboBox<String> c) {
		c.removeAllItems();
		for (Tour tour : tour_DAO.getAllTour()) {
			c.addItem(tour.getMaTour());
		}
	}
	public JPanel getTab() {
		return pnQLTour;
	}
}
