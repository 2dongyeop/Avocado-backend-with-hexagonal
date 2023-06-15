# TEAM Avocado

> ### *Introduction.*
빠른 개발을 위해 계층형 아키텍처를 선택해 설계한 [Avocado-backend](https://github.com/HBNU-Avocado/Avocado-backend) 프로젝트를 **헥사고날 아키텍처로 리팩토링**합니다.

기존 프로젝트에서 `책임의 구분`, `아쉬웠던 기술의 선택` 등의 아쉬웠던 점을 개선하는 데에 노력합니다.

[코드 리뷰](https://www.notion.so/leedongyeop/35dbe6751ec641f4bbdffc314f4ccef8)를 통해 잘못된 설계를 다시 잡고, Issue와 PR의 내용을 통해 고민한 흔적들을 확인하실 수 있습니다.


<br/>

<br/>

> ### *Overview.*
*현재 수많은 플랫폼에서 리뷰 서비스를 제공하지만, 아직까지 병원비를 공개하는 플랫폼은 없다.*

*리뷰를 통해 가격 비교를 할 수 있고, 나아가 예약 서비스와 픽업 서비스를 부가 기능으로 제공한다.*

*따라서, 고령화 사회에 맞춰 고령층을 포함한 모든 연령층이 사용하기 쉬운 웹 리뷰 플랫폼을 제작한다.*

<br/>

<img src="https://github.com/HBNU-Avocado/Avocado-backend/blob/main/document/image/prototype1.jpg" width = 600/>

<br/>

<br/>

> ### *Document.*
  - [프로젝트 노션 링크](https://leedongyeop.notion.site/Avocado-287972df87fa4a8bb976bba7649919ca)
    - [요구사항 설게](https://www.notion.so/leedongyeop/d7c5da5175b14c71b6cbe21650607ebd)
    - [데이터베이스 설계](https://www.notion.so/leedongyeop/0badd5357d8a41bfb9b78db729c24ed7)
    - [시퀀스 다이어그램 및 ERD](https://www.notion.so/leedongyeop/204db9578a2b44be877399d3e5d0b6b4)
    - [API 설계도](https://www.notion.so/leedongyeop/API-102a2f25e370479195fa616572f369ff)
    - [트러블슈팅](https://www.notion.so/leedongyeop/0459864398e84439bfa679d2470aeb8e)
  - [프론트 웹 서버 레포](https://github.com/HBNU-Avocado/Avocado-frontend)
  - [백엔드 API 서버 레포](https://github.com/HBNU-Avocado/Avocado-backend)
  

<br/>

<br/>

> ### *Sequence Diagram*

<img src="https://github.com/HBNU-Avocado/Avocado-backend/blob/main/document/image/Sequence-diagram-v3.png" width = 800/>


<br/>

<br/>

> ### *Environment.*
- OS
  - macOS Ventura `13.2.1`
- Language
  - Java 17
- Framework
  - org.springframework.boot `3.0.2`
  - JUnit5
  - Rest Assured
- Datebase
  - PostgreSQL
  - Redis
- Etc
  - Docker
