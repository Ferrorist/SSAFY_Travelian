<script setup>
import { ref, reactive } from "vue";
import { findpwd } from "@/api/user.js";
import BaseModal from "../common/BaseModal.vue";

const props = defineProps({
  show: Boolean,
});

const InputEmail = ref("");

const user = reactive({
  userId: "",
  emailId: "",
  emailDomain: "",
});

const emit = defineEmits(["closeModal"]);

const findPassword = () => {
  user.emailId = InputEmail.value.split("@")[0];
  user.emailDomain = InputEmail.value.split("@")[1];

  const success = (response) => {
    initInput();
    alert(`해당 유저의 비밀번호는 [${response.data}] 입니다.`);
    emit("closeModal");
  };

  const fail = (error) => {
    initInput();
    alert("찾을 수 없는 유저입니다.");
  };

  findpwd(success, fail, user);
};

const initInput = () => {
  user.userId = "";
  user.emailId = "";
  user.emailDomain = "";
  InputEmail.value = "";
};
</script>

<template>
  <BaseModal :show="show">
    <template v-slot:header>
      <h3>Find Password</h3>
      <button
        type="button"
        class="btn-close"
        @click="$emit('closeModal')"></button>
    </template>
    <template v-slot:body>
      <div class="mb-3">
        <label for="userId" class="form-label">ID : </label>
        <input
          type="text"
          id="userId"
          class="form-control"
          v-model="user.userId" />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email : </label>
        <input
          type="email"
          id="email"
          class="form-control"
          v-model="InputEmail" />
      </div>
    </template>
    <template v-slot:footer>
      <button @click="findPassword">Find</button>
    </template>
  </BaseModal>
</template>

<style scoped></style>
