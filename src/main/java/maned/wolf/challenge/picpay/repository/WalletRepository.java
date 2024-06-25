package maned.wolf.challenge.picpay.repository;

import maned.wolf.challenge.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
