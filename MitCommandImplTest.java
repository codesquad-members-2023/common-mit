
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;
import java.util.Optional;

class MitCommandImplTest {
    @Test
    @DisplayName("mit list 명령어를 수행하여 디렉토리안에 파일들을 가져오는지 테스트")
    public void list_givenDirectoryName_whenMitList_thenGetFileList(){
        //given
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<File>> optionalList = mit.list("./Work/Masters");
        //then
        optionalList.ifPresentOrElse(mit.outputList, ()-> Assertions.fail("테스트에 실패하였습니다."));
    }

    @ParameterizedTest
    @DisplayName("mit list 명령어 수행시 적절하지 않은 디렉토리명을 입력시 빈 리스트를 가져오는지 테스트")
    @ValueSource(strings = {"", " ","./Work/Master", "#@#!", "1235"})
    public void list_givenDirectoryName_whenMitList_thenEmptyFileList(String directoryName){
        //given
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<File>> optionalList = mit.list(directoryName);
        //then
        Assertions.assertThat(optionalList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("mit list 명령어 수행시 디렉토리가 파일이 없고 디렉도리만 있는 경우 빈 리스트를 가져오는지 테스트")
    public void list_givenNotExistFile_whenMitList_thenEmptyFileList(){
        //given
        String directoryName = "./Work";
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<File>> optionalList = mit.list(directoryName);
        //then
        Assertions.assertThat(optionalList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("mit list 명령어 수행시 파일을 입력한 경우 빈 리스트를 가져오는지 테스트")
    public void list_givenFileName_whenMitList_thenEmptyFileList(){
        //given
        String fileName = "./Work/Masters/a";
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<File>> optionalList = mit.list(fileName);
        //then
        Assertions.assertThat(optionalList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("mit list 명령어 수행시 디렉토리명이 null인 경우 빈 리스트를 가져오는지 테스트")
    public void list_givenDirectoryNameWithNull_whenMitList_thenEmptyFileList(){
        //given
        String directoryName = null;
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<File>> optionalList = mit.list(directoryName);
        //then
        Assertions.assertThat(optionalList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("디렉토리안에 파일들을 암호화하여 출력하는지 테스트")
    public void hash_givenDirectoryName_whenMitHash_thenHashedFileList(){
        //given
        String directoryName = "./Work/Masters";
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<HashedFile>> optionalList = mit.hash(directoryName);
        //then
        optionalList.ifPresentOrElse(mit.outputHash, ()-> Assertions.fail("테스트에 실패하였습니다."));
    }

    @Test
    @DisplayName("디렉토리안에 파일들을 각각 압축하여 출력하는지 테스트")
    public void zlib_givenDirectoryName_whenMitZlib_thenZipFileList(){
        //given
        String directoryName = "./Work/Masters";
        MitCommand mit = new MitCommandImpl();
        //when
        Optional<List<File>> optionalList = mit.zlib(directoryName);
        //then
        optionalList.ifPresentOrElse(mit.outputList, ()-> Assertions.fail("테스트에 실패하였습니다."));
    }

}