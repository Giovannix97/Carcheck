package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the Region table of database
 */
public class RegionBean {
    
	@Table(name = "region")
	public RegionBean() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
    private int id;
    private String name;
}
