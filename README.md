# common-mit
CS16 공통 프로젝트 저장소

# git의 목표
버전관리
백업
협업

# 용어

# branch
작업을 할 때, 메인 프로젝트 브랜치로부터 추가 가지를 뻗어 작업이 마무리 되면 메인 브랜치에 병합한다(merge)

    git branch  (로컬 브랜치 목록 조회)
    git branch -r  (원격 브랜치 목록 조회)
    git branch -a  (모든 브랜치 목록 조회)

git branch {새로운 브랜치 이름}
- {새로운 브랜치 이름}의 브랜치를 새로 생성

git branch -d {삭제할 브랜치 이름}
- 해당 브랜치를 삭제할 수 있다

git checkout {옮겨갈 브랜치 이름}
- 현재 위치한 브랜치에서 다른 브랜치로 위치를 옮길 때 사용하며 옮겨갈 브랜치는 이미 존재해야 한다

git checkout -b {새로운 브랜치 이름}
- git branch + git checkout 의 수행을 합친 명령어
- 옮겨갈 브랜치를 생성함과 동시에 이동

git stash (추후에 다시 찾아보기)
- 진행중인 작업을 임시로 저장해두고 싶을 때 사용하는 명령어
- 아직 완료하지 않은 일을 다른 공간에 저장해두고 나중에 다시 git stash pop 으로 꺼내와서 작업을 마무리 할 수 있다

git cherry-pick {커밋 해시 넘버}
- A브랜치에서 원하는 커밋을 복사해 B 브랜치에 새로운 커밋으로 생성할 수 있다
  - 잘라낸 것이 아니라 복사한 커밋이기 때문에 복사 후 옮겨진 커밋의 해시 넘버는 기존 커밋과 다르다
- B 브랜치의 위치에서 git cherry-pick {A브랜치에 속한 커밋 해시 넘버}를 실행해야한다
- cherry-pick을 사용한 경우 기존 A 브랜치에서 복사된 커밋들은 (더이상 필요가 없어졌으니) git reset --hard 해줘야 한다
- 필요한 커밋만 쏙 복사해오기 때문에 cherry-pick 이라는 이름


# 되돌리기
git reset --hard

# commit
commit : 버전을 생성한다 
- 업데이트를 확정한다는 의미
- 스냅샷처럼 확정된 순간의 코드 상태를 커밋 메세지와 함께 저장소(Repository)에 저장한다

    commit = hash + message + author + 코드 스냅샷   

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
- remote : 파일이 원격 저장소 전용 서버에 관리
  - git에서의 remote 는 외부의 깃헙 저장소가 있는 url에 대한 alias(별칭)을 만들어 관리하기 위한 명령어이다 
  - 이 명령어는 단순히 GitHub만을 위한 것이 아니며, GitLab, Garrit 과 같은 외부 저장소를 다루기 위해서 사용한다
- local	: 내 PC에 파일이 저장되는 개인 전용 저장소
- init : git init은 Git 저장소를 새로 만드는 명령
  - 버전 관리를하지 않은 기존 프로젝트를 Git 저장소로 변환하거나 새로운 빈 저장소를 생성하고 초기화하는 경우에 사용
  - git init 명령을 실행하면 저장소에 관련된 모든 메타 데이터를 갖는 .git 하위 디렉토리가 프로젝트의 루트에 생성

- clone	: Clone은 깃허브 Repository에 있는 파일을 내 로컬 컴퓨터로 복사해오는 작업
  - git clone은 아래 코드들이 함축되어 있다
    
    $ git init (로컬 저장소 생성하기)
    $ git remote add (원격 저장소 url 복사해서 생성한 로컬 저장소에 등록하기)
    $ git fetch (원격 저장소에 있는 모든 branch들 로컬 저장소에 등록하기)
    $ git checkout (main/master브랜치로 전환하기)



### 베어 저장소(bare repository) * 추가 서치 필요
--bare 플래그를 지정하면 작업 디렉토리가 없는 저장소가 생성되고 그 저장소에서 파일을 편집하거나 변경을 적용 할 수 없다

bare의 지정은 그 저장소를 개발 환경으로써가 아닌 저장용 공간으로 인식시키는 방법이라고 생각하면 된다

즉, 실질적으로 모든 Git 작업 플로우에서 중앙 저장소는 베어 저장소이며, 개발자의 로컬 저장소는 베어 저장소가 아니다



### remote 

- git remote : alias 목록을 가져온다
- git remote -v : alias와 alias가 연결된 url도 같이 표시

git remote -v 사용해 alias와 url 모두  가져오기

git remote -v를 사용하면 alias와 url을 모두 가져올 수 있다 
-v는 verbose의 약자로 "말수가 많은" 이라는 뜻을 가진다
뜻만큼 "모든 내용"을 출력한다.

    $ git remote -v
    origin	https://github.com/simplistudio/demo-github.git (fetch)
    origin	https://github.com/simplistudio/demo-github.git (push)
    origin-ssh	git@github.com:simplistudio/demo-github.git (fetch)
    origin-ssh	git@github.com:simplistudio/demo-github.git (push)

[remote](https://kotlinworld.com/284#git%--remote%---v%--%EC%--%AC%EC%-A%A-%ED%--%B-%--alias%EC%--%--%--url%--%EB%AA%A-%EB%--%--%C-%A-%--%EA%B-%--%EC%A-%B-%EC%--%A-%EA%B-%B-)

## 상태 관리
- git repository	
- staging area: working directory에 있는 file을 git add 하게 되면, 파일은 Staged(커밋 준비 상태)가 되고 이때 Staged file 은 staging area에 위치하게 된다
  - 커밋을 준비 (to be committed)하는 위치

- working directory(워킹 디렉토리): Unstaged 상태인 Modified file (수정된 파일)들이 있는 디렉토리

## 파일 관련 

워킹 디렉토리에 있는 파일들에 대한 깃의 추적 여부

- Untracked(관리대상 X) : Git 저장소에는 있으나 현재 Git에 의해 관리되지 않고 있는 파일 - git이 신경쓰고 있지 않기 때문에 git으로 복구하는 것이 불가능
- Tracked File(관리대상 O, 깃이 알고 있음) : Git에서 관리해주는 파일을 의미
  - Unmodified : 파일이 수정되지 않은 상태(모든 파일의 상태가 Unmodified = working tree clean)
  - Modified : 파일이 수정된 상태
  - Staged : 파일이 저장할 예정인 상태 (커밋으로 저장소에 기록할, add 명령으로 커밋에 추가될 staged상태로 만들 수 있다)

git add 명령으로 파일을 새로 추적할 수 있다

[git문서](https://git-scm.com/book/ko/v2/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-%EC%88%98%EC%A0%95%ED%95%98%EA%B3%A0-%EC%A0%80%EC%9E%A5%EC%86%8C%EC%97%90-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0)

# git log에서 빠져나오기
q 입력

# conflict 
1. 두 브랜치가 같은 라인 코드를 수정 후 커밋
2. 작업이 다 끝나면 main에서 이 브랜치들을 merge 
3. 이때 conflict가 발생

자동 병합하면 안되는 걸 알려준다
=> 둘다 같은 라인 코드 수정했네? 확인하고 다시 수정 해봐 

# 기타

특수기호를 포함해서 커밋하면 안 된다

## 한번에 add + commit하기
git commit -am "커밋메세지"
-a (add의 약자)
- 주의: 최초 한번은 add 를 통해 tracked 상태가 되었어야 함
- 추적하고 싶지 않은 파일이 실수로 추적되는 사고를 방지할 수 있다


