import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class MitCommandInputTest {
    @ParameterizedTest
    @DisplayName("mit 명령어 입력이 주어졌을때 문자열 입력받아서 반환받는지 테스트")
    @ValueSource(strings = {"mit list ./Work/Masters", "mit hash ./Work/Masters", "mit zlib ./Work/Masters"})
    public void input_givenInputString_whenInput_thenSuccess(String input){
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        MitCommandInput mitCommandInput = new MitCommandInput();
        //when
        String actual = mitCommandInput.input();
        //then
        Assertions.assertThat(actual).isEqualTo(input);
    }

    @ParameterizedTest
    @DisplayName("부적절한 mit 명령어가 주어졌을때 예외가 발생하는지 테스트")
    @ValueSource(strings = {"mit list", "mit list ", "", " ", "1@#!@"})
    public void input_givenInputString_whenInput_thenFail(String input){
        //given
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        MitCommandInput mitCommandInput = new MitCommandInput();
        //when and then
        Assertions.assertThatThrownBy(mitCommandInput::input);
    }
    
}