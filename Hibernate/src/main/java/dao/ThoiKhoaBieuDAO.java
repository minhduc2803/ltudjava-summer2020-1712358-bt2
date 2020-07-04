package dao;

/**
 * dao
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 23/6/2020 - 11:38 AM
 * @Description
 */
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import pojo.*;
import util.HibernateUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieuDAO {
    public static List<ThoiKhoaBieu> getListThoiKhoaBieu() {
        List<ThoiKhoaBieu> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select tkb from ThoiKhoaBieu tkb";
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
    public static List<ThoiKhoaBieu> getListThoiKhoaBieuAll() {
        List<ThoiKhoaBieu> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List resultWithAliasedBean = session.createSQLQuery(
                    "select sinhvien.mssv as MSSV, monhoc.mamon as MaMon, monhoc.malop as MaLop " +
                       "from sinhvien inner join monhoc on sinhvien.malop = monhoc.malop")
                    .addScalar("MSSV")
                    .addScalar("MaMon")
                    .addScalar("MaLop")
                    .setResultTransformer( Transformers.aliasToBean(ThoiKhoaBieuID.class))
                    .list();

             for(Object r:resultWithAliasedBean) {
                 ds.add(new ThoiKhoaBieu((ThoiKhoaBieuID) r));
             }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }

    public static ThoiKhoaBieu getThoiKhoaBieu(ThoiKhoaBieuID ID){
        ThoiKhoaBieu tkb  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            tkb = (ThoiKhoaBieu) session.get(ThoiKhoaBieu.class, ID);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return tkb;
    }
    public static boolean addThoiKhoaBieu(ThoiKhoaBieu tkb){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(ThoiKhoaBieuDAO.getThoiKhoaBieu(tkb.getID()) != null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(tkb);
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean updateThoiKhoaBieu(ThoiKhoaBieu tkb){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(ThoiKhoaBieuDAO.getThoiKhoaBieu(tkb.getID()) == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(tkb);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean deleteThoiKhoaBieu(ThoiKhoaBieuID ID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        ThoiKhoaBieu tkb = ThoiKhoaBieuDAO.getThoiKhoaBieu(ID);
        if(tkb == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "delete from ThoiKhoaBieu where MSSV= :mssv AND MaLop= :malop AND MaMon= :mamon";
            Query query = session.createQuery(hql);
            query.setString("mssv",ID.getMSSV());
            query.setString("malop",ID.getMaLop());
            query.setString("mamon",ID.getMaMon());
            System.out.println(query.executeUpdate());
            //session.delete(tkb);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static int importThoiKhoaBieu(){
        int numberOfThoiKhoaBieu = 0;
        List<ThoiKhoaBieu> ds = getListThoiKhoaBieuAll();
        for(ThoiKhoaBieu tkb: ds){
            if(addThoiKhoaBieu(tkb)){
                numberOfThoiKhoaBieu++;
            }

        }
        return numberOfThoiKhoaBieu;
    }
}

