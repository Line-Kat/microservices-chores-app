package com.chores.reward.eventdriven;

import com.chores.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardEventHandler {
    private final RewardService rewardService;

    @RabbitListener(
            queues = "chore.completed"
    )

    void handleRewardEvent() {
        rewardService.reward();
    }
}
