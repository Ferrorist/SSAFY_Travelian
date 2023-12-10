<script setup>
import { ref, reactive } from "vue";
import BaseModal from "../common/BaseModal.vue";
import { login } from "@/api/user";
const props = defineProps({
  show: Boolean,
});

const emit = defineEmits(["successLogin", "closeModal", "regist", "openModal"]);

const getError = ref(false);
const user = reactive({
  userId: "",
  password: "",
});

const loginUser = () => {
  const success = (response) => {
    const userlogin = response.data;
    window.sessionStorage.setItem("userInfo", JSON.stringify(userlogin));
    user.userId = "";
    user.password = "";
    emit("successLogin");
  };

  const fail = (error) => {
    console.error(error);
    user.password = "";
    getError.value = true;
  };

  login(user, success, fail);
};
</script>

<template>
  <BaseModal :show="show">
    <template v-slot:header
      >Login
      <button
        type="button"
        class="btn-close"
        @click="$emit('closeModal')"></button>
    </template>
    <template v-slot:body>
      <div class="row mb-3 mt-3">
        <div class="col-md-12">
          <label class="form-label">ID:</label>
          <input type="text" class="form-control" v-model="user.userId" />
        </div>
      </div>
      <div class="row mb-3 mt-6">
        <div class="col-md-12">
          <label class="form-label">PASSWORD:</label>
          <input
            type="password"
            class="form-control"
            v-model="user.password"
            @keyup.enter="loginUser" />
        </div>
      </div>
      <p style="color: red" v-if="getError">
        존재하지 않는 아이디이거나, 일치하지 않는 비밀번호입니다.
      </p>
    </template>
    <template v-slot:footer>
      <div style="margin-right: 170px; padding-top: 10px">
        <p style="color: grey" @click="$emit('openModal')">forget password?</p>
      </div>
      <div>
        <button class="btn btn-sm" @click="$emit('regist')">JOIN</button>
      </div>
      <div>
        <button class="btn btn-outlined-primary btn-sm" @click="loginUser">
          LOGIN
        </button>
      </div>
    </template>
  </BaseModal>
</template>

<style scoped></style>
