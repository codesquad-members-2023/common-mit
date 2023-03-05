# 원격저장소

지역 저장소 : 자신이 commit하는 저장소  
원격 저장소 : 지역 저장소와 연결되어서 동기화 되는 저장소(원격)

## local에 가상의 원격저장소를 생성하고 동기화 하기

### 가상의 원격 저장소 생성

work space가 없는 작업이 불가능한 저장소 bare를 생성한다.

```
git init --bare "저장소 이름"
```

### 지역 저장소와 원격(가상) 저장소 연결

지역 저장소에서 아래 명령어 입력

```
git remote add "원격저장소 명칭" "원격 저장소 루트"
git remote add origin /Users/sarang_daddy/코드스쿼드/CS16/hub/remote

"원격저장소 명칭"은 통상 origin을 사용한다. (메인으로 동기화 하는 원격저장소)

git remote -v // 저장소 확인
```

![](https://velog.velcdn.com/images/sarang_daddy/post/17babd10-2d96-4e17-a09f-d380a299fe87/image.png)

### 자동으로 원격 저장소에 동기화 하기 (push)

```
git push --set-upstream origin master
// push하면 원격 저장소에 자동으로 push(동기화)한다는 명령어
```

![](https://velog.velcdn.com/images/sarang_daddy/post/3c841e3d-d056-4c6a-9b8a-034af924a7c7/image.png)

</br>

## GitHub에 원격저장소를 생성하고 동기화(백업) 하기

### GitHub에서 새로운 repository(원격 저장소)를 만든다.

![](https://velog.velcdn.com/images/sarang_daddy/post/1e490a78-595d-48dd-8ae7-476b6c2cacb7/image.png)

### 로컬 저장소에 원격 저장소를 연결(add) 시킨다.

```
git remote add "원격 저장소 명칭" "원격 저장소 주소"
git remote add origin https://github.com/sarangdaddy/fthgit_test.git

git remote -v // 연결된 원격저장소 확인
```

![](https://velog.velcdn.com/images/sarang_daddy/post/7af8e908-88e1-42a7-aed3-a5369f49fa28/image.png)

### 로컬 저장소 내용을 원격 저장소로 보내기. (백업)

```
git push -u origin master

// origin 주소(원격 저장소)의 main 브렌치로 로컬 저장소 내용을 보낸다.
// -u는 한번만 쓰면 된다. 로컬 저장소 브렌치와 마스터 브렌치를 연결 시킨다는 의미

이후 부터는 git push만 입력하면 push된다.
```

```
깃허브의 기본 브렌치명이 main으로 바뀌었기 때문에
아래 처럼 원격 저장소에 연결하려는 브렌치명을 main으로 변경후 연결해준다.

git branch -M main
git push -u origin main
```

![](https://velog.velcdn.com/images/sarang_daddy/post/9cb30593-e240-4248-866f-9496f8717d63/image.png)

</br>

## GitHub 원격 저장소에 있는 프로젝트를 로컬 저장소로 가져오기

### GitHub 원격저장소 주소를 가져온다.

```
git clone "원격 저장소 주소" "새로운 디렉토리 이름"
git clone https://github.com/sarangdaddy/fthgit_test.git .
(. 은 원격저장소 디렉토리 이름 그대로 사용)
```

![](https://velog.velcdn.com/images/sarang_daddy/post/df45dcf9-4caf-4164-b184-dd24b480f4cd/image.png)

### GitHub 원격저장소에 push된 내용을 로컬저장소로 가져오기 (Pull)

두가지 이상의 로컬저장소 데이터를 하나의 원격 저장소(repo)에 push하는 경우,

- 두명 이상의 사용자가 하나의 원격 저장소를 사용하는 경우
- 한명의 사용자가 집, 사무실 등 다른 로컬 저장소에서 동일 프로젝트를 진행하는 경우

A로컬에서 작업 후 원격저장소에 push하면 같이 사용하는 원격 저장소는 A로컬과 같은 상태지만  
B로컬은 A로컬과 원격 저장소와 다른 상태로 유지되고 있다. (새로운 업데이트가 안되있음)  
이럴 때는 B로컬에서 원격저장소를 pull하여 최신 상태를 가져올 수 있다.

```
git pull

여러 컴퓨터는 사용하더라도 항상 같은 서버상태와 코드를 가지고 작업이 가능하다.
항상 새로운 작업을 시작하기 전에 pull을 하도록 한다.
```

</br>
