// DB 에서 메뉴 정보 가져와서 아래와 같은 형태로 나오도록 연동 필요
/*
export const headerMenus = [
  {
    title: "FRoNTDooR",
    url: "/vue",
  },
];
*/
export const getJson = () => {
  const HeaderMenus = {
    name: "MenuSection",
    data() {
      return {
        data: [],
        id: 1,
      };
    },
    methods: {
      get() {
        this.axios
          .get(
            `https://jsonplaceholder.typicode.com/todos/${this.id}`,
            {},
            {
              header: {
                "Content-Type": "application/json",
              },
            }
          )
          .then((result) => {
            console.log(result.data);
            this.data.push(result.data);
            this.id++;
          })
          .catch((error) => {
            console.log(error);
          })
          .finally(() => {
            console.log("finally");
          });
      },
    },
  };

  HeaderMenus.get();
};
