package com.academy.pingiz.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryJPA extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
