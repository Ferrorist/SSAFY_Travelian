<script setup>
import { ref } from "vue";
import AdminList from "@/components/admin/AdminList.vue";
import createUserModal from "@/components/modal/createUserModal.vue";
const options = ref("id");
const desc = ref(false);

const showCreateUserModal = ref(false);
</script>

<template>
  <main>
    <div style="height: 50px"></div>
    <div class="heading_container">
      <h2>
        Admin<img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px"
          alt="" />
      </h2>
    </div>

    <div class="container-fluid p-3 text-center" style="max-width: 1300px">
      <form method="post" action="${root}/user?cmd=list">
        <div style="float: left">
          <select id="sort" v-model="options">
            <option value="" disabled>정렬 기준</option>
            <option value="id">아이디</option>
            <option value="name">이름</option>
          </select>
        </div>
        <div style="float: left; margin: 0 10px">
          <input id="reverseOrder" type="checkbox" v-model="desc" />
          <label for="reverseOrder">내림차순</label>
        </div>
      </form>
      <button
        class="btn btn-outline-primary mb-3"
        style="float: right"
        @click="showCreateUserModal = true">
        회원 등록하기
      </button>
      <AdminList :option="options" :sort="desc" />
    </div>
  </main>

  <Teleport to="body">
    <createUserModal 
      :show="showCreateUserModal"
      @close-modal="showCreateUserModal = false"
    />
  </Teleport>
</template>

<style scoped></style>
