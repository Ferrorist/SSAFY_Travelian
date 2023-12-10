<script setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getThemeList } from "@/api/board.js";
import ThemeListItem from "./item/ThemeListItem.vue";

const router = useRouter();
const route = useRoute();

const category = route.params.category;

const getTitle = (category) => {
  if (category == "star") return "별 보러 가는 여행";
  else if (category == "drink") return "술과 함께하는 여행";
  else if (category == "camping") return "자연 속 캠핑 여행";
};

const title = getTitle(category);
const attractions = ref([]);
const success = (response) => {
  if (response.status === 200) {
    console.log(category);
    attractions.value = response.data;
  }
};

const fail = (error) => {
  console.log(JSON.stringify(error));
};

getThemeList(success, fail, category);
</script>

<template>
  <div style="background-color: #f3f0f6">
    <div style="height: 70px"></div>
    <div class="container pb-5">
      <div class="heading_container">
        <h2>{{ title }}</h2>
      </div>

      <div class="row">
        <ThemeListItem
          v-for="attraction in attractions"
          :key="attraction.contentId"
          :attraction="attraction" />
      </div>
    </div>
  </div>
</template>

<style scoped>
#search {
  border: 1px solid black;
  border-radius: 0%;
  width: 400px;
}

.input-group button {
  -webkit-box-flex: 1;
  border: none;
  display: inline-block;
  padding: 10px 30px;
  background-color: #4b208c;
  color: #ffffff;
  border-radius: 0;
  font-size: 15px;
  text-transform: uppercase;
}

.form-search {
  display: flex;
  float: right;
}
.form-select {
  border: 1px solid black;
  border-radius: 0%;
  width: 300px;
}
</style>
