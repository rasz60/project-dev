import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../components/contents/HomeView";
import MoneyReports from "../components/contents/MoneyReports";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/vue",
      name: "home",
      component: HomeView,
    },
    {
      path: "/vue/moneyReports",
      name: "moneyReports",
      component: MoneyReports,
    },
  ],
});

export default router;
