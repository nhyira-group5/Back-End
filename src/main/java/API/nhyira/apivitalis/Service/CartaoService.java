package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.DTO.Cartao.CartaoCreateEditDto;
import API.nhyira.apivitalis.DTO.Cartao.CartaoMapper;
import API.nhyira.apivitalis.Entity.Cartao;
import API.nhyira.apivitalis.Exception.DuplicatedCartaoException;
import API.nhyira.apivitalis.Repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CartaoMapper cartaoMapper;

    @Autowired
    public CartaoService(CartaoRepository cartaoRepository, CartaoMapper cartaoMapper) {
        this.cartaoRepository = cartaoRepository;
        this.cartaoMapper = cartaoMapper;
    }



    public Cartao createCartao(CartaoCreateEditDto dto) {
        if (cartaoRepository.findByBandeiraAndCvv(dto.getBandeira(), dto.getCvv()).isPresent()) {
            throw new DuplicatedCartaoException("a mesma bandeira e CVV!");
        } else if (cartaoRepository.findByNumero(dto.getNumero()).isPresent()) {
            throw new DuplicatedCartaoException("o mesmo número!");
        } else if (cartaoRepository.findByNomeTitular(dto.getNomeTitular()).isPresent()) {
            throw new DuplicatedCartaoException("o mesmo nome do titular!");
        } else {
            Cartao cartao = cartaoMapper.toEntity(dto);
            return cartaoRepository.save(cartao);
        }
    }

    public Optional<Cartao> getCartao(Integer id) {
        return cartaoRepository.findById(id);
    }
}
