import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../components/contents/HomeView";
import MoneyReports from "../components/contents/MoneyReports";
import LoginView from "../components/contents/LoginView";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/moneyReports",
      name: "moneyReports",
      component: MoneyReports,
    },
    {
      path: "/formLogin",
      name: "login",
      component: LoginView,
    },
  ],
});

export default router;
