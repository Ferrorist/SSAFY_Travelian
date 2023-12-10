<script setup>
import CourseFormItem from "./item/CourseFormItem.vue";
import { ref, reactive, computed } from "vue";
import { useRouter, onBeforeRouteLeave } from "vue-router";
import { postCourse } from "@/api/course.js";
import CourseCreateMap from "@/components/common/maps/CourseCreateMap.vue";
const router = useRouter();
const userInfo = JSON.parse(sessionStorage.getItem("userInfo"));

const attractions = ref([]); // 코스 관광지 목록?
const isCreated = ref(false);
const course = reactive({
  name: "",
  date: "",
  location: "전국",
  comment: "",
  userId: userInfo.userId,
});

const attraction = reactive({
  contentId: "",
  title: "",
  courseId: "",
  order: 0,
  userId: userInfo.userId,
});

// 코스 등록
const registCourse = () => {
  const success = (response) => {
    if (response.status == 200) {
      alert("등록이 완료되었습니다");
      isCreated.value = true;
      router.push({
        name: "CourseList",
      });
    }
  };
  const fail = (error) => console.log(error);

  if (attractions.value.length == 0) {
    alert("등록된 관광지가 없습니다!");
    return;
  }

  postCourse(success, fail, course, attractions.value);
};

const Addattraction = (area) => {
  if(attractions.value.length >= 5){
    alert("최대 5개 지정 가능합니다!");
    return;
  }

  for(let i = 0; i < attractions.value.length; i++){
    if(attractions.value[i].contentId == area.contentId) return;
  }
  console.log(area.title);
  const item = ref({
    contentId: area.contentId,
    contentTypeId: area.contentTypeId,
    title: area.title,
    addr1: area.addr1,
    addr2: area.addr2,
    tel: area.tel,
    firstImage: area.firstImage,
    firstImage2: area.firstImage2,
    longitude: area.longitude,
    latitude: area.latitude,
    mlevel: area.mlevel,
    userId: userInfo.userId,
    order: attractions.value.length,
  });

  attractions.value.push(item.value);
};

const getAttractions = computed(() => {
  return attractions.value;
});

const initParams = () => {
  course.name = "";
  course.date = "";
  course.location = "";
  course.comment = "";
  attractions.value = [];
};

const checkisInit = () => {
  return (
    course.name === "" &&
    course.date === "" &&
    course.location === "" &&
    course.comment === "" &&
    attractions.value.length == 0
  );
};

const deleteAttr = (contentId) => {
  attractions.value = attractions.value.filter((area) => {
    return area.contentId != contentId;
  });
}

onBeforeRouteLeave(() => {
  if (checkisInit() || isCreated) return true;
  if (
    window.confirm(
      "해당 페이지를 떠나면 현재 기록이 저장되지 않습니다.\n페이지를 떠나시겠습니까?"
    )
  )
    return true;
  else return false;
});
</script>

<template>
  <section class="contact_section layout_padding">
    <div class="container">
      <div class="heading_container">
        <h2>COURSE</h2>
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px; margin-bottom: 10px"
          alt="" />
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-8">
          <CourseCreateMap
            style="width: 100%; height: 100%"
            @add-attraction="Addattraction" />
        </div>
        <div class="col-md-4">
          <CourseFormItem
            type="create"
            :course="course"
            :attractions="getAttractions"
            @deleteAttr="deleteAttr" />
          <div class="d-flex">
            <button type="button" @click="registCourse">글쓰기</button>
            <button
              type="reset"
              style="background-color: gray"
              @click="initParams">
              리셋
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped></style>
