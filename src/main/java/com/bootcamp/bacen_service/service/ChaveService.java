package com.bootcamp.bacen_service.service;

import com.bootcamp.bacen_service.dto.ChaveRequestDTO;
import com.bootcamp.bacen_service.dto.ChaveResponseDTO;
import com.bootcamp.bacen_service.exception.ChaveJaCadastradaException;
import com.bootcamp.bacen_service.exception.ChaveNaoLocalizadaException;
import com.bootcamp.bacen_service.model.Chave;
import com.bootcamp.bacen_service.repository.ChaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ChaveService {

    private final ChaveRepository chaveRepository;

    @Transactional
    public ChaveResponseDTO criarChave(final ChaveRequestDTO chaveRequestDTO) {

        if(chaveRepository.existsByChave(chaveRequestDTO.getChave())) {
            throw new ChaveJaCadastradaException(
              String.format("A chave: %s já existe no sistema.", chaveRequestDTO.getChave())
            );
        }

        Chave chave = Chave.builder()
                .chave(chaveRequestDTO.getChave())
                .ativa(chaveRequestDTO.getAtiva())
                .build();

        chave = chaveRepository.save(chave);

        return ChaveResponseDTO.builder()
                .chave(chave.getChave())
                .ativa(chave.getAtiva())
                .build();
    }

    public ChaveResponseDTO buscarChave (final String chavePesquisada) {
        Chave chave = chaveRepository.findByChave(chavePesquisada).orElseThrow(
                () -> new ChaveNaoLocalizadaException(
                        String.format("A chave: %s não existe no sistema.", chavePesquisada)
                ));

        return ChaveResponseDTO.builder()
                .chave(chave.getChave())
                .ativa(chave.getAtiva())
                .build();
    }

}
