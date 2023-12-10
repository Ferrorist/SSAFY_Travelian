<script setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getReviews } from "@/api/review.js";
import ReviewListItem from "./item/ReviewListItem.vue";

const router = useRouter();
const route = useRoute();

const reviews = ref([]);

const props = defineProps({ contentId: String });

const contentId = route.params.contentId;

console.log(contentId);

const success = (response) => {
  if (response.status === 200) {
    reviews.value = response.data;
  }
};

const fail = (error) => {
  console.log(JSON.stringify(error));
};

const movePage = () => {
  console.log(contentId);
  router.push({
    name: "ReviewCreate",
    params: { contentId: contentId },
  });
};

getReviews(success, fail, contentId);
</script>

<template>
  <div class="row d-flex flex-column m-0">
    <div class="row p-3">
      <div class="col-md-2 text-start">
        <button
          type="button"
          id="btn-mv-register"
          class="btn btn-outline-primary btn-sm"
          @click="movePage">
          글쓰기
        </button>
      </div>

      <div style="padding-top: 20px">
        <table id="board-list" class="table table-hover">
          <thead>
            <tr class="text-center">
              <th>No.</th>
              <th>Title</th>
              <th>Nickname</th>
            </tr>
          </thead>
          <tbody>
            <ReviewListItem
              v-for="review in reviews"
              :key="review.reviewNo"
              :review="review" />
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
