package com.alfabank.kosmos.exchangerate.model;

import java.util.Map;

public class Exchange {

//    disclaimer: "https://openexchangerates.org/terms/",
//    license: "https://openexchangerates.org/license/",
//    timestamp: 1449877801,
//    base: "USD",
//    rates: {
//        AED: 3.672538,
//        AFN: 66.809999,
//        /* ... */
//    }

    private String disclaimer;
    private String license;
    private String base;
    private Map<String, Double> rates;

    public Exchange() {
    }

    public Exchange(String disclaimer, String license, String base, Map<String, Double> rates) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.base = base;
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exchange exchange = (Exchange) o;

        if (getDisclaimer() != null ? !getDisclaimer().equals(exchange.getDisclaimer()) : exchange.getDisclaimer() != null)
            return false;
        if (getLicense() != null ? !getLicense().equals(exchange.getLicense()) : exchange.getLicense() != null)
            return false;
        if (getBase() != null ? !getBase().equals(exchange.getBase()) : exchange.getBase() != null) return false;
        return getRates() != null ? getRates().equals(exchange.getRates()) : exchange.getRates() == null;
    }

    @Override
    public int hashCode() {
        int result = getDisclaimer() != null ? getDisclaimer().hashCode() : 0;
        result = 31 * result + (getLicense() != null ? getLicense().hashCode() : 0);
        result = 31 * result + (getBase() != null ? getBase().hashCode() : 0);
        result = 31 * result + (getRates() != null ? getRates().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "disclaimer='" + disclaimer + '\'' +
                ", license='" + license + '\'' +
                ", base='" + base + '\'' +
                ", rates=" + rates +
                '}';
    }
}
