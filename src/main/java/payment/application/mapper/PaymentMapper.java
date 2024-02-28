package payment.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;
import payment.domain.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", source = "payment.id")
    @Mapping(target = "cartId", source = "payment.cartId")
    @Mapping(target = "value", source = "payment.value")
    @Mapping(target = "status", source = "payment.status")
    PaymentResponse convertToPaymentResponse(Payment payment);

    @Mapping(target = "cartId", source = "paymentRequest.cartId")
    @Mapping(target = "value", source = "paymentRequest.value")
    @Mapping(target = "status", source = "paymentRequest.status")
    Payment convertToPayment(PaymentRequest paymentRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "cartId", source = "paymentRequest.cartId")
    @Mapping(target = "value", source = "paymentRequest.value")
    Payment convertToPaymentWithId(PaymentRequest paymentRequest, Long id);
}
