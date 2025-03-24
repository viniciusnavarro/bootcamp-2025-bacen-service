package com.bootcamp.bacen_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ChaveRequestDTO {
    private String chave;
    private Boolean ativa;
}
