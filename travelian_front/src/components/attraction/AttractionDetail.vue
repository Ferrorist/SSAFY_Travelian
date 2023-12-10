<script setup>
import { ref, onBeforeMount, onMounted, computed } from "vue";
import { useRoute } from "vue-router";
import { getAttraction } from "@/api/attraction.js";
import AttractionDetailMap from "../common/maps/AttractionDetailMap.vue";
import ReviewList from "@/components/review/ReviewList.vue";

const attraction = ref({});
const contentId = ref(0);
const route = useRoute();

contentId.value = route.params.contentId;

onBeforeMount(() => {
  const success = (response) => {
    attraction.value = response.data;
  };

  const fail = (error) => {
    console.error(error);
  };

  getAttraction(success, fail, contentId.value);
});

const computedAttraction = computed(() => {
  return attraction.value;
});
</script>

<template>
  <div class="container">
    <div class="heading_container">
      <h2>{{ attraction.title }}</h2>
    </div>

    <div class="row" style="height: calc(100% - 80px)">
      <!-- 지도와 이미지를 반반으로 표시하는 부분 -->
      <div class="col-md-6" style="height: 100%">
        <!-- 지도 -->
        <div class="row m-1" style="height: 50%">
          <AttractionDetailMap
            style="width: 100%; height: 100%"
            :attraction="computedAttraction" />
        </div>

        <div class="row" v-show="attraction.firstImage != ''">
          <div class="attraction-img">
            <img :src="attraction.firstImage" />
          </div>
        </div>
      </div>
      <!-- 게시판 -->
      <div class="col-md-6">
        <ReviewList :contentId="contentId" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  height: 98vh;
  margin-bottom: 10%;
  padding: 5%;
}

.container h1 {
  font-weight: bold;
}

.attraction-img img {
  width: 100%;
  aspect-ratio: 1/1;
  object-fit: cover;
}
</style>
