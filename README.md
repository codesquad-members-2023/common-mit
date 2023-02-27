# common-mit

CS16 공통 프로젝트 저장소

# CS16. 버전관리와 PR

## ⭐️ 학습키워드: `#commit` `#SHA` `#git` `#zlib` `#Pull Request` `#file` `#PR`

## ⏰ 학습 계획

- 루카스 배경지식 학습하기
- 버전 관리 시스템에 대해 알아보기
- git 관련 용어 학습하기
- 좋은 커밋 메세지 작성하기 위한 요령

## 📚 학습 정리

### 1) git 관련 용어 정리

**(1) 저장소 관련 용어**

- **local:** git 저장소는 사용자의 컴퓨터인 로컬 저장소(local repository) 와 원격 저장소(remote repository)로 나뉜다. 로컬 저장소는 내 PC에 파일이 저장되는 개인 전용 저장소이다.
- **remote:** 원격 저장소는 파일이 원격 저장소 전용 서버에서 관리가 되며, 여러 사람들이 함께 공유하고 작업하기 위해 사용하는 저장소이다.
- **init:** `git init` 명령은 프로젝트의 디렉토리에 `.git`이라는 하위 디렉토리를 만든다. 이 `.git`디렉토리에는 저장소에 필요한 뼈대(skeleton)가 되는 파일들이 들어가 있다. 그 중 중요한 항목들로는 현재 checkout한 브랜치를 가리키는 `HEAD`파일, staging area의 정보를 저장하는 `index`파일, 모든 컨텐츠를 저장하는 데이터베이스 역할을 하는 `objects` 디렉토리, 커밋 객체의 포인터를 저장하는 `refs` 디렉토리가 있다.
- **clone:** 다른 프로젝트에 참여하거나 Git 저장소를 복사하고 싶을 때 `git clone`을 사용한다. 이 명령어는 저장소에 있는 거의 모든 데이터를 복사해온다. 프로젝트 히스토리도 전부 받아온다. Git은 https:// 프로토콜 뿐 아니라 SSH 프로토콜와 같은 다양한 프로토콜을 지원한다.

**(2) 상태 관리 용어**

Git은 파일을 **Committed**, **Modified**, **Staged** 이렇게 3가지 상태로 관리한다. **Committed**는 데이터가 로컬 DB에 안전하게 저장됐다는 것을 의미한다. **Modified**는 수정한 파일을 아직 로컬 DB에 커밋하지 않은 것을 의미한다. 그리고 **Staged**는 현재 파일을 곧 커밋할 예정이라고 표시한 상태이다.

이러한 세 가지 상태는 git 프로젝트의 세 가지 단계인 워킹 트리, staging area, git directory와 연결되어 있다.

