public class Mit {

    public void printFileLists(String cmd) {

        MitOutputView.printContents("printFileLists");

    }

    public void printHash(String cmd) {
        MitOutputView.printContents("printHash");

    }

    public void printZlib(String cmd) {
        MitOutputView.printContents("printZlib");

    }

    public void printCmds() {
        MitOutputView.printContents(" list: 디렉토리에서 전체 파일 목록을 탐색하고, 파일 크기와 파일명을 출력");
        MitOutputView.printContents(" hash: 디렉토리에서 전체 파일 목록을 탐색하고, 각 파일 내용에 대한 sha256 해시 값을 출력");
        MitOutputView.printContents(" zlib: 디렉토리에서 전체 파일 목록을 탐색하고, 각 파일을 zlib로 압축해서 .z를 붙여서 저장");
    }
}
