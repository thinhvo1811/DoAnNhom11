package entity;

public class Tour {
	private String maTour;
	private String tenTour;
	private int soNgay;
	private double giaTour;
	public Tour(String maTour, String tenTour, int soNgay, double giaTour) {
		super();
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.soNgay = soNgay;
		this.giaTour = giaTour;
	}
	public Tour(String maTour) {
		super();
		this.maTour = maTour;
	}
	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}
	public String getTenTour() {
		return tenTour;
	}
	public void setTenTour(String tenTour) {
		this.tenTour = tenTour;
	}
	
	public int getSoNgay() {
		return soNgay;
	}
	public void setSoNgay(int soNgay) {
		this.soNgay = soNgay;
	}
	public double getGiaTour() {
		return giaTour;
	}
	public void setGiaTour(double giaTour) {
		this.giaTour = giaTour;
	}
	@Override
	public String toString() {
		return "Tour [maTour=" + maTour + ", tenTour=" + tenTour + ", soNgay="
				+ soNgay + ", giaTour=" + giaTour + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maTour == null) ? 0 : maTour.hashCode());
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
		Tour other = (Tour) obj;
		if (maTour == null) {
			if (other.maTour != null)
				return false;
		} else if (!maTour.equals(other.maTour))
			return false;
		return true;
	}
	
}
