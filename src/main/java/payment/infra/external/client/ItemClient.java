package payment.infra.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ITEMS-SERVICE")
public interface ItemClient {

    @PutMapping("api/items/{id}/decrease")
    void decreaseItemAmount(@PathVariable Long id, @RequestParam int amount);

    @PutMapping("api/items/{id}/increase")
    void increaseItemAmount(@PathVariable Long id, @RequestParam int amount);
}
