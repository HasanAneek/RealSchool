package com.example.realschool.repository;

import com.example.realschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
//    @RestResource(path = "findByStatus")
    List<Contact> findAllByStatus(String status);

//    @RestResource(path = "findByStatusNamed")
    Page<Contact> findByStatus(String status, Pageable pageable);


    @Transactional
    @Modifying
//    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    @Query("UPDATE Contact c SET c.status = :status WHERE c.contactId = :id")
    int updateStatusById(String status, int id);
}
