package br.com.market.imarket.model;

public class Section {
    private Long id;
    private String sectionName;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setName(String name) {
        this.sectionName = name;
    }
}
