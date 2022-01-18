package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dto.WallBracketDto;
import nl.novi.TechItEasy.dto.WallBracketInputDto;
import nl.novi.TechItEasy.models.WallBracket;
import nl.novi.TechItEasy.service.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WallBracketController {
private WallBracketService wallBracketService;

@Autowired
    public WallBracketController(WallBracketService wallBracketService){
    this.wallBracketService = wallBracketService;
}
@GetMapping("/wallbrackets")
    public List<WallBracketDto> getAllWallBrackets(){

    var dtos = new ArrayList<WallBracketDto>();

    var wallBrackets = wallBracketService.getAllWallBrackets();

    for (WallBracket wallBracket : wallBrackets){
        dtos.add(WallBracketDto.fromWallBracket(wallBracket));
    }
    return dtos;
}
@GetMapping("/wallbrackets/{id}")
    public WallBracketDto getWallBracket(@PathVariable("id") Long id){

    var wallBracket = wallBracketService.getWallBracket(id);

    return WallBracketDto.fromWallBracket(wallBracket);
}
@PostMapping("/wallbrackets")
    public WallBracketDto addWallBracket(@RequestBody WallBracketInputDto dto){
    var wallBraket = wallBracketService.addWallBracket((dto.toWallBracket()));
    return WallBracketDto.fromWallBracket(wallBraket);
}
@DeleteMapping("/wallbrackets/{id}")
    public void deleteWallBracket(@PathVariable("id") Long id){
    wallBracketService.deleteWallBracket(id);
}

@PutMapping("/wallbrackets/{id}")
    public WallBracketDto updateWallBracket(@PathVariable("id") Long id, @RequestBody WallBracket wallBracket){
        wallBracketService.updateWallBracket(id, wallBracket);
        return WallBracketDto.fromWallBracket(wallBracket);
    }
}
