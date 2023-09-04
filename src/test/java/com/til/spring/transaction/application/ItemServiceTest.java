package com.til.spring.transaction.application;

import com.til.spring.transaction.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceTest {

    @Autowired(required = false)
    private ItemService itemService;

    @DisplayName("Transactional -> Transactional 순서의 메서드를 호출하면 저장된다.")
    @Test
    final void invocationTest() {
        assertThat(itemService.selfInvocationTransactionalTransactional(new Item("name", 2_000, 5)))
                .isTrue();
    }

    @DisplayName("Transactional -> Transactional 없는 메서드를 호출하면 저장된다. Transaction 전파")
    @Test
    final void invocationTest2() {
        assertThat(itemService.selfInvocationNonIsTransactionalToNonTransactional(new Item("name2", 2_000, 5)))
                .isTrue();
    }

    @DisplayName("Transactional이 없는 메서드에서 -> Transactional 순서의 메서드를 호출하면 저장되지 않는다.")
    @Test
    final void invocationTest3() {
        assertThat(itemService.selfInvocationNonTransactionalToIsTransactional(new Item("name2", 2_000, 5)))
                .isFalse();
    }

    @DisplayName("Required Propagation 을 테스트한다.")
    @Test
    final void propagationRequiredTest() {
        Map<String, Object> result = itemService.propagationRequired(new Item("name2", 2_000, 5));
        System.out.println("parent: " + result.get("parent"));
        System.out.println("child: " + result.get("child"));
//        assertThat(result.get("parent")).isEqualTo(result.get("child")); // 안에 숫자는 동일하다.
    }

    @DisplayName("Requires-new Propagation 을 테스트한다.")
    @Test
    final void propagationRequiresNewTest() {
        Map<String, Object> result = itemService.propagationRequiresNewParent(new Item("name2", 2_000, 5));
        System.out.println("parent: " + result.get("parent"));
        System.out.println("child: " + result.get("child"));
        assertThat(result.get("parent")).isNotEqualTo(result.get("child"));
    }
}