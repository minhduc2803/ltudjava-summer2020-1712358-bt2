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
import pojo.BangDiem;
import util.HibernateUtil;

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

}
