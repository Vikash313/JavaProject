package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.NeoRepo;
import com.example.demo.model.Neo;

@RestController
@RequestMapping("/api")
public class NeoController
{
   @Autowired
   NeoRepo repo;
  
   
   @PostMapping("/")
   public Neo addNeo(@RequestBody Neo neo)
   {
 	  repo.save(neo);
 	  return neo;
   }
   
   @GetMapping("/")
   public List<Neo> getNeos()
   {
	   return repo.findAll();
   }
   
   @DeleteMapping("/{email}")
   public String deleteneo(@PathVariable String email)
   {
 	  Neo n =  repo.getOne(email);
 	  
 	  repo.delete(n);
 	  return "deleted";
   }
   
   @PutMapping("/")
   public Neo saveOrUpdateNeo(@RequestBody Neo neo)
   {
	   repo.save(neo);
	   return neo;
   }
   
   @GetMapping("/{email}")
   public Optional<Neo> getNeo(@PathVariable("email") String email)
   {
 	  
 	  return repo.findById(email);
 	  	 
   }

   
}
