package com.workia.orders.application.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClientBody {
    private String email;
    private String name;
}
