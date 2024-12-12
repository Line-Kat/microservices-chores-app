package com.chores.reward.eventdriven;

import com.chores.reward.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

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
        balanceService.updateBalance(message);
    }
}
