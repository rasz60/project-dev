<!-- TodoTitle.vue -->

<template>
  <div class="title">
    <p class="title__message">{{ message }}</p>
    <p class="title__task">
      You've got
      <span class="title__task-total">{{ taskTotal }}</span> tasks today.
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      message: "Hello, Jaewon.",
      taskTotal: 10,
    };
  },
};
</script>

<style lang="scss">
.title {
  //max-width: $max-width;
  max-width: 720px;
  margin: 0 auto;
  letter-spacing: 0.03rem;
  color: #fff;

  &__text {
    cursor: default;
    font-size: 1.6rem;
    //@include flexbox;
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
    //@include flex-wrap(wrap);
    -webkit-flex-wrap: wrap;
    -moz-flex-wrap: wrap;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    //@include ellipsis();
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 0;
    -webkit-box-orient: vertical;
  }

  .wrap {
    position: relative;
    white-space: nowrap;
    overflow: hidden;
  }

  &__message {
    display: block;
    min-height: 2.7rem;
    margin-right: 0.4rem;
    //@include flex-shrink(0);
    -webkit-flex-shrink: 0;
    -moz-flex-shrink: 0;
    -ms-flex-shrink: 0;
    -ms-flex-negative: 0;
    flex-shrink: 0;
  }

  &__name {
    display: block;
    background: 0;
    border: 0;
    outline: 0;
    color: inherit;
    font-size: inherit;
    min-width: 1rem;
    min-height: 2.7rem;
    overflow: hidden;
    //@include flex-shrink(1);
    -webkit-flex-shrink: 1;
    -moz-flex-shrink: 1;
    -ms-flex-shrink: 1;
    -ms-flex-negative: 1;
    flex-shrink: 1;

    br {
      display: none;
    }

    &:hover {
      text-decoration: underline;
      cursor: text;
    }
    &:focus {
      opacity: 0.8;
      // outline: 1px solid #fafafa;
      background: rgba(255, 255, 255, 0.15);
      text-decoration: underline;
    }
  }

  &__task {
    margin-top: 3.5rem;
    margin-bottom: 5rem;
    font-weight: bold;

    &-top {
      display: block;
      font-size: 2.6rem;
    }

    &-count {
      display: block;
      font-size: 5.4rem;
    }

    &-total {
      font-size: 50%;
    }

    &-bottom {
      display: block;
      font-size: 2.8rem;
      line-height: 50%;
    }
  }
}

//애니메이션 관리
.title {
  &__text {
    //@include animation(fadeShow, 500ms);
    -webkit-animation-delay: 0;
    -webkit-animation-duration: 500ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 0;
    -moz-animation-duration: 500ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 0;
    -o-animation-duration: 500ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 0;
    animation-duration: 500ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
  &__task-top {
    //@include animation(fadeShow, 600ms, 1, 400ms);
    -webkit-animation-delay: 400ms;
    -webkit-animation-duration: 600ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 400ms;
    -moz-animation-duration: 600ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 400ms;
    -o-animation-duration: 600ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 400ms;
    animation-duration: 600ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
  &__task-count {
    //@include animation(fadeShow, 600ms, 1, 550ms);
    -webkit-animation-delay: 550ms;
    -webkit-animation-duration: 600ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 550ms;
    -moz-animation-duration: 600ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 550ms;
    -o-animation-duration: 600ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 550ms;
    animation-duration: 600ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
  &__task-bottom {
    //@include animation(fadeShow, 600ms, 1, 700ms);
    -webkit-animation-delay: 700ms;
    -webkit-animation-duration: 600ms;
    -webkit-animation-name: fadeShow;
    -webkit-animation-fill-mode: both;
    -moz-animation-delay: 700ms;
    -moz-animation-duration: 600ms;
    -moz-animation-name: fadeShow;
    -moz-animation-fill-mode: both;
    -o-animation-delay: 700ms;
    -o-animation-duration: 600ms;
    -o-animation-name: fadeShow;
    -o-animation-fill-mode: both;
    animation-delay: 700ms;
    animation-duration: 600ms;
    animation-name: fadeShow;
    animation-fill-mode: both;
    animation-iteration-count: 1;
  }
}
</style>
