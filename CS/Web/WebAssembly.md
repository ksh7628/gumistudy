# 웹어셈블리(WebAssembly, wasm)

## 웹어셈블리란?

<br/>

웹어셈블리는 C나 C++과 같은 프로그래밍 언어를 컴파일해서 어느 브라우저에서나 빠르게 실행되는 바이너리 형식(0, 1로 이루어진 이진 형식)으로 바꿔주는 기술을 뜻합니다. (프로그래밍 언어가 아닙니다.)

보통 웹애플리케이션을 개발할 때 사용하는 언어는 HTML, CSS, JavaScript입니다. 그 중에서 JavaScript를 이용하여 동적인 부분을 개발하게 되는데요. 근래엔 JavaScript의 속도가 매우 빨라졌지만 그렇다고 해도 C나 C++과 같은 언어들에 비해서는 느립니다.

웹어셈블리는 별도의 플러그인을 사용하지 않고 브라우저에서 C나 C++과 같은 언어 수중의 성능을 보여줄 수 있는 기술이 목표입니다.

이는 웹 플랫폼에 큰 영향을 미칩니다. 이전에 불가능했던 웹에서 실행되는 클라이언트 응용 프로그램을 사용하여 웹에서 여러 언어로 작성된 코드를 네이티브에 가까운 속도로 실행하는 길을 제공합니다.

게다가 WebAssembly 코드를 사용하여 이를 활용하는 방법을 알 필요조차 없습니다. WebAssembly 모듈을 웹 (또는 Node.js) 앱으로 가져와 JavaScript를 통해 사용할 수 있도록 할 수 있습니다. JavaScript 프레임 워크는 WebAssembly를 사용하여 대규모 성능 이점과 새로운 기능을 제공하면서도 웹 개발자가 쉽게 기능을 사용할 수 있도록 할 수 있습니다.

<br/>

## 웹 어셈블리 목적

<br/>

- 빠르고, 효율적이고, 높은 이식성을 가진다.
- 읽기 쉬우면서 디버깅이 가능해야 한다.
- 안전하고, 웹을 망치지 않아야 한다.

## 처리과정

<br/>

- javascript 처리 과정
  
  ![](https://tech.kakao.com/wp-content/uploads/2021/05/Img-8.png)

- WebAssembly 처리 과정
  
  ![](https://lh4.googleusercontent.com/sA6DPio1Bz2DmsLd8_BHKUUWwFxHD3F5Z4qUpxUt0t7xJxOLOGial8KiYjxBuFm-77NVucikDOtXP6FlUyxBQQgXiMRQT1LCVJ-LKAWeXLmeUb6Szu7-j_oRwMpkIQNJHD0en2Si)

<br/>

1. 구문 해석 (parse – decode)
- js 파일이 브라우저에 전달되면, 추상 구문 트리 ( AST : Abstract Syntax Tree )로 변환이 되어야 합니다.
- AST는 각 엔진마다 다르지만 바이트 코드 형태로 변환이 됩니다. 반면, wasm 파일은 이미 바이트 코드 형태이기 때문에, 변환 과정이 필요 없이 해석(decode)만 하여 검증 과정만 거치면 됩니다.

2. 컴파일 및 최적화 (compile + optimize)
- js는 각 브라우저마다 다르지만 컴파일 과정을 거칩니다. 기본 컴파일러나, JIT (Just-in-time) 컴파일러를 사용하는 방식입니다.
- 해당 과정은 여러 요인이 있지만 WASM은 LLVM 컴파일러를 사용하여 이미 많은 최적화가 진행된 상태입니다. 따라서 js에 비해 최적화 과정이 적어지게 됩니다.

3. 재 최적화 (re-optimize)
- 재 최적화는 js에서만 일어나는 과정입니다. 이는 JIT 컴파일러를 사용하는 과정에서 간혹 여러 이유로 인해 기존 최적화를 버리고 다시 최적화를 하는 과정입니다.
- WASM은 명시적인 타입 같은 이유로, JIT에서 데이터를 수집, 타입 추론과 같은 과정이 필요 없습니다.

4. 실행 (execute)
- js가 빠르게 실행되기 위해서는 JIT를 자세히 알아야 합니다. 하지만 이는 실제로 알기도 어렵고, 모든 브라우저에서 동일하게 적용되지는 않습니다.
- 반면 WASM은 이를 위한 노력이 필요 없습니다. 이미 최적화가 많이 진행되었기 때문이죠.
- 이는 js가 프로그래머를 위해 작성된 언어라면, wasm는 컴파일러를 위해 작성되었습니다. 결과적으로 더 이상적인 명령어 셋(instruction set)을 제공하여 10% ~ 800%의 성능 차이를 가져옵니다.

5. 가비지 컬렉션 (GC : garbage collection)
- 아직까지 WASM에서는 GC를 제공하지 않습니다. C, C++처럼 메모리를 수동으로 관리하는 것이 어렵지만, 안정적인 성능을 제공할 수는 있습니다.

<br/>

### 웹 어셈블리의 활용 사례

<br/>

웹 어셈블리의 활용 사례는 다음과 같습니다.

- Microsoft의 Blazor
- GoLang
- AssemblyScript
- AutoCAD
- Figma

<br/>

### 참고
- https://developer.mozilla.org/ko/docs/WebAssembly/Concepts
- https://medium.com/@su_bak/technology-webassembly%EB%9E%80-b8ce77b06165
- https://tech.kakao.com/2021/05/17/frontend-growth-08/