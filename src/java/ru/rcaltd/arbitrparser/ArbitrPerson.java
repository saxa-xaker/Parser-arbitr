package ru.rcaltd.arbitrparser;

import java.util.Date;

public class ArbitrPerson {

    private Long id;
    private String theSroId;
    private String userId;
    private String arbitrSurname;
    private String arbitrName;
    private String arbitrSecondname;
    private String arbitrInn;
    private String arbitrSnils;
    private String arbitrEfrsbRegNumber;
    private Date arbitrRosreestrRegDate;
    private Date arbitrSroEntryDate;
    private boolean isArbitrInTheSro;
    private boolean isArbitrActive;

    public ArbitrPerson(String theSroId, String userId, String arbitrSurname, String arbitrName,
                        String arbitrSecondname, String arbitrInn, String arbitrSnils, String arbitrEfrsbRegNumber,
                        Date arbitrRosreestrRegDate, Date arbitrSroEntryDate, boolean isArbitrInTheSro,
                        boolean isArbitrActive) {
        this.theSroId = theSroId;
        this.userId = userId;
        this.arbitrSurname = arbitrSurname;
        this.arbitrName = arbitrName;
        this.arbitrSecondname = arbitrSecondname;
        this.arbitrInn = arbitrInn;
        this.arbitrSnils = arbitrSnils;
        this.arbitrEfrsbRegNumber = arbitrEfrsbRegNumber;
        this.arbitrRosreestrRegDate = arbitrRosreestrRegDate;
        this.arbitrSroEntryDate = arbitrSroEntryDate;
        this.isArbitrInTheSro = isArbitrInTheSro;
        this.isArbitrActive = isArbitrActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheSroId() {
        return theSroId;
    }

    public void setTheSroId(String theSroId) {
        this.theSroId = theSroId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArbitrSurname() {
        return arbitrSurname;
    }

    public void setArbitrSurname(String arbitrSurname) {
        this.arbitrSurname = arbitrSurname;
    }

    public String getArbitrName() {
        return arbitrName;
    }

    public void setArbitrName(String arbitrName) {
        this.arbitrName = arbitrName;
    }

    public String getArbitrSecondname() {
        return arbitrSecondname;
    }

    public void setArbitrSecondname(String arbitrSecondname) {
        this.arbitrSecondname = arbitrSecondname;
    }

    public String getArbitrInn() {
        return arbitrInn;
    }

    public void setArbitrInn(String arbitrInn) {
        this.arbitrInn = arbitrInn;
    }

    public String getArbitrSnils() {
        return arbitrSnils;
    }

    public void setArbitrSnils(String arbitrSnils) {
        this.arbitrSnils = arbitrSnils;
    }

    public String getArbitrEfrsbRegNumber() {
        return arbitrEfrsbRegNumber;
    }

    public void setArbitrEfrsbRegNumber(String arbitrEfrsbRegNumber) {
        this.arbitrEfrsbRegNumber = arbitrEfrsbRegNumber;
    }

    public Date getArbitrRosreestrRegDate() {
        return arbitrRosreestrRegDate;
    }

    public void setArbitrRosreestrRegDate(Date arbitrRosreestrRegDate) {
        this.arbitrRosreestrRegDate = arbitrRosreestrRegDate;
    }

    public Date getArbitrSroEntryDate() {
        return arbitrSroEntryDate;
    }

    public void setArbitrSroEntryDate(Date arbitrSroEntryDate) {
        this.arbitrSroEntryDate = arbitrSroEntryDate;
    }

    public boolean isArbitrInTheSro() {
        return isArbitrInTheSro;
    }

    public void setArbitrInTheSro(boolean arbitrInTheSro) {
        isArbitrInTheSro = arbitrInTheSro;
    }

    public boolean isArbitrActive() {
        return isArbitrActive;
    }

    public void setArbitrActive(boolean arbitrActive) {
        isArbitrActive = arbitrActive;
    }

    @Override
    public String toString() {
        return "ArbitrPerson{" +
                "id=" + id +
                ", the_sro_id=" + theSroId +
                ", user_id='" + userId + '\'' +
                ", arbitrSurname='" + arbitrSurname + '\'' +
                ", arbitrName='" + arbitrName + '\'' +
                ", arbitrSecondname='" + arbitrSecondname + '\'' +
                ", arbitrInn='" + arbitrInn + '\'' +
                ", arbitrSnils='" + arbitrSnils + '\'' +
                ", arbitrEfrsbRegNumber=" + arbitrEfrsbRegNumber +
                ", arbitrRosreestrRegDate=" + arbitrRosreestrRegDate +
                ", arbitrSroEntryDate=" + arbitrSroEntryDate +
                ", isArbitrInTheSro=" + isArbitrInTheSro +
                ", isArbitrActive=" + isArbitrActive +
                '}';
    }
}