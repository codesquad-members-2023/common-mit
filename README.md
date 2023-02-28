# CS16

## 학습 계획

- [x]  학습 계획 작성
- [ ]  미션 이해하면서 학습 키워드 정리
- [x]  쪼개고 우선순위 정하기
- [x]  구현
    - [x]  step 0: 입출력 및 명령어 파싱
    - [x]  step 1: list
    - [x]  step 2: hash
    - [x]  step 3: zlib
- [ ]  학습 정리

---

# 미션 쪼개기

- [x]  [step 0] 입력 받아서 명령어 만들기 - mit / 명령어 / path(String) 수준으로
- [x]  [step 0] 입출력 관리 객체, 출력용 객체 만들기
- [x]  [git] 작업 환경 조성 - local에 fork 및 test PR
- [x]  [step 1] list
    1. 시스템에 접근해서 특정 디렉토리의 entity 목록 fetch
    2. 각 파일의 용량 fetch
    3. 출력 객체 만들기
- [x]  [step 2] hash
    1. CryptoKit - hash 함수
    2. 파일에 대한 hash 구하기
- [x]  [step 3] zlib
    1. NSData가 제공하는 기능으로 압축하기

---

# 미션

## 1단계: list

### 파일 다루기

macOS 파일 시스템과 파일 경로 다루기

