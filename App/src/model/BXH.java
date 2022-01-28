/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class BXH {
    int mavong, vitri, madoi, sotran, sotrangthang, sotranthua, sotranhoa, sobanthang, sobanthua, hieuso, diem;
    String tenvong, tendoi;

    public BXH(int mavong, int vitri, int madoi, int sotran, int sotrangthang, int sotranthua, int sotranhoa, int sobanthang, int sobanthua, int hieuso, int diem, String tenvong, String tendoi) {
        this.mavong = mavong;
        this.vitri = vitri;
        this.madoi = madoi;
        this.sotran = sotran;
        this.sotrangthang = sotrangthang;
        this.sotranthua = sotranthua;
        this.sotranhoa = sotranhoa;
        this.sobanthang = sobanthang;
        this.sobanthua = sobanthua;
        this.hieuso = hieuso;
        this.diem = diem;
        this.tenvong = tenvong;
        this.tendoi = tendoi;
    }

    public BXH(int vitri, int sotran, int sotrangthang, int sotranthua, int sotranhoa, int sobanthang, int sobanthua, int hieuso, int diem, String tenvong, String tendoi) {
        this.vitri = vitri;
        this.sotran = sotran;
        this.sotrangthang = sotrangthang;
        this.sotranthua = sotranthua;
        this.sotranhoa = sotranhoa;
        this.sobanthang = sobanthang;
        this.sobanthua = sobanthua;
        this.hieuso = hieuso;
        this.diem = diem;
        this.tenvong = tenvong;
        this.tendoi = tendoi;
    }

    
    
    public int getMavong() {
        return mavong;
    }

    public void setMavong(int mavong) {
        this.mavong = mavong;
    }

    public int getVitri() {
        return vitri;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }

    public int getMadoi() {
        return madoi;
    }

    public void setMadoi(int madoi) {
        this.madoi = madoi;
    }

    public int getSotran() {
        return sotran;
    }

    public void setSotran(int sotran) {
        this.sotran = sotran;
    }

    public int getSotrangthang() {
        return sotrangthang;
    }

    public void setSotrangthang(int sotrangthang) {
        this.sotrangthang = sotrangthang;
    }

    public int getSotranthua() {
        return sotranthua;
    }

    public void setSotranthua(int sotranthua) {
        this.sotranthua = sotranthua;
    }

    public int getSotranhoa() {
        return sotranhoa;
    }

    public void setSotranhoa(int sotranhoa) {
        this.sotranhoa = sotranhoa;
    }

    public int getSobanthang() {
        return sobanthang;
    }

    public void setSobanthang(int sobanthang) {
        this.sobanthang = sobanthang;
    }

    public int getSobanthua() {
        return sobanthua;
    }

    public void setSobanthua(int sobanthua) {
        this.sobanthua = sobanthua;
    }

    public int getHieuso() {
        return hieuso;
    }

    public void setHieuso(int hieuso) {
        this.hieuso = hieuso;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getTenvong() {
        return tenvong;
    }

    public void setTenvong(String tenvong) {
        this.tenvong = tenvong;
    }

    public String getTendoi() {
        return tendoi;
    }

    public void setTendoi(String tendoi) {
        this.tendoi = tendoi;
    }
    
    
}
