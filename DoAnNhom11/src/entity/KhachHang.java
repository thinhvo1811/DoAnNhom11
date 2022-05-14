package entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String email;
	private String sdt;
	private String diachi;
	
	public KhachHang(String maKH, String tenKH, String email, String sdt, String diachi) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.email = email;
		this.sdt = sdt;
		this.diachi = diachi;
	}
	public KhachHang(String maKH) {
		this.maKH = maKH;
	}
	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", email=" + email + ", sdt=" + sdt + ", diachi="
				+ diachi + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(diachi, email, maKH, sdt, tenKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(diachi, other.diachi) && Objects.equals(email, other.email)
				&& Objects.equals(maKH, other.maKH) && Objects.equals(sdt, other.sdt)
				&& Objects.equals(tenKH, other.tenKH);
	}
	
	
	
}
