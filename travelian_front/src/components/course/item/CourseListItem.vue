<script setup>
import { useRouter } from "vue-router";
const prop = defineProps({ type: String, course: Object });
const course = prop.course;
const router = useRouter();

const goDetail = () => {
  router.push({
    name: "CourseDetail",
    params: {
      courseId: course.courseId,
    },
  });
};
</script>

<template>
  <tr @click="goDetail">
    <th>{{ course.courseId }}</th>
    <td>
      <a style="text-decoration: none; color: black">{{ course.name }}</a>
    </td>
    <td>{{ course.userId }}</td>
    <td>{{ course.date }}</td>
    <td>{{ course.location }}</td>
  </tr>
</template>

<style scoped>
td {
  text-align: center;
}
</style>
