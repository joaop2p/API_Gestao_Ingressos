package com.example.demo.models.dto.absolute;

import com.example.demo.models.dto.forms.AddressFormDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data Transfer Object for User")
public record UserDTO(
        @Schema(description = "ID do usuário", example = "1")
        Long id,
        @Schema(description = "Nome do usuário", example = "João da Silva")
        String name,
        @Schema(description = "Email do usuário", example = "joao.silva@example.com")
        String email,
        @Schema(description = "Documento do usuário", example = "123.456.789-00")
        String documentId,
        @Schema(description = "Endereço do usuário")
        AddressFormDTO address
) {
}
