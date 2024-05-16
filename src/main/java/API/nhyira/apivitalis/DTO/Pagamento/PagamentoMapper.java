package API.nhyira.apivitalis.DTO.Pagamento;

import API.nhyira.apivitalis.Entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public PagamentoCreateEditDto convertToDto(Pagamento pagamento) {
        PagamentoCreateEditDto dto = new PagamentoCreateEditDto();
        dto.setUsuarioId(pagamento.getUsuarioId());
        dto.setAssinatura_id(pagamento.getAssinatura_id());
        dto.setTipo(pagamento.getTipo());
        dto.setData_vencimento(pagamento.getData_vencimento());
        dto.setData_pagamento(pagamento.getData_pagamento());
        return dto;
    }

    public PagamentoExibitionDto convertToExibitionDto(Pagamento pagamento) {
        PagamentoExibitionDto dto = new PagamentoExibitionDto();
        dto.setIdHistoricoPagamento(pagamento.getIdHistoricoPagamento());
        dto.setUsuarioId(pagamento.getUsuarioId());
        dto.setAssinatura_id(pagamento.getAssinatura_id());
        dto.setTipo(pagamento.getTipo());
        dto.setData_vencimento(pagamento.getData_vencimento());
        dto.setData_pagamento(pagamento.getData_pagamento());
        return dto;
    }

    public Pagamento convertToEntity(PagamentoCreateEditDto pagamentoDto) {
        Pagamento pagamento = new Pagamento();
        pagamento.setUsuarioId(pagamentoDto.getUsuarioId());
        pagamento.setAssinatura_id(pagamentoDto.getAssinatura_id());
        pagamento.setTipo(pagamentoDto.getTipo());
        pagamento.setData_vencimento(pagamentoDto.getData_vencimento());
        pagamento.setData_pagamento(pagamentoDto.getData_pagamento());
        return pagamento;
    }
}