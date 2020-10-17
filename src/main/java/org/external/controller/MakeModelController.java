package org.external.controller;


import org.models.core.properies.VehicleProperties;
import org.models.core.validators.MakeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/makemodel")
@RestController
@Validated
public class MakeModelController {



    @Autowired
    VehicleProperties vehicleProperties;

    @GetMapping("all/make")
    public List<String>  gatAllMakeTypes(){
        return vehicleProperties.getMake();
    }

    @GetMapping("/all/models")
    public Map<String,List<String>> getAllModels(){
        return vehicleProperties.getModels();
    }

    @GetMapping("/models")
    public List<String> getModelsByMake(@RequestParam("make") @Valid @MakeValidator String make){
        return vehicleProperties.getModels().get(make);
    }

}
