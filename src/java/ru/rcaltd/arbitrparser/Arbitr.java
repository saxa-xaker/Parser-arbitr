package ru.rcaltd.arbitrparser;

public class Arbitr {

    private String arbitrFio;
    private String arbitrUrl;


    public Arbitr(String arbitrFio, String arbitrUrl) {
        this.arbitrFio = arbitrFio;
        this.arbitrUrl = arbitrUrl;
    }

    public String getArbitrFio() {
        return arbitrFio;
    }

    public void setArbitrFio(String arbitrFio) {
        this.arbitrFio = arbitrFio;
    }


    public String getArbitrUrl() {
        return arbitrUrl;
    }

    public void setArbitrUrl(String arbitrUrl) {
        this.arbitrUrl = arbitrUrl;
    }

    @Override
    public String toString() {
        return "Arbitr{" +
                "arbitrFio='" + arbitrFio + '\'' +
                ", arbitrUrl='" + arbitrUrl + '\'' +
                '}';
    }
}
