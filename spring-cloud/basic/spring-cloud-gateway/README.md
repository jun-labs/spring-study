## 🏛️ Spring Cloud API-Gateway

만들면서 배우는 Spring Cloud API-Gateway 학습 저장소.

<br/><br/><br/><br/>

## ⌨️ 프로그래밍 요구사항

&nbsp;&nbsp; - Query API만 개발한다. Command는 신경 쓰지 않는다. <br/>
&nbsp;&nbsp; - API Gateway에서 유량을 제어한다. <br/>
&nbsp;&nbsp; - 한 사용자는 1분당 최대 100번까지 요청을 보낼 수 있다. <br/>
&nbsp;&nbsp; - 최대 요청 횟수를 넘긴 사용자는 5분간 접속을 금지한다. <br/>
&nbsp;&nbsp; - API Gateway에서 사용자 정보와 IP 주소를 API 서버로 보낸다. <br/>
&nbsp;&nbsp; - 거의 변경되지 않는, 자주 조회되는 정보는 API Gateway에서 캐싱한다. <br/>
&nbsp;&nbsp; - 사용자의 모든 Request/Response를 로그로 남긴다. <br/>
&nbsp;&nbsp; - 모든 사용자 로그는 한 곳에 저장한다. 민감 데이터가 아니라면 보관 주기는 30일로 제한한다. <br/>
&nbsp;&nbsp; - JPA를 사용하지 않으며 모든 쿼리는 직접 작성한다. <br/>
&nbsp;&nbsp; - 테스트는 통합 테스트까지 작성하며 문서화는 Swagger를 활용한다. <br/>
&nbsp;&nbsp; - 스프링 WebFlux를 학습하며 내용을 정리한다. <br/>

<br/><br/><br/><br/><br/><br/><br/><br/>

## 📑 기능 요구사항

&nbsp;&nbsp; - 한 소모임에는 참여자, 공지사항, 자유 게시글, 카테고리, 해시태그, 이미지가 존재한다. <br/>
&nbsp;&nbsp; - 이미지는 별도의 테이블을 만들지 않고 URL만 저장한다. <br/>
&nbsp;&nbsp; - 소모임 첫 페이지에 접속하면 공지사항과 최근 작성된 5개의 게시글을 불러온다. <br/>
&nbsp;&nbsp; - 북마크 된 소모임을 조회할 수 있다. <br/>
&nbsp;&nbsp; - 랜딩 페이지는 무한 스크롤로 구현한다. <br/>
&nbsp;&nbsp; - 사용자 요청/응답 결과를 볼 수 있다. <br/>

<br/>
