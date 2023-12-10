<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref } from "vue";
import { getReview, deleteReview } from "@/api/review";

const route = useRoute();
const router = useRouter();
const review = ref({});
const userId = JSON.parse(sessionStorage.getItem("userInfo")).userId;
var content = "";
const delReview = () => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    const success = (response) => {
      if (response.stauts == 200) {
        alert("삭제가 완료 되었습니다.");
      }
      router.push({
        name: "AttractionDetail",
      });
    };

    const fail = (error) => {
      alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
      router.push({ name: "AttractionDetail" });
    };

    deleteReview(success, fail, review.value.reviewId);
  }
};

const reviewId = route.params.reviewId;

const success = (response) => {
  if (response.status === 200) {
    review.value = response.data;
    getContent();
  }
};

const fail = (error) => {
  console.log(error);
};
const getContent = () => {
  content = review.value.content.replaceAll("\n", "<br />");
};

getReview(success, fail, reviewId);
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="my-5" style="height: 30px">
          <h2 class="title">
            {{ review.subject }}
          </h2>
        </div>
        <div class="row">
          <div class="col-md-12">
            <div class="clearfix align-content-center text-end">
              <p>
                <span class="fw-bold">
                  <mark>{{ review.userId }}</mark></span
                >
                <br />
              </p>
            </div>
          </div>

          <div class="divider mb-3"></div>
          <div class="text-secondary" v-html="content"></div>
          <div class="divider mt-3 mb-3"></div>
          <div class="d-flex justify-content-end">
            <RouterLink
              :to="{
                name: 'AttractionDetail',
              }"
              type="button"
              id="btn-list"
              class="btn btn-outline-dark mb-3">
              목록
            </RouterLink>
            <div v-if="userId == review.userId">
              <RouterLink
                type="button"
                id="btn-mv-modify"
                class="btn btn-purple mb-3 ms-1"
                :to="{ name: 'ReviewModify' }">
                수정
              </RouterLink>
              <a
                @click="delReview(review.reviewId)"
                type="button"
                id="btn-delete"
                class="btn btn-danger mb-3 ms-1">
                삭제
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
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

.divider {
  background-color: #8a2be2;
}

.text-secondary {
  height: 350px;
  background-color: #eae6f5;
  padding: 20px;
}

mark {
  background-color: #eae6f5;
}
</style>
