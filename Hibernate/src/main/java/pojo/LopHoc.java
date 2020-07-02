package pojo;

/**
 * pojo
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:08 AM
 * @Description
 */

public class LopHoc implements java.io.Serializable{
    private String MaLop;

    public LopHoc(){}
    public LopHoc(String MaLop){
        this.MaLop = MaLop;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }
}
