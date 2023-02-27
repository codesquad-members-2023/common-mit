# common-mit
CS16 공통 프로젝트 저장소

# git의 목표
버전관리
백업
협업

# 용어

# commit
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

![image](https://user-images.githubusercontent.com/86706366/221496742-31aaa0d3-0ba7-4f5e-ae61-e14a7ccdf3b5.png)

![지역저장소 브랜치와 원격저장소 브랜치](https://user-images.githubusercontent.com/86706366/221497004-1325a55a-5920-46ee-8adf-b7142b1a5d59.png)
- head가 가리키는 feature1 은 지역저장소 브랜치
- origin/feature1 은 원격저장소 브랜치
- 동기화 되기 전의 상태 
![동기화 후](https://user-images.githubusercontent.com/86706366/221497638-d05aa6c7-e703-479b-854a-353f06976e98.png)
- 동기화 이후의 상태
# pull
fetch + merge

fetch : 원격저장소에서 읽어옴
merge : 병합하다

![pull](https://user-images.githubusercontent.com/86706366/221501427-054cd4e3-a21f-4d50-86b5-98169ec66530.png)
- home까지는 같은 작업을 했고 
- 원격 저장소는 L1이 올라가 있으며 내 저장소는 원격 저장소와 동기화되지 않은 채 R1이 생성돼있다
![merge into](https://user-images.githubusercontent.com/86706366/221502076-14fa1b6f-4bcf-4546-af02-f8ede4936557.png)
- merge into current branch : 원격저장소를 내 로컬 현재 브랜치로 병합한다

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
