import "vue-final-modal/style.css";

import { createApp } from "vue";
import { createPinia } from "pinia";
import { createVfm } from "vue-final-modal";
import { BootstrapVue } from "bootstrap-vue";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import "./assets/css/style.css";
const app = createApp(App);
const vfm = createVfm();
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(pinia);
app.use(router);
app.use(vfm);
app.use(BootstrapVue);

app.mount("#app");
