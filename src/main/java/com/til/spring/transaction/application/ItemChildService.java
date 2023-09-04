package com.til.spring.transaction.application;

import com.til.spring.transaction.domain.Item;
import com.til.spring.transaction.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemChildService {

    private final EntityManager entityManager;

    private final ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object propagationRequiredNewChild(Item item) {
        itemRepository.save(item);
        return entityManager.getDelegate();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Object propagationRequiredChild(Item item) {
        itemRepository.save(item);
        return entityManager.getDelegate();
    }
}
