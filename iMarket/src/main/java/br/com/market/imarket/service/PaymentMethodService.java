package br.com.market.imarket.service;

import br.com.market.imarket.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;


@Service
public class PaymentMethodService {

    final PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }
}
