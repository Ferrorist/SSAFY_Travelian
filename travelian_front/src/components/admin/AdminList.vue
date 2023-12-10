<script setup>
import { ref, onUpdated } from "vue";
import { getUserList } from "@/api/admin";
import AdminListItem from "./item/AdminListItem.vue";
const props = defineProps({ option: String, sort: Boolean });
const users = ref([]);

const getList = () => {
  const success = (response) => {
    if (response.status == 200) {
      users.value = response.data;
    }
  };

  const fail = (error) => {
    console.error(error);
  };

  getUserList(success, fail);
};

getList();

onUpdated(() => {
  // 정렬 기준이 있을 경우
  if (props.option !== "") {
    // 내림차순으로 정렬할 경우
    if (props.sort) {
      switch (props.option) {
        case "id":
          users.value.sort((a, b) => b.userId.localeCompare(a.userId));
          break;
        case "name":
          users.value.sort((a, b) => b.name.localeCompare(a.name));
          break;
      }
    }
    // 그렇지 않다면,
    else {
      switch (props.option) {
        case "id":
          users.value.sort((a, b) => a.userId.localeCompare(b.userId));
          break;
        case "name":
          users.value.sort((a, b) => a.name.localeCompare(b.name));
          break;
      }
    }
  }
});
</script>

<template>
  <table class="table table-hover" style="margin: auto">
    <thead class="thead-dark">
      <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>이메일</th>
        <th>관리자 여부</th>
      </tr>
    </thead>
    <tbody>
      <AdminListItem v-for="user in users" :key="user.userId" :user="user" />
    </tbody>
  </table>
</template>

<style scoped></style>
