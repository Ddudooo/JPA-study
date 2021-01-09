# 객체지향 쿼리 언어

## JPA는 다양한 쿼리 방법을 지원

- **`JPQL`**
- `JPA Criteria`
- `**QueryDSL**`
- 네이티브 SQL
- JDBC API 직접 사용, `MyBatis`, `SpringJdbcTemplate` 함께 사용

### JPQL

- JPA를 사용하면 엔티티 객체를 중심으로 개발
- 문제는 검색 쿼리
- 검색을 할 때도 테이블이 아닌 **엔티티 객체를 대상으로 검색**
- 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
- 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요
- JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
- SQL과 문법 유사, `SELECT`, `FROM`, `WHERE`, `GROUP BY`, `HAVING`, `JOIN` 지원
- JPQL은 엔티티 객체를 대상으로 쿼리
- SQL은 데이터베이스 테이블을 대상으로 쿼리
- 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
- SQL을 추상화해서 특정 데이터베이스 SQL에 의존X
- JPQL을 한마디로 정의하면 객체 지향 SQL

### Criteria

- 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
- JPQL 빌더 역할
- JPA 공식 기능
- **단점 - 너무 복잡하고 실용성이 없다.**
- `Criteria` 대신에 `QueryDSL` 사용 권장

### QueryDSL

- 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
- JPQL 빌더 역할
- 컴파일 시점에 문법 오류를 찾을 수 있음
- 동적쿼리 작성 편리함
- 단순하고 쉬움
- 실무 사용 권장

## JPQL

- JPQL은 객체지향 쿼리 언어다.
  따라서 테이블을 대상으로 쿼리하는 것이 아니라
  **엔티티 객체를 대상으로 쿼리한다.**
- JPQL은 SQL을 추상화해서
  **특정데이터베이스 SQL에 의존하지 않는다.**
- **JPQL은 결국 SQL로 변환된다.**

### 문법

```sql
select_문 :: =
select_절
from_절
[where_절]
[groupby_절]
[having_절]
[orderby_절]
update_문 :: = update_절 [where_절]
delete_문 :: = delete_절 [where_절]
```

- `select m from Member as m where m.age > 18`
- 엔티티와 속성은 대소문자 구분O (Member, age)
- JPQL 키워드는 대소문자 구분X (SELECT, FROM, where)
- 엔티티 이름 사용, 테이블 이름이 아님(Member)
- **별칭은 필수(m)** (as는 생략가능)

### 집합과 정렬

```sql
select
COUNT(m), //회원수
SUM(m.age), //나이 합
AVG(m.age), //평균 나이
MAX(m.age), //최대 나이
MIN(m.age) //최소 나이
from Member m
```

- GROUP BY, HAVING
- ORDER BY

### TypeQuery, Query

- `TypeQuery`
  반환 타입이 명확할 때 사용
- `Query`
  반환 타입이 명확하지 않을때 사용

### 결과 조회 API

- `query.getResultList()`
  - **결과가 하나 이상일 때**, 리스트 반환
  - 결과가 없으면 빈 리스트 반환
- `query.getSingleResult()`
  - **결과가 정확히 하나**, 단일 객체 반환
  - 결과가 없으면: `javax.persistence.NoResultException`
  - 둘 이상이면: `javax.persistence.NonUniqueResultException`