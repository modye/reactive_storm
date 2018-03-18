package com.point.futureburger.controller;

import com.point.futureburger.model.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/burger")
@AllArgsConstructor
public class BurgerController {

    private List<UUID> certifiedOriginalFood;

    @GetMapping("/bread")
    public ResponseEntity<Bread> getBread() throws InterruptedException {
        Bread bread = new Bread();
        certifiedOriginalFood.add(bread.getId());
        Thread.sleep(200);
        return ResponseEntity.ok(bread);
    }

    @PutMapping("/bread")
    public ResponseEntity cutBread(@RequestBody Bread toCut) throws InterruptedException {
        if (!certifiedOriginalFood.contains(toCut.getId())) {
            return ResponseEntity.notFound().build();
        }
        if (BreadState.CUT.equals(toCut.getState())) {
            return ResponseEntity.badRequest().body("Unable to cut an already cut bread");
        }
        Thread.sleep(1500);
        toCut.setState(BreadState.CUT);
        return ResponseEntity.ok(toCut);
    }

    @GetMapping("/cheese")
    public ResponseEntity<Cheese> getCheese() throws InterruptedException {
        Thread.sleep(400);

        Cheese cheese = new Cheese();
        certifiedOriginalFood.add(cheese.getId());
        return ResponseEntity.ok(cheese);
    }

    @GetMapping("/bacon")
    public ResponseEntity<Bacon> getBacon() throws InterruptedException {
        Thread.sleep(200);
        Bacon bacon = new Bacon();
        certifiedOriginalFood.add(bacon.getId());
        return ResponseEntity.ok(bacon);
    }

    @PutMapping("/bacon")
    public ResponseEntity cookBacon(@RequestBody Bacon toCook) throws InterruptedException {
        if (!certifiedOriginalFood.contains(toCook.getId())) {
            return ResponseEntity.notFound().build();
        }
        if (BaconState.COOKED.equals(toCook.getState())) {
            return ResponseEntity.badRequest().body("You will burn that awesome bacon !");
        }
        Thread.sleep(3000);
        toCook.setState(BaconState.COOKED);
        return ResponseEntity.ok(toCook);
    }

    @GetMapping("/salad")
    public ResponseEntity<Salad> getSalad() throws InterruptedException {
        Thread.sleep(100);
        Salad salad = new Salad();
        certifiedOriginalFood.add(salad.getId());
        return ResponseEntity.ok(salad);
    }

    @GetMapping("/salsa")
    public ResponseEntity<Salsa> getSalsa() throws InterruptedException {
        Thread.sleep(100);
        Salsa salsa = new Salsa();
        certifiedOriginalFood.add(salsa.getId());
        return ResponseEntity.ok(salsa);
    }

    @GetMapping("/steak")
    public ResponseEntity<Steak> getSteak() throws InterruptedException {
        Steak steak = new Steak();
        certifiedOriginalFood.add(steak.getId());
        Thread.sleep(400);
        return ResponseEntity.ok(steak);
    }

    @PutMapping("/steak")
    public ResponseEntity cookSteak(@RequestBody Steak toCook) throws InterruptedException {
        if (!certifiedOriginalFood.contains(toCook.getId())) {
            return ResponseEntity.notFound().build();
        }
        if (SteakState.COOKED.equals(toCook.getState())) {
            return ResponseEntity.badRequest().body("You will burn that lovely steak !");
        }
        toCook.setState(SteakState.COOKED);
        Thread.sleep(1500);
        return ResponseEntity.ok(toCook);
    }

    @GetMapping("/tomato")
    public ResponseEntity<Tomato> getTomato() throws InterruptedException {
        Thread.sleep(200);
        Tomato tomato = new Tomato();
        certifiedOriginalFood.add(tomato.getId());
        return ResponseEntity.ok(tomato);
    }

    @PostMapping
    public ResponseEntity buildBurger(@RequestBody @Valid Burger toBuild) throws InterruptedException {

        if (oneIngredientIsSuspicious(toBuild)) {
            return ResponseEntity.badRequest().body("I don't trust one of your ingredients evil cook");
        }

        if (BaconState.COOKED.equals(toBuild.getBacon().getState()) && BreadState.CUT.equals(toBuild.getBread().getState()) && SteakState.COOKED.equals(toBuild.getSteak().getState())) {
            Thread.sleep(5000);
            return ResponseEntity.ok(toBuild);
        }
        return ResponseEntity.badRequest().body("Ingredients are not ready");
    }

    private boolean oneIngredientIsSuspicious(Burger burger) {
        return burger.getIngredients().stream().anyMatch(p -> !certifiedOriginalFood.contains(p.getId()));
    }
}
