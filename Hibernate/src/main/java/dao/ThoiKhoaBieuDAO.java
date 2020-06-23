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
import pojo.ThoiKhoaBieu;
import util.HibernateUtil;

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

}

