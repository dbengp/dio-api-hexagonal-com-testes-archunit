package br.com.hexagonal.customer_api.adapters.in.controller;

import br.com.hexagonal.customer_api.adapters.in.controller.mapper.CustomerMapper;
import br.com.hexagonal.customer_api.adapters.in.controller.request.CustomerRequest;
import br.com.hexagonal.customer_api.adapters.in.controller.response.CustomerResponse;
import br.com.hexagonal.customer_api.application.core.domain.Customer;
import br.com.hexagonal.customer_api.application.ports.in.DeleteCustomerByIdInputPort;
import br.com.hexagonal.customer_api.application.ports.in.FindCustomerByIdInputPort;
import br.com.hexagonal.customer_api.application.ports.in.InsertCustomerInputPort;
import br.com.hexagonal.customer_api.application.ports.in.UpdateCustomerInputPort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    private final InsertCustomerInputPort insertCustomerInputPort;
    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final UpdateCustomerInputPort updateCustomerInputPort;
    private final DeleteCustomerByIdInputPort deleteCustomerByIdInputPort;
    private final CustomerMapper customerMapper;

    public CustomerController(InsertCustomerInputPort insertCustomerInputPort,
                              FindCustomerByIdInputPort findCustomerByIdInputPort,
                              UpdateCustomerInputPort updateCustomerInputPort,
                              DeleteCustomerByIdInputPort deleteCustomerByIdInputPort,
                              CustomerMapper customerMapper) {
        this.insertCustomerInputPort = insertCustomerInputPort;
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.updateCustomerInputPort = updateCustomerInputPort;
        this.deleteCustomerByIdInputPort = deleteCustomerByIdInputPort;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = customerMapper.toCustomer(customerRequest);
        insertCustomerInputPort.insert(customer, customerRequest.postal());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("id")
            @NotBlank(message = "O ID do cliente não pode ser vazio ou nulo.")
            @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "O ID do cliente deve ser um ObjectId válido (24 caracteres hexadecimais).")
            final String id){
        Customer customer = findCustomerByIdInputPort.find(id);
        CustomerResponse customerResponse = customerMapper.toCustomerReponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id")
            @NotBlank(message = "O ID do cliente não pode ser vazio ou nulo.")
            @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "O ID do cliente deve ser um ObjectId válido (24 caracteres hexadecimais).")
            final String id,
            @Valid @RequestBody CustomerRequest customerRequest){
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);
        updateCustomerInputPort.update(customer, customerRequest.postal());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id")
            @NotBlank(message = "O ID do cliente não pode ser vazio ou nulo.")
            @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "O ID do cliente deve ser um ObjectId válido (24 caracteres hexadecimais).")
            final String id){
        deleteCustomerByIdInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }


}
