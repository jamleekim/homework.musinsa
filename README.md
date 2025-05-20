# Musinsa 과제 - 상품 및 브랜드 관리 API

**MUSINSA** 과제 프로젝트는 Spring Boot 기반으로 상품 및 브랜드의 등록, 조회, 수정, 삭제 기능과 함께 카테고리별 최저가 조회 및 브랜드별 전체 카테고리 최저 합계 조회 API를 제공합니다. JPA를 이용한 데이터베이스 연동과 OpenAPI(Swagger)를 통한 문서 자동화 지원이 특징입니다.

## 목차

- [프로젝트 개요](#프로젝트-개요)
- [기능 및 특징](#기능-및-특징)
- [패키지 구조](#패키지-구조)
- [설치 및 실행 방법](#설치-및-실행-방법)
- [API 명세 및 호출 예제](#api-명세-및-호출-예제)
- [테스트 및 예외 처리](#테스트-및-예외-처리)
- [구현 시 고려 사항](#구현-시-고려-사항)

## 프로젝트 개요

이 프로젝트는 제품(상품)과 브랜드 관리를 위한 백엔드 애플리케이션입니다. 

제공하는 API는 다음과 같습니다.

- **상품(Admin)**: 생성, 수정, 삭제
- **브랜드(Admin)**: 생성, 수정, 삭제
- **카테고리별 최저가 조회**: 각 카테고리에서 최저가 상품 목록과 총합 조회
- **브랜드별 전체 최저 합계 조회**: 모든 카테고리 상품을 한 브랜드에서 구매할 때 총합이 가장 낮은 브랜드 및 해당 상품 리스트 조회

## 기능 및 특징

- **Spring Boot 3.2.5 & Java 17**
- **Spring Data JPA**: BrandEntity, ProductEntity를 통한 ORM 매핑
- **Hexagonal Architecture** 기반의 패키지 구조
- **OpenAPI(Swagger)**: `/swagger-ui.html` 및 `/v3/api-docs`
- **초기 데이터 로드**: `schema.sql` & `data.sql` 이용
- **Global Exception Handling**: `@RestControllerAdvice` 기반 예외 처리
- **유닛/통합 테스트 분리**: `src/test` 및 `src/integrationTest`

## 패키지 구조

```plaintext
homework.musinsa    // 루트 패키지
├── application     // 애플리케이션 계층
│   ├── port
│   │   ├── in       // UseCase 인터페이스
│   │   └── out      // OutputPort 인터페이스
│   └── service     // UseCase 구현체
├── domain          // 도메인 계층
│   ├── enums       // Category enum 정의
│   └── model       // Brand, Product DTO 레코드
├── infrastructure  // 인프라스트럭처 계층
│   ├── adapter
│   │   ├── in
│   │   │   ├── rest
│   │   │   │   ├── request   // Command DTO
│   │   │   │   ├── response  // Response DTO
│   │   │   │   └── exception // 예외 매핑
│   │   └── out
│   │       ├── mapper       // Entity ⇄ Model 매핑
│   │       └── persistance  // JPA Adapter & Repository
│   ├── entity     // JPA Entity
│   └── config     // SwaggerConfig 등
└── resources
    ├── schema.sql  // DDL
    ├── data.sql    // 초기 데이터
    └── application.yml
```

## 설치 및 실행 방법

### 필수 요건

- **Java 17 이상**
- **Gradle (빌드 자동화를 위해 사용)**

### 설치 단계

- **의존성 설치 및 빌드**
    ```bash
    ./gradlew build
    ```

- **애플리케이션 실행**
    ```bash
    ./gradlew bootRun
    ```
  또는 빌드된 JAR 파일을 실행할 수 있습니다.
    ```bash
    java -jar build/libs/musinsa-0.0.1-SNAPSHOT.jar
    ```

### 데이터베이스 확인

- 애플리케이션은 H2 메모리 데이터베이스를 사용합니다.
- **H2 콘솔**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- **사용자 이름**: `sa`
- **비밀번호**: (공백)

### API 문서 확인 (Swagger)

- API 스펙을 통해 각 엔드포인트에 대한 설명과 테스트가 가능합니다.
- Swagger UI: http://localhost:8080/swagger-ui.html

### 주요 API 명세 및 호출 예제

1.  전체 합계 최저 브랜드 조회
    - **URL**: `/api/brands/lowest-total`
    - **메서드**: `GET`
    - **응답 예제**:
    ```json
    {
        "최저가": {
        "브랜드": "D",
        "카테고리": [
            {
                "카테고리": "상의",
                "가격": "10,100"
            },
            {
                "카테고리": "아우터",
                "가격": "5,100"
            },
            {
                "카테고리": "바지",
                "가격": "3,000"
            },
            {
                "카테고리": "스니커즈",
                "가격": "9,500"
            },
            {
                "카테고리": "가방",
                "가격": "2,500"
            },
            {
                "카테고리": "모자",
                "가격": "1,500"
            },
            {
                "카테고리": "양말",
                "가격": "2,400"
            },
            {
                "카테고리": "액세서리",
                "가격": "2,000"
            }
        ],
        "총액": "36,100" 
        }
    }
    ```

2. 카테고리별 최저가 상품 조회
    - **URL**: `/api/categories/lowest-prices`
    - **메서드**: `GET`
    - **응답 예제**:
    ```json
    {
      "products": [
      {
        "price": 10000,
        "category": "상의",
        "brandName": "C",
        "brandId": 17,
        "productId": 17
      },
      {
    /* ... */
      },
      {
        "price": 1900,
        "category": "액세서리",
        "brandName": "F",
        "brandId": 48,
        "productId": 48
      }
    ],
      "totalPrice": 34100
    }
    ```

3. 카테고리 가격범위 조회
    - **URL**: `/api/categories/{카테고리명}/price-range`
    - **메서드**: `GET`
    - **응답 예제**:
    ```json
    {
      "카테고리": "상의",
      "최저가": [
        {
          "브랜드": "C",
          "가격": "10,000"
        }
      ],
      "최고가": [
        {
          "브랜드": "I",
          "가격": "11,400"
        }
      ]
    }
    ```


## 테스트 및 예외 처리

### 테스트

- `org.springframework.boot:spring-boot-starter-test`를 사용하여 유닛 및 통합 테스트를 지원합니다.
- Gradle 명령어로 테스트를 실행할 수 있습니다.
- 유닛테스트 실행 

    ```bash
    ./gradlew test
    ```

- 통합테스트 실행

    ```bash
    ./gradlew integrationTest
    ```
- 테스트 코드 작성 방식 (예시 : `CategoryTest`)
    - 테스트 클래스 구조 : `@Nested`를 사용하여 테스트하는 대상 메소드나 기능별로 테스트를 그룹화하여 작성합니다.
    - 가독성
      - 메소드명 : should_기대하는동작_when_주어지는조건 형식으로 표현 (ex. `should_throw_IllegalArgument_exception_for_invalid_displayName`) 
      - Hamcrest 매처 (`assertThat`, `is`,`equalTo` .. ) 를 사용
      - 테스트의 의도를 보다 명확하게 표현합니다.

### 예외 처리

- **전역 예외 핸들러**: `@RestControllerAdvice` 기반 `GlobalExceptionHandler`에서 API 예외를 일관되게 처리합니다.
- **표준 응답**: 모든 오류는 `{"message": "오류 상세 내용"}` JSON 형식으로 통일하여 반환합니다.
- **주요 매핑**:
    - `ValidationException`, `IllegalArgumentException`, `HttpMessageNotReadableException`: **400 Bad Request** (잘못된 요청/입력)
    - `ResourceNotFoundException`: **404 Not Found** (리소스 없음)
    - `IllegalStateException`: **409 Conflict** (비즈니스 로직 충돌)
    - 그 외 모든 `Exception`: **500 Internal Server Error** (내부 오류, 클라이언트에겐 일반 메시지 제공 및 서버 로그 기록)