import Vuex from "vuex";
import storage from "./modules/storage";
import * as getters from "./modules/getter";
import * as mutations from "./modules/mutations";

export const store = new Vuex.Store({
  state: {
    todoItems: storage.fetch(),
    userName: storage.fetchName(),
    todoOldestOrder: true,
    menuItems: storage.fetchMenus(),
  },
  getters: getters,
  mutations: mutations,
});
