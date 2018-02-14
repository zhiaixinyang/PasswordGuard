package com.mdove.passwordguard.model.net;

/**
 * Created by MDove on 2018/2/14.
 */

public class RealUpdate {
    private String src;
    private String check;
    private String nowversion;

    public String getNowversion() {
        return nowversion;
    }

    public void setNowversion(String nowversion) {
        this.nowversion = nowversion;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
