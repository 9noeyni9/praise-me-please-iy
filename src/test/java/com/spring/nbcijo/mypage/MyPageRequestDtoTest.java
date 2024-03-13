package com.spring.nbcijo.mypage;

import static org.assertj.core.api.Assertions.assertThat;

import com.spring.nbcijo.common.UserFixture;
import com.spring.nbcijo.dto.request.UpdatePasswordRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MyPageRequestDtoTest {

    @DisplayName("비밀번호 변경 요청 테스트")
    @Nested
    class UpdatePasswordRequestDtoTest implements UserFixture {

        @DisplayName("비밀번호 변경 요청 테스트 성공")
        @Test
        void updatePasswordRequestDto_success() {
            // given
            var updatePasswordRequestDto = TEST_PASSWORD_UPDATE_REQUEST;

            // when
            var validations = validate(updatePasswordRequestDto);

            // then
            assertThat(validations).isEmpty();
        }

        @DisplayName("잘못된 형식의 비밀번호 변경 요청-실패")
        @Test
        void updatePasswordRequestDto_fail() {
            // given
            var updatePasswordRequestDto = TEST_PASSWORD_UPDATE_REQUEST_FAIL;

            // when
            var validations = validate(updatePasswordRequestDto);

            // then
            assertThat(validations).hasSize(1);
        }
    }

    private Set<ConstraintViolation<UpdatePasswordRequestDto>> validate(
        UpdatePasswordRequestDto updatePasswordRequestDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(updatePasswordRequestDto);
    }

}
