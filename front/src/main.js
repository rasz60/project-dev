import "./assets/scss/style.scss";

import { createApp } from "vue";

import App from "./App.vue";
import router from "./router";

// #1.
//import store from "./store";
//createApp(App).use(store).use(router).mount("#app");

const app = createApp(App);

app.use(router);

app.mount(`#app`);
