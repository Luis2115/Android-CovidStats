package com.reymi.covid_stats_global.Models;

public class ResponseCountryInfo {
    private String flag;

    public ResponseCountryInfo() {
    }

    public ResponseCountryInfo(String flag) {
        super();
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
