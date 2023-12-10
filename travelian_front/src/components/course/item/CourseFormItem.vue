<script setup>
import { ref, onMounted, computed } from "vue";
import CourseAttrFormItem from "./CourseAttrFormItem.vue";
const prop = defineProps({
  type: String,
  course: Object,
  attraction: Object,
  attractions: Array,
});

const emit = defineEmits(["evtProcess", "deleteAttr"]);
var appendComponent;

onMounted(() => {
  var ele = document.getElementById("course");
  var order = 0;
  function keycheck(evt) {
    var keyCode = evt.which ? evt.which : event.keyCode;
    if (keyCode == 13) return true;
  }

  appendComponent = (event) => {
    if (keycheck(event)) {
      prop.attraction.order = ++order;
      var mapInput = document.createElement("input");
      mapInput.setAttribute("class", "message-box");
      mapInput.setAttribute(":placeholder", "title");
      mapInput.setAttribute("v-model", "prop.attraction.title");
      mapInput.setAttribute("v-on:keypress", "appendComponent($event)");
      mapInput.autofocus = true;
      ele.appendChild(mapInput);
      prop.attractions.push(prop.attraction);
    }
  };
});

const getAttractions = computed(() => {
  return prop.attractions;
});

const deleteAttr = (contentId) => {
  emit("deleteAttr", contentId);
}

const validate = () => {
  let isValid = true;
  let errMsg = "";
  emit("evtProcess");
};
</script>

<template>
  <form action="">
    <div>
      <div>
        <input
          type="text"
          id="name"
          name="name"
          v-model="course.name"
          class="message-box"
          placeholder="코스 이름을 지어주세요" />
      </div>
      <div>
        <select class="select-box" v-model="course.date">
          <option value="" selected>몇박몇일</option>
          <option value="당일치기">당일치기</option>
          <option value="1박2일">1박2일</option>
          <option value="2박3일">2박3일</option>
          <option value="3박4일">3박4일</option>
        </select>
      </div>
      <div>
        <select class="select-box" v-model="course.location">
          <option value="전국" selected>전국</option>
          <option value="서울">서울</option>
          <option value="인천">인천</option>
          <option value="대전">대전</option>
          <option value="대구">대구</option>
          <option value="광주">광주</option>
          <option value="부산">부산</option>
          <option value="울산">울산</option>
          <option value="세종특별자치시">세종특별자치시</option>
          <option value="경기도">경기도</option>
          <option value="강원특별자치도">강원특별자치도</option>
          <option value="충청북도">충청북도</option>
          <option value="충청남도">충청남도</option>
          <option value="경상북도">경상북도</option>
          <option value="경상남도">경상남도</option>
          <option value="전라북도">전라북도</option>
          <option value="전라남도">전라남도</option>
          <option value="제주도">제주도</option>
        </select>
      </div>
      <div>
        <input
          type="text"
          id="comment"
          name="comment"
          v-model="course.comment"
          class="message-box"
          placeholder="코스 한줄평 입력" />
      </div>
      <div v-show="getAttractions.length != 0">
        <CourseAttrFormItem
          v-for="attraction in getAttractions"
          :key="attraction.contentid"
          :attraction="attraction"
          @deleteAttr="deleteAttr" />
      </div>
    </div>
  </form>
</template>

<style scoped>
.select-box {
  width: 100%;
  height: 80px;
  margin-bottom: 25px;
  padding-left: 25px;
  background-color: transparent;
  box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.16);
  border: none;
  outline: none;
  color: grey;
}
</style>
