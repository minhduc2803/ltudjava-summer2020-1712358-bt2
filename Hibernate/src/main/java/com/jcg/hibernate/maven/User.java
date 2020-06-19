package com.jcg.hibernate.maven;

/**
 * com.jcg.hibernate.maven
 *
 * @created by ADMIN - StudentID : 1712358
 * @Date 19/6/2020 - 22:28 PM
 * @Description
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sinhvien")
public class User {


    @Id
    @Column(name = "MSSV")
    private String MSSV;

    @Column(name = "STT")
    private int STT;

    @Column(name = "HoTen")
    private String HoTen;

    @Column(name = "GioiTinh")
    private String GioiTinh;

    @Column(name = "CMND")
    private String CMND;


    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }


    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

}