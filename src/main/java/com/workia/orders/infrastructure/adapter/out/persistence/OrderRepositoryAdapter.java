package com.workia.orders.infrastructure.adapter.out.persistence;

import com.workia.orders.application.ports.out.OrderRepositoryPort;
import com.workia.orders.domain.model.CalculatedOrder;
import com.workia.orders.domain.model.CreatedOrder;
import com.workia.orders.infrastructure.adapter.out.persistence.entity.CreatedOrderEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final ConcurrentHashMap<String, CreatedOrderEntity> dbMap = new ConcurrentHashMap<>();



    @Override
    public CreatedOrder saveOrder(CalculatedOrder order) {
        UUID uuid = UUID.randomUUID();
        CreatedOrderEntity createdOrderEntity = CreatedOrderEntity.builder()
                .creationDate(LocalDateTime.now())
                .totalAmount(order.getTotalAmount())
                .uuid(uuid.toString())
                .build();
        this.dbMap.put(createdOrderEntity.getUuid(), createdOrderEntity);

        return CreatedOrder.builder()
                .creationDate(createdOrderEntity.getCreationDate())
                .totalAmount(createdOrderEntity.getTotalAmount())
                .uuid(createdOrderEntity.getUuid())
                .build();
    }
}
