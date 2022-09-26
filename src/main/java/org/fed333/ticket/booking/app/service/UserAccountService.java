package org.fed333.ticket.booking.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.UserAccount;
import org.fed333.ticket.booking.app.repository.UserAccountRepository;

@Slf4j
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount refillAccount(UserAccount account, double money) {
        if (money < 0) {
            throw new RuntimeException("Money to set, cannot be negative.");
        }
        account.setMoney(money);
        UserAccount saved = userAccountRepository.save(account);
        log.info("Refilled account id: {}, set money to {}", account.getId(), account.getMoney());
        return saved;
    }

}
