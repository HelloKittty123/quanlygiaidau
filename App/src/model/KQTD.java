/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class KQTD {

    int mavong, matran, matrancu, madoi1, madoi2, sobanthang1, sobanthang2;
    String tenvong, tentran, tendoi1, tendoi2, SVD;
    Date thoigianthidau;

    public KQTD() {
    }

    public KQTD(int mavong, int matran, int matrancu, int madoi1, int madoi2, int sobanthang1, int sobanthang2, String tenvong, String tentran, String tendoi1, String tendoi2, String SVD, Date thoigianthidau) {
        this.mavong = mavong;
        this.matran = matran;
        this.matrancu = matrancu;
        this.madoi1 = madoi1;
        this.madoi2 = madoi2;
        this.sobanthang1 = sobanthang1;
        this.sobanthang2 = sobanthang2;
        this.tenvong = tenvong;
        this.tentran = tentran;
        this.tendoi1 = tendoi1;
        this.tendoi2 = tendoi2;
        this.SVD = SVD;
        this.thoigianthidau = thoigianthidau;
    }

    public KQTD(int mavong, int matran, int madoi1, int madoi2, int sobanthang1, int sobanthang2, String tenvong, String tentran, String tendoi1, String tendoi2, String SVD, Date thoigianthidau) {
        this.mavong = mavong;
        this.matran = matran;
        this.madoi1 = madoi1;
        this.madoi2 = madoi2;
        this.sobanthang1 = sobanthang1;
        this.sobanthang2 = sobanthang2;
        this.tenvong = tenvong;
        this.tentran = tentran;
        this.tendoi1 = tendoi1;
        this.tendoi2 = tendoi2;
        this.SVD = SVD;
        this.thoigianthidau = thoigianthidau;
    }

    public KQTD(int matran, int madoi1, int madoi2, int sobanthang1, int sobanthang2, String tenvong, String tentran, String tendoi1, String tendoi2, String SVD, Date thoigianthidau) {
        this.matran = matran;
        this.madoi1 = madoi1;
        this.madoi2 = madoi2;
        this.sobanthang1 = sobanthang1;
        this.sobanthang2 = sobanthang2;
        this.tenvong = tenvong;
        this.tentran = tentran;
        this.tendoi1 = tendoi1;
        this.tendoi2 = tendoi2;
        this.SVD = SVD;
        this.thoigianthidau = thoigianthidau;
    }

    public KQTD(int matran, int madoi1, int madoi2, int sobanthang1, int sobanthang2, String tendoi1, String tendoi2, String SVD, Date thoigianthidau) {
        this.matran = matran;
        this.madoi1 = madoi1;
        this.madoi2 = madoi2;
        this.sobanthang1 = sobanthang1;
        this.sobanthang2 = sobanthang2;
        this.tendoi1 = tendoi1;
        this.tendoi2 = tendoi2;
        this.SVD = SVD;
        this.thoigianthidau = thoigianthidau;
    }

    public KQTD(int matran, int matrancu, int madoi1, int madoi2, int sobanthang1, int sobanthang2, String SVD, Date thoigianthidau) {
        this.matran = matran;
        this.matrancu = matrancu;
        this.madoi1 = madoi1;
        this.madoi2 = madoi2;
        this.sobanthang1 = sobanthang1;
        this.sobanthang2 = sobanthang2;
        this.SVD = SVD;
        this.thoigianthidau = thoigianthidau;
    }

    public KQTD(int matran, int madoi1, int madoi2, int sobanthang1, int sobanthang2, String SVD, Date thoigianthidau) {
        this.matran = matran;
        this.madoi1 = madoi1;
        this.madoi2 = madoi2;
        this.sobanthang1 = sobanthang1;
        this.sobanthang2 = sobanthang2;
        this.SVD = SVD;
        this.thoigianthidau = thoigianthidau;
    }

    public KQTD(int matran, String tentran) {
        this.matran = matran;
        this.tentran = tentran;
    }

    public KQTD(String tenvong) {
        this.tenvong = tenvong;
    }

    public int getMavong() {
        return mavong;
    }

    public void setMavong(int mavong) {
        this.mavong = mavong;
    }

    public int getMatran() {
        return matran;
    }

    public void setMatran(int matran) {
        this.matran = matran;
    }

    public int getMatrancu() {
        return matrancu;
    }

    public void setMatrancu(int matrancu) {
        this.matrancu = matrancu;
    }

    public int getMadoi1() {
        return madoi1;
    }

    public void setMadoi1(int madoi1) {
        this.madoi1 = madoi1;
    }

    public int getMadoi2() {
        return madoi2;
    }

    public void setMadoi2(int madoi2) {
        this.madoi2 = madoi2;
    }

    public int getSobanthang1() {
        return sobanthang1;
    }

    public void setSobanthang1(int sobanthang1) {
        this.sobanthang1 = sobanthang1;
    }

    public int getSobanthang2() {
        return sobanthang2;
    }

    public void setSobanthang2(int sobanthang2) {
        this.sobanthang2 = sobanthang2;
    }

    public String getTenvong() {
        return tenvong;
    }

    public void setTenvong(String tenvong) {
        this.tenvong = tenvong;
    }

    public String getTentran() {
        return tentran;
    }

    public void setTentran(String tentran) {
        this.tentran = tentran;
    }

    public String getTendoi1() {
        return tendoi1;
    }

    public void setTendoi1(String tendoi1) {
        this.tendoi1 = tendoi1;
    }

    public String getTendoi2() {
        return tendoi2;
    }

    public void setTendoi2(String tendoi2) {
        this.tendoi2 = tendoi2;
    }

    public String getSVD() {
        return SVD;
    }

    public void setSVD(String SVD) {
        this.SVD = SVD;
    }

    public Date getThoigianthidau() {
        return thoigianthidau;
    }

    public void setThoigianthidau(Date thoigianthidau) {
        this.thoigianthidau = thoigianthidau;
    }

}
