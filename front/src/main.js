import "./assets/scss/style.scss";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import { store } from "./store/store";
import axios from "axios";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

import { fas } from "@fortawesome/free-solid-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";

library.add(fas, fab, far);

const app = createApp(App);

axios.defaults.headers.get["Content-Type"] = "application/json";
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.put["Content-Type"] = "application/json";
app
  .use(router)
  .use(store)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount(`#app`);

app.config.globalProperties.axios = axios;
