package com.esgi.twittbaguettes.repository;

import java.util.List;

import com.esgi.twittbaguettes.domain.Message;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    List<Message> findByLastName(@Param("name") String name);

}