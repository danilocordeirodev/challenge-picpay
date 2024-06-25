package maned.wolf.challenge.picpay.controller.dto;

import maned.wolf.challenge.picpay.entity.Wallet;
import maned.wolf.challenge.picpay.entity.WalletType;

public record CreateWalletRequestDTO(
        String fullName,
        String cpfCnpj,
        String email,
        String password,
        WalletType.Enum walletType
) {
    public Wallet toWallet() {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}
