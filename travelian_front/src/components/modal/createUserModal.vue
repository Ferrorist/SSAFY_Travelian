<script setup>
import { ref, reactive, watch, computed } from "vue";
import { createUser } from "@/api/admin";
import BaseModal from "@/components/common/BaseModal.vue";
import { HttpStatusCode } from "axios";

const props = defineProps({
  show: Boolean,
});
const checkPass = ref("");
const user = reactive({
  userId: "",
  password: "",
  name: "",
  emailId: "",
  emailDomain: "",
  registerTime: "",
  isAdmin: false,
});

const create = () => {
  const success = (response) => {
    if (response.status == HttpStatusCode.Ok) {
      alert("회원을 생성하였습니다!");
      user.userId = "";
      user.password = "";
      checkPass.value = "";
      user.name = "";
      user.emailId = "";
      user.emailDomain = "";
      user.isAdmin = false;
      emit("closeModal");
    }
  };

  const fail = (error) => {
    alert("회원 등록에 실패하였습니다.");
    console.error(error);
  };

  if (user.password == checkPass.value) {
    createUser(success, fail, user);
  } else {
    alert("비밀번호와 비밀번호 확인란의 내용이 일치하지 않습니다.");
  }
};

const emit = defineEmits(["closeModal"]);
</script>

<template>
  <BaseModal :show="show" id="create_modal">
    <template v-slot:header>
      <h3>회원 등록</h3>
      <button
        type="button"
        class="btn-close"
        @click="$emit('closeModal')"></button>
    </template>
    <template v-slot:body>
      <div class="mb-3">
        <label for="name" class="form-label">NAME : </label>
        <input type="text" id="name" class="form-control" v-model="user.name" />
      </div>
      <div class="mb-3">
        <label for="userId" class="form-label">ID : </label>
        <input
          type="text"
          id="userId"
          class="form-control"
          v-model="user.userId" />
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">PASSWORD : </label>
        <input
          type="password"
          id="password"
          class="form-control"
          v-model="user.password" />
      </div>
      <div class="mb-3">
        <label for="check_password" class="form-label">CHECK PASSWORD : </label>
        <input
          type="password"
          id="check_password"
          class="form-control"
          v-model="checkPass" />
        <p
          class="check_msg"
          v-show="checkPass.length > 0 && user.password != checkPass">
          비밀번호와 일치한지 확인바랍니다.
        </p>
      </div>
      <div class="mb-3">
        <label for="emailid" class="form-label">E-MAIL : </label>
        <div class="input-group">
          <input type="text" class="form-control" v-model="user.emailId" />
          <span class="input-group-text">@</span>
          <select
            class="form-select"
            aria-label="이메일 도메인 선택"
            v-model="user.emailDomain">
            <option value="" disabled>SELECT</option>
            <option value="ssafy.com">SSAFY</option>
            <option value="google.com">GOOGLE</option>
            <option value="naver.com">NAVER</option>
            <option value="kakao.com">KAKAO</option>
          </select>
        </div>
      </div>
    </template>
    <template v-slot:footer>
      <div style="margin-right: 235px">
        <input
          id="isAdmin"
          type="checkbox"
          style="margin-right: 10px"
          v-model="user.isAdmin" />
        <label for="isAdmin">관리자 계정</label>
      </div>
      <div>
        <button style="float: left" @click="create">회원 생성</button>
      </div>
    </template>
  </BaseModal>
</template>

<style scoped>
.check_msg {
  margin-top: 5px;
  font-size: 15px;
  color: grey;
}
</style>
