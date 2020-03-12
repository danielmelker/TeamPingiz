package com.academy.pingiz.matchTracker;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepositoryJPA extends CrudRepository<Match, Long> {

    Match findById(long id);
}
