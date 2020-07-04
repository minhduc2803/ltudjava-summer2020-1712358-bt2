package dao;

/**
 * dao
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:39 AM
 * @Description
 */
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.*;
import util.HibernateUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class BangDiemDAO {
    public static List<BangDiem> getListBangDiem() {
        List<BangDiem> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select bd from BangDiem bd";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static BangDiem getBangDiem(BangDiemID ID){
        BangDiem bd  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            bd = (BangDiem) session.get(BangDiem.class, ID);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return bd;
    }
    public static boolean addBangDiem(BangDiem bd){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(BangDiemDAO.getBangDiem(bd.getID()) != null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(bd);
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static int importBangDiem(File f){
        int numberOfBangDiem = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String firstLine = br.readLine().trim();
            System.out.println(firstLine);
            String line = "";
            while((line = br.readLine()) != null){
                String[] elements = line.split(",");


                String filename = f.getName();
                String[] malopmamon = filename.substring(0,filename.length()-4).split("-",2);
                String MaLop = malopmamon[0];
                String MaMon = malopmamon[1];
                float DiemGK = Float.valueOf(elements[3]);
                float DiemCK = Float.valueOf(elements[4]);
                float DiemKhac = Float.valueOf(elements[5]);
                float DiemTong = Float.valueOf(elements[6]);
                BangDiem bd = new BangDiem(elements[1].trim(),MaLop,MaMon,elements[2].trim(),DiemGK,DiemCK,DiemKhac,DiemTong);
                boolean result = addBangDiem(bd);
                if(result) {
                    numberOfBangDiem++;
                    System.out.println("Thêm bảng điểm thành công");
                }else
                {
                    System.out.println("Thêm bảng điểm thất bại");

                }
                System.out.println(line);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return numberOfBangDiem;
    }
}
