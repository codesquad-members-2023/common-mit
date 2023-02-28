package util.file;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.file.FileUtil;

class FileUtilTest {

    @Test
    void compress() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));

        // when

        // then
        assertDoesNotThrow(() -> FileUtil.compress(file));
        assertNotNull(FileUtil.compress(file));
    }

    @Test
    @DisplayName("압축 할때 파일이 해당 경로에 없는 경우")
    void compress2() {
        // given
        File file = new File(String.format("%s/c.java", getPath()));

        // when

        // then
        assertThrows(RuntimeException.class, () ->  FileUtil.compress(file));

    }

    @Test
    void getContent() {
        // given
        File file = new File(String.format("%s/a.java", getPath()));

        // when

        // then
        assertDoesNotThrow(() -> FileUtil.getContent(file));
        assertNotNull(FileUtil.getContent(file));
    }

    @Test
    @DisplayName("파일 내용을 가져올 떄 파일이 해당 경로에 없는 경우")
    void getContent2() {
        // given
        File file = new File(String.format("%s/c.java", getPath()));

        // when

        // then
        assertThrows(RuntimeException.class, () ->  FileUtil.getContent(file));

    }

    private String getPath() {
        String projectPath = System.getProperty("user.dir");
        return String.format("%s/src/test/resources", projectPath);
    }
}