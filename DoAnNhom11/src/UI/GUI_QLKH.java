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
import dao.Ve_DAO;
import entity.KhachHang;

public class GUI_QLKH {
	private JPanel pnQLKH = new JPanel();
	private JLabel lbmaKH, lbtenKH, lbemail, lbsdt, lbdiaChi;
	private JTextField tfMaKH, tftenKH, tfemail, tfsdt, tfdiaChi, tfTim;
	private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnLuu, btnTim;
	private DefaultTableModel model;
	private JTable table;
	private KhachHang_DAO khachHang_DAO;
	private int edit = 0;
	private Ve_DAO ve_DAO;

	public GUI_QLKH() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		khachHang_DAO = new KhachHang_DAO();
		ve_DAO = new Ve_DAO();
		JPanel pnTitle = new JPanel(), pnCenter = new JPanel();
		JLabel lbTitle = new JLabel("Quản Lí Khách Hàng");
		pnTitle.setBackground(Color.white);
		lbTitle.setFont(new Font("Serif", Font.BOLD, 20));
		pnTitle.add(lbTitle);
		Font font = new Font("Serif", Font.BOLD, 16);
		String header[] = { "Mã Khách Hàng", "Tên Khách Hàng", "Email", "Số Điện Thoại", "Địa Chỉ" };
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setRowHeight(25);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setForeground(Color.white);
		tableHeader.setBackground(new Color(19, 170, 255));
		tableHeader.setFont(font);
		JScrollPane scroll = new JScrollPane(table);
		pnQLKH.setLayout(new BorderLayout());

		pnCenter.setLayout(null);
		int x = 40, y = 10, w = 120, h = 25;
		lbmaKH = new JLabel("Mã khách hàng: ");
		lbmaKH.setFont(font);
		lbtenKH = new JLabel("Tên khách hàng: ");
		lbtenKH.setFont(font);
		lbemail = new JLabel("Email: ");
		lbemail.setFont(font);
		lbsdt = new JLabel("Số điện thoại: ");
		lbsdt.setFont(font);
		lbdiaChi = new JLabel("Địa chỉ: ");
		lbdiaChi.setFont(font);
		pnCenter.add(lbmaKH);
		lbmaKH.setBounds(x, y, w, h);
		pnCenter.add(tfMaKH = new JTextField());
		tfMaKH.setBounds(x + 120, y, w + 300, h);
		pnCenter.add(lbtenKH);
		lbtenKH.setBounds(x + 580, y, w, h);
		pnCenter.add(tftenKH = new JTextField());
		tftenKH.setBounds(x + 700, y, w + 300, h);

		pnCenter.add(lbemail);
		lbemail.setBounds(x, y + 50, w, h);
		pnCenter.add(tfemail = new JTextField());
		tfemail.setBounds(x + 120, y + 50, w + 300, h);
		pnCenter.add(lbsdt);
		lbsdt.setBounds(x + 580, y + 50, w, h);
		pnCenter.add(tfsdt = new JTextField());
		tfsdt.setBounds(x + 700, y + 50, w + 300, h);

		pnCenter.add(lbdiaChi);
		lbdiaChi.setBounds(x, y + 100, w, h);
		pnCenter.add(tfdiaChi = new JTextField());
		tfdiaChi.setBounds(x + 120, y + 100, w + 880, h);

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

