package entity;

import java.sql.Date;
import java.util.Objects;

public class LoTrinh {
	private String MaLT;
	private Tour maTour;
	private Date ngayXP;
	private Date ngayKT;
	private String diemXP;
	private String diemKT;
	public String getMaLT() {
		return MaLT;
	}
	public void setMaLT(String maLT) {
		MaLT = maLT;
	}
	public Tour getMaTour() {
		return maTour;
	}
	public void setMaTour(Tour maTour) {
		this.maTour = maTour;
	}
	public Date getNgayXP() {
		return ngayXP;
	}
	public void setNgayXP(Date ngayXP) {
		this.ngayXP = ngayXP;
	}
	public Date getNgayKT() {
		return ngayKT;
	}
	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
	public String getDiemXP() {
		return diemXP;
	}
	public void setDiemXP(String diemXP) {
		this.diemXP = diemXP;
	}
	public String getDiemKT() {
		return diemKT;
	}
	public void setDiemKT(String diemKT) {
		this.diemKT = diemKT;
	}
	public LoTrinh(String maLT, Tour maTour, Date ngayXP, Date ngayKT, String diemXP, String diemKT) {
		super();
		MaLT = maLT;
		this.maTour = maTour;
		this.ngayXP = ngayXP;
		this.ngayKT = ngayKT;
		this.diemXP = diemXP;
		this.diemKT = diemKT;
	}
	public LoTrinh(String maLT) {
		MaLT = maLT;
	}
	@Override
	public int hashCode() {
		return Objects.hash(MaLT);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoTrinh other = (LoTrinh) obj;
		return Objects.equals(MaLT, other.MaLT);
	}
	@Override
	public String toString() {
		return "LoTrinh [MaLT=" + MaLT + ", maTour=" + maTour + ", ngayXP=" + ngayXP + ", ngayKT=" + ngayKT
				+ ", diemXP=" + diemXP + ", diemKT=" + diemKT + "]";
	}
	
}
