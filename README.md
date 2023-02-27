# common-mit
CS16 공통 프로젝트 저장소

- PR개요
    - Pull Request 언제 쓰는데 ?
    
    <aside>
    💡 협업 프로젝트에 참여할 때, 원본코드를 가지고 검증되지 않은 수정작업을 거칠 수 없으니 , Fork → Clone 으로 내 로컬 저장소에 복사 한 뒤, 코드를 수정하는 방식을 사용한다. 
    이때, 내가 업데이트한 코드를 원본에 반영 시키고자 할 때, **Pull Request**를 보내 관리자에게 일련의 검증과정을 거친 후 문제가 없다면 메인 브랜치에 Merge 할 수 있다. 
    따라서 **[내가 업데이트한 코드가 있으니, 한번 검사해 주시고 Merge 해주세요.]** 라고 말하는 것과 같다.
    
    </aside>
    
    - 수행 절차
        1. Fork : 내 깃헙 레포지토리로 복제 
        2. Clone : 내 로컬 레포지토리로 복제 
        3. Remote 설정
        4. Branch 생성
        5. 작업 후 Add → Commit → Push
        6. Pull Request
        7. Merge Pull Request
        8. Merge 이후 branch 제거

## 해보자.

### Fork

<img width="1192" alt="스크린샷 2023-02-27 오후 6 39 20" src="https://user-images.githubusercontent.com/88966578/221543512-f88bc878-6469-42bb-9f59-740107469763.png">


**눈으로 Fork를 찾은 후, 손가락을 움직여 클릭해라.** 

<img width="1168" alt="스크린샷 2023-02-27 오후 6 42 15" src="https://user-images.githubusercontent.com/88966578/221543570-8cfe1054-c30a-4244-8af2-a83877256c3d.png">


Fork가 완료되면, 나의 Github repository에 본제본이 들어오게 된다. 

이젠 내가 여기에 무슨 짓을 하든 괜찮다. 

### Clone

![ee41ecf03d21e320da904400eda79191092cd820bca7f39e271c6b1d4ff086231f9d2d2251c12c6e9488261a8a44611ac232b33ed4689f87cc6b388fab2acb1175c7dd87565cde7cec3f0d797c33a115b3670fb295e9abec5c4054a47cf242ff21799026831544a5b2722add46b3efb4](https://user-images.githubusercontent.com/88966578/221543687-806716b2-ac95-4078-9ddc-2708e6064d8d.jpeg)


### Remote 설정

레포지토리를 clone하면 origin 이라는 이름으로 remote url이 **내 원격 저장소**로 기본으로 설정되어 있다.

하지만 내 원격 저장소 뿐만 아니라, 포크 했던 원본 저장소도 remote로 등록 해주는 게 좋다. 나중에 원본 저장소 내용과 동기화 하기 위해 사용할 것이기 때문이다.

```swift
$ git remote add PRTest https://github.com/HG-SONG/common-mit.git
// $ git remote add <별명> <내 깃헙 repo url>
$ git remote add master https://github.com/codesquad-members-2023/common-mit.git
// $ git remote add <별명> <원본 깃헙 repo url>
$ git remote -v //저장소 확인 
```

<img width="519" alt="스크린샷 2023-02-27 오후 7 01 18" src="https://user-images.githubusercontent.com/88966578/221543766-64d80048-4055-4fe9-9fd2-3f16f4099f1b.png">


사실 나는 Gitkraken user이기 때문에, 그냥 클릭질 몇번하면 된다 ^^
 

<img width="444" alt="스크린샷 2023-02-27 오후 7 20 50" src="https://user-images.githubusercontent.com/88966578/221543805-d83df2e2-e592-4da0-b0c0-21475f0fa25b.png">


### Branch 생성

- Branch분기를 왜 만드냐?
    - 만약에 어떤 기능을 추가하는 업데이트를 진행한다고 치자, 코드 작성을 끝냈고, push를 했을 때, branch 분기가 되어있지 않으면 추가된 코드가 어떤 문제를 일으키면 수정하기가 귀찮다. 하지만 branch분기가 되어있다면, Push시, master에는 기존 코드가 남아있고, 내가 고친 branch만 최신코드로 업데이트 되기 때문에 다시 이전 코드로 되돌리기가 매우 쉽다.
    
    ```swift
    // 브랜치 생성
    $ git branch PRtest
    
    // 브랜치 이동
    $ git checkout PRtest
    
    // 브랜치 생성후 이동 (단축 명령어)
    $ git checkout -b PRtest
    
    // 브랜치 리스트
    $ git branch
    ```
    
    하지만 나는 Gitkraken user이기 때문에, 그냥 클릭질 몇번하면 된다 ^^
    
    <img width="389" alt="스크린샷 2023-02-27 오후 7 23 58" src="https://user-images.githubusercontent.com/88966578/221543840-12bd7eb0-77df-4596-9eaf-a68456de5efe.png">

    
    - test용 push를 해보자.
    
    <img width="1014" alt="스크린샷 2023-02-27 오후 7 33 31" src="https://user-images.githubusercontent.com/88966578/221543885-6678e4f0-a541-41a0-900f-23aba41950df.png">

    
    <img width="297" alt="스크린샷 2023-02-27 오후 7 35 07" src="https://user-images.githubusercontent.com/88966578/221543927-5a1b327b-3dcb-42ea-95c9-3084e43852d0.png">

    
    **Branch 2개 체크. 합격.**
    
    <img width="833" alt="스크린샷 2023-02-27 오후 7 34 50" src="https://user-images.githubusercontent.com/88966578/221543987-bbf80c13-174f-402c-9c85-df7afc652858.png">

    
    **PRtest Branch에 반영됨. 합격.**
    
    <img width="834" alt="스크린샷 2023-02-27 오후 7 35 00" src="https://user-images.githubusercontent.com/88966578/221544049-81a8d048-db8e-40e3-a241-9cfe720fd84d.png">

    
    **main Branch는 원본 유지. 합격.**
    
    (master Branch 지우고 그냥 main에 원본 저장하기로 했다.)

### Push후 PR

깃헙 저장소 상단을 보면 **Compare & pull request** 버튼이 활성화되어 있는 것을 볼 수 있다.

상단에 버튼을 클릭하고, PR 메세지를 작성하고 **Cretae pull request** 버튼을 누르면 풀 리퀘스트를 생성하게 된다.

![스크린샷 2023-02-27 오후 10.30.02.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a88bef61-7190-4c75-b44f-6cb959678007/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-02-27_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_10.30.02.png)

**눌러.**

### 미션 구현

- 해당 디렉토리의 파일목록 출력
    
    **FileManager**
    
    swift에서는 파일들을 다루기 위한 클래스로 **FileManager**를 제공하고 있다.FileManager는 **FileManager.default** 인스턴스를 기본으로 제공하며, 원하면 자신만의 인스턴스를 새롭게 생성할 수도 있다.파일시스템의 파일 혹은 디렉토리들은 모두 경로를 가지고 있어 URL 혹은 String 데이터 타입을 통해 파일에 접근할 수 있도록 해준다.
