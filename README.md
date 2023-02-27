# common-mit
CS16 공통 프로젝트 저장소

# git의 목표
버전관리
백업
협업

# 용어
commit : 버전을 생성한다 

# head 
![head](https://user-images.githubusercontent.com/86706366/221490954-94a9747f-d13e-40a3-a6d5-340446c8417b.png)
- 깃 그래프 상에서는 O가 헤드를 상징한다

head 는 
working directory와 stage area가 어떤 버전이 만들어진 시점의 stage area와 같은지 알려준다

헤드를 이동시키고 싶은 커밋 아이디로 옮기면 해당 버전으로 되돌아갈 수 있다
git checkout : 커밋아이디에 해당하는 파일의 이름과 내용을 stage area 와 working directory 에 올려줌

브랜치 는 
마지막 버전을 가리킴

따라서 git checkout 브랜치 으로 헤드가 가리키는 것을 마지막 버전(현재)로 돌려주면 돌아옴



## 저장소 관련
remote	
local	
init	
clone	

## 상태 관리
git repository	
staging area	
working directory	

## 파일 관련
Untracked	
Unmodified	
Modified	
Staged	
