package API.nhyira.apivitalis.Service;


import API.nhyira.apivitalis.DTO.Pagamento.PagamentoCreateEditDto;
import API.nhyira.apivitalis.Entity.Pagamento;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Repository.AssinaturaRepository;
import API.nhyira.apivitalis.Repository.PagamentoRepository;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PayPalService {

    private APIContext apiContext;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PayPalService() {
        this.apiContext = new APIContext("AbUI1pKq9fQ02hZRHomR5Xwpp4FySt0voxZcQRejfx4uFys0Y7j7nn9iNxBxQnVXb-YU3y4ILCqj9Qp8", "EIr1aIAUafsa6cteAxgk555X8fmnVgIfOHeaUJddRvZi4mvcgj6GaE5RtvTELT1wQ71UIyHFXQMm0So1", "sandbox");
    }

    public Payment createPayment(
            PagamentoCreateEditDto dto,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {

        Double total = Double.valueOf(assinaturaRepository.findById(dto.getAssinatura_id())
                .orElseThrow(() -> new NaoEncontradoException("Assinatura"))
                .getValor());

        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.US, "%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);
        Pagamento pagamento = convertToPagamento(createdPayment, dto);


        if (pagamento.getUsuarioId() != null && usuarioRepository.existsById(pagamento.getUsuarioId())) {
            pagamentoRepository.save(pagamento);
        } else {
            throw new NaoEncontradoException("Usuário");
        }

        return createdPayment;
    }


    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        Payment executedPayment = payment.execute(apiContext, paymentExecute);

        Pagamento pagamento = convertToPagamento(executedPayment, null);
        if (pagamento.getUsuarioId() != null) {
            pagamentoRepository.save(pagamento);
        }

        return executedPayment;
    }

    private Pagamento convertToPagamento(Payment payment, PagamentoCreateEditDto dto) {
        Pagamento pagamento = new Pagamento();


        if (dto != null && dto.getUsuarioId() != null) {
            pagamento.setUsuarioId(dto.getUsuarioId());
        }

        if (dto != null && dto.getAssinatura_id() != null) {
            pagamento.setAssinatura_id(dto.getAssinatura_id());
        }

        if (payment.getIntent() != null) {
            pagamento.setTipo(payment.getIntent());
        }

        if (dto != null && dto.getData_vencimento() != null) {
            pagamento.setData_vencimento(dto.getData_vencimento());
        }

        if (dto != null && dto.getData_pagamento() != null) {
            pagamento.setData_pagamento(dto.getData_pagamento());
        }

        return pagamento;
    }
}