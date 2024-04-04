<template lang="">
  <div className="header__menu">
    <ul>
      <li v-for="(menus, key) in data" :key="key">menus.title</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [],
      id: 1,
    };
  },
  method: {
    get() {
      this.$axios
        .get(
          "http://localhost:5858/vue/v1/getMenus",
          {},
          {
            header: {
              "Content-Type": "application/json",
            },
          }
        )
        .then((rst) => {
          console.log("성공", rst);
        })
        .catch((err) => {
          console.log("실패", err);
        });
    },
  },
};
</script>
