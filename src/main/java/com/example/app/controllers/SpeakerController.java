package com.example.app.controllers;


import com.example.app.models.Session;
import com.example.app.models.Speaker;
import com.example.app.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speakers")



public class SpeakerController {


    @Autowired
    private SpeakerRepository speakerrepository ;

    @GetMapping
    public List<Speaker> list(){
        return speakerrepository.findAll();
    }



    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return  speakerrepository.getOne(id);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerrepository.save(speaker);
    }


    @RequestMapping(value = "{id}" ,method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        speakerrepository.deleteById(id);

    }


    @RequestMapping (value = "{id}" , method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id , @RequestBody Speaker speaker){
        Speaker existingspeaker = speakerrepository.getOne(id);
        BeanUtils.copyProperties(speaker ,existingspeaker , "speaker_id");
        return speakerrepository.save(existingspeaker);
    }

}
