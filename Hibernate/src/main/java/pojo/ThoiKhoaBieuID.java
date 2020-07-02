package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 3/7/2020 - 1:05 AM
 * @Description
 */
public class ThoiKhoaBieuID implements java.io.Serializable{
    public String MSSV;
    public String MaLop;
    public String MaMon;

    public ThoiKhoaBieuID(){}
    public ThoiKhoaBieuID(String MSSV, String MaLop, String MaMon){
        this.MSSV = MSSV;
        this.MaLop = MaLop;
        this.MaMon = MaMon;
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
}