![git_3stages](https://user-images.githubusercontent.com/76121068/221580936-f441edae-da55-4798-975d-0bdb6322e3f1.png)

- **Working directory**

  작업 디렉토리(Working directory)는 워킹 트리(Working Tree)라고도 부르며 현재 프로젝트 디렉토리 내 존재하는 파일들 그 자체를 의미한다.

- **Staging area(Index)**

  개념적으로는 커밋이 이뤄질 준비가 된 파일의 내용들이 위치하는 영역을 의미하며, 실제로는 하나의 파일(.git/index)로 존재한다. 로컬에 변동사항이 생겼을 때 `git add`명령어로 해당 변동사항을 인덱스 영역에 반영시킬 수 있다.
  인덱스 파일에는 커밋이 이뤄질 준비가 된 파일의 내용들 각각에 대해서 그 파일명과 해당 파일의 내용을 담은 Blob 파일의 참고(주소)가 기록된다.

- **Git repository**

  Git이 버전 관리를 하기 위해서 필요로 하는 데이터들을 저장하는 곳이다. 기본적으로 버전 관리를 시작한 시점부터 현재 시점까지의 여러 버전들에 해당하는 파일들의 내용이 Blob 파일로서 이곳에 저장되어 있다. 이곳에 저장된 파일들을 특별히 오브젝트 파일이라 부르며, Blob 파일도 오브젝트 파일의 한 종류이다. 실제로 프로젝트 폴더 하위에 있는 .git/objects/ 폴더에 위치한다.

**(3) 파일 관련 용어**

![git_lifecycle](https://user-images.githubusercontent.com/76121068/221581570-1894b691-09b6-42c0-adcc-810c83639caf.png)

- **Untracked:** 워킹 디렉토리에 존재하지만 git의 관리대상이 아닌 파일로 스냅샷과 Staging area에도 포함되지 않은 파일이다. 처음 저장소를 clone한 이후에 만들어진 파일이나 마지막 커밋 이후 새로 만들어진 파일들이 여기에 해당한다.
- **Unmodified:** Git으로 관리되고 있는 Tracked 파일 중 수정되지 않은 파일 상태이다. 한 번 이상 commit 된 파일 중 수정하지 않은 파일 또는 다른 저장소의 파일들을 clone 해왔을 때의 파일들이 여기에 해당한다.
- **Modified:** Git으로 관리되고 있는 Tracked 파일 중 수정을 한 파일 상태이다. Unmodified 상태의 파일을 수정하면 Modified 상태가 된다. `git status`명령을 실행했을 때 “Changes not staged for commit:” 설명 뒤에 나오는 파일들이 이에 해당한다.
- **Staged:** commit 할 준비가 된 파일의 상태이다. Staging area 영역에 있는 파일의 상태이며, Untracked 상태의 파일이나 Modified 상태의 파일들을 `git add` 명령을 통해 staged 상태로 만들 수 있다. `git status`명령을 실행했을 때 “Changes to be committed:” 설명 뒤에 나오는 파일들이 이에 해당한다.

**(4) Git의 객체들(Objects)**

- **Commit:** Git에서 가장 중요한 객체로 이 파일에는 부모 커밋에 대한 참조, 커밋 메세지, 가리키고 있는 트리(tree) 파일의 참조 등이 기록된다. 하나의 버전을 생성한다는 것은 하나의 commit 파일을 만드는 것이다.
- **Tree:** Tree파일에는 커밋 시점의 파일들 각각에 대해서 그 파일명과 해당 파일의 내용을 담고 있는 Blob 파일의 참조가 기록된다. 커밋 당 하나 이상의 트리를 포함한다. 파일 시스템의 디렉토리와 유사하다.
- **Blob:** Binary Large Object, 버전 관리하는 파일들 각각의 내용들은 git 저장소에서 Blob 파일 형태로 저장된다. 파일 내용에 SHA-1 체크섬을 이용해 Blob 파일의 이름을 얻어내기 때문에, 내용이 같은 파일들 모두 하나의 Blob 파일로 저장된다.
- Tag → 현재는 많이 중요하지 않으므로 skip

(5) Git 브랜치

### 2) Git의 무결성과 SHA-1

Git에서 commit을 하다가 log를 출력해보면 `6765184f655929c48ff8e5332b914a42b4b1ad93`처럼 이상한 문자열이 자주 보인다. 이것은 SHA-1 알고리즘으로 생성된 hash 값인데 각 commit마다 고유한 id로 이 hash값을 사용한다.

이렇게 중복없는 임의의 문자열을 commit id로 할당하면 이전 commit id가 어떤 것인지 서버로부터 최신번호 확인이 필요 없어지므로 온/오프라인 구분없이 어떤 환경에서도 commit이 가능해진다.

하지만 단순한 hash 값만으로는 commit 순서를 기억할 수 없다. 따라서 git의 commit 객체에는 부모 커밋에 대한 참조가 함께 저장된다. 따라서 이전에 어떤 커밋이 있는지 확인이 가능하고 순서를 기억할 수 있게 된다.

Git 공식 문서에는 다음과 같은 설명이 나와있다.

**Git의 무결성**

> Git은 데이터를 저장하기 전에 항상 체크섬(checksum)을 구하고 그 체크섬으로 데이터를 관리한다.
> 그래서 체크섬을 이해하는 역할을 하는 Git 없이는 어떠한 파일이나 디렉토리도 변경할 수 없다.
> 체크섬은 Git에서 사용하는 가장 기본적인(Atomic) 데이터 단위이자 Git의 기본 철학이다.

Git 없이는 체크섬을 다룰 수 없어서 파일의 상태도 알 수 없고 심지어 데이터를 잃어버릴 수도 없다.
Git은 SHA-1 해시(Hash)를 사용하여 체크섬을 만든다. 만들어진 체크섬은 40자 길이의 16진수 문자열이다. 파일의 내용이나 디렉토리 구조를 이용하여 체크섬을 구한다.

SHA-1은 다음과 같이 생겼다. `24b9da6552252987aa493b52f8696cd6d3b00373`

> Git은 모든 것을 해시로 식별하기 때문에 이런 값은 여기저기서 보인다.
> 실제로 Git은 파일을 이름으로 저장하지 않고 해당 파일의 해시로 저장한다.

- **체크섬(Checksum)이란?** 체크섬은 CRC32, MD5, SHA-1 등 데이터의 암호 해독이 필요없는 단방향 해시 알고리즘에서 반환되는 값이다. 데이터를 수정하면 반환되는 값이 변경되기 때문에 데이터 무결성을 검증하는 데 사용할 수 있습니다. 이러한 이유로, 체크섬은 때때로 fingerprint(지문)이라고 불린다.
- **SHA-1:** SHA-1은 미국 국가안보국(NSA)에 의해 설계된 해시 함수이다. 그것은 40자 길이의 160비트 16진수 문자열을 반환한다. 1996년부터 2010년까지 주로 MD5를 대체하기 위해 보안 암호화에 사용되었지만, 현재는 대부분 데이터 무결성을 위해 사용된다.
- **SHA-1 알고리즘으로 만들어진 hash값은 정말 중복이 없는가?** 실제로 중복이 발생할 확률은 1 / 2^80 이라고 한다. 한 마디로 거의 없다고 보면 된다.

### 3) Git 명령어들

제대로 사용법과 의미를 몰랐었던 명령어들 위주로만 간략히 정리하였다.

- `stash`
- `diff`
- `reset`
- `rebase`
- `branch`
- `fetch`
- `merge`
- `switch`
- `restore`

### 4) PR 운영 방식

## 🗂️ 좋은 CL 설명문 작성법

CL 설명문이란 코드에 어떤 변화가 있고 왜 그런 변화가 필요한지 설명하는 기록물이다. 좋은 CL문 작성은 언제나 중요하다. 항상 작성 전에 1분 정도 고민하고 작성하는 습관을 들이자. 미래의 나와 나의 코드를 볼 다른 많은 개발자들을 위해서 제대로 작성하자!

> `CL`: ‘Change List’의 약어, 소스 코드 관리에 반영되었거나 리뷰를 받고 있는 코드 집합체 단위

### 1) 첫 줄

- 무엇을 했는지를 간략히 설명한다.
- 명령문 형태의 완전한 문장으로 작성한다.
- 첫 줄 이후에는 줄바꿈을 한 번 한다.

CL 설명문의 첫 줄은 전체 내용의 요약이 되므로 최대한 간결하고 명확하게 작성되어야 한다. 나중에 코드 이력을 찾아보는 개발자가 한 눈에 알아볼 수 있게 작성되어야 한다.

### 2) 본문에는 유용한 정보를 담는다.

**“어떤”** 문제를 **“어떻게”** 해결했는지를 적고 **“왜”** 이것이 최선의 방법인지 설명을 적는다. 그리고 코드에 대한 설명도 적고 배경설명과 추후 발전 방향도 포함될 수 있다. 해결책에 기술적인 결점이 있다면 이 또한 포함한다. 이슈 트래킹 넘버나 참고 문서를 덧붙여도 좋다. 작고 사소한 CL이라도 배경 설명을 꼭 한다.

### 안 좋은 CL 설명문 예시

“Fix bug”는 설명문으로는 매우 빈약하다. 어떤 버그인가? 버그를 고치기 위해 어떤 일을 했는가?

> 아래와 같은 설명문은 모두 안 좋은 예시이다.
>
> - “Fix build”
> - “Add patch.”
> - “Moving code from A to B.”
> - “Add convenience functions.”

짧고 디테일하지 않은 설명문은 유용한 정보를 제공해주지 못한다.

### 좋은 CL 설명문 예시

### Functionality change

예시:

> rpc: remove size limit on RPC server message freelist.
>
> Servers like FizzBuzz have very large messages and would benefit from reuse.
> Make the freelist larger, and add a goroutine that frees the freelist entries
> slowly over time, so that idle servers eventually release all freelist
> entries.

처음 몇 개의 단어들에는 CL이 무엇을 하는지 적혀있다. 나머지 설명에는 그 문제를 어떻게 해결했는지, 왜 이것이 좋은 해결책인지, 그리고 구체적인 실행에 대한 추가 정보가 적혀있다.

### 3) CL을 제출하기 전에 설명문 검토하기

코드 리뷰를 거치면서 꽤 많은 것이 바뀌었을 수도 있다. 최종적으로 코드를 머지하기 전 설명문에 변경 사항이 반영되어 있는지 꼭 확인한다.

### 4) 작게 쪼개기

- 작다는 것의 기준은 무엇인가?

  일반적으로 하나의 CL에는 **하나의 독립적인 변화**가 있어야만 한다.

  - 최소한으로 단 하나만 포함시킨다. 기능(feature) 중 일부만을 하나의 CL로 만든다. 하지만 리뷰어가 알아야하는 모든 정보를 담는다. CL이 머지된 후에도 프로그램은 잘 돌아가야한다.

## 💻 미션. mit 명령어

### 🥲 미션 수행과정

### 1단계. list 명령어

- 콘솔에서 다음과 같은 mit 명령어를 실행했을 때 아래 기능을 하는 프로그램을 구현한다.
  - 해당 디렉토리에서 전체 파일 목록을 탐색하고 파일명을 출력한다.
  - 각 파일의 파일 크기도 함께 출력한다.
  - 명령 형식은 아래와 같다.

```jsx
mit list /Work/Masters/

// 1.js(34KB) 2.js(13KB) 3.js(5KB)
```

- 우선 특정 디렉토리 내 파일을 모두 읽어오는 방법을 찾아본다 → nodejs ‘fs’ 모듈에 있는 `readdir` 메소드를 이용하니 지정한 경로에 있는 디렉토리의 모든 파일명들을 출력할 수 있었다.
- 내가 원하는 디렉토리 경로를 설정할 수 있는 방법을 찾아본다.

## 📜 참고자료

- [https://soojin.ro/review/cl-descriptions](https://soojin.ro/review/cl-descriptions) (좋은 CL 설명문 작성법)
- [http://rogerdudler.github.io/git-guide/index.ko.html](http://rogerdudler.github.io/git-guide/index.ko.html) (git 간편 안내서)
- [https://git-scm.com/book/ko/v2](https://git-scm.com/book/ko/v2) (Pro Git 2nd edition)
- [https://opentutorials.org/course/3838](https://opentutorials.org/course/3838) (생활코딩 - GITn)
- [https://it-eldorado.tistory.com/4](https://it-eldorado.tistory.com/4) (Git 내부 동작원리 이해)
- [https://blog.outsider.ne.kr/1505](https://blog.outsider.ne.kr/1505) (checkout vs switch/restore)
- [https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tkdldjs35&logNo=221920644169](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tkdldjs35&logNo=221920644169) (git 기초명령어와 branch)
- [https://guides.codepath.com/websecurity/Cryptographic-Hash-Algorithms](https://guides.codepath.com/websecurity/Cryptographic-Hash-Algorithms) (SHA-1)
- [https://antilog.tistory.com/8](https://antilog.tistory.com/8) (git commit id)
