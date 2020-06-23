package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:37 AM
 * @Description
 */
public class ThoiKhoaBieu implements java.io.Serializable{
    private String MSSV;
    private String MaLop;
    private String MaMon;

    public ThoiKhoaBieu(){}

    public ThoiKhoaBieu(String MSSV, String MaLop, String MaMon){
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
