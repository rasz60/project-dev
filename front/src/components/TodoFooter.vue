<!-- TodoFooter.vue -->

<template>
  <footer class="footer">SpringBoot + Vue.js + MySQL</footer>
</template>

<style lang="scss">
.footer {
  //@include flexbox;
  display: -webkit-box;
  display: -moz-box;
  display: -ms-flexbox;
  display: -webkit-flex;
  display: flex;

  //@include justify-content(center);
  -webkit-justify-content: center;
  -moz-justify-content: center;
  -ms-justify-content: center;
  justify-content: center;
  -ms-flex-pack: center;

  margin-top: 8rem;
  margin-bottom: 3px;
  font-size: 1.2rem;
  color: #bdbdbd;

  &__link {
    margin-left: 0.42rem;

    &:hover {
      color: #a7a7a7;
      text-decoration: underline;
    }
  }
}
</style>
