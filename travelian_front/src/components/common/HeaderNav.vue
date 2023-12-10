<script setup>
import loginModal from "../modal/loginModal.vue";
import registModal from "../modal/registModal.vue";
import findPasswordModal from "../modal/findPasswordModal.vue";

import { ref } from "vue";
import { useRouter } from "vue-router";
const userInfo = ref(JSON.parse(sessionStorage.getItem("userInfo")));
const showLoginModal = ref(false);
const showRegistModal = ref(false);
const showFindPasswordModal = ref(false);

const router = useRouter();

const successLogin = () => {
  userInfo.value = JSON.parse(sessionStorage.getItem("userInfo"));
  showLoginModal.value = false;
};

const Logout = () => {
  sessionStorage.removeItem("userInfo");
  userInfo.value = null;
  router.replace({ name: "home" });
};

const regist = () => {
  showLoginModal.value = false;
  showRegistModal.value = true;
};
</script>

<template>
  <header class="header_section">
    <div class="container">
      <nav class="navbar navbar-expand-lg custom_nav-container">
        <RouterLink :to="{ name: 'home' }" class="navbar-brand">
          <img src="@/assets/images/travelian_logo.jpg" alt="" />
        </RouterLink>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation">
          <span class="s-1"> </span>
          <span class="s-2"> </span>
          <span class="s-3"> </span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <div
            class="d-flex ml-auto flex-column flex-lg-row align-items-center">
            <ul class="navbar-nav">
              <li class="nav-item">
                <div>
                  <b-dropdown
                    text="TRAVEL"
                    class="btn-secondary m-md-2"
                    variant="btn-secondary">
                    <b-dropdown-item>
                      <RouterLink :to="{ name: 'trip' }" style="color: #4b208c">
                        ✨ Search ✨</RouterLink
                      >
                    </b-dropdown-item>
                    <b-dropdown-item>
                      <RouterLink
                        :to="{ name: 'ThemeMenu' }"
                        style="color: #4b208c">
                        ⛺ Theme ⛺</RouterLink
                      >
                    </b-dropdown-item>
                    <b-dropdown-item>
                      <RouterLink
                        :to="{ name: 'course' }"
                        style="color: #4b208c"
                        >🚝 Course 🚝</RouterLink
                      >
                    </b-dropdown-item>
                  </b-dropdown>
                </div>
              </li>

              <!-- <li class="nav-item">
                <RouterLink
                  :to="{ name: 'trip' }"
                  class="nav-link"
                  style="color: black"
                  aria-current="page"
                  >관광지 검색</RouterLink
                >
              </li> -->
              <li class="nav-item">
                <RouterLink
                  :to="{ name: 'FAQBoard' }"
                  class="nav-link"
                  aria-current="page"
                  >FAQ</RouterLink
                >
              </li>
              <div v-if="userInfo == null">
                <li class="nav-item" style="display: inline-block">
                  <a
                    class="nav-link"
                    aria-current="page"
                    @click="showLoginModal = true"
                    >Login</a
                  >
                </li>
                <li class="nav-item" style="display: inline-block">
                  <a
                    class="nav-link"
                    aria-current="page"
                    @click="showRegistModal = true">
                    Join</a
                  >
                </li>
              </div>
              <div v-else>
                <li class="nav-item" style="display: inline-block">
                  <a class="nav-link" aria-current="page" @click="Logout"
                    >Logout</a
                  >
                </li>
                <li
                  class="nav-item"
                  v-if="userInfo.isAdmin === '1'"
                  style="display: inline-block">
                  <RouterLink
                    class="nav-link"
                    aria-current="page"
                    :to="{ name: 'admin' }">
                    Admin
                  </RouterLink>
                </li>
              </div>
            </ul>
          </div>
        </div>
      </nav>

      <Teleport to="body">
        <loginModal
          :show="showLoginModal"
          @success-login="successLogin"
          @close-modal="showLoginModal = false"
          @regist="regist"
          @open-modal="
            showLoginModal = false;
            showFindPasswordModal = true;
          " />
      </Teleport>

      <Teleport to="body">
        <registModal
          :show="showRegistModal"
          @success-login="successLogin"
          @close-modal="showRegistModal = false" />
      </Teleport>

      <Teleport to="body">
        <findPasswordModal
          :show="showFindPasswordModal"
          @success-login="successLogin"
          @close-modal="showFindPasswordModal = false" />
      </Teleport>
    </div>
  </header>
</template>

<style scoped>
header {
  background-color: #eae6f5;
}

.btn-secondary {
  color: white;
  --bs-btn-color: #fff;
  --bs-body-color: #fff;
  --bs-btn-bg: #4b208c;
  --bs-btn-border-color: #4b208c;
  --bs-btn-hover-color: #fff;
  --bs-btn-hover-bg: #4b208c;
  --bs-btn-hover-border-color: #4b208c;
  --bs-btn-focus-shadow-rgb: 49, 132, 253;
  --bs-btn-active-color: #fff;
  --bs-btn-active-bg: #4b208c;
  --bs-btn-active-border-color: #0a53be;
  --bs-btn-active-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
  --bs-btn-disabled-color: #fff;
  --bs-btn-disabled-bg: #4b208c;
  --bs-btn-disabled-border-color: #4b208c;
  --bs-border-radius: 0.625rem;
}
</style>
