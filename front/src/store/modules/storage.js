import { store } from "@/store/store";
import axios2 from "axios";
const storage = {
  async fetch(orderState) {
    var setState = true;
    const arr = [];
    if (orderState == undefined || orderState == null) {
      setState = true;
    } else if (orderState != null || orderState != "") {
      setState = orderState;
    }
    await axios2.get("api/v1/todos/" + setState).then((res) => {
      const jsonData = res.data;
      if (jsonData.length > 0) {
        for (let i = 0; i < jsonData.length; i++) {
          arr.push(jsonData[i]);
        }
      }
    });
    store.state.todoItems = arr;
  },

  fetchName() {
    if (localStorage.getItem("userName")) {
      const userName = localStorage.getItem("userName");
      return userName;
    }
  },

  async fetchMenus() {
    const menus = [];

    await axios2.get("/api/v1/getMenus").then((res) => {
      const jsonData = res.data;
      if (jsonData.length > 0) {
        for (let i = 0; i < jsonData.length; i++) {
          menus.push(jsonData[i]);
        }
      }
    });
    store.state.menuItems = menus;
  },

  async getLoginInfo() {
    const loginInfo = {
      username: "",
      email: "",
      picture: "",
    };
    await axios2.get("/api/v1/loginInfo").then((res) => {
      const jsonData = res.data;
      loginInfo.username = jsonData.username;
      loginInfo.email = jsonData.email;
      loginInfo.picture = jsonData.picture;
    });
    store.state.loginInfo = loginInfo;
  },
};

export default storage;
