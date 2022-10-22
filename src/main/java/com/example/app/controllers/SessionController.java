package com.example.app.controllers;


import com.example.app.models.Session;
import com.example.app.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")

public class SessionController {
    @Autowired
    private  SessionRepository sessionrepository ;

    @GetMapping
    public List<Session> list(){
        return sessionrepository.findAll();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Optional<Session> get(@PathVariable Long id){
        return sessionrepository.findById(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionrepository.save(session);
    }


    @RequestMapping(value = "{id}" ,method = RequestMethod.DELETE )
    public void delete(@PathVariable Long id){
        sessionrepository.deleteById(id);
    }


    @RequestMapping (value = "{id}" , method = RequestMethod.PUT)
    public Session update(@PathVariable Long id , @RequestBody Session session ){
        Optional<Session> existingsession = sessionrepository.findById(id);
        BeanUtils.copyProperties(session ,existingsession , "session_id");
        return sessionrepository.saveAndFlush(session);

    }






}
