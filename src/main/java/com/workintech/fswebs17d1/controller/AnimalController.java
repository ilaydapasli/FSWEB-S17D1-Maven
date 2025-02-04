package com.workintech.fswebs17d1.controller;
//dış dünyaya projelerimizi açtığımız kısım
//spring boot için anatasyonları eklemeliyiz

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//eğer bir controller yazmanız gerekiyorsa spring boot starter webi eklemem,z lazım
// bir controllera erişmek için path tanımlamamız lazım
//localhost:8585/workintech/animal

@RestController
@RequestMapping(path= "/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals;
//herhangi bir yerden çağırmama gerek kalmıyor proje ayağa kalkarken animals hash mapı hazır duruyot ve ayağa kalkıyr.

    @PostConstruct
    public void loadAll(){
        System.out.println("postconstruct çalıştı.");
        this.animals=new HashMap<>();
        this.animals.put(1,new Animal(1,"maymun"));
    }
    @GetMapping
    public List<Animal> getAnimals(){
        System.out.println("---animal get all trigged");
        return new ArrayList<>(animals.values());
    }
    @GetMapping("{id}")
    public Animal getAnimal(@PathVariable ("id") int id){
        if (id<0){
            System.out.println("id cannot less than zero " + id);
            return null;
        }
        return  this.animals.get(id);

    }
    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(),animal);

    }
    @PutMapping("{id}")
    public Animal updateANimal(@PathVariable int id,@RequestBody Animal newAnimal){
    this.animals.replace(id,newAnimal);
    return this.animals.get(id);
    }
    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable ("id") int id){
        this.animals.remove(id);
    }
}
