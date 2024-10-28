package com.chores.reward.eventdriven;

import com.chores.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RewardEventHandler {
    private final RewardService rewardService;

    @RabbitListener(
            queues = "chore.completed"
    )

    void handleRewardEvent(
            RewardEvent message
    ) {
        rewardService.updateBalance(message);

        // if child has a saving goal rewardService.updateSavingGoal()
    }
}
