package payment.infra.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import payment.infra.external.dto.response.CartResponse;

@FeignClient(name = "CART-SERVICE")
public interface CartClient {

    @GetMapping("api/carts/{id}")
    CartResponse getCartById(@PathVariable Long id);
}
