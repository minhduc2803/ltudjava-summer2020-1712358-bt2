package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 22/6/2020 - 20:57 PM
 * @Description
 */
public class SinhVien implements java.io.Serializable{
    private String MSSV;
    private String MaLop;
    private String HoTen;
    private String GioiTinh;
    private String CMND;


    public SinhVien(){}
    public SinhVien(String MSSV, String MaLop){
        this.MSSV = MSSV;
        this.MaLop = MaLop;
    }
    public SinhVien(String MSSV, String MaLop, String HoTen, String GioiTinh, String CMND){
        this.MSSV = MSSV;
        this.MaLop = MaLop;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.CMND = CMND;
    }
    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }



}
