package dao;

/**
 * dao
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 22/6/2020 - 22:30 PM
 * @Description
 */

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.SinhVien;
import util.HibernateUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class SinhVienDAO {
    public static List<SinhVien> getListSinhVien() {
        List<SinhVien> ds = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "select sv from SinhVien sv";
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
    public static SinhVien getSinhVien(String MSSV){
        SinhVien sv  = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            sv = (SinhVien) session.get(SinhVien.class, MSSV);
        }catch (Exception ex){
            System.err.println(ex);
        }finally {
            session.close();
        }
        return sv;
    }
    public static boolean addSinhVien(SinhVien sv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(SinhVienDAO.getSinhVien(sv.getMSSV()) != null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(sv);
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean updateSinhVien(SinhVien sv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        if(SinhVienDAO.getSinhVien(sv.getMSSV()) == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(sv);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static boolean deleteSinhVien(String MSSV){
        Session session = HibernateUtil.getSessionFactory().openSession();
        SinhVien sv = SinhVienDAO.getSinhVien(MSSV);
        if(sv == null){
            return false;
        }
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(sv);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
            System.err.println(ex);
        }finally {
            session.close();
        }
        return true;
    }
    public static int importSinhVien(File f){
        int numberOfSinhVien = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String firstLine = br.readLine().trim();
            System.out.println(firstLine);
            String line = "";
            while((line = br.readLine()) != null){
                String[] elements = line.split(",");


                String filename = f.getName();
                String lophocName = filename.substring(0,filename.length()-4);

                SinhVien sv = new SinhVien(elements[1].trim(),lophocName,elements[2].trim(),elements[3].trim(),elements[4].trim());
                boolean result = addSinhVien(sv);
                if(result) {
                    numberOfSinhVien++;
                    System.out.println("Thêm sinh viên thành công");
                }else
                {
                    System.out.println("Thêm sinh viên thất bại");

                }
                System.out.println(line);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return numberOfSinhVien;
    }
}
