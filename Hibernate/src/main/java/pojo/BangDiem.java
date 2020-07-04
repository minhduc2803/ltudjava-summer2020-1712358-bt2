package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:38 AM
 * @Description
 */
public class BangDiem implements java.io.Serializable{
    private String MSSV;
    private String MaLop;
    private String MaMon;
    private float DiemGK;
    private float DiemCK;
    private float DiemKhac;
    private float DiemTong;

    public BangDiem(){

    }

    public BangDiem(String MSSV, String MaLop, String MaMon){
        this.MSSV = MSSV;
        this.MaLop = MaLop;
        this.MaMon = MaMon;
    }
    public BangDiem(String MSSV, String MaLop, String MaMon, float DiemGK, float DiemCK, float DiemKhac, float DiemTong ){
        this.MSSV = MSSV;
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

