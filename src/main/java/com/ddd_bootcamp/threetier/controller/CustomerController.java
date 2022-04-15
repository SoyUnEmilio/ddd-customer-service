package com.ddd_bootcamp.threetier.controller;

import com.ddd_bootcamp.domain.Address;
import com.ddd_bootcamp.domain.Customer;
import com.ddd_bootcamp.threetier.applicationservice.CustomerAppService;
import com.ddd_bootcamp.threetier.controller.resource.CustomerResource;
import com.ddd_bootcamp.threetier.controller.viewModel.AddressRequest;
import com.ddd_bootcamp.threetier.controller.viewModel.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CustomerController {

    private final CustomerAppService customerAppService;

    public CustomerController(CustomerAppService customerAppService) {
        this.customerAppService = customerAppService;
    }

    @PostMapping("/customers")
    public CustomerResource create(@RequestBody CustomerRequest request) {
        System.out.println("request = " + request);
        Customer customer = customerAppService.createCustomer(
                new Customer(new Address(request.getAddressRequest().getCity())));
        Customer newCustomer = customerAppService.createCustomer(customer);
        return new CustomerResource().from(newCustomer);
    }

    @PostMapping("/customers/{customerId}/accounts")
    public CustomerResource createAccount(@RequestBody CustomerRequest request, @PathVariable String customerId) {
        System.out.println("request = " + request);
        System.out.println("PathVariable = " + customerId);
        Customer customer = customerAppService.fetchCustomer(UUID.randomUUID());
        return new CustomerResource();
    }

    @PutMapping("/customers/{customerId}/address")
    public CustomerResource createAccount(@RequestBody AddressRequest request, @PathVariable String customerId) {
        System.out.println("request = " + request);
        System.out.println("PathVariable = " + customerId);
        Customer customer = customerAppService.updateAddress(UUID.randomUUID(), new Address(request.getCity()));
        return new CustomerResource();
    }


}
