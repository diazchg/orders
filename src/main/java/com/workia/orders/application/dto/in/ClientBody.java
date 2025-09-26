package com.workia.orders.application.dto.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClientBody {

    @NotEmpty(message = "Must provide an email")
    private String email;

    @NotEmpty(message = "Must provide a client name")
    @Size(min = 2, max = 30, message = "Must provide a valid client name")
    private String name;
}
