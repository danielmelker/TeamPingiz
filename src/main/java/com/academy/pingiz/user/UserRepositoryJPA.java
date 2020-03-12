package com.academy.pingiz.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoryJPA extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
