<script setup>
import { ref, reactive } from "vue";
import BaseModal from "../common/BaseModal.vue";
import { regist, checkId } from "@/api/user";
const props = defineProps({
  show: Boolean,
});

const emit = defineEmits(["successRegist", "closeModal"]);
const enableIdLength = ref(false);
const enableChecker = ref(true);
const user = reactive({
  userId: "",
  password: "",
  name: "",
  emailId: "",
  emailDomain: "",
});

const initUser = () => {
  user.userId = "";
  user.password = "";
  passCheck.value = "";
  user.name = "";
  user.emailId = "";
  user.emailDomain = "";
};

const checkInputID = () => {
  // 이미 존재하는 아이디일 경우
  const success = (response) => {
    console.log(response.data);
    enableChecker.value = false;
  };

  // 존재하지 않는 아이디일 경우
  const fail = (error) => {
    console.error(error);
    enableChecker.value = true;
  };

  if (user.userId.length >= 4) {
    enableIdLength.value = true;
    checkId(user.userId, success, fail);
  } else {
    enableIdLength.value = false;
    enableChecker.value = false;
  }
};

const RegistUser = () => {
  const success = (response) => {
    alert("회원가입을 완료하였습니다!");
    initUser();
    emit("closeModal");
  };

  const fail = (error) => {
    console.error(error);
  };

  if (user.password === passCheck.value) {
    regist(user, success, fail);
  }
};

const passCheck = ref("");
</script>

<template>
  <BaseModal :show="show">
    <template v-slot:header
      >JOIN
      <button
        type="button"
        class="btn-close"
        @click="$emit('closeModal')"></button>
    </template>
    <template v-slot:body>
      <div class="col-lg-8 col-md-10 col-sm-12 pl-3">
        <div class="mb-3" style="width: 400px">
          <label for="username" class="form-label">NAME : </label>
          <input type="text" class="form-control" v-model="user.name" />
        </div>
        <div class="mb-3" style="width: 400px">
          <label for="userid" class="form-label">ID : </label>
          <input
            type="text"
            class="form-control"
            v-model="user.userId"
            @change="checkInputID" />
        </div>
        <div id="result-view" class="mb-3" v-show="enableIdLength">
          <p style="color: green" v-show="enableChecker">
            사용할 수 있는 아이디입니다.
          </p>
          <p style="color: red" v-show="!enableChecker">
            이미 존재하는 아이디입니다.
          </p>
        </div>
        <div id="result-view" class="mb-4" v-show="!enableIdLength">
          <p style="color: red">아이디는 4자 이상이어야 합니다.</p>
        </div>
        <div class="mb-4" style="width: 400px">
          <label for="userpwd" class="form-label">PASSWORD : </label>
          <input type="password" class="form-control" v-model="user.password" />
        </div>
        <div class="mb-4" style="width: 400px">
          <label for="pwdcheck" class="form-label">CHECK PASSWORD : </label>
          <input type="password" class="form-control" v-model="passCheck" />
        </div>
        <div class="mb-4" style="width: 400px">
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
      </div>
    </template>
    <template v-slot:footer>
      <div class="col-auto text-center mt-5">
        <button
          type="submit"
          class="btn btn-outline-primary mb-3 mx-2"
          @click="RegistUser">
          SIGN UP
        </button>
        <button
          type="button"
          class="btn btn-outline-success mb-3 mx-2"
          @click="initUser">
          INIT
        </button>
      </div>
    </template>
  </BaseModal>
</template>

<style scoped></style>
