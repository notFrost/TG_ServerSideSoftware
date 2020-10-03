package com.opense.traininggain.service;

import com.opense.traininggain.domain.model.Customer;
import com.opense.traininggain.domain.repository.CustomerRepository;
import com.opense.traininggain.domain.service.CustomerService;
import com.opense.traininggain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long cCustomer) {
        return customerRepository.findById(cCustomer).orElseThrow(()->new ResourceNotFoundException("Customer","Id",cCustomer));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long cCustomer, Customer customerDetails) {
        return customerRepository.findById(cCustomer).map(customer -> {
            customer.setnCustomer(customerDetails.getnCustomer());
            customer.setnBirthDate(customerDetails.getnBirthDate());
            customer.setfMale(customerDetails.getfMale());
            customer.setnPhone(customerDetails.getnPhone());
            customer.settEmail(customerDetails.gettEmail());
            customer.settEmail(customerDetails.gettEmail());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", cCustomer));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Long cCustomer) {
        Customer customer = customerRepository.findById(cCustomer)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "Id", cCustomer));
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}
