<script setup>
import { reactive } from "vue";
import { useRoute, useRouter } from "vue-router";
import ReviewFormItem from "./item/ReviewFormItem.vue";
import { putReview, getReview } from "@/api/review.js";

const route = useRoute();
const router = useRouter();

const userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
const contentId = route.params.contentId;

const review = reactive({
  reviewId: null,
  content: "",
  subject: "",
  userId: userInfo.userId,
  contentId: contentId,
});

const modifyReview = () => {
  const success = (response) => {
    if (response.status == 200) {
      alert("수정이 완료되었습니다");

      router.push({
        name: "AttractionDetail",
        params: {
          contentId: contentId,
        },
      });
    }
  };

  const fail = (error) => console.log(error);

  putReview(success, fail, review);
};

const reviewId = route.params.reviewId;

const success = (response) => {
  if (response.status == 200) {
    const r = response.data;

    if (userInfo.userId != r.userId) {
      alert("허용되지 않은 접근입니다.");
      window.history.back();
    }

    review.reviewId = r.reviewId;
    review.content = r.content;
    review.subject = r.subject;
    review.userId = r.userId;
    review.contentId = r.contentId;
  }
};

const fail = (error) => console.log(error);

getReview(success, fail, reviewId);
</script>

<template>
  <div class="container">
    <div class="my-5" style="height: 30px">
      <h1 class="title">Review</h1>
    </div>
    <ReviewFormItem
      type="modify"
      :review="review"
      @evt-process="modifyReview" />
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
