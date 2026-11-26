1. 일정 생성
    - HTTP Method: POST
    - URL: /schedules
    - 설명: 새로운 일정을 생성함
    - Request:
        - Parameter: 없음
        - QueryString: 없음
        - Header: Content-Type: application/json
        - Body:
            - title (String): 일정 제목
            - content (String): 일정 내용
            - author (String): 작성자명
            - password (String): 비밀번호
        - 예시:
            
            ```json
            {
              "title": "입문 Spring 수강",
              "content": "입문 Spring 영상 강의 시청",
              "author": "홍길동",
              "password": "1q0p2w9o"
            }
            ```
            
    - Response:
        - Status Code: 201 Created
            - 일정 생성에 성공하고 생성한 일정 데이터를 비밀번호를 제외하고 반환
            - Body:
                - id (int): 일정 고유 식별자
                - title (String): 일정 제목
                - content (String): 일정 내용
                - author (String): 작성자명
                - createdAt (String): 작성일
                - updatedAt (String): 수정일 (작성일과 동일)
        - Status Code: 400 Bad Request
            - 필수 항목이 누락 또는 잘못된 요청 형식
2. 전체 일정 조회
    - HTTP Method: GET
    - URL: /schedules
    - 설명: 등록된 일정 목록을 전부 조회. 작성자명을 전달하면 해당 작성자의 일정만 조회.
        - 작성자 명은 선택적 조회 조건. 전달하지 않으면 전체 일정 조회
    - Request:
        - Parameter: 없음
        - QueryString:
            - author (String, Optional): 작성자명
        - Header: Accept: application/json
        - Body: 없음
    - Response:
        - Status Code: 200 OK
            - 조회에 성공하면 일정 목록을 반환
            - 작성자명이 전달된 경우 해당 작성자의 일정만 반환
            - 수정일 기준 내림차순으로 정렬
            - 조회 결과가 없으면 빈 배열 반환
            - Body:
                - id (int): 일정 고유 식별자
                - title (String): 일정 제목
                - content (String): 일정 내용
                - author (String): 작성자명
                - createdAt (String): 작성일
                - updatedAt (String): 수정일
            - 예시 (요청 URL: /schedules?author=홍길동):
                
                ```json
                [
                  {
                    "id": 1,
                    "title": "입문 Spring 수강",
                    "content": "입문 Spring 영상 강의 시청",
                    "author": "홍길동",
                    "createdAt": "2026-11-25T20:00:00",
                    "updatedAt": "2026-11-25T20:00:00"
                  },
                  {
                    "id": 2,
                    "title": "Java 알고리즘 수강",
                    "content": "Java 알고리즘 영상 강의 시청",
                    "author": "홍길동",
                    "createdAt": "2026-11-25T19:00:00",
                    "updatedAt": "2026-11-25T19:30:00"
                  }
                ]
                ```
                
        - Status Code: 400 Bad Request
            - 잘못된 Query String 형식
3. 선택 일정 조회
    - HTTP Method: GET
    - URL: /schedules/{id}
    - 설명: 일정의 고유 식별자를 이용하여 선택한 일정 단건의 정보만 조회
    - Request:
        - Parameter:
            - id (int): 일정 고유 식별자
        - QueryString: 없음
        - Header: Accept: application/json
        - Body: 없음
    - Response:
        - Status Code: 200 OK
            - 조회에 성공하면 선택한 일정 정보를 반환
            - Body:
                - id (int): 일정 고유 식별자
                - title (String): 일정 제목
                - content (String): 일정 내용
                - author (String): 작성자명
                - createdAt (String): 작성일
                - updatedAt (String): 수정일
        - Status Code: 400 Bad Request
            - 잘못된 ID 형식
        - Status Code: 404 Not Found
            - 해당 ID 의 일정이 존재하지 않음
4. 일정 수정
    - HTTP Method: PATCH
    - URL: /schedules/{id}
    - 설명: 선택한 일정의 제목, 작성자명 을 수정
        - 일정 수정을 요청할 때 비밀번호를 함께 전달해야 함
        - 수정일을 수정 완료 시점으로 변경해야 함
    - Request:
        - Parameter:
            - id (int): 일정 고유 식별자
        - QueryString: 없음
        - Header: Content-Type: application/json
        - Body:
            - title (String): 변경할 일정 제목
            - author (String): 변경할 작성자명
            - password (String): 비밀번호
        - 예시:
            
            ```json
            {
              "title": "입문 Spring 강의 복습",
              "author": "홍길동",
              "password": "1q0p2w9o"
            }
            ```
            
    - Response:
        - Status Code: 200 OK
            - 일정 수정에 성공하면 수정한 일정 데이터를 비밀번호를 제외하고 반환
            - Body:
                - id (int): 일정 고유 식별자
                - title (String): 일정 제목
                - content (String): 일정 내용
                - author (String): 작성자명
                - createdAt (String): 작성일
                - updatedAt (String): 수정일
            - Status Code: 400 Bad Request
                - 필수 항목 누락 또는 잘못된 요청 형식
            - Status Code: 403 Forbidden
                - 비밀번호 불일치
            - Status Code: 404 Not Found
                - 해당 ID 의 일정이 존재하지 않음
5. 일정 삭제
    - HTTP Method: DELETE
    - URL: /schedules/{id}
    - 설명: 선택한 일정을 삭제
    - Request:
        - Parameter:
            - id (int): 일정 고유 식별자
        - QueryString: 없음
        - Header: Content-Type: application/json
        - Body:
            - password (String): 비밀번호
    - Response:
        - Status Code: 204 No Content
            - 요청이 처리되어 응답할 본문이 없으니 반환하지 않음
        - Status Code: 403 Forbidden
            - 비밀번호 불일치
        - Status Code: 404 Not Found
            - 해당 ID 의 일정이 존재하지 않음
