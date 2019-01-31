package com.hpe.training;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerDao dao;

    @RequestMapping(path="/customers", method=RequestMethod.GET,
        produces="application/json")
    public Iterable<Customer> getCustomers() {
        return dao.findAll();
    }


    @RequestMapping(path="/customers/{custId}", method=RequestMethod.GET,
        produces="application/json")
    public Customer getCustomerById(@PathVariable("custId") String id) {
        return dao.findById(id).get();
    }


    @RequestMapping(path="/customers/from/{city}", method=RequestMethod.GET,
        produces="application/json")
    public List<Customer> getCustomersFromCity(@PathVariable String city) {
        return dao.findByCity(city);
    }
    

    @RequestMapping(path="/customers/withtitle/{title}", method=RequestMethod.GET,
        produces="application/json")
    public List<Customer> getCustomersByTitle(@PathVariable String title) {
        return dao.findByContactTitle(title);
    }
}









