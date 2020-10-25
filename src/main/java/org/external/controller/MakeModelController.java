package org.external.controller;


import org.models.core.domain.Make;
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
import java.util.Set;

@RequestMapping("/makemodel")
@RestController
@Validated
public class MakeModelController {



    @Autowired
    VehicleProperties vehicleProperties;

    @GetMapping("/all/make")
    public Set<String>  gatAllMakeTypes(){
        return  vehicleProperties.getMakes().keySet();
    }

    @GetMapping("/models")
    public Set<String> getModelsByMake(@RequestParam("make") @Valid @MakeValidator String make){
        return vehicleProperties.getMakes().get(make).getModels() ;
    }

}
