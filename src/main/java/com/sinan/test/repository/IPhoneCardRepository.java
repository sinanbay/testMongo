package com.sinan.test.repository;

import com.sinan.test.model.PhoneCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IPhoneCardRepository extends MongoRepository<PhoneCard, String> {
    List<PhoneCard> findAllByName(String name);
    Optional<PhoneCard> findByName(String name);
}
