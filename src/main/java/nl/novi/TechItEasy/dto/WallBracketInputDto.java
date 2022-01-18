package nl.novi.TechItEasy.dto;

import nl.novi.TechItEasy.models.WallBracket;

public class WallBracketInputDto {
    public Long id;
    public String size;
    public boolean ajusatable;
    public  String name;
    public Double price;

    public WallBracket toWallBracket(){
        var wallBracket = new WallBracket();

        wallBracket.setId(id);
        wallBracket.setSize(size);
        wallBracket.setAjustable(ajusatable);
        wallBracket.setName(name);
        wallBracket.setPrice(price);

        return wallBracket;
    }

}
