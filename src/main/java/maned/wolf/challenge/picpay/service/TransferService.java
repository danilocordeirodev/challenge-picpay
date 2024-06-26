package maned.wolf.challenge.picpay.service;

import maned.wolf.challenge.picpay.controller.dto.TransferRequestDTO;
import maned.wolf.challenge.picpay.entity.Transfer;
import maned.wolf.challenge.picpay.entity.Wallet;
import maned.wolf.challenge.picpay.exception.InsufficientBalanceException;
import maned.wolf.challenge.picpay.exception.TransferNotAllowedForWalletTypeException;
import maned.wolf.challenge.picpay.exception.TransferNotAuthorizedException;
import maned.wolf.challenge.picpay.exception.WalletNotFoundException;
import maned.wolf.challenge.picpay.repository.TransferRepository;
import maned.wolf.challenge.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository,
                           AuthorizationService authorizationService,
                           NotificationService notificationService,
                           WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    public Transfer transfer(TransferRequestDTO dto) {
        var sender = walletRepository.findById(dto.payer())
                .orElseThrow(() -> new WalletNotFoundException(dto.payer()));

        var receiver = walletRepository.findById(dto.payee())
                .orElseThrow(() -> new WalletNotFoundException(dto.payee()));
        
        validateTransfer(dto, sender);

        sender.debit(dto.value());
        receiver.credit(dto.value());

        var transfer = new Transfer(sender, receiver, dto.value());
        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferRequestDTO dto, Wallet sender) {
        if(!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalanceEqualOrGreatherThan(dto.value())) {
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(dto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
