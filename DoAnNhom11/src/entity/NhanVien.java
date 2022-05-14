package entity;

public class NhanVien {
	private String maNV;
	private String pw;
	private String nlpw;
	public NhanVien(String maNV, String pw, String nlpw) {
		super();
		this.maNV = maNV;
		this.pw = pw;
		this.nlpw = nlpw;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNlpw() {
		return nlpw;
	}
	public void setNlpw(String nlpw) {
		this.nlpw = nlpw;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", pw=" + pw + ", nlpw=" + nlpw + "]";
	}
}
