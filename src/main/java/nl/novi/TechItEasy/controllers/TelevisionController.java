package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dto.IdInputDto;
import nl.novi.TechItEasy.dto.TelevisionDto;
import nl.novi.TechItEasy.dto.TelevisionInputDto;
import nl.novi.TechItEasy.exceptions.BadRequestException;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TelevisionController {
    private TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService){
        this.televisionService = televisionService;
    }


    //Get-request voor alle televisies
    @GetMapping("/televisions")
    public List<TelevisionDto> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand){

        var dtos = new ArrayList<TelevisionDto>();
        List<Television> televisions;

        if (brand != null){
            televisions = televisionService.getAllTelevisionsByBrand(brand);
        } else if (brand == null){
            televisions = televisionService.getAllTelevisions();
        } else {
            throw new BadRequestException();
        }

        for (Television television : televisions){
            dtos.add(TelevisionDto.fromTelevision(television));
        }
        return dtos;

    }

    //Get-request voor 1 televisie
    @GetMapping("/television/{id}")
    public TelevisionDto getTelevision(@PathVariable("id") Long id){
        var television = televisionService.getTelevision(id);

        return TelevisionDto.fromTelevision(television);
    }

    @PostMapping("television")
    public TelevisionDto addTelevision(@RequestBody TelevisionInputDto dto){
        var television = televisionService.addTelevision(dto.toTelevision());
        return TelevisionDto.fromTelevision(television);
    }
    @DeleteMapping("/television/{id}")
    public void deleteTelevision(@PathVariable("id") Long id){
        televisionService.deleteTelevision(id);
    }

    @PutMapping("/television/{id}")
    public TelevisionDto updateTelevision(@PathVariable("id") Long id, @RequestBody Television television){
        televisionService.updateTelevision(id, television);
        return TelevisionDto.fromTelevision(television);
    }

    @PutMapping("/televisions/{id}/remotecontroller")
    public void assignRemoteControllerToTelevision(@PathVariable("id") Long id, @RequestBody IdInputDto input){
        televisionService.assignRemoteControllerToTelevision(id, input.id);
    }
    }

