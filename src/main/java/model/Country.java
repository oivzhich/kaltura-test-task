package model;

import lombok.Data;

@Data
public class Country {
    private String objectType;
    private String code;
    private Integer id;
    private String name;

    public Country() {
        this.objectType = "KalturaCountry";
        this.code = "";
        this.id = 5;
        this.name = "";
    }
}
