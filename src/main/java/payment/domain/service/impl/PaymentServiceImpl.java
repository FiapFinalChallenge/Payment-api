package payment.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payment.application.dto.response.PaymentResponse;
import payment.application.mapper.PaymentMapper;
import payment.domain.repository.IPaymentRepository;
import payment.domain.service.contract.IPaymentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final IPaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    public List<PaymentResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::convertToPaymentResponse)
                .toList();
    }
}
