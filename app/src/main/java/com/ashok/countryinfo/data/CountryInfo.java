package com.ashok.countryinfo.data;

import java.util.ArrayList;

public class CountryInfo {
    private String title;
    private ArrayList<InfoRow> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<InfoRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<InfoRow> rows) {
        this.rows = rows;
    }
}
