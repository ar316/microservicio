package org.usuarioservice.carroservice.service;

import org.usuarioservice.carroservice.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getCars();
    Car getCarById(Long id);
    Car addCar(Car car);
    Car updateCar(Car car);
    Car deleteCar(Long id);

    List<Car> findByUsuarioId(Long idUsuario);
}
