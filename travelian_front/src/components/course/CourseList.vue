<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { getCourses, getSearchCourses } from "@/api/course.js";
import CourseListItem from "./item/CourseListItem.vue";

const router = useRouter();

const maxLength = 10; // 한 페이지에 표현할 게시글 개수
const pageNo = ref(1);

const courses = ref([]);
const option = ref("");
const keyword = ref("");

const success = (response) => {
  if (response.status == 200) {
    courses.value = response.data;
  }
};

const fail = (error) => {
  console.error(error);
};

getCourses(success, fail, (pageNo.value - 1) * maxLength);

const SearchButton = (integer) => {
  const searchSuccess = (response) => {
    if (response.status === 200) {
      if (response.data.length != 0) {
        courses.value = response.data;
        console.log(courses.value.length);
      } else {
        alert("데이터를 찾을 수 없습니다.");
        pageNo.value -= integer;
      }
    }
  };

  const searchFail = (error) => {
    console.log(error);
  };

  if (keyword.value == "" || option.value == "") {
    getCourses(searchSuccess, searchFail, (pageNo.value - 1) * maxLength);
  } else
    getSearchCourses(
      searchSuccess,
      searchFail,
      keyword.value,
      option.value,
      (pageNo.value - 1) * maxLength
    );
};

const moveCreate = () => {
  router.push({
    name: "CourseCreate",
  });
};

const movePageNo = (integer) => {
  if (pageNo.value + integer <= 0) return;

  pageNo.value += integer;
  SearchButton(integer);
};
</script>

<template>
  <div class="row d-flex flex-column min-vh-100 m-0">
    <div class="my-5" style="height: 30px">
      <h1 class="title">
        COURSE
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px"
          alt="" />
      </h1>
    </div>
    <div class="row p-3">
      <div class="col-md-2 text-start ml-5">
        <button
          type="button"
          id="btn-mv-register"
          class="btn btn-outline-primary btn-sm"
          @click="moveCreate">
          글쓰기
        </button>
      </div>

      <div class="col-md-7 offset-3">
        <div class="d-flex" id="form-search">
          <select
            class="form-select form-select-sm ms-5 me-1 w-50"
            aria-label="검색조건"
            v-model="option">
            <option value="" selected>Search with</option>
            <option value="courseId">No.</option>
            <option value="name">Title</option>
            <option value="user_id">Nickname</option>
          </select>
          <div class="input-group input-group-sm">
            <input
              type="text"
              class="form-control"
              placeholder="keyword"
              v-model="keyword" />
            <button
              id="btn-search"
              class="btn btn-dark"
              type="button"
              @click="SearchButton(0)">
              검색
            </button>
          </div>
        </div>
      </div>

      <div style="padding: 20px 60px 0px 60px">
        <table id="board-list" class="table table-hover">
          <thead>
            <tr class="text-center">
              <th>No.</th>
              <th>Title</th>
              <th>Nickname</th>
              <th>Schedule</th>
              <th>Region</th>
            </tr>
          </thead>
          <tbody>
            <CourseListItem
              v-for="course in courses"
              :key="course.courseId"
              :course="course" />
          </tbody>
        </table>
      </div>
    </div>
    <div style="padding-top: 20px; display: flex; justify-content: center">
      <img
        src="@/assets/images/right_arrow.png"
        style="
          transform: rotate(-0.5turn);
          width: 40px;
          height: 40px;
          margin-inline: 20px;
        "
        @click="movePageNo(-1)"
        v-show="pageNo > 1" />
      <p style="padding-top: 4px; margin-inline: 2px; font-size: 20px">
        {{ pageNo }}
      </p>
      <img
        src="@/assets/images/right_arrow.png"
        style="width: 40px; height: 40px; margin-inline: 20px"
        @click="movePageNo(1)"
        v-show="courses.length >= 10" />
    </div>
  </div>
</template>

<style scoped></style>
