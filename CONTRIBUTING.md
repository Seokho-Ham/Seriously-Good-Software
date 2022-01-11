# 코드 기여하기

- seriously-good-software 코드 저장소에 코드를 추가하기 위해서는 [pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests)를 생성해야 합니다.

## 코드 작성

### 브랜치 전략

- `main` 브랜치가 기본 브랜치입니다.
- `main` 브랜치의 최신 커밋을 기반으로 각자 브랜치를 생성하여 작업합니다.
  - 브랜치명 규칙 : `챕터번호-닉네임` (ex. `2-quokka`)

## Pull Request 생성

- 앞서 푸시한 브랜치를 바탕으로 `pull request`를 생성합니다.
- `base branch`는 `main`으로 설정합니다.

### Pull Request 제목

- PR을 `squash and merge`로 병합하면, PR의 내용이 하나의 커밋으로 합쳐져서 `base branch`에 하나의 커밋이 추가됩니다. 이 때, 추가되는 커밋의 이름은 기본적으로 PR의 제목입니다.
- PR 제목 규칙
  - ex) [닉네임] 2장 레퍼런스 구현 요약 정리&연습문제 풀이

### Pull Request 본문

- `pull request`를 생성 시 템플릿이 본문에 자동으로 반영됩니다.
- 각 항목을 알맞은 내용으로 작성해주세요.

## Pull Request 생성 후 코드리뷰

- 코드리뷰를 자유롭게 진행합니다.
- 요구사항이 적절하게 구현되었는지, 코드 상 문제가 될 부분은 없는지, 코드 개선점 등이 있을지 등을 확인하고 코멘트를 남길 수 있습니다.
- 코멘트 작성시 [code suggestion](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/incorporating-feedback-in-your-pull-request)를 이용하면 코드작성자가 리뷰어의 의도를 쉽게 파악할 수 있습니다.

## 코드병합

- 해당 `pull request`의 웹 화면에서 `squash and merge` 버튼을 통해 코드를 병합합니다.
