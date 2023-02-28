# common-mit
CS16 공통 프로젝트 저장소

### NANII - CS16. Git

## 용어 정리

- `remote` : 원격 / 내 로컬 저장소를 업로드하는 원격 공간 / ex. Github, Bitbucket.. 등
- `local` : 내 PC에서 관리하는 공간
- init : `git init` -> 저장소에 필요한 뼈대파일들이 저장된 .git폴더를 생성
- `clone` : remote repo에 존재하는 프로젝트를 내PC(local)로 가져오는 것


### 상태 관리

- `git repository` : 프로젝트를 위한 파일이 저장되어있는 저장소
- `staging area` : working directory에서 작업한 파일을 local repository에 전달하기 위해 파일들을 분류한는 공간
- `working directory` : 작업이 일어나는 폴더

### 파일 관련

- `Tracked` : [관리대상임] 이미 스냅샷에 포함돼 있던 파일
- `Untracked` : [관리대상이 아님] 깃에서 추적하지 않는 파일, 스냅샷도 staging area에도 포함되지 않은 파일
- `Unmodified` : 파일이 수정되지 않은 상태
- `Modified` : 파일이 수정된 상태로, 아직 로컬 데이터베이스에 커밋하지 않은 상태
- `Commited` : 데이터가 로컬 데이터베이스에 저장된 상태
- `Staged` : Staging Area에 반영된 상태, 현재 수정한 파일을 커밋으로 저장소에 기록예정인 상태

### Git Objects

- `Objects` : git 명령이 관리를 위해 생성하는 모든 개체들
- `Commit` : 커밋마다 변경된 파일에 대한 트리 정보와 커밋 메시지를 보관하는 개체
  - `Tree` : 파일 변화를 트리 형태로 참조하는 개체
    - `blob` : tree의 파일들이 blob의 형태로 존재하는 게 커밋

### SHA-1(Secure Hash Algorithm 1)

- 역할 : 임의의 길이의 입력데이터 (최대 2⁶⁴)를 160비트의 출력데이터(해시값)로 바꿉니다
- 용도 : 인터넷 보안 프로토콜과 공개키 인증서에 적용되는 중요한 암호 알고리즘에 쓰입니다
- `git hash-object` : 40자 길이의 체크섬 해시를 출력하는 명령어
  - 헤더 정보와 데이터 모두에 대한 SHA-1 해시가 나타난다
- `git cat-file -t SHA-1 key 번호` : 가리키는 해당 개체가 무슨 개체인지 확인하는 명령어


### 작업의 흐름

- 작업 디렉토리(Working directory) : 실제 파일들이 존재하는 공간
- 인덱스(Index) : 준비 영역(staging area)의 역할
- HEAD : 최종 확정본(commit)을 나타냄


<br>

------


## Path module

```js
const notes = '/users/joe/notes.txt';

path.dirname(notes); // /users/joe
path.basename(notes); // notes.txt
path.extname(notes); // .txt
```

```js
path.resolve("/Users", "../daleseo", "test.txt") // /daleseo/test.txt'
```