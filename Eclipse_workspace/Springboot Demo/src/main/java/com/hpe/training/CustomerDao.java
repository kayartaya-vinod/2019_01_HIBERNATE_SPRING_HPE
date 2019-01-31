package com.hpe.training;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, String> {
    @Query("from Customer where contactDetails.city=?1")
    public List<Customer> findByCity(String city);
    
    public List<Customer> findByContactTitle(String contactTitle);
}