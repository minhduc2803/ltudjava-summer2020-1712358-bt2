package dao;


/**
 * dao
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 5/7/2020 - 12:25 PM
 * @Description
 */
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.GiaoVu;
import pojo.SinhVien;
import util.HibernateUtil;

public class GiaoVuDAO {
    public static GiaoVu getGiaoVu(String Username){
        GiaoVu gv  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            gv = (GiaoVu) session.get(GiaoVu.class, Username);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return gv;
    }
    public static boolean updateGiaoVu(GiaoVu gv){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        try{
            session.beginTransaction();
            String sql = String.format("UPDATE giaovu set password='%s' where username='%s';",gv.getPassword(),gv.getUsername());
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            //transaction.commit();
        }catch (Exception ex){
            //transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
}
