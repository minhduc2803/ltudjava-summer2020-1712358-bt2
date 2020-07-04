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
    public BangDiemID(){}
    public BangDiemID(String MSSV, String MaLop, String MaMon){
        this.MSSV = MSSV;
        this.MaLop = MaLop;
        this.MaMon = MaMon;
    }
}
