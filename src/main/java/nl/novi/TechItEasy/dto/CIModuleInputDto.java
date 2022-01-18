package nl.novi.TechItEasy.dto;

import nl.novi.TechItEasy.models.CIModule;

public class CIModuleInputDto {
    public Long id;
    public String name;
    public String type;
    public Double price;

    public CIModule toCIModule() {
        var ciModule = new CIModule();

        ciModule.setId(id);
        ciModule.setName(name);
        ciModule.setPrice(price);
        ciModule.setType(type);

        return ciModule;
    }
}
