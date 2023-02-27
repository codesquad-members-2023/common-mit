> 본 글은 (git 공식 문서)[https://git-scm.com/book/en/v2]의 내용을 정리한 글입니다.

# common-mit
CS16 공통 프로젝트 저장소

# 용어정리

## 저장소 관련

<details>
<summary>remote : 인터넷이나 네트워크 어딘가에 있는 저장소를 말한다.</summary>

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

### 리모트 저장소를 Pull 하거나 Fetch 하기

```text
$ git fetch <remote>  # 리모트 저장소의 데이터를 모두 로컬로 가져온다.
$ git pull <remote>   # fetch 후 자동으로 merge 한다.
```

### 리모트 저장소에 Push 하기

`git push <리모트 저장소 이름> <브랜치 이름>` 명령어로, 프로젝트를 공유할 수 있다.
이 명령어는 Clone 한 리모트 저장소에 `쓰기 권한`이 있고, Clone 하고 난 이후 아무도 원격 저장소에 `Push 하지 않았을 때`만 사용할 수 있다.

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

### 리모트 저장소 이름을 바꾸거나 리모트 저장소 삭제하기

`git remote rename` 명령으로 리모트 저장소의 이름을 변경할 수 있다.

```text
$ git remote rename pb paul
```

</details>
