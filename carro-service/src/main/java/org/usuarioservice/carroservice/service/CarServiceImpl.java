package org.usuarioservice.carroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usuarioservice.carroservice.entity.Car;
import org.usuarioservice.carroservice.repository.CarRepository;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;
    @Override
    public List<Car> getCars() {
        return (List) carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
            return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        Car carToUpdate = carRepository.findById(car.getId()).orElse(null);
        carToUpdate.setColor(car.getColor());
        carToUpdate.setColor(car.getMake());
        return carRepository.save(carToUpdate);
    }

    @Override
    public Car deleteCar(Long id) {
        Car carToDelete = carRepository.findById(id).orElse(null);
        carRepository.delete(carToDelete);
        return carToDelete ;
    }


    @Override
    public List<Car> findByUsuarioId(Long idUsuario) {
        return carRepository.findByUsuarioId(idUsuario);
    }


}
