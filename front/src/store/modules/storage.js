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
    console.log(setState);
    await axios2.get("/todos/" + setState).then((res) => {
      const jsonData = res.data;
      console.log(res);
      if (jsonData.length > 0) {
        for (let i = 0; i < jsonData.length; i++) {
          arr.push(jsonData[i]);
        }
      }
    });
    store.state.menuItems = arr;
  },
  fetchName() {
    if (localStorage.getItem("userName")) {
      const userName = localStorage.getItem("userName");
      return userName;
    }
  },
};

export default storage;
