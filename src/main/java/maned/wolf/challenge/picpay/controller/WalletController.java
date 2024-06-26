package maned.wolf.challenge.picpay.controller;

import jakarta.validation.Valid;
import maned.wolf.challenge.picpay.controller.dto.CreateWalletRequestDTO;
import maned.wolf.challenge.picpay.entity.Wallet;
import maned.wolf.challenge.picpay.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletRequestDTO requestDTO) {
        var wallet = walletService.createWallet(requestDTO);

        return ResponseEntity.ok(wallet);
    }
}