		tfTim.setText("Nhập vào mã khách hàng muốn tìm");
		tfTim.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfTim.getText().trim().equals("")) {
					tfTim.setText("Nhập vào mã khách hàng muốn tìm");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(tfTim.getText().trim().equals("Nhập vào mã khách hàng muốn tìm")) {
					tfTim.setText("");
				}
			}
		});
		
		
		pnQLKH.add(pnTitle, BorderLayout.NORTH);
		pnQLKH.add(pnCenter);
		DoculieuDatabaseVaoTable();
		btnLuu.setEnabled(false);
		moKhoaTextfields(false);
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit = 1;
				if (btnThem.getText().equalsIgnoreCase("Thêm")) {
					moKhoaTextfields(true);
					moKhoaControls(false);
					btnLuu.setEnabled(true);
					btnThem.setEnabled(true);
					btnSua.setEnabled(false);
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(false);
					clearTextfield();
					btnThem.setText("Hủy");
					btnThem.setIcon(new ImageIcon("img/Huy.png"));
				} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnLuu.setEnabled(false);
					btnSua.setEnabled(true);
					btnXoa.setEnabled(true);
					btnXoaTrang.setEnabled(true);
					btnThem.setText("Thêm");
					btnThem.setIcon(new ImageIcon("img/Them.png"));
				}
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					if(ve_DAO.getVeTheoMaKH(tfMaKH.getText()).size()>0) {
						JOptionPane.showMessageDialog(null, "Khách hàng đã đặt vé k thể xóa");
					}else {
						if (JOptionPane.showConfirmDialog(null, "Ban co muon xoa", "Canh Bao",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							int r = table.getSelectedRow();
							model.removeRow(r); // xóa trong table model
							khachHang_DAO.deleteKH(tfMaKH.getText());
							clearTextfield();
							TrangChu.tabQLVe.loadCBBMaKH(TrangChu.tabQLVe.getCBBMaKH());
							JOptionPane.showMessageDialog(null, "Xóa thành công");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần xóa");
				}
			}
		});
		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clearTextfield();
			}
		});
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1) {
					edit = 2;
					if (btnSua.getText().equalsIgnoreCase("Sửa")) {
						moKhoaTextfields(true);
						tfMaKH.setEditable(false);
						moKhoaControls(false);
						btnLuu.setEnabled(true);
						btnSua.setEnabled(true);
						btnThem.setEnabled(false);
						btnXoa.setEnabled(false);
						btnXoaTrang.setEnabled(false);
						btnSua.setText("Hủy");
						btnSua.setIcon(new ImageIcon("img/Huy.png"));
					} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
						moKhoaTextfields(false);
						moKhoaControls(true);
						btnLuu.setEnabled(false);
						btnThem.setEnabled(true);
						btnXoa.setEnabled(true);
						btnXoaTrang.setEnabled(true);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
					}
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn nhân viên cần cập nhật");
				}
			}
		});
		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (edit == 1) {
					if (KTData()) {
						KhachHang kh = new KhachHang(tfMaKH.getText(), tftenKH.getText(), tfemail.getText(),
								tfsdt.getText(), tfdiaChi.getText());
						if(khachHang_DAO.getAllKhachHang().contains(kh)) {
							JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại");
						}
						else {
							khachHang_DAO.createKH(kh);
							updatetableTableData();
							JOptionPane.showMessageDialog(null, "Thêm thành công");
							TrangChu.tabQLVe.loadCBBMaKH(TrangChu.tabQLVe.getCBBMaKH());
							clearTextfield();
							moKhoaControls(false);
							moKhoaControls(true);
							btnThem.setText("Thêm");
							btnThem.setIcon(new ImageIcon("img/Them.png"));
							btnSua.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(true);
							btnLuu.setEnabled(false);
							moKhoaTextfields(false);
						}
					}
				} else if (edit == 2) {
					if (KTData()) {
						khachHang_DAO.update(new KhachHang(tfMaKH.getText(), tftenKH.getText(), tfemail.getText(),
								tfsdt.getText(), tfdiaChi.getText()));
						updatetableTableData();
						clearTextfield();
						updatetableTableData();
						JOptionPane.showMessageDialog(null, "Cập nhật thành công");
						btnThem.setEnabled(true);
						btnXoa.setEnabled(true);
						btnXoaTrang.setEnabled(true);
						btnSua.setText("Sửa");
						btnSua.setIcon(new ImageIcon("img/Sua.png"));
						btnLuu.setEnabled(false);
						moKhoaTextfields(false);
					}
				}
				
			}
		});
		btnTim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(khachHang_DAO.getTheoMaKhachHang(tfTim.getText()).size()==0) {
					JOptionPane.showMessageDialog(null, "K tìm thấy kết quả phù hợp");
				}
				else {
					if(btnTim.getText().equals("Tìm")) {
							model.setRowCount(0);
							ArrayList<KhachHang> list = khachHang_DAO.getTheoMaKhachHang(tfTim.getText());
							for (KhachHang kh : list) {
								model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getEmail(), kh.getSdt(),
										kh.getDiachi() });
						}
						btnTim.setText("Hủy");
						btnTim.setIcon(new ImageIcon("img/Huy.png"));	
						JOptionPane.showMessageDialog(null, "Tìm thấy");
					}else {
						btnTim.setText("Tìm");
						tfTim.setText("Nhập vào mã khách hàng muốn tìm");
						model.setRowCount(0);
						DoculieuDatabaseVaoTable();
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
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				tfMaKH.setText(model.getValueAt(row, 0).toString());
				tftenKH.setText(model.getValueAt(row, 1).toString());
				tfemail.setText(model.getValueAt(row, 2).toString());
				tfsdt.setText(model.getValueAt(row, 3).toString());
				tfdiaChi.setText(model.getValueAt(row, 4).toString());
			}
		});
	}

	public void DoculieuDatabaseVaoTable() {
		List<KhachHang> list = khachHang_DAO.getAllKhachHang();
		for (KhachHang kh : list) {
			model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getEmail(), kh.getSdt(), kh.getDiachi() });
		}
	}

	private void clearTextfield() {
		tfMaKH.setText("");
		tftenKH.setText("");
		tfemail.setText("");
		tfsdt.setText("");
		tfdiaChi.setText("");
		tfMaKH.requestFocus();
	}

	private void selectedRow(int row) {
		if (row != -1) {
			table.setRowSelectionInterval(row, row);
			table.scrollRectToVisible(table.getCellRect(row, row, true));
		}
	}

	private void moKhoaTextfields(boolean b) {
		tfMaKH.setEditable(b);
		tftenKH.setEditable(b);
		tfemail.setEditable(b);
		tfsdt.setEditable(b);
		tfdiaChi.setEditable(b);
	}

	private void moKhoaControls(boolean b) {
		btnThem.setEnabled(b);
		btnXoa.setEnabled(b);
		btnSua.setEnabled(b);
		btnXoaTrang.setEnabled(b);
	}

	private void updatetableTableData() {
		model.setRowCount(0);
		List<KhachHang> list = khachHang_DAO.getAllKhachHang();
		for (KhachHang kh : list) {
			model.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), kh.getEmail(), kh.getSdt(), kh.getDiachi() });
		}
	}

	private boolean KTData() {
		String makh = tfMaKH.getText();
		String tenkh = tftenKH.getText();
		String email = tfemail.getText();
		String sodt = tfsdt.getText();
		String diachi = tfdiaChi.getText();

		if (!(makh.length() > 0 && makh.matches("KH\\d{1,4}"))) {
			JOptionPane.showMessageDialog(null, "Mã theo mẫu: KH#### (# : 0-9)");
			return false;
		}
		if (!(tenkh.length() > 0 && tenkh.matches("[A-z ]+"))) {
			JOptionPane.showMessageDialog(null, "Tên không chứa số và kí tự đặc biệt ");
			return false;
		}
		if (!(email.length() > 0 && email.matches("^(\\S+)@(\\S+)$"))) {
			JOptionPane.showMessageDialog(null, "Email phải chứa @ và không có khoảng trắng");
			return false;
		}
		if (!(sodt.length() > 0 && sodt.matches("0[2|3|5|7|8|9][0-9]{8}"))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại gồm 10 số bắt đầu bằng 02, 03, 05, 07, 08, 09");
			return false;
		}
		if (!(diachi.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống");
			return false;
		}

		return true;
	}

	public JPanel getTab() {
		return pnQLKH;
	}
}
