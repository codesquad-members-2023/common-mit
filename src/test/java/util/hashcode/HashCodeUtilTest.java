package util.hashcode;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import util.file.FileUtil;
import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.hashcode.HashCodeUtil;

class HashCodeUtilTest {

    @Test
    void create() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));
        byte[] content = FileUtil.getContent(file);

        // when


        // then
        assertDoesNotThrow(() -> HashCodeUtil.getHashCode(content, "SHA-256"));
        assertNotNull(HashCodeUtil.getHashCode(content, "SHA-256"));
    }

    @Test
    @DisplayName("해당 알고리즘이 없는 경우")
    void create2() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));
        byte[] content = FileUtil.getContent(file);

        // when

        // then
        assertThrows(IllegalArgumentException.class
            , () -> HashCodeUtil.getHashCode(content, "test"));

    }

    private String getPath() {
        String projectPath = System.getProperty("user.dir");
        return String.format("%s/src/test/resources", projectPath);
    }
}