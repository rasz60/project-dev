import "./assets/scss/style.scss";

import { createApp } from "vue";

import App from "./App.vue";
import router from "./router";

// #1.
//import store from "./store";
//createApp(App).use(store).use(router).mount("#app");

createApp(App).use(router).mount(`#app`);
