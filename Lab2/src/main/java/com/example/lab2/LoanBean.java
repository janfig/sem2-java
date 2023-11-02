package com.example.lab2;

import java.io.Serializable;
import java.text.DecimalFormat;

public class LoanBean implements Serializable {
    private String kwota;
    private String procent;
    private String liczbaRat;

    public String getKwota() {
        return kwota;
    }

    public void setKwota(String kwota) {
        this.kwota = kwota;
    }

    public String getProcent() {
        return procent;
    }

    public void setProcent(String procent) {
        this.procent = procent;
    }

    public String getLiczbaRat() {
        return liczbaRat;
    }

    public void setLiczbaRat(String liczbaRat) {
        this.liczbaRat = liczbaRat;
    }

    public String getRata() {
//        try {
            double k = Double.parseDouble(kwota);
            double pr = Double.parseDouble(procent);
            double n = Double.parseDouble(liczbaRat);

            double p = pr/12;
            double rata = (k * p) / (1 - (1 / Math.pow(1 + p, n)));
            DecimalFormat df = new DecimalFormat("#.00");

            return df.format(rata);
//        } catch (Exception e) {
//            return "Błędne dane!";
//        }
    }
}
