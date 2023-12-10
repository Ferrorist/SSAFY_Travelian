<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted, computed } from "vue";
import {
  getCourse,
  deleteCourse,
  getCourseAttraction,
  sendCourse,
  likeCourse,
} from "@/api/course";

import CourseDetailMap from "../common/maps/CourseDetailMap.vue";

const route = useRoute();
const router = useRouter();
const course = ref({});
const attractions = ref([]);
const email = ref("");
const userId = JSON.parse(sessionStorage.getItem("userInfo")).userId;

const isLoading = ref(false);

const delCourse = () => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    const success = (response) => {
      if (response.stauts == 200) {
        alert("삭제가 완료 되었습니다.");
      }
      router.push({
        name: "CourseList",
      });
    };

    const fail = (error) => {
      console.log(error);
    };

    deleteCourse(success, fail, course.value.courseId);
  }
};

const pushLike = () => {
  console.log(course.value.courseId);
  const success = (response) => {
    if (response.status == 200) {
      course.value.likes = course.value.likes + 1;
    }
  };

  const fail = (error) => {
    console.log(error);
  };

  likeCourse(success, fail, course.value.courseId);
};

const subscribe = () => {
  isLoading.value = true;
  const success = (response) => {
    isLoading.value = false;
    if (response.status == 200) {
      alert("이메일 전송이 완료 되었습니다.");
      email.value = "";
    }
  };

  const fail = (error) => {
    console.log(error);
  };

  sendCourse(success, fail, courseId, email.value);
};

const courseId = route.params.courseId;

onMounted(() => {
  const success = (response) => {
    if (response.status === 200) {
      course.value = response.data;
      console.log("course's info: ", course.value);

      getCourseAttraction(
        (response) => {
          attractions.value = response.data;
          console.log(attractions.value);
        },
        (error) => console.error(error),
        courseId
      );
    }
  };

  const fail = (error) => {
    console.log(error);
  };

  getCourse(success, fail, courseId);
});
</script>

<template>
  <section class="contact_section layout_padding" style="height: 100%">
    <div class="container" style="margin-bottom: 20px">
      <div class="heading_container">
        <h2>{{ course.name }}</h2>
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px; margin-bottom: 10px"
          alt="" />
      </div>

      <div class="row">
        <div class="d-flex justify-content-end mt-3 mb-3">
          <button class="like" @click="pushLike">
            <img src="@/assets/images/like.png" />
            <span>{{ course.likes }}</span>
          </button>
        </div>

        <CourseDetailMap class="map" :attractions="attractions" />

        <!-- <div class="comment mb-3">
        
          <h1>{{ course.comment }}</h1>
        </div> -->

        <!-- 댓글 부분 -->
        <div class="comment-container">
          <div class="comment-header">
            <span>COMMENT</span>
          </div>
          <p>{{ course.comment }}</p>
        </div>

        <!-- 이메일 입력 및 구독 버튼 -->
        <div class="d-flex justify-content-end">
          <div
            v-show="isLoading"
            class="spinner-border"
            style="margin-right: 15px; margin-top: 10px; color: #4b208c"></div>
          <div class="email_subscribe_container">
            <div class="d-flex">
              <input
                type="email"
                placeholder="Enter your email"
                v-model="email"
                class="me-2" />
              <button @click="subscribe(email)" class="btn2 btn-primary">
                send
              </button>
            </div>
          </div>
        </div>

        <div class="d-flex justify-content-start">
          <RouterLink
            :to="{
              name: 'CourseList',
            }"
            type="button"
            id="btn-list"
            class="btn btn-green mb-3">
            목록
          </RouterLink>
          <div v-if="userId == course.userId">
            <a
              @click="delCourse(course.courseId)"
              type="button"
              id="btn-delete"
              class="btn btn-danger mb-3 ms-1">
              삭제
            </a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.map {
  height: 500px;
  border-radius: 20px;
}
.email_subscribe_container {
  width: 500px;
}

.btn2 {
  margin-top: 0px;
  padding-bottom: 0px;
  padding-top: 0px;
  padding-left: 45px;
  height: 50px;
}

.like {
  border-radius: 10px;
  padding: 5px;
  width: 100px;
  margin-top: 0px;
  border: 1px solid #4b208c;
  background-color: #4b208c;
}

.like:hover {
  background-color: #eae6f5;
  border: 1px solid #4b208c;
}

.like img {
  width: 30px;
}

.like span {
  font-weight: bold;
}

.comment-container {
  background-color: #eae6f5;
  border-radius: 20px;
  padding: 25px;
  /* box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); */
  margin-top: 30px;
  margin-bottom: 30px;
}

.comment-header {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.comment-header img {
  width: 24px;
  margin-right: 10px;
}

.comment-header span {
  font-weight: bold;
  font-size: 18px;
}

.comment p {
  margin: 0;
  line-height: 1.6;
}
</style>
