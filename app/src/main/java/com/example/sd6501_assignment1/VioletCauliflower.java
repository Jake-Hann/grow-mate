package com.example.sd6501_assignment1;

public class VioletCauliflower {

    private final String name = "Violet Cauliflower";
    private final int germinationDays = 14;
    private final int transplantDays = 29;
    private final int harvestDays = 140;
    private String germinationDate = "";
    private String transplantDate = "";
    private String harvestDate = "";
    private final String growingInformationWhen = "Sow January to March.";
    private final String growingInformationWhere = "Into trays for subsequent transplanting.";
    private final String growingInformationHow = "Sow seeds thinly 1cm deep in rows 30cm apart.";
    private final String growingInformationCare = "Keep tray evenly moist during germination " +
                                                 "period.";

    public VioletCauliflower() {
    }

    public String getName() {
        return name;
    }

    public int getGerminationDays() {
        return germinationDays;
    }

    public int getTransplantDays() {
        return transplantDays;
    }

    public int getHarvestDays() {
        return harvestDays;
    }

    public String getGerminationDate() {
        return germinationDate;
    }

    public void setGerminationDate(String germinationDate) {
        this.germinationDate = germinationDate;
    }

    public String getTransplantDate() {
        return transplantDate;
    }

    public void setTransplantDate(String transplantDate) {
        this.transplantDate = transplantDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }

    public String getGrowingInformationWhen() {
        return growingInformationWhen;
    }

    public String getGrowingInformationWhere() {
        return growingInformationWhere;
    }

    public String getGrowingInformationHow() {
        return growingInformationHow;
    }

    public String getGrowingInformationCare() {
        return growingInformationCare;
    }
}
