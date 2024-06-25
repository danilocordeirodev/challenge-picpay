package maned.wolf.challenge.picpay.service;

import maned.wolf.challenge.picpay.controller.dto.CreateWalletRequestDTO;
import maned.wolf.challenge.picpay.entity.Wallet;
import maned.wolf.challenge.picpay.exception.WalletDataAlreadyExistsException;
import maned.wolf.challenge.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletRequestDTO dto) {
        var walllet = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walllet.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
        return walletRepository.save(dto.toWallet());
    }
}
