package com.sinan.test.service;

import com.sinan.test.exception.PhoneCardAlreadyExistsException;
import com.sinan.test.exception.PhoneCardNotFoundException;
import com.sinan.test.model.PhoneCard;
import com.sinan.test.repository.IPhoneCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhoneCardService {
    private final IPhoneCardRepository pcRepository;

    public List<PhoneCard> getPhoneCards(String name) {
        if (name == null) {
            return pcRepository.findAll();
        } else {
            return pcRepository.findAllByName(name);
        }
    }

    public PhoneCard createPhoneCard(PhoneCard newEntity) {

        Optional<PhoneCard> phoneByName = pcRepository.findByName(newEntity.getName());
        if (phoneByName.isPresent()) {
            throw new PhoneCardAlreadyExistsException("Phone Card already exists with name: " + newEntity.getName());
        }
        return pcRepository.save(newEntity);
    }

    public void deletePhoneCard(String id) {
        pcRepository.deleteById(id);
    }

    public PhoneCard getPhoneCardById(String id) {
        return pcRepository.findById(id)
                .orElseThrow(() -> new PhoneCardNotFoundException("Phone Card not found with id: " + id));
    }

    public void updatePhoneCard(String id, PhoneCard newPhoneCard) {
        PhoneCard oldPhoneCard = getPhoneCardById(id);
        oldPhoneCard.setName(newPhoneCard.getName());
        oldPhoneCard.setSurname(newPhoneCard.getSurname());
        oldPhoneCard.setPhoneNumber(newPhoneCard.getPhoneNumber());
        oldPhoneCard.setAddress(newPhoneCard.getAddress());
        pcRepository.save(oldPhoneCard);
    }
}
