## 학습계획
1. 공통 프로젝트 저장소를 fork 하고 PR을 오픈해서 mergr하는 방법을 테스트해본다.
2. git 용어 및 명령어를 학습해본다.
3. mit 명령어를 하나씩 구현하고 PR을 생성한다.
## GIT이란?
> ### 분산형 버전 관리 시스템
> 파일들을 시점에 따라 버전으로 관리하는 방법  
> 중앙 저장소 하나에 관리하지 않고 분산해서 관리한다.  
> 특정 시점의 버전을 꺼내올 수 있고 버전을 되돌릴 수 있다.
### 장점
1. 각 파일을 이전 상태로 되돌릴 수 있다.
2. 프로젝트를 통째로 이전 상태로 되돌릴 수 있다.
3. 시간에 따라 수정 내용을 비교해볼 수 있다.
4. 누가 수정했는지 추적할 수 있다.
5. 여러 명이 동시에 병렬 개발이 가능하다.
### Git의 주요 객체
- **Commit**
  - 부모 커밋에 대한 참조를 가지고 있다.
  - 커밋 메시지
  - 트리
- **Tree**
  - 커밋은 하나 이상의 트리를 가진다.
  - 파일 시스템의 디렉토리 같은 구조
- **Blob**
  - Binary Large Object
  - 작업한 모든 파일 객체
  - SHA1 체크섬을 이용해서 파일을 식별 가능(key(SHA1체크섬) - value로 되어 있다)

## git 용어 및 명령어
### 저장소 관련 용어
- **remote**  
  remote 저장소 : 원격 저장소, PC가 아닌 다른 원격의 저장소, github에 만든 repository가 원격 저장소이다.
- **local**  
  local 저장소 : 현재 PC에 존재하는 저장소
- **init**  
  `git init` : 로컬 저장소를 원격 저장소와 연결하기 위해 제일 처음 사용하는 명령어로 `git init`을 실행하면 현재
  디렉토리에 `.git` 디렉토리를 생성한다. 이를 저장소를 초기화한다고 하고 git 명령어를 사용할 수 있게 된다.
- **clone**  
  원격 저장소의 데이터를 로컬 저장소로 복제하는 기능으로  
  `git clone {원격 저장소 주소} [디렉토리명]` 명령어로 복제할 수 있다.
### 상태 관리 용어
- **git repository**  
git 저장소 : 커밋된 데이터들을 보관하는 저장소
- **staging area**  
변경 사항이 있는 파일들을 커밋하기 위해서 보관하는 공간  
`git add {파일명}` 명령으로 추가할 수 있다.  
- **working directory**  
현재 작업하고 있는 디렉토리. 여기서 코드를 작성하고 add 명령을 통해 staging area로 옮길 수 있다.  
### 파일 관련
- **Untracked**  
working directory에 있지만 git으로 버전관리를 하지 않는 상태  
- **Unmodified**  
신규로 파일이 추가된 상태  
- **modified**  
추가된 파일이 수정된 상태  
- **staged**  
Staging Area에 반영된 상태(`git add`로 추가한 상태)

## 미션 구현
### File 클래스 사용
```java
// 디렉토리 생성
File directory = new File("C:/CodeSquad/CS16/common-mit/");
// 파일 목록
File[] files = directory.listFiles();
// 파일 크기
long fileSize = file.length();
```
### sha256
```java
// 파일을 Byte[]로 바꾼다
fileByte = Files.readAllBytes(file.toPath());
※ .git 파일은 읽으면 AccessDeniedException 발생

// MessageDigest를 이용해서 SHA256으로 해싱한다.
MessageDigest md = MessageDigest.getInstance("SHA-256");
md.update(fileByte);

StringBuilder sb = new StringBuilder();
// byte를 16진수로 변경한다.
for(byte b : fileByte){
    sb.append(String.format("%02x", b));
}
return sb.toString();
```
### 파일 압축하기
```java
String fileName = file.getName();
byte[] buffer = new byte[1024];
try {
    // 파일명 변경 a.java -> a.z
    String zipFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".z";
    // 압축해서 생성될 파일
    File zipFile = new File(file.getPath().replace(fileName, ""), zipFileName);
    // 출력 스트림
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
    // 입력 스트림
    FileInputStream in = new FileInputStream(file);

    ZipEntry ze = new ZipEntry(file.getName());
    out.putNextEntry(ze);

    int len;
    while((len = in.read(buffer)) > 0) {
        out.write(buffer, 0, len);
    }
    out.closeEntry();
} catch (IOException e) {
    throw new RuntimeException(e);
}
```
### 실행 결과
![capture](https://user-images.githubusercontent.com/57559288/221610448-f9505e33-3e2b-48c2-bc01-554d09921ec4.png)

### 학습 정리
> 커밋은 객체이다.. 각 커밋은 부모 커밋에 대한 참조를 가지고 있다.  
> git은 각 파일들을 해시값으로 인덱싱해서 원하는 파일을 가져온다.

### 참고
[Git의 개념 및 원리](https://inpa.tistory.com/entry/GIT-%E2%9A%A1%EF%B8%8F-%EA%B0%9C%EB%85%90-%EC%9B%90%EB%A6%AC-%EC%89%BD%EA%B2%8C%EC%9D%B4%ED%95%B4)  
[디렉토리 파일 불러오기](https://mine-it-record.tistory.com/432)  
[File to byte[]](https://blog.naver.com/PostView.naver?blogId=hj_kim97&logNo=222309453794&redirect=Dlog&widgetTypeCall=true&directAccess=false)  
[sha256 Hashing](https://needjarvis.tistory.com/251)  
[파일 압축하기](https://kitty-geno.tistory.com/170)