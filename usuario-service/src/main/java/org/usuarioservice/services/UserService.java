package org.usuarioservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.usuarioservice.DTO.CarDTO;
import org.usuarioservice.entities.User;
import org.usuarioservice.repository.IUserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  IUserRepository userRepository;


    //metodo para traer los carros del microservicio carro al micro de usuarios
    public List<CarDTO> getAllCarsByUser(Long IdUser){
        return List.of(restTemplate.getForObject("http://localhost:9090/car/usuario/getCar/" + IdUser, CarDTO[].class));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User delete(Long user) {
        User userToDelete = userRepository.findById(user)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ningún usuario con el ID proporcionado"));
        userRepository.delete(userToDelete);
        return userToDelete;
    }
}
