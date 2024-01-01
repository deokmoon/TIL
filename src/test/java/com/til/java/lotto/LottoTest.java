package com.til.java.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    private List<Integer> numbers;

    @BeforeEach
    final void setUp() {
        numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
    }

    @Test
    @DisplayName("Lotto 클래스는 숫자를 보관할 List를 갖고있다.")
    final void createLotto() {
        // when - 행위
        Lotto lotto = Lotto.createLotto(numbers);
        // then - 결과(예상)
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    @DisplayName("Lotto 클래스는 숫자를 보관할 List를 갖고있다.")
    final void validateLottoNumber() {
        // given
        numbers.add(47);
        // when // then
        assertThatThrownBy(() -> Lotto.createLotto(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자 범위 오류");
    }

    @Test
    @DisplayName("Lotto 클래스는 숫자를 보관할 List를 갖고있다.")
    final void validateLottoNumberLength() {
        // given
        numbers.add(47);
        // when // then
        assertThatThrownBy(() -> Lotto.createLotto(numbers)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 6개까지의 숫자를 갖고있어야된다.");
    }
}
