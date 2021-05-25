package com.reymi.covid_stats_global.Models;

public class GlobalStats {
    private String nombre;
    private String cantidad;
    private int img;

    public GlobalStats(String nombre, String cantidad, int img) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
