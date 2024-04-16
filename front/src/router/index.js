import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../components/contents/HomeView";
import MoneyReports from "../components/contents/MoneyReports";
import LoginView from "../components/contents/LoginView";
import SigninView from "../components/contents/SigninView";

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
    {
      path: "/signin",
      name: "signin",
      component: SigninView,
    },
  ],
});

export default router;
