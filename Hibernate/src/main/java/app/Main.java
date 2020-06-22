package app;

/**
 * app
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 22/6/2020 - 22:30 PM
 * @Description
 */
import dao.SinhVienDAO;
import pojo.SinhVien;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SinhVien sv = SinhVienDAO.getSinhVien("1742001");
        if(sv != null){
            System.out.println("hello: "+sv.getHoTen());
        }
    }
}
