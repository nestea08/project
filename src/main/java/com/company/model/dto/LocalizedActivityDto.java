package com.company.model.dto;


public class LocalizedActivityDto {
    private String enTitle;
    private String ruTitle;
    private String enDescription;
    private String ruDescription;

    public LocalizedActivityDto(String enTitle, String ruTitle, String enDescription, String ruDescription) {
        this.enTitle = enTitle;
        this.ruTitle = ruTitle;
        this.enDescription = enDescription;
        this.ruDescription = ruDescription;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public String getRuTitle() {
        return ruTitle;
    }

    public String getEnDescription() {
        return enDescription;
    }

    public String getRuDescription() {
        return ruDescription;
    }
}
