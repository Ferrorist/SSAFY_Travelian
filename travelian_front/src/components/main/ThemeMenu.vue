<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
</script>

<template>
  <section
    class="service_section layout_padding d-flex flex-column m-0"
    style="background-color: #f3f0f6">
    <div class="container">
      <div class="heading_container">
        <h2>원하는 여행지를 찾아보세요</h2>
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px; margin-bottom: 10px" />
      </div>

      <div class="service_container">
        <RouterLink
          :to="{ name: 'ThemeList', params: { category: 'star' } }"
          class="box">
          <div class="img-box">
            <img
              src="@/assets/images/item_logo/shooting-star.png"
              class="img1"
              alt="" />
          </div>
          <div class="detail-box">
            <h5>별 보러 가는 여행</h5>
            <p class="explain">별빛 속으로, 푸른 우주의 품으로</p>
            <p class="explain">
              우리의 여행은 매혹적인 별들의 이야기를 전하고 있죠. 별들의 미소에
              직접 마주하고, 놀라움에 흠뻑 빠져보세요.
            </p>
          </div>
        </RouterLink>
        <RouterLink
          :to="{ name: 'ThemeList', params: { category: 'drink' } }"
          class="box">
          <div class="img-box">
            <img
              src="@/assets/images/item_logo/drinks.png"
              class="img1"
              alt="" />
          </div>
          <div class="detail-box">
            <h5>술과 함께하는 여행</h5>
            <p class="explain">
              도시의 불빛이 스며드는 밤, 음미의 여정을 떠나다
            </p>
            <p class="explain">
              해가 지는 풍경을 배경으로, 특별한 술을 즐길 수 있는 공간을
              안내합니다.
            </p>
          </div>
        </RouterLink>
        <RouterLink
          :to="{ name: 'ThemeList', params: { category: 'camping' } }"
          class="box">
          <div class="img-box">
            <img
              src="@/assets/images/item_logo/telescope.png"
              class="img1"
              alt="" />
          </div>
          <div class="detail-box">
            <h5>자연 속 캠핑 여행</h5>
            <p class="explain">자연의 속삭임을 따라 미지의 길을 걷다</p>
            <p class="explain">
              숲속 캠핑은 마치 자연의 품에 안겨 새로운 에너지를 얻는 듯한 특별한
              기억을 안겨줍니다
            </p>
          </div>
        </RouterLink>
        <div class="btn-box">
          <RouterLink :to="{ name: 'ThemeList', params: { category: 'more' } }"
            >더보기</RouterLink
          >
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.box {
  background-color: white;
}
.explain {
  color: black;
  font-size: small;
}
</style>
