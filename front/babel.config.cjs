module.exports = {
    //debug : build 오류로 수정, file명 .js->.cjs
    //presets: ["@vue/cli-plugin-babel/preset"],
    
    presets: [
        [
            '@babel/preset-env',
            {
                    targets: {
                    node: 'current'
                    }
            }
        ]
    ]
};