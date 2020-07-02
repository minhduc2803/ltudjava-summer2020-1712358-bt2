package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:22 AM
 * @Description
 */
public class MonHoc implements java.io.Serializable{
    private MonHocID ID;
    private String TenMon;
    private String PhongHoc;

    public MonHoc(){}
    public MonHoc(MonHocID ID){
        this.ID = ID;
    }
    public MonHoc(String MaMon, String MaLop, String TenMon, String PhongHoc){
        this.ID = new MonHocID(MaMon,MaLop);
        this.TenMon = TenMon;
        this.PhongHoc = PhongHoc;
    }

    public MonHocID getMonHocID(){
        return ID;
    }
    public void setMonHocID(MonHocID ID){
        this.ID = ID;
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
