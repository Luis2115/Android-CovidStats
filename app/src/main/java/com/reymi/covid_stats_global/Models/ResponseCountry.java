package com.reymi.covid_stats_global.Models;

public class ResponseCountry {

    private String country;
    private ResponseCountryInfo countryInfo;
    private int cases;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private int active;
    private int critical;

    public ResponseCountry() {
    }

    /**
     * @param country Nombre del Pais
     * @param recovered Casos Recuperados
     * @param cases Casos Totales
     * @param critical Casos Criticos
     * @param active Casos Activos
     * @param countryInfo Objeto con la url de la imagen del Pais
     * @param deaths Ciudadanos Muertos
     * @param todayCases Casos al Dia
     * @param todayDeaths Ciudadanos Muertos en el Dia
     */
    public ResponseCountry(String country, ResponseCountryInfo countryInfo, int cases, int todayCases, int deaths, int todayDeaths, int recovered,  int active, int critical) {
        super();
        this.country = country;
        this.countryInfo = countryInfo;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ResponseCountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(ResponseCountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }
}
