<script setup>
import { ref, onMounted } from "vue";
const prop = defineProps({
  attraction: Object,
});

const emit = defineEmits(["deleteAttr"]);

const deleteAttr = () => {
  console.log("클릭!");
  console.log("attraction: ", prop.attraction);
  emit("deleteAttr", prop.attraction.contentId);
};
</script>

<template>
  <div id="course" ref="course" class="attr-div">
    <input
      type="text"
      :placeholder="title"
      v-model="prop.attraction.title"
      readonly />
    <img src="@/assets/images/x-mark.png" @click="deleteAttr" />
  </div>
</template>

<style scoped>
.attr-div {
  display: flex;
  width: 100%;
  height: 80px;
  padding-left: 20px;
  padding-right: 20px;
  background-color: transparent;
  box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.3);
  border: none;
  outline: none;
  color: grey;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.attr-div input {
  padding-left: unset;
  box-shadow: unset;
  margin-bottom: unset;
}
</style>
