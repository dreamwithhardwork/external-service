package org.external.controller;

import java.util.List;

import org.models.core.dao.VariantRepository;
import org.models.core.domain.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/variant")
@RestController
public class VarinatController {
    
    @Autowired
    VariantRepository variantRepository;


    @GetMapping("/makemodel")
    public List<Variant> getAllVariantsByMake(@RequestParam("make") String make, @RequestParam("model") String model){
        return variantRepository.findByMakeAndModel(make, model);
    }

    // to be moved to seperate service
    @PostMapping
    public Variant save(@RequestBody Variant variant){
        return variantRepository.save(variant);
    }

    @DeleteMapping
    public Boolean delete(@RequestParam("id") String id){
        variantRepository.deleteById(id);
        return true;

    }


}
