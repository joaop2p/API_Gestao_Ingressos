package com.example.demo.services;

import com.example.demo.models.dto.forms.AddressFormDTO;
import com.example.demo.models.dto.forms.LocalFormDTO;
import com.example.demo.models.entities.Local;
import com.example.demo.repository.LocalRepository;
import com.example.demo.utils.exceptions.LocalAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocalService {
    final LocalRepository localRepository;

    @Transactional
    public Local createLocal(LocalFormDTO local) {
        AddressFormDTO addressDTO = local.address();
        if (localRepository.existsByAddress(
                addressDTO.street(),
                addressDTO.number(),
                addressDTO.district(),
                addressDTO.city(),
                addressDTO.state(),
                addressDTO.zipCode()
        ) && localRepository.existsByName(local.name())) {
            throw new LocalAlreadyExists("Local with the same name and address already exists");
        }
        Local entity = Local.fromDTOForm(local);
        return localRepository.save(entity);
    }
}
