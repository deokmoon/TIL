package com.til.spring.transaction.application;

import com.til.spring.transaction.domain.Item;
import com.til.spring.transaction.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

//@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ItemService {

    private final EntityManager entityManager;
    private final ItemRepository itemRepository;

    private final ItemChildService itemChildService;

    public boolean selfInvocationNonTransactionalToIsTransactional(Item item) {
        return this.selfInvocationSave(item);
    }
    @Transactional
    public boolean selfInvocationSave(Item item) {
        itemRepository.save(item);
        return entityManager.contains(item);
    }

    @Transactional
    public boolean selfInvocationTransactionalTransactional(Item item) {
        return this.selfInvocationSaveTransactional(item);
    }
    @Transactional
    public boolean selfInvocationSaveTransactional(Item item) {
        itemRepository.save(item);
        return entityManager.contains(item);
    }

    @Transactional
    public boolean selfInvocationNonIsTransactionalToNonTransactional(Item item) {
        return selfInvocationNonTransactional(item);
    }

    private boolean selfInvocationNonTransactional(Item item) {
        itemRepository.save(item);
        return entityManager.contains(item);
    }

    @Transactional
    public Map<String, Object> propagationRequired(Item item) {
        Map<String, Object> result = new HashMap<>();
        result.put("parent", entityManager.getDelegate().toString());
        result.put("child", itemChildService.propagationRequiredChild(item));
        return result;
    }
    @Transactional
    public Map<String, Object> propagationRequiresNewParent(Item item) {
        Map<String, Object> result = new HashMap<>();
        result.put("parent", entityManager.getDelegate());
        result.put("child", itemChildService.propagationRequiredNewChild(item));
        return result;
    }

}
