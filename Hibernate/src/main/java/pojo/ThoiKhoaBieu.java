package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:37 AM
 * @Description
 */
public class ThoiKhoaBieu implements java.io.Serializable{
    private ThoiKhoaBieuID ID;
    public ThoiKhoaBieu(){
        this.ID = new ThoiKhoaBieuID();
    }
    public ThoiKhoaBieu(ThoiKhoaBieuID ID){
        this.ID = ID;
    }
    public ThoiKhoaBieu(String MSSV, String MaLop, String MaMon){
        this.ID = new ThoiKhoaBieuID();
        this.ID.MSSV = MSSV;
        this.ID.MaLop = MaLop;
        this.ID.MaMon = MaMon;
    }
    public ThoiKhoaBieuID getID(){ return ID;}
    public void setID(ThoiKhoaBieuID ID){
        this.ID = ID;
    }
    public String getMSSV() {
        return ID.MSSV;
    }

    public void setMSSV(String MSSV) {
        this.ID.MSSV = MSSV;
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

}
