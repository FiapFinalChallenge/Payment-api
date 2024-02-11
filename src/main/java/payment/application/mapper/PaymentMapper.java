package payment.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import payment.application.dto.request.PaymentRequest;
import payment.application.dto.response.PaymentResponse;
import payment.domain.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", source = "payment.id")
    @Mapping(target = "value", source = "payment.value")
    PaymentResponse convertToPaymentResponse(Payment payment);

    @Mapping(target = "value", source = "paymentRequest.value")
    Payment convertToPayment(PaymentRequest paymentRequest);
}
