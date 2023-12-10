<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
</script>

<template>
  <section class="service_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <h2>다양한 여행을 계획해 보세요</h2>
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px; margin-bottom: 10px"
          alt="" />
      </div>
      <div class="service_container">
        <RouterLink :to="{ name: 'trip' }" class="box">
          <div class="img-box">
            <img
              src="@/assets/images/item_logo/mountains.png"
              class="img1"
              alt="" />
          </div>
          <div class="detail-box">
            <h5>여행지 검색</h5>
          </div>
        </RouterLink>
        <RouterLink :to="{ name: 'ThemeMenu' }" class="box">
          <div class="img-box">
            <img
              src="@/assets/images/item_logo/luggage.png"
              class="img1"
              alt="" />
          </div>
          <div class="detail-box">
            <h5>테마별 여행</h5>
          </div>
        </RouterLink>

        <RouterLink :to="{ name: 'course' }" class="box">
          <div class="img-box">
            <img
              src="@/assets/images/item_logo/picture.png"
              class="img1"
              alt="" />
          </div>
          <div class="detail-box">
            <h5>여행 코스</h5>
          </div>
        </RouterLink>
      </div>
    </div>
  </section>
</template>

<style scoped>
.service_container {
  padding: 80px 0;
}
</style>
