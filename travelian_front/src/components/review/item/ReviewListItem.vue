<script setup>
import { useRouter } from "vue-router";
const prop = defineProps({ type: String, review: Object });
const review = prop.review;
const router = useRouter();

const goDetail = () => {
  router.push({
    name: "ReviewDetail",
    params: {
      reviewId: review.reviewId,
    },
  });
};
</script>

<template>
  <tr>
    <td>{{ review.reviewId }}</td>
    <td>
      <a style="text-decoration: none; color: black" @click="goDetail">{{
        review.subject
      }}</a>
    </td>
    <td>{{ review.userId }}</td>
  </tr>
</template>

<style scoped>
td {
  text-align: center;
}
</style>
