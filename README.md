# LanguageExchangeMall  
사용자가 언어 교환 파트너를 찾고, 관심 있는 파트너를 위시리스트에 저장하며,  
공지사항을 확인할 수 있는 **JSP/Servlet 기반의 MVC 웹 프로젝트**입니다.

---

## 📌 프로젝트 소개
LanguageExchangeMall은 **JSP + Servlet + JDBC** 기반으로 구현한  
언어교환 커뮤니티 서비스입니다.  
이 프로젝트를 통해 **MVC 패턴, DAO/DTO 구조, DB 연동, 세션 기반 로그인 처리** 등  
자바 웹 개발의 핵심 기술들을 직접 설계하고 구현했습니다.

---

## 🚀 주요 기능 (Features)

### 🔹 1. 사용자 기능
- 회원가입 / 로그인 (세션 기반)
- 마이페이지
- 개인 위시리스트 조회

### 🔹 2. 파트너(언어교환 상대) 기능
- 언어 교환 파트너 목록 조회
- 파트너 상세 정보 확인
- 관심 있는 파트너 위시리스트 등록/삭제

### 🔹 3. 공지사항 (Notice)
- 공지 목록 조회
- 공지 상세 화면
- NoticeDAO / Notice DTO 기반 CRUD

### 🔹 4. 위시리스트
- 위시리스트 추가
- 위시리스트 목록 조회
- 삭제 기능 구현

---

## 🧱 기술 스택 (Tech Stack)

### Backend
- **Java Servlet / JSP**
- **MVC 아키텍처 패턴**
- **DAO / DTO 구조**
- **JDBC + MySQL**

### Frontend
- **JSP**
- **HTML5 / CSS3**
- **기본 UI 구성**

### Server / Tools
- **Tomcat 9**
- **Eclipse**
- **Git / GitHub**

---

## 🛠 구현한 핵심 기술

### ✔ MVC 패턴
- Controller: Servlet으로 요청 처리  
- Model: DAO/DTO  
- View: JSP  
→ 유지보수성과 확장성이 높은 구조로 설계

### ✔ DAO & DTO 설계
- 각 기능별 DAO 분리 (NoticeDAO, PartnerDAO, WishlistDAO)
- DTO를 통한 데이터 이동 안정성 확보

### ✔ 데이터베이스 연동
- JDBC 기반 MySQL CRUD 구현
- PreparedStatement 사용하여 SQL 인젝션 방지

### ✔ 세션 기반 로그인 처리
- 로그인 성공 시 세션 생성
- 마이페이지 접근 시 세션 검증 구현

---

## 📌 앞으로 추가하고 싶은 기능
- 파트너 매칭 알고리즘(추천 기능)
- 검색/필터 기능
- 관리자 페이지
- UI/UX 개선 (부트스트랩 적용)
- Spring Boot 리팩토링

---

## 👩‍💻 개발자
**Nazima Ergeshova**  
AI Software Department, 영진전문대학교  
백엔드/풀스택 개발자를 목표로 학습 중

---

## 📄 라이선스
본 프로젝트는 학습 및 포트폴리오 목적의 개인 프로젝트입니다.
