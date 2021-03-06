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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MonHocDAO {
    public static List<MonHoc> getListMonHoc() {
        List<MonHoc> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List resultWithAliasedBean = session.createSQLQuery(
                    "select MaMon, MaLop, TenMon, PhongHoc from monhoc")
                    .addScalar("MaMon")
                    .addScalar("MaLop")
                    .addScalar("TenMon")
                    .addScalar("PhongHoc")
                    .setResultTransformer( Transformers.aliasToBean(MonHocID.class))
                    .list();

            for(Object r:resultWithAliasedBean) {
                MonHocID mh = (MonHocID)r;
                ds.add(new MonHoc(mh.getMaMon(),mh.getMaLop(),mh.getTenMon(),mh.getPhongHoc()));
            }
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
                    .setResultTransformer( Transformers.aliasToBean(MonHocID.class))
                    .list();

            for(Object r:resultWithAliasedBean) {
                MonHocID mh = (MonHocID)r;
                ds.add(new MonHoc(mh.getMaMon(),mh.getMaLop(),mh.getTenMon(),mh.getPhongHoc()));
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
        List<MonHoc> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List resultWithAliasedBean = session.createSQLQuery(
                    "select MaMon, MaLop, TenMon, PhongHoc from monhoc where MaLop = \""+ID.MaLop+"\" and MaMon = \""+ID.MaMon+"\"")
                    .addScalar("MaMon")
                    .addScalar("MaLop")
                    .addScalar("TenMon")
                    .addScalar("PhongHoc")
                    .setResultTransformer( Transformers.aliasToBean(MonHocID.class))
                    .list();

            for(Object r:resultWithAliasedBean) {
                MonHocID mh = (MonHocID)r;
                ds.add(new MonHoc(mh.getMaMon(),mh.getMaLop(),mh.getTenMon(),mh.getPhongHoc()));
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds.get(0);
    }
    public static boolean addMonHoc(MonHoc mh){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        try{
            session.beginTransaction();
            String sql = String.format("INSERT INTO monhoc(mamon,malop,tenmon,phonghoc) values('%s','%s','%s','%s');",mh.getMaMon(),mh.getMaLop(),mh.getTenMon(),mh.getPhongHoc());
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
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f.getCanonicalFile()), StandardCharsets.UTF_8));

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
