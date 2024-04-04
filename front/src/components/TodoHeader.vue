<template>
  <header class="header">
    <h1 class="logo">My Todo</h1>
    <p class="date">{{ timestamp }}</p>
  </header>
</template>

<script>
export default {
  data() {
    return {
      timestamp: "",
    };
  },
  created() {
    const now = new Date();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    const weekList = new Array(
      "Sun.",
      "Mon.",
      "Tue.",
      "Wed.",
      "Thu.",
      "Fri.",
      "Sat."
    );
    const week = weekList[now.getDay()];
    this.timestamp = `${month}/${date} ${week}`;
  },
};
</script>

<style lang="scss">
.header {
  //max-width: $max-width;
  max-width: 720px;
  margin: 0 auto;
  padding: 20px 0 55px;
  color: #fff;
  /*@include flexbox;*/
  display: -webkit-box;
  display: -moz-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;

  //@include align-items(center);
  -webkit-align-items: center;
  -moz-align-items: center;
  -ms-align-items: center;
  align-items: center;

  //@include justify-content(space-between);
  -webkit-justify-content: space-between;
  -moz-justify-content: space-between;
  -ms-justify-content: space-between;
  justify-content: space-between;
  -ms-flex-pack: space-between;

  &__date {
    letter-spacing: 0.02rem;
    font-size: 1.2rem;
  }
}

.logo {
  width: 5.8rem;
  height: 1.6rem;
  background-size: 100%;
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-image: icon("logo", "ffffff");
}
</style>
