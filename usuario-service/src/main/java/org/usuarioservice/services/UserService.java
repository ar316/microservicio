package org.usuarioservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.usuarioservice.DTO.CarDTO;
import org.usuarioservice.entities.User;
import org.usuarioservice.repository.IUserRepository;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  IUserRepository userRepository;


    //metodo para traer los carros del microservicio carro al micro de usuarios
    public Map<String, Object> getAllCarsByUser(Long IdUser) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            // Buscando usuario
            User nameUser = userRepository.findById(IdUser).orElse(null);
            if (nameUser == null) {
                result.put("message", "User Not Found");
                return result;
            }
            result.put("usuario", nameUser.getName());

            try {
                // Llamada al microservicio de carros
                ResponseEntity<CarDTO[]> response = restTemplate.getForEntity("http://localhost:9090/car/usuario/getCar/" + IdUser, CarDTO[].class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    List<CarDTO> carList = Arrays.asList(response.getBody());
                    if (carList.isEmpty()) {
                        result.put("message", "no tiene carros");
                    } else {
                        result.put("Carros", carList);
                    }
                } else {
                    result.put("message", "no tiene carros");
                }
            } catch (HttpClientErrorException.NotFound e) {
                // Manejar específicamente el caso 404 Not Found
                result.put("message", "no tiene carros");
            }
        } catch (Exception e) {
            result.clear(); // Clear the result to ensure it only contains the error message
            result.put("message", "An error occurred while fetching the data");
        }
        return result;
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
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ningún usuario con el ID proporcionado"));
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
