package com.workia.orders.infrastructure.adapter.out.persistence;

import com.workia.orders.application.ports.out.OrderRepositoryPort;
import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.infrastructure.adapter.out.persistence.entity.CreatedOrderEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private static Map<String, CreatedOrderEntity> dbMap;

    @PostConstruct
    private void initializeDB(){
        OrderRepositoryAdapter.dbMap = new HashMap<>();
    }

    @Override
    public CreatedOrderEntity saveOrder(CalculatedOrder order) {
        UUID uuid = UUID.randomUUID();
        CreatedOrderEntity createdOrderEntity = CreatedOrderEntity.builder()
                .creationDate(LocalDateTime.now())
                .totalAmount(order.getTotalAmount())
                .uuid(uuid.toString())
                .build();
        OrderRepositoryAdapter.dbMap.put(createdOrderEntity.getUuid(), createdOrderEntity);
        return createdOrderEntity;
    }
}
