package org.external.controller;

import org.models.core.dao.MakeRepository;
import org.models.core.domain.Make;
import org.models.core.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/make")
public class MakeController {

    @Autowired
    MakeRepository makeRepository;

    @GetMapping("/all")
    public List<Make> gatAllMakeTypes(){
        return  makeRepository.findAll();
    }

    @GetMapping("/{name}")
    public Make getMakeByName(@PathVariable("name") String name){
        return makeRepository.findOneByName(name);
    }

    @PostMapping
    public Make saveOrUpdate(@RequestBody Make make){
        make.set_name(Util.generateIdFromUniqueName(make.getName()+make.getType().name()));
        return makeRepository.save(make);
    }
}
