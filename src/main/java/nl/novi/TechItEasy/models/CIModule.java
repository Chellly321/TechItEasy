package nl.novi.TechItEasy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ciModules")
public class CIModule {

    //attributen

    @Id
    @GeneratedValue
    Long id;

    String name;
    String type;
    Double price;

    //default contructor
    public CIModule() {}
    public CIModule(String name, String type, Double price){
    }

    //full constructor
    public CIModule(Long id, String name, String type, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    //Setters and getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
