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
import org.hibernate.transform.Transformers;
import pojo.SinhVien;
import pojo.ThoiKhoaBieu;
import pojo.ThoiKhoaBieuID;
import util.HibernateUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
    public static List<SinhVien> getSinhVienTheoLop(String MaLop){
        List<SinhVien> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List resultWithAliasedBean = session.createSQLQuery(
                    "select MSSV, MaLop, HoTen, GioiTinh, CMND from sinhvien where MaLop = \""+MaLop+"\"")
                    .addScalar("MSSV")
                    .addScalar("MaLop")
                    .addScalar("HoTen")
                    .addScalar("GioiTinh")
                    .addScalar("CMND")
                    .setResultTransformer( Transformers.aliasToBean(SinhVien.class))
                    .list();

            for(Object r:resultWithAliasedBean) {
                ds.add((SinhVien) r);
            }
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static List<SinhVien> getSinhVienTheoLopTheoMon(String MaLop, String MaMon){
        List<SinhVien> ds = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List resultWithAliasedBean = session.createSQLQuery(
                    "select sinhvien.MSSV as MSSV, sinhvien.MaLop as MaLop, sinhvien.HoTen as HoTen, sinhvien.GioiTinh as GioiTinh, sinhvien.CMND as CMND " +
                       "from " +
                       "thoikhoabieu " +
                       "inner join sinhvien " +
                       "on thoikhoabieu.mssv = sinhvien.mssv " +
                       "where thoikhoabieu.malop = \""+MaLop+"\" and thoikhoabieu.mamon = \""+MaMon+"\"")
                    .addScalar("MSSV")
                    .addScalar("MaLop")
                    .addScalar("HoTen")
                    .addScalar("GioiTinh")
                    .addScalar("CMND")
                    .setResultTransformer( Transformers.aliasToBean(SinhVien.class))
                    .list();

            for(Object r:resultWithAliasedBean) {
                ds.add((SinhVien) r);
            }
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

        Transaction transaction = null;
        try{
            session.beginTransaction();
            String sql = String.format("UPDATE sinhvien set password='%s' where mssv='%s';",sv.getPassword(),sv.getMSSV());
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
