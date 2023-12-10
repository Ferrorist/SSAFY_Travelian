<script setup></script>

<template>
  <div class="container">
    <div class="my-5" style="height: 30px; margin-bottom: 50px">
      <h1 class="title">
        FAQ
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px"
          alt=""
        />
      </h1>
    </div>

    <div
      style="visibility: hidden; position: absolute; width: 0px; height: 0px"
    >
      <svg xmlns="http://www.w3.org/2000/svg">
        <symbol viewBox="0 0 24 24" id="expand-more">
          <path d="M16.59 8.59L12 13.17 7.41 8.59 6 10l6 6 6-6z" />
          <path d="M0 0h24v24H0z" fill="none" />
        </symbol>
        <symbol viewBox="0 0 24 24" id="close">
          <path
            d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12z"
          />
          <path d="M0 0h24v24H0z" fill="none" />
        </symbol>
      </svg>
    </div>

    <details open>
      <summary>
        Travelian은 어떤 서비스인가요?
        <svg
          class="control-icon control-icon-expand"
          width="24"
          height="24"
          role="presentation"
        >
          <use
            xmlns:xlink="http://www.w3.org/1999/xlink"
            xlink:href="#expand-more"
          />
        </svg>
      </summary>
      <p></p>
      <p>Travelian은 여정 속 소중한 순간을 기록하는 서비스입니다</p>
      <p>단순히 추억을 기록할 뿐 아니라</p>
      <p>
        다양한 여행자들의 순간을 공유하고 시간과 공간을 넘어 소통할 수 있습니다.
      </p>
    </details>

    <details>
      <summary>
        현재 제공되는 테마 외의 다른 테마여행은 없나요?
        <svg
          class="control-icon control-icon-expand"
          width="24"
          height="24"
          role="presentation"
        >
          <use
            xmlns:xlink="http://www.w3.org/1999/xlink"
            xlink:href="#expand-more"
          />
        </svg>
      </summary>
      <p></p>
      <p>아쉽게도 아직은 제공되는 세가지 테마만 제공됩니다 ..</p>
      <p>
        앞으로 다양한 테마를 기준으로 검색하는 서비스를 제공할 수 있도록
        하겠습니다 !
      </p>
    </details>

    <details>
      <summary>
        코스 공유 게시판은 어떻게 사용하나요?
        <svg
          class="control-icon control-icon-expand"
          width="24"
          height="24"
          role="presentation"
        >
          <use
            xmlns:xlink="http://www.w3.org/1999/xlink"
            xlink:href="#expand-more"
          />
        </svg>
      </summary>
      <p></p>
      <p>지역, 여행일수를 기준으로 여행지를 기록할 수 있습니다.</p>
      <p>
        지도에 지역명, 여행지를 검색해 해당 장소를 선택하면 코스에 자동으로
        입력됩니다.
      </p>
      <p>코스 게시글에서 선택한 장소를 지도로 확인할 수 있습니다.</p>
    </details>
  </div>
</template>

<style scoped>
.container {
  height: 78vh;
}

body {
  font-family: "IBM Plex Sans", sans-serif;
  background-color: rgba(0, 0, 0, 0.1);
}

h2 {
  margin: 20px auto 80px;
  font-size: 38px;
  font-weight: 300;
  text-align: center;
  letter-spacing: 2px;
  line-height: 1.5;
}

details {
  width: 75%;
  min-height: 5px;
  max-width: 700px;
  padding: 45px 70px 45px 45px;
  margin: 0 auto;
  position: relative;
  font-size: 22px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 15px;
  box-sizing: border-box;
  transition: all 0.3s;
}

details + details {
  margin-top: 20px;
}

details[open] {
  min-height: 50px;
  background-color: #f6f7f8;
  box-shadow: 2px 2px 20px rgba(0, 0, 0, 0.2);
}

details p {
  color: #434444;
  font-weight: 300;
  font-size: medium;
}

summary {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: 500;
  cursor: pointer;
}

summary:focus {
  outline: none;
}
</style>
