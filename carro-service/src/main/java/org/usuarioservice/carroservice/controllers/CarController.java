package org.usuarioservice.carroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usuarioservice.carroservice.entity.Car;
import org.usuarioservice.carroservice.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/getAllCars")
    public ResponseEntity<List<Car>> getAllUsers() {
        List<Car>  users = carService.getCars();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getCarById/{id}")
    public ResponseEntity<Car> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    //endpoint para crear usuario
    @PostMapping("/create")
    public ResponseEntity<Car> getUserById(@RequestBody  Car car) {
        return ResponseEntity.ok(carService.addCar(car)) ;
    }

    //crear el endpint para editar
    @PutMapping("/edit")
    public ResponseEntity<Car> updateUser(@RequestBody  Car car) {

        return ResponseEntity.ok( carService.updateCar(car));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> deleteUser(@PathVariable  Long id) {
        return ResponseEntity.ok( carService.deleteCar(id));
    }

    @GetMapping("/usuario/getCar/{UsuarioId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable Long UsuarioId) {
        List<Car> cars = carService.findByUsuarioId(UsuarioId);
        if (cars != null && !cars.isEmpty()) {
            return ResponseEntity.ok(cars);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
