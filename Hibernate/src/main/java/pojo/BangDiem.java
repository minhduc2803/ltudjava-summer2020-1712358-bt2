package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:38 AM
 * @Description
 */
public class BangDiem implements java.io.Serializable{
    private BangDiemID ID;
    private String HoTen;
    private float DiemGK;
    private float DiemCK;
    private float DiemKhac;
    private float DiemTong;

    public BangDiem(){
        ID = new BangDiemID();
    }

    public BangDiem(BangDiemID ID){
        this.ID = ID;
    }
    public BangDiem(String MSSV, String HoTen, String MaLop, String MaMon, float DiemGK, float DiemCK, float DiemKhac, float DiemTong ){
        this.ID = new BangDiemID();
        this.ID.MSSV = MSSV;
        this.HoTen = HoTen;
        this.ID.MaLop = MaLop;
        this.ID.MaMon = MaMon;
        this.DiemGK = DiemGK;
        this.DiemCK = DiemCK;
        this.DiemKhac = DiemKhac;
        this.DiemTong = DiemTong;
    }

    public BangDiemID getID() {
        return ID;
    }

    public void setID(String MSSV) {
        this.ID = ID;
    }


    public String getMSSV() {
        return ID.MSSV;
    }

    public void setMSSV(String MSSV) {
        this.ID.MSSV = MSSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getMaMon() {
        return ID.MaMon;
    }

    public void setMaMon(String MaMon) {
        this.ID.MaMon = MaMon;
    }

    public String getMaLop() {
        return ID.MaLop;
    }

    public void setMaLop(String MaLop) {
        this.ID.MaLop = MaLop;
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

