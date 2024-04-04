<!-- TodoHello.vue -->
<template>
  <div className="hello">
    <p className="hello__guide">
      <span className="hello__guide-text">Nice to meet you!</span>
      <span className="hello__guide-text"
        >I’m going to remember your tasks.</span
      >
    </p>
    <p className="hello__ask">What is your name?</p>
    <label htmlFor="user-name">Name</label>
    <div className="main-input">
      <input
        className="hello__input"
        type="text"
        id="user-name"
        placeholder="Let me know your name"
        v-model="userName"
        v-on:keypress.enter="addUserName(userName)"
      />
      <button className="hello__button">
        <span className="blind">Enter</span>
      </button>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  data() {
    return {
      userName: "",
    };
  },
  methods: {
    ...mapMutations({
      addUserName: "setUserName",
    }),
  },
};
</script>

<style lang="scss">
.hello {
  //max-width: $max-width;
  max-width: 720px;
  margin: 0 auto;
  letter-spacing: 0.03rem;
  color: #fff;

  label {
    display: none;
  }

  &__guide {
    padding-top: 2.4rem;
    margin-bottom: 6rem;
    font-size: 1.6rem;
    line-height: 1.75;
    letter-spacing: 0.03rem;
  }

  &__guide-text {
    display: block;
  }

  &__ask {
    font-size: 2.8rem;
    font-weight: bold;
    margin-bottom: 4rem;
  }
}

//애니메이션 관리
.hello {
  &__guide-text {
    @for $i from 1 through 2 {
      &:nth-child(#{$i}) {
        //@include animation(fadeShow, 450ms, 1, #{$i * 200}ms);
        -webkit-animation-delay: #{$i * 200}ms;
        -webkit-animation-duration: 450ms;
        -webkit-animation-name: fadeShow;
        -webkit-animation-fill-mode: both;
        -moz-animation-delay: #{$i * 200}ms;
        -moz-animation-duration: 450ms;
        -moz-animation-name: 1;
        -moz-animation-fill-mode: both;
        -o-animation-delay: #{$i * 200}ms;
        -o-animation-duration: 450ms;
        -o-animation-name: 1;
        -o-animation-fill-mode: both;
        animation-delay: #{$i * 200}ms;
        animation-duration: 450ms;
        animation-name: 1;
        animation-fill-mode: both;
        animation-iteration-count: 1;
      }
    }
  }
  &__ask {
    //@include animation(fadeShow, 750ms, 1, 900ms);
    -webkit-animation-delay: 900ms;
    -webkit-animation-duration: 750ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 900ms;
    -moz-animation-duration: 750ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 900ms;
    -o-animation-duration: 750ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 900ms;
    animation-duration: 750ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
  .main-input {
    //@include animation(fadeShow, 850ms, 1, 1100ms);
    -webkit-animation-delay: 1100ms;
    -webkit-animation-duration: 850ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 1100ms;
    -moz-animation-duration: 850ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 1100ms;
    -o-animation-duration: 850ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 1100ms;
    animation-duration: 850ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
}
</style>
