package com.academy.pingiz.forum;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForumRepositorySql extends CrudRepository<ForumPost,Long> {

   List<ForumPost> findAllByOrderByTimePostedDesc();

}
