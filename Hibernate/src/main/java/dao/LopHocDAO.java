package dao;

/**
 * dao
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:13 AM
 * @Description
 */
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.LopHoc;
import util.HibernateUtil;

import java.io.File;
import java.util.List;

public class LopHocDAO {
    public static List<LopHoc> getListLopHoc() {
        List<LopHoc> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select lh from LopHoc lh";
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
    public static LopHoc getLopHoc(String MaLop){
        LopHoc lh  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            lh = (LopHoc) session.get(LopHoc.class, MaLop);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return lh;
    }
    public static boolean addLopHoc(LopHoc lh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(LopHocDAO.getLopHoc(lh.getMaLop()) != null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(lh);
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean updateLopHoc(LopHoc lh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(LopHocDAO.getLopHoc(lh.getMaLop()) == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(lh);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean deleteLopHoc(String MaLop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        LopHoc lh = LopHocDAO.getLopHoc(MaLop);
        if (lh == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(lh);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    public static void importLopHoc(File f){
        LopHocDAO lophocdao = new LopHocDAO();
        String filename = f.getName();
        String lophocName = filename.substring(0,filename.length()-4);
        System.out.println("Tên lớp học: "+lophocName);
        LopHoc lophoc = new LopHoc(lophocName);
        lophocdao.addLopHoc(lophoc);
    }
}
