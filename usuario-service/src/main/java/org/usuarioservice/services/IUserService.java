package org.usuarioservice.services;

import org.usuarioservice.entities.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    User create(final User user);

    User update(final User user);

    User getUser(final Long id);

    User delete(final Long id);

}
