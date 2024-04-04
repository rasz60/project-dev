import "./assets/scss/style.scss";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { store } from "./store/store";
import axios from "axios";
// axios 변수 선언

const app = createApp(App);

axios.defaults.headers.get["Content-Type"] = "application/json";
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.put["Content-Type"] = "application/json";
app.use(router).use(store).mount(`#app`);

app.config.globalProperties.axios = axios;
