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
public class CLB {

    int madoi, socauthu;
    String tendoi, doitruong, HLV;

    public CLB() {
    }

    public CLB(int madoi, int socauthu, String tendoi, String doitruong, String HLV) {
        this.madoi = madoi;
        this.socauthu = socauthu;
        this.tendoi = tendoi;
        this.doitruong = doitruong;
        this.HLV = HLV;
    }

    public CLB(int madoi, String tendoi, String doitruong, String HLV) {
        this.madoi = madoi;
        this.tendoi = tendoi;
        this.doitruong = doitruong;
        this.HLV = HLV;
    }

    public CLB(String tendoi, String doitruong, String HLV) {
        this.tendoi = tendoi;
        this.doitruong = doitruong;
        this.HLV = HLV;
    }

    public int getMadoi() {
        return madoi;
    }

    public void setMadoi(int madoi) {
        this.madoi = madoi;
    }

    public int getSocauthu() {
        return socauthu;
    }

    public void setSocauthu(int socauthu) {
        this.socauthu = socauthu;
    }

    public String getTendoi() {
        return tendoi;
    }

    public void setTendoi(String tendoi) {
        this.tendoi = tendoi;
    }

    public String getDoitruong() {
        return doitruong;
    }

    public void setDoitruong(String doitruong) {
        this.doitruong = doitruong;
    }

    public String getHLV() {
        return HLV;
    }

    public void setHLV(String HLV) {
        this.HLV = HLV;
    }

}
