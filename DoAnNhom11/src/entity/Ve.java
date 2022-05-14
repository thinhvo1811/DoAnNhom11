package entity;

import java.sql.Date;

public class Ve {
	private String maVe;
	private LoTrinh loTrinh;
	private double giaVe;
	private int soNguoi;
	private KhachHang khachHang;
	private Tour tour;
	public Ve(String maVe, LoTrinh loTrinh, double giaVe, int soNguoi, KhachHang khachHang, Tour tour) {
		super();
		this.maVe = maVe;
		this.loTrinh = loTrinh;
		this.giaVe = giaVe;
		this.soNguoi = soNguoi;
		this.khachHang = khachHang;
		this.tour = tour;
	}
	public Ve(String maVe) {
		this.maVe = maVe;
	}
	public String getMaVe() {
		return maVe;
	}
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}
	public LoTrinh getLoTrinh() {
		return loTrinh;
	}
	public void setLoTrinh(LoTrinh loTrinh) {
		this.loTrinh = loTrinh;
	}
	public double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public Tour getTour() {
		return tour;
	}
	public void setTour(Tour tour) {
		this.tour = tour;
	}
	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", ngayDatTour=" + loTrinh + ", giaVe=" + giaVe + ", soNguoi=" + soNguoi
				+ ", khachHang=" + khachHang + ", tour=" + tour + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maVe == null) ? 0 : maVe.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		if (maVe == null) {
			if (other.maVe != null)
				return false;
		} else if (!maVe.equals(other.maVe))
			return false;
		return true;
	}
	
}
