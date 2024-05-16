## Use Case Diagram
![Uploading 일정관리UseCaseDiagram.drawio.svg…]()


![API 명세서](https://documenter.getpostman.com/view/34878494/2sA3JRZz5n#4d3f9a5f-8bd1-4fb7-9cf6-f09571873924)

## Creat -> Post : 1단계 요구사항
1. `할일 제목`, `할일 내용`, `담당자`, `비밀번호`, `작성일`을 저장할 수 있습니다.
- 저장된 일정 정보를 반환 받아 확인할 수 있습니다.
![스크린샷 2024-05-16 122158](https://github.com/gaeun7/scheduleProject/assets/162283154/c38b7b94-39f5-4ce3-a229-0c158b1c80a5)

## Read -> Get : 2단계 요구사항
2. 선택한 일정의 정보를 조회할 수 있습니다.
![스크린샷 2024-05-16 141154](https://github.com/gaeun7/scheduleProject/assets/162283154/513a44e5-b124-48c0-9fab-ab879b688c39)

## Read -> Get : 3단계 요구사항
3. 등록된 일정 전체를 조회할 수 있습니다.
- 조회된 일정 목록은 `작성일` 기준 내림차순으로 정렬 되어있습니다.
- ![스크린샷 2024-05-16 142817](https://github.com/gaeun7/scheduleProject/assets/162283154/cb58304a-3cce-4e13-ad5a-5ff5b677a68d)

## Update -> Put : 4단계 요구사항
4. 선택한 일정의 `할일 제목`, `할일 내용`, `담당자`을 수정할 수 있습니다.
- 서버에 일정 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
4-1. 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
![스크린샷 2024-05-16 145251](https://github.com/gaeun7/scheduleProject/assets/162283154/63f1ef37-401d-4a97-b303-84e1a647d781)
![스크린샷 2024-05-16 145319](https://github.com/gaeun7/scheduleProject/assets/162283154/efd1e4b3-c768-455f-849e-87b5e9f5d293)

## Delete -> Delete : 5단계 요구사항
5. 선택한 일정을 삭제할 수 있습니다.
- 서버에 일정 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
![image](https://github.com/gaeun7/scheduleProject/assets/162283154/fc0f75b2-28c2-437a-a46e-a1a1c7296557)
![image](https://github.com/gaeun7/scheduleProject/assets/162283154/f3e9cc79-13a9-4a84-9920-4efe9926c58a)

