> 본 글은 (git 공식 문서)[https://git-scm.com/book/en/v2]의 내용을 정리한 글입니다.

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

> 중요
> 위의 명령어는 위험한 명령어들이다. 원래 파일로 덮어 썼기 때문에 수정한 내용은 전부 사라진다.
> 수정한 내용이 진짜 마음에 들지 않을 때만 사용하자.

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

### 리모트 저장소에 Push 하기

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
