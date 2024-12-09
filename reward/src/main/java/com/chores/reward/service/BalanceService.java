package com.chores.reward.service;

import com.chores.reward.DTO.ChildChoreDTO;
import com.chores.reward.eventdriven.RewardEvent;
import com.chores.reward.model.Balance;
import com.chores.reward.repository.BalanceRepository;
import com.chores.reward.repository.SavingGoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final SavingGoalRepository savingGoalRepository;
    private final SavingGoalService savingGoalService;

    // gets a call from User when a child is created
    public Balance addBalanceToChild(UUID childUuid) {
        // Check if the childUuid already exists
        if (balanceRepository.findBalanceByChildUuid(childUuid).isEmpty()) {
            Balance balance = new Balance();
            balance.setBalanceUuid(UUID.randomUUID());
            balance.setChildUuid(childUuid);
            balance.setBalanceValue(0);

            return balanceRepository.save(balance);
        }
        return null;
    }

    public Optional<Balance> getBalance(UUID childUuid) {
        return balanceRepository.findBalanceByChildUuid(childUuid);
    }

    public Balance parentUpdateBalance(Balance balance) {
        Balance tempBalance = balanceRepository.findBalanceByChildUuid(balance.getChildUuid())
                .orElseGet(() -> {balance.setBalanceUuid(UUID.randomUUID());
                return balance;});
        tempBalance.setBalanceValue(balance.getBalanceValue());
        return balanceRepository.save(tempBalance);
    }

    // method receives a ChildChoreDateDTO and updates a child's balance
    public void updateBalance(RewardEvent message) {
        List<ChildChoreDTO> listOfChildChoreDTO = message.getChildChoreDateDTO().getListOfChildChoreDTO();
        UUID childUuid = listOfChildChoreDTO.get(0).getChildUuid();

        Balance balance = balanceRepository.findBalanceByChildUuid(childUuid).orElseThrow();
        int currentValue = balance.getBalanceValue();
        int addedValue = 0;

        for(ChildChoreDTO cc : listOfChildChoreDTO) {
            addedValue += cc.getValue();
        }

        int newValue = currentValue + addedValue;
        balance.setBalanceValue(newValue);
        balanceRepository.save(balance);

        savingGoalRepository.getSavingGoalByChildUuid(childUuid)
                .ifPresent(savingGoal -> savingGoalService.updateSavingGoal(childUuid, (long) newValue));

    }
}
