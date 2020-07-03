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
import org.hibernate.transform.Transformers;
import pojo.*;
import util.HibernateUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
    public static List<MonHoc> getMonHocTheoLop(String MaLop){
        List<MonHoc> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List resultWithAliasedBean = session.createSQLQuery(
                    "select MaMon, MaLop, TenMon, PhongHoc from monhoc where MaLop = \""+MaLop+"\"")
                    .addScalar("MaMon")
                    .addScalar("MaLop")
                    .addScalar("TenMon")
                    .addScalar("PhongHoc")
                    .setResultTransformer( Transformers.aliasToBean(MonHoc.class))
                    .list();

            for(Object r:resultWithAliasedBean) {
                ds.add((MonHoc) r);
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static MonHoc getMonHoc(MonHocID ID){
        MonHoc mh  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            mh = (MonHoc) session.get(MonHoc.class, ID);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return mh;
    }
    public static boolean addMonHoc(MonHoc mh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(MonHocDAO.getMonHoc(mh.getMonHocID()) != null){
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
        if(MonHocDAO.getMonHoc(mh.getMonHocID()) == null){
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
    public static boolean deleteMonHoc(MonHocID ID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        MonHoc mh = MonHocDAO.getMonHoc(ID);
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
    public static int importCourse(File f){
        int numberOfCourse = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String firstLine = br.readLine().trim();
            System.out.println(firstLine);
            String line = "";
            while((line = br.readLine()) != null){
                String[] elements = line.split(",");


                String filename = f.getName();
                String lophocName = filename.substring(0,filename.length()-4);

                MonHoc mh = new MonHoc(elements[1].trim(),lophocName,elements[2].trim(),elements[3].trim());
                boolean result = addMonHoc(mh);
                if(result) {
                    numberOfCourse++;
                    System.out.println("Thêm môn học thành công");
                }else
                {
                    System.out.println("Thêm môn học thất bại");

                }
                System.out.println(line);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return numberOfCourse;
    }
}
