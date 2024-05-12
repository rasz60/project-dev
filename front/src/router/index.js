import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../components/contents/HomeView";
import MoneyReports from "../components/contents/MoneyReports";
import LoginView from "../components/contents/LoginView";
import SignupView from "../components/contents/SignupView";
import UserInfoView from "../components/contents/UserInfoView";
import NoticeBoardView from "../components/contents/NoticeBoardView";

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
      path: "/signup",
      name: "signup",
      component: SignupView,
    },
    {
      path: "/userInfo",
      name: "userInfo",
      component: UserInfoView,
    },
    {
      path: "/notice",
      name: "notice",
      component: NoticeBoardView,
    },
  ],
});

export default router;
