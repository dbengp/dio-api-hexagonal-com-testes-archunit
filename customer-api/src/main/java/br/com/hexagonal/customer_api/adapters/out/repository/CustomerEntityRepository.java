package br.com.hexagonal.customer_api.adapters.out.repository;

import br.com.hexagonal.customer_api.adapters.out.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends MongoRepository<CustomerEntity, String> {
}
