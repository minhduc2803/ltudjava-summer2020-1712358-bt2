package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 30/6/2020 - 22:02 PM
 * @Description
 */
public class BangDiemID implements java.io.Serializable{
    public String MSSV;
    public String MaLop;
    public String MaMon;

    private String HoTen;
    private float DiemGK;
    private float DiemCK;
    private float DiemKhac;
    private float DiemTong;

    public BangDiemID(){}
    public BangDiemID(String MSSV, String MaLop, String MaMon){
        this.MSSV = MSSV;
        this.MaLop = MaLop;
        this.MaMon = MaMon;
    }
    public BangDiemID(String MSSV, String HoTen, String MaLop, String MaMon, float DiemGK, float DiemCK, float DiemKhac, float DiemTong ){
        this.MSSV = MSSV;
        this.HoTen = HoTen;
        this.MaLop = MaLop;
        this.MaMon = MaMon;
        this.DiemGK = DiemGK;
        this.DiemCK = DiemCK;
        this.DiemKhac = DiemKhac;
        this.DiemTong = DiemTong;
    }
    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public float getDiemGK() {
        return DiemGK;
    }

    public void setDiemGK(float DiemGK) {
        this.DiemGK = DiemGK;
    }

    public float getDiemCK() {
        return DiemCK;
    }

    public void setDiemCK(float DiemCK) {
        this.DiemCK = DiemCK;
    }

    public float getDiemKhac() {
        return DiemKhac;
    }

    public void setDiemKhac(float DiemKhac) {
        this.DiemKhac = DiemKhac;
    }

    public float getDiemTong() {
        return DiemTong;
    }

    public void setDiemTong(float DiemTong) {
        this.DiemTong = DiemTong;
    }

}
