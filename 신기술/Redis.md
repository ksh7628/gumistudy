# Redis

<br/>

## What is Redis?

<br/>

레디스에 대해 알아보기 전에 NOSQL에 대해 알아보자.


### NOSQL이란?
RDBMS는 보통 “관계형 데이터베이스”라고 하며 반면 NOSQL은 “비관계형 데이터베이스”이다. 보통 NOSQL은 키-밸류나 컬럼, 문서 형식의 데이터 모델을 이용한다.
(RDBMS가 아닌 다른 형태로 데이터를 저장하는 기술이라고 생각하면 된다.)

<br/>
NOSQL: Not Only SQL, 비관계형 데이터베이스

그렇다면 왜, 언제 NOSQL을 쓰는 걸까? 아주 많은 양의 데이터를 효율적으로 처리가 필요할 때, 데이터의 분산처리, 빠른 쓰기 및 데이터의 안정성이 필요할 때 사용한다. 특정 서버에 장애가 발생했을 때에도 데이터 유실이나 서비스 중지가 없는 형태의 구조이기 때문에, NOSQL을 사용한다.

<br/>

### NOSQL의 종류
1. 키-밸류 스토리지형 키-밸류형: `Redis`, memcached, Coherence
2. 열 지향 와이드 컬럼 스토어: Cassandra, HBASE, Cloud Database
3. 문서형: MongoDB, Couchbase, MarkLogic, DynamicDB MS-DocumentDB
4. 그래프형: Neo4j

<br/>

### REDIS(REmote Dictionary Server)

REDIS(REmote Dictionary Server)는 `메모리 기반의 “키-값” 구조 데이터 관리 시스템이며, 모든 데이터를 메모리에 저장하고 조회하기에 빠른 Read, Write 속도를 보장하는 비 관계형 데이터베이스이다.`

레디스는 크게 `5가지< String, Set, Sorted Set, Hash, List >의 데이터 형식을 지원`한다.

Redis는 빠른 오픈 소스 인 메모리 키-값 데이터 구조 스토어이며, 다양한 인 메모리 데이터 구조 집합을 제공하므로 사용자 정의 애플리케이션을 손쉽게 생성할 수 있다.

<br/>

![](https://miro.medium.com/max/2656/1*ZAj8ReBr4ZchEyZuDMCkFg.jpeg)

<br/>

### 레디스 장점
- 리스트, 배열과 같은 데이터를 처리하는데 유용하다.
- 리스트형 데이터 입력과 삭제가 MySQL에 비해서 10배정도 빠르다고 한다.
- 메모리를 활용하면서 영속적인 데이터 보존
- Redis Server는 1개의 싱글 쓰레드로 수행되며, 따라서 서버 하나에 여러개의 서버를 띄우는 것이 가능하다.

<br/>
<br/>

참고자료
- https://jyejye9201.medium.com/%EB%A0%88%EB%94%94%EC%8A%A4-redis-%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80-2b7af75fa818