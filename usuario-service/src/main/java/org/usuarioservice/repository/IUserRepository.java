package org.usuarioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.usuarioservice.entities.User;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {




}
