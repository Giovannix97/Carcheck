package it.carchek.model.bean;

public class Fuel implements VehicleProperty {
   public Fuel(short id,String description)
    {
    this.id=id;
    this.description=description;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getInformation() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private short id;
    private String description;
}
