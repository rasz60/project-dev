import "./assets/scss/style.scss";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

import axios from "axios";

// axios 변수 선언

const app = createApp(App);

app.use(axios);
app.use(router);

app.mount(`#app`);

app.config.globalProperties.$axios = axios;
