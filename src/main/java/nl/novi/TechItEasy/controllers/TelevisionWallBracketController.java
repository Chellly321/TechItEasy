package nl.novi.TechItEasy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/tvwb")
public class TelevisionWallBracketController {
    private TelevisionWallBracketService televisionWallBracketService;
}