[Swift로 파일 다루기](https://hcn1519.github.io/articles/2017-07/swift_file_manager)

[File System Basics](https://developer.apple.com/library/archive/documentation/FileManagement/Conceptual/FileSystemProgrammingGuide/FileSystemOverview/FileSystemOverview.html#//apple_ref/doc/uid/TP40010672-CH2-SW2)

### 방법 1

> 문자열로 파일을 가져오고 → 용량 따로 구하는 방법
> 

[contentsOfDirectory(atPath:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/filemanager/1414584-contentsofdirectory)

### 방법 2 ✅

> shallow 탐색으로 파일들에 대한 url 가져오고 url로 이름이랑 파일 정보 가져오는 방법
> 

이게 좀 더 정석 같은 방법이려나

문자열을 URL로 바꿔보자.

[fileURL(withPath:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsurl/1410828-fileurl)

그리고 디렉토리 하위 엔티티에 접근하자

이 중에서 파일인 친구들만 구해야 할 것이다.

[contentsOfDirectory(at:includingPropertiesForKeys:options:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/filemanager/1413768-contentsofdirectory)

파일의 URL을 통해 데이터를 생성하자

[init(contentsOf:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsdata/1413892-init)

자 이제 이 데이터의 크기만 구하면 된다.

잠깐… Data 타입으로 변환하지 않아도 사용할 수 있는 인터페이스가 있다고?

원하는 값을 키 타입으로 전달해서 얻는 인터페이스를 찾았다.

[resourceValues(forKeys:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/url/1780058-resourcevalues)

위 메소드는 키 셋을 전달하는 방법으로 속성에 접근한다.

키 타입은 다음과 같고 프로퍼티로 키를 제공한다.

(근데 enum같은 optionSet이 아니라 이런 식으로 구현하는 이유가 뭘까? 이런 패턴 볼 때마다 매번 궁금.)

[URLResourceKey | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/urlresourcekey)

암튼 사이즈 키에는 다른 키도 많았지만, 이게 제일 기본인 것 같아서 선택

[fileAllocatedSizeKey | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/urlresourcekey/1409814-fileallocatedsizekey)

이름은 굳이 따로 받지 않아도 이런 방법이 있었다.

[lastPathComponent | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/url/1780317-lastpathcomponent)

참고

- entity size
    
    [https://stackoverflow.com/questions/32814535/how-to-get-directory-size-with-swift-on-os-x](https://stackoverflow.com/questions/32814535/how-to-get-directory-size-with-swift-on-os-x)
    
- String → url / url → String
    
    [https://www.zerotoappstore.com/swift-url-to-string.html](https://www.zerotoappstore.com/swift-url-to-string.html)
    
- isDirectory
    
    [https://stackoverflow.com/questions/65152001/how-to-check-if-swift-url-is-directory](https://stackoverflow.com/questions/65152001/how-to-check-if-swift-url-is-directory)
    

## 2단계: hash

### URL → Data

[init(contentsOf:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsdata/1413892-init)

위에서 탈락한 방법인데 드디어 쓸 차례가 왔다. 얻은 data는 말 그대로 파일의 pure한 데이터이다.

### sha256

위에서 얻은 파일의 데이터(contents, 내용)를 가지고 SHA256 hash를 생성할 것이다.

[hash(data:) | Apple Developer Documentation](https://developer.apple.com/documentation/cryptokit/sha256/hash(data:))

CryptoKit 이 제공하는 SHA256 인터페이스로 쉽게 hash를 얻을 수 있었다. 다만 얻어진 hash는 String이 아니라 Digest라고 하는 특별한 결과 타입이었다.

[Introducing CryptoKit](https://www.kodeco.com/10846296-introducing-cryptokit#toc-anchor-003)

[init(describing:) | Apple Developer Documentation](https://developer.apple.com/documentation/swift/string/init(describing:)-67ncf)

스위프트 String에 알 수 없는 타입의 객체를 protocol conformation에 따라서 내부적으로 문자열로 리턴해주는 인터페이스가 있었는데, 문제는…

```
SHA256 Digest: d058287d76c9595fd7e27cee63880d87b39b284034de68a4f7a9bc9840ff0cf6
```

이렇게 description 형태의 문자열로 리턴된다는 것이다. pure 한 값을 얻을 수 없기 때문에 이걸 사용하는 건 의미 없다고 판단했다.

[How to calculate the SHA hash of a String or Data instance - free Swift 5.4 example code and tips](https://www.hackingwithswift.com/example-code/cryptokit/how-to-calculate-the-sha-hash-of-a-string-or-data-instance)

자료를 찾다보니 이렇게 포매팅하는 방법이 있었다. 위에서 사용했던 인터페이스와 결과는 동일해서 채택하기로 했다.

## 3단계: zlib

### 데이터 압축 하기

[compressed(using:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsdata/3174960-compressed)

[NSData.CompressionAlgorithm.zlib | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/nsdata/compressionalgorithm/zlib)

데이터 압축도 Foundation이 제공하고 있다. compressed(using:) + compression algorithm 조합으로 사용하면 되는데… 그런데… 주의할 것이 하나 있다.

compressed 메소드는 NSData의 메소드이기 때문에 bridging 해서 사용해야 한다는 것이다.

### 파일 쓰기

[[Swift/iOS] FileManager로 파일 생성(쓰기), 읽기, 삭제하기](https://leeari95.tistory.com/32)

- path 추가 -
- 새 path에 data 쓰기

### TS - 파일 URL 경로

```swift
private static func zlib(_ path: String) -> [ListOutput] {
    var outputs: [ListOutput] = []
    do {
      let entities = try getEntitiesURL(from: path)
      outputs = try entities.compactMap { (entity: URL) -> ListOutput? in
        guard let zlib = entity.data?.zlib else { return nil }
        let fileName = entity.lastPathComponent
        
//        let fixedFileName = fileName.components(separatedBy: ".").first!
//        print(fixedFileName)
        
        let newFileName = fixedFileName + ".z"
        guard let pathURL = URL(string: path) else { return nil }
        let newFileURL = pathURL.appending(component: newFileName)
        print(newFileURL.absoluteString)
        try zlib.write(to: newFileURL)
        guard let zlibSize = newFileURL.fileSize else { return nil }
        return ListOutput(fileName: newFileName, info: zlibSize)
      }
    } catch {
      print(error)
    }
    return outputs
  }
```

이런 식으로 문자열 URL을 만들어서 파일을 write하려고 했는데 아무리 수정을 해도 에러가 났다.

한 번은 b.swfit.z 처럼 이름을 붙이면 확장자를 식별할 수가 없어서 그런 건가? 이런 뚱단지 같은 추측을 하고 이거다 하면서 좋아했다. 근데 수정을 해도 소용이 없었음.

> [에러 내용]
> 
> 
> **2023-02-28 14:16:34.506689+0900 CS16-effie[20150:977706] CFURLCopyResourcePropertyForKey failed because it was passed a URL which has no scheme**
> 
> **Error Domain=NSCocoaErrorDomain Code=518 "The file couldn’t be saved because the specified URL type isn’t supported." UserInfo={NSURL=/Users/effie/work/b.z}**
> 

아하 URL에 scheme을 지정해주지 않아서 그런 거였군. write가 어디에 저장하라는 건지 알 수가 없으니까 에러가 났던 것이다.

그렇다면 이쯤에서 URL Scheme이 뭔지 짚고 넘어가자.

일단 scheme은 사전적으로 계획, 설계 등의 뜻을 가지고 있는데, 개발 용어인 scheme에 가장 근접한 뜻은 ‘체계’ 정도가 될 것 같다.

[Uniform Resource Identifier](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#Syntax)

위키피디아 에서는 다음과 같이 설명하고 있다.

> A URI has a scheme that refers to a specification for assigning identifiers within that scheme.
> 

해당 스킴 내에서 식별자를 할당하기 위한 사양을 참조하는 스킴을 갖는다…

이게 무슨 리눅스 이즈 낫 유닉스 같은 소리일까.

URI는 결국 Identifier, 즉 식별자이다. 내가 구현하고 있는 기능처럼 파일을 저장하거나, 특정 공간에 있는 데이터를 참조하거나 요청하는 작업 에 URI가 사용된다. 특정 리소스에 대한 작업을 할 때, 서로 다른 자원를 식별하기 위해서 존재하는 것이 URI인 것이다. 

`URI = scheme ":" ["//" authority] path ["?" query] ["#" fragment]`

이 공식은 위키피디아에서 가져온 URI의 generic syntax이다 . 다섯 가지 component가 중요한 순서대로 이루어져 있고, 스킴은 그 중 가장 앞에 존재하게 된다. 즉 스킴은 URI 구성 요소 중에서 가장 중요한 요소인 것이다.

> As such, the URI syntax is a federated and extensible naming system wherein each scheme's specification may further restrict the syntax and semantics of identifiers using that scheme.
> 

스킴의 사양은 그 스킴을 사용하는 identifier의 syntax와 semantic을 제한한다. 즉 작업의 처리 주체는 특정 스킴의 사양을 토대로, 스킴에 따라 URI를 다르게 해석해서 작업을 처리하는 것이다.

따라서 스킴 없이 전달된 URI로는 정확히 어떻게 이 URI를 사용할 것인지 결정할 수 없다.

[write(to:options:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/data/1779858-write)

> **`url`**
> 
> 
> The location to write the data into.
> 

write는 전달된 URI가 어떤 시스템의 것인지, 정확히 어떤 시스템에 해당 데이터를 저장해야 하는지 알 수 없기 때문에 이런 URL로는 쓰기 작업을 할 수 없다며 에러를 보내온 것이다.

그렇다면 파일 시스템을 위한 URI는 어떻게 작성해야 할까?

[file URI scheme](https://en.wikipedia.org/wiki/File_URI_scheme)

진행 중이었던 구현에서는 문자열로 path를 구성 → 문자열을 받는 이니셜라이저로 URL을 생성했는데…

이런 방식보다는 확실히 로컬 파일 시스템을 받는 방식이 안전할 것 같다는 생각이 들었다. 나중에 path component를 추가하더라도…

[init(fileURLWithPath:) | Apple Developer Documentation](https://developer.apple.com/documentation/foundation/url/3126800-init)

찾아보니까 파일 스킴에 path를 붙여주는 URL 이니셜라이저가 있었다. 활용해보자

스킴을 추가하니 정상적으로 동작한다. 야호 !