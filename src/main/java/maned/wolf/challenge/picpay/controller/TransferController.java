package maned.wolf.challenge.picpay.controller;

import jakarta.validation.Valid;
import maned.wolf.challenge.picpay.controller.dto.TransferRequestDTO;
import maned.wolf.challenge.picpay.entity.Transfer;
import maned.wolf.challenge.picpay.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferRequestDTO dto) {
        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);
    }
}
