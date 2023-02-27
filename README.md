# common-mit
## CS16 공통 프로젝트 저장소
### 기능 1
- `printListOfFiles(String directoryPath)` : 디렉토리 탐색 후 내부 파일 및 디렉토리 출력
    1. `File` 생성자에 경로를 매개변수로 넣어 `File`객체를 생성 후, `listFiles()` 메소드를 통해 내부 파일 및 디렉토리들을 `File`객체로 배열에 넣음.

        ```java
        File[] files = new File(directoryPath).listFiles();
        ```

    2. for 반복문으로 File객체 요소를 탐색하며 정해진 포멧에 맞게 출력함
        1. `file.isFile()` 메소드로 파일 또는 디렉토리 여부를 확인함
        2. `file.getName()` 메소드로 파일 또는 디렉토리의 이름을 구함
        3. `file.length()` 메소드로 파일 또는 디렉토리의 크기를 구함. byte 크기로 계산되기 때문에 KB로 변환하기 위해서 1,000을 나눔

            ```java
            for (File file : files) {
                String kind = file.isFile() ? "파일" : "디렉토리";
                System.out.printf("<%s> %s %dKB\n", kind, file.getName(), file.length() / 1000);
            }
            ```

    3. 올바른 경로가 아니여서 File 객체가 `null`인 경우 예외처리를 함
  
       ![https://user-images.githubusercontent.com/85631282/221630950-01003ff9-1c61-4b6e-93ae-167ffb4b9e20.png](https://user-images.githubusercontent.com/85631282/221630950-01003ff9-1c61-4b6e-93ae-167ffb4b9e20.png)

### 기능 2
- `MessageDigest` 클래스 사용
    1. `File` 생성자에 경로를 매개변수로 넣어 `File`객체를 생성 후, `listFiles()` 메소드를 통해 내부 파일 및 디렉토리들을 `File`객체로 배열에 넣음

        ```java
        File[] files = new File(directoryPath).listFiles();
        ```

    2. 숨김 파일 또는 디렉토리인 경우 `continue`로 넘긴다. 오직 파일만 해시값을 구하도록 함

        ```java
        for (File file : files) {
            if (file.isHidden() || file.isDirectory()) {
                continue;
            }
            ...
        }
        ```

       ❗⚠ 숨김 파일에는 접근할 수 없어서 오류가 난다. 접근 권한 때문에 이해를 할 수 있지만, 디렉토리인 경우에는 왜 해시값을 구할 수 없는 걸까? 디렉토리에 해시값을 구하면 `null` 또는 오류를 낸다. 이점에 대해서 추가 공부가 필요하다.

    3. `getHashCode()` 메소드에서 매개변수로 받은 file 객체에 대한 `SHA-256` 알고리즘에 의해 해시값을 도출한다.
- 3가지 파일에 대한 해시값 구하기

  ![https://user-images.githubusercontent.com/85631282/221631407-7b3cd5f8-ab72-4a55-856f-fcb710e659f8.png](https://user-images.githubusercontent.com/85631282/221631407-7b3cd5f8-ab72-4a55-856f-fcb710e659f8.png)

  `java.txt` 파일 내용을 수정하면 해시값이 다르다.

  ![https://user-images.githubusercontent.com/85631282/221631686-a132fc09-7cbd-46ed-bfae-ffcfa5d70fa0.png](https://user-images.githubusercontent.com/85631282/221631686-a132fc09-7cbd-46ed-bfae-ffcfa5d70fa0.png)
  
https://charlie-dev.tistory.com/8
### 기능 3