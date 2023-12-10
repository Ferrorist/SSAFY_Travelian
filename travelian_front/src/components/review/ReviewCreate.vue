<script setup>
import { reactive } from "vue";
import { useRouter, useRoute } from "vue-router";
import ReviewFormItem from "@/components/review/item/ReviewFormItem.vue";
import { postReview } from "@/api/review.js";

const router = useRouter();
const route = useRoute();

const userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
const contentId = route.params.contentId;

console.log(contentId);

const review = reactive({
  reviewId: null,
  content: "",
  subject: "",
  userId: userInfo.userId,
  contentId: contentId,
});

const registReview = () => {
  const success = (response) => {
    if (response.status == 200) {
      alert("등록이 완료되었습니다");
      router.push({
        name: "AttractionDetail",
        params: {
          contentId: contentId,
        },
      });
    }
  };

  const fail = (error) => console.log(error);

  postReview(success, fail, review);
};
</script>

<template>
  <div class="container">
    <div class="my-5" style="height: 30px">
      <h1 class="title">Review</h1>
    </div>
    <ReviewFormItem
      type="create"
      :review="review"
      :contentId="contentId"
      @evt-process="registReview" />
  </div>
</template>

<style scoped>
.container {
  height: 68vh;
  width: 100%;
  position: relative;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  background-color: #fff;
}
</style>
