package com.chores.reward.eventdriven;

import com.chores.reward.service.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RewardEventHandler {
    private final BalanceService balanceService;

    @RabbitListener(
            queues = "chore.completed"
    )
    void handleRewardEvent(
            RewardEvent message
    ) {
        log.info("RECEIVED FROM RABBIT: " + message);

        balanceService.updateBalance(message);

    }
}
