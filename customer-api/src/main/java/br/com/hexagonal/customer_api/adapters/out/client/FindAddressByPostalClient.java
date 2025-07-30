package br.com.hexagonal.customer_api.adapters.out.client;

import br.com.hexagonal.customer_api.adapters.out.client.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "FindAddressByPostalClient",
        url = "${postal.client.address.url}"
)
public interface FindAddressByPostalClient {

    @GetMapping("/{postal}")
    AddressResponse find(@PathVariable("postal") String postal);
}
