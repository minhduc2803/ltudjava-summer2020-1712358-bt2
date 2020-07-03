package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 2/7/2020 - 23:55 PM
 * @Description
 */
public class MonHocID implements java.io.Serializable{
    public String MaMon;
    public String MaLop;
    private String TenMon;
    private String PhongHoc;
    public MonHocID(){}
    public MonHocID(String MaMon, String MaLop){
        this.MaMon = MaMon;
        this.MaLop = MaLop;
    }
    public MonHocID(String MaMon, String MaLop, String TenMon, String PhongHoc){
        this.MaMon = MaMon;
        this.MaLop = MaLop;
        this.TenMon = TenMon;
        this.PhongHoc = PhongHoc;
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

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getPhongHoc() {
        return PhongHoc;
    }

    public void setPhongHoc(String PhongHoc) {
        this.PhongHoc = PhongHoc;
    }

}
