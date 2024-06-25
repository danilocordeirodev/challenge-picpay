package maned.wolf.challenge.picpay.service;

import maned.wolf.challenge.picpay.controller.dto.CreateWalletRequestDTO;
import maned.wolf.challenge.picpay.entity.Wallet;
import maned.wolf.challenge.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletRequestDTO requestDTO) {
        return walletRepository.save(requestDTO.toWallet());
    }
}
