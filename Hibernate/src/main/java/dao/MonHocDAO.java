package dao;

/**
 * dao
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:35 AM
 * @Description
 */
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.MonHoc;
import util.HibernateUtil;

import java.util.List;

public class MonHocDAO {
    public static List<MonHoc> getListMonHoc() {
        List<MonHoc> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select mh from MonHoc mh";
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
    public static MonHoc getMonHoc(String MaMon){
        MonHoc mh  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            mh = (MonHoc) session.get(MonHoc.class, MaMon);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return mh;
    }
    public static boolean addMonHoc(MonHoc mh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(MonHocDAO.getMonHoc(mh.getMaMon()) != null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(mh);
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean updateMonHoc(MonHoc mh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(MonHocDAO.getMonHoc(mh.getMaMon()) == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(mh);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean deleteMonHoc(String MaMon){
        Session session = HibernateUtil.getSessionFactory().openSession();
        MonHoc mh = MonHocDAO.getMonHoc(MaMon);
        if(mh == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(mh);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
}
