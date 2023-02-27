> 본 글은 [git 공식 문서](https://git-scm.com/book/en/v2)의 내용을 정리한 글입니다.

# common-mit
CS16 공통 프로젝트 저장소

## git 의 기초

<details>
<summary>되돌기기</summary>

일을 하다보면 모든 단계에서 어떤 것을 되돌리고(Undo) 싶을 때가 있다.  
아래에서 살펴볼 방법들은 한번 실행하면 되돌릴 수 없다.  

### 커밋을 수정하고 싶을 때

다시 커밋하고 싶으면 파일 수정 작업을 하고, Staging Area에 추가한 다음 `--amend` 옵션을 사용하여 커밋을 재작성 할 수 있다.

```text
$ git commit -m 'initial commit'
$ git add forgotten_file
$ git commit --amend
```

여기서 실행한 3개의 명령어는 모두 커밋 한 개로 기록된다. 두 번째 커밋은 첫 번째 커밋을 덮어쓴다. 

<br>

### 파일 상태를 Unstage로 변경하기

`git add `명령어를 통해 Stage Area 에 올린 파일을다시 Unstage 로 변경하는 명령어는 다음과 같다.

```text
$ git reset HEAD <fild>
```

어떤 파일을 reset할 수 있는지는 `git satus` 명령을 통해 확인할 수 있다.

```text
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage) // 친절하게 git에서 알려준다.

    renamed:    README.md -> README
    modified:   CONTRIBUTING.md
```

<br>

### Modified 파일 되돌리기

어떠한 파일을 수정했는데, 마음에 들지 않아서 다시 커밋 시점의 파일로 되돌리려 한다. 그때, 아래의 명령어를 사용할 수 있다.

```text
$ git checkout -- <file>
```

이 또한 `git status` 명령어로 확인할 수 있다.

```text
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

    modified:   CONTRIBUTING.md
```

<br>

> 중요
> 위의 명령어는 위험한 명령어들이다. 원래 파일로 덮어 썼기 때문에 수정한 내용은 전부 사라진다.
> 수정한 내용이 진짜 마음에 들지 않을 때만 사용하자.

</details>

<details>
<summary>Git Alias</summary>
명령을 완벽하게 입력하지 않으면 git 은 알아듣지 못한다. git 의 명령을 전부 입력하는 것이 귀찮다면 `git config`를 사용하여 각 명령의 Alias 을 쉽게 만들 수 있다.

```text
$ git config --global alias.co checkout
$ git config --global alias.br branch
$ git config --global alias.ci commit
$ git config --global alias.st status
```

위의 설정을 통해, `git commit`대신 `git ci`만으로도 커밋을 할 수 있다.

<br>

이미 있는 명령을 새로운 명령으로 만들어 사용할 수도 있다.

```text
$ git config --global alias.unstage 'reset HEAD --'
```

아래 두 명령은 동일한 명령이다.

```text
$ git unstage fileA
$ git reset HEAD -- fileA
```

<br>

그리고 git의 명령어뿐만 아니라 외부 명령어도 실행할 수 있다. `!`를 제일 앞에 추가하면 외부 명령을 실행한다.  
커스텀 스크립트를 만들어서 사용할 때 매우 유용하다. 아래 명령은 git visual 이라고 입력하면 gitk 가 실행된다.  

```text
$ git config --global alias.visual '!gitk'
```

</details>

<details>
<summary>리모트 저장소</summary>

리모트 저장소란 인터넷이나 네트워크 어딘가에 있는 저장소를 말한다.
`git remote` 명령으로 현재 프로젝트에 등록된 리모트 저장소를 확인할 수 있다.

```text
$ git clone https://github.com/schacon/ticgit
Cloning into 'ticgit'...
remote: Reusing existing pack: 1857, done.
remote: Total 1857 (delta 0), reused 0 (delta 0)
Receiving objects: 100% (1857/1857), 374.35 KiB | 268.00 KiB/s, done.
Resolving deltas: 100% (772/772), done.
Checking connectivity... done.
$ cd ticgit
$ git remote
origin
```
> 저장소를 `Clone` 하면 `origin`이라는 이름으로 리모트 저장소가 자동으로 등록된다.
<br>
`-v` 옵션을 주어 단축이름과 URL을 함께 볼 수 있다.

```text
$ git remote -v
origin	https://github.com/schacon/ticgit (fetch)
origin	https://github.com/schacon/ticgit (push)
```

<br>

### 리모트 저장소 추가하기

`git remote add <단축이름> <url>` 명령을 사용하여, 기존 워킹 디렉토리에 새 리모트 저장소를 쉽게 추가할 수 있다.

```text
$ git remote
origin
$ git remote add pb https://github.com/paulboone/ticgit # URL을 pb 라는 이름으로 사용함을 명시
$ git remote -v
origin	https://github.com/schacon/ticgit (fetch)
origin	https://github.com/schacon/ticgit (push)
pb	https://github.com/paulboone/ticgit (fetch)
pb	https://github.com/paulboone/ticgit (push)
```

<br>

### 리모트 저장소를 Pull 하거나 Fetch 하기

```text
$ git fetch <remote>  # 리모트 저장소의 데이터를 모두 로컬로 가져온다.
$ git pull <remote>   # fetch 후 자동으로 merge 한다.
```
<br>

### 리모트 저장소에 Push 하기[README.md](README.md)

`git push <리모트 저장소 이름> <브랜치 이름>` 명령어로, 프로젝트를 공유할 수 있다.<br>
이 명령어는 Clone 한 리모트 저장소에 `쓰기 권한`이 있고, Clone 하고 난 이후 아무도 원격 저장소에 `Push 하지 않았을 때`만 사용할 수 있다.<br>

<br>

### 리모트 저장소 살펴보기

`git remote show <리모트 저장소 이름>` 명령으로 리모트 저장소의 구체적인 정보를 확인할 수 있다.

```text
$ git remote show origin
* remote origin
  Fetch URL: https://github.com/schacon/ticgit
  Push  URL: https://github.com/schacon/ticgit
  HEAD branch: master
  Remote branches:
    master                               tracked
    dev-branch                           tracked
  Local branch configured for 'git pull':
    master merges with remote master
  Local ref configured for 'git push':
    master pushes to master (up to date)
```

<br>

### 리모트 저장소 이름을 바꾸거나 리모트 저장소 삭제하기

`git remote rename` 명령으로 리모트 저장소의 이름을 변경할 수 있다.

```text
$ git remote rename pb paul
```

</details>


## Git 브랜치

<details>
<summary>브랜치란 무엇인가</summary>

### 브랜치란 무엇인가

Git의 브랜치는 `커밋(커밋 객체) 사이를 가볍게 이동할 수 있는 어떤 포인터` 같은 것이다.   
기본적으로 Git은 master 브랜치를 만든다. 처음 커밋하면 이 master 브랜치가 생성된 커밋을 가리킨다.  
이후 커밋을 만들면 master 브랜치는 자동으로 가장 마지막 커밋을 가리킨다.

>Git 버전 관리 시스템에서 “master” 브랜치는 특별하지 않다. 다른 브랜치와 다른 것이 없다.   
> 다만 모든 저장소에서 “master” 브랜치가 존재하는 이유는 git init 명령으로 초기화할 때 자동으로 만들어진 이 브랜치를 애써 다른 이름으로 변경하지 않기 때문이다.

<br>

### 커밋 객체란 무엇인가

Git에서 `커밋하면` 아래와 같은 정보를 포함하는 `커밋 개체(커밋 Object)`가 저장된다.
- 현 Staging Area 에 있는 데이터의 `스냅샷에 대한 포인터`
- 저자나 커밋 메세지 같은 메타데이터
- 이전 커밋에 대한 포인터

이전 커밋 포인터가 있어서 현재 커밋이 무엇을 기준으로 바뀌었는지를 알 수 있다.

<br>

### 스냅샷이란 무엇인가
파일을 복사하는 방식으로 수정본을 관리하면 같은 내용을 반복해서 저장하기 때문에, 많은 용량을 차지한다.  
또 수정된 부분들을 일일이 찾아야 하기 때문에 검색할 때도 매우 불편하다.  
git 은 이러한 시스템적인 단점을 해결하려고 변경된 파일 전체를 저장하지 않고, 파일에서 변경된 부분을 찾아 수정된 내용만 저장한다.  
마치 변화된 부분만 찾아 사진을 찍는 것과 같다고 하여 스냅샷 방식이라고 한다.

> git의 스냅샷은 HEAD 가 가리키는 커밋을 기반으로 사진을 찍는다.  
> 그리고 이를 스테이지 영역과 비교하여 새로운 커밋으로 기록한다.
> 이처럼, git 은 스냅샷 방식을 이용하여 빠르게 버전의 차이점을 처리하고, 용량을 적게 사용한다.

<br>

### 브랜치에서 HEAD란 무엇인가

Git은 지금 작업 중인 브랜치가 무엇인지 파악하기 위해, `HEAD`라는 특수한 **포인터**를 사용한다.  
이 포인터는 지금 작업하는 로컬 브랜치를 가리킨다.  

</details>

<details>
<summary>브랜치 관련 옵션들</summary>

`git branch` 명령은 단순히 브랜치를 만들고 삭제하는 것이 아니다. 브랜치를 관리하는데 필요한 옵션들은 아래와 같다.

```text
$ git branch                // 브랜치의 목록을 보여줌
$ git branch -v             // 브랜치마다 마지막 커밋 메시지를 보여줌
$ git branch --merged       // Merge 된 브랜치를 보여줌 ( 이미 merge 된 브랜치이기 때문에 삭제해도 되는 브랜치이다)
$ git branch --no-merged    // Merge 되지 않은 브랜치를 보여줌
$ git branch -d <브랜치 이름>  // 브랜치를 삭제함
```

</details>

<details>
<summary>브랜치 워크플로</summary>


</details>