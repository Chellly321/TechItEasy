package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.dto.CIModuleDto;
import nl.novi.TechItEasy.dto.CIModuleInputDto;
import nl.novi.TechItEasy.models.CIModule;
import nl.novi.TechItEasy.service.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CIModuleController {
    private CIModuleService ciModuleService;

    @Autowired
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping(value = "/cimodules")
    public List<CIModuleDto> getAllCiModules() {

        var dtos = new ArrayList<CIModuleDto>();

        var ciModules = ciModuleService.getAllCIModules();

        for (CIModule ciModule : ciModules) {
            dtos.add(CIModuleDto.fromCIModule(ciModule));
        }
        return dtos;
    }

    @GetMapping(value = "/cimodules/{id}")
    public CIModuleDto getCIModule(@PathVariable("id") Long id) {
        var ciModule = ciModuleService.getCIModule(id);

        return CIModuleDto.fromCIModule(ciModule);
    }

    @DeleteMapping(value = "/cimodules/{id}")
    public void deleteCIModule(@PathVariable("id") Long id) {
        ciModuleService.deleteCIModule(id);
    }

    @PostMapping("/cimodules")
    public CIModuleDto addCIModule(@RequestBody CIModuleInputDto dto) {
        var ciModule = ciModuleService.addCIModule(dto.toCIModule());
        return CIModuleDto.fromCIModule(ciModule);

    }
    @PutMapping("/cimodules/{id}")
    public CIModuleDto updateCIModule(@PathVariable("id") Long id, @RequestBody CIModule ciModule){
        ciModuleService.updateCIModule(id, ciModule);
        return CIModuleDto.fromCIModule(ciModule);
    }
}
