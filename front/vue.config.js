const { defineConfig } = require("@vue/cli-service");
import { fileURLToPath, URL } from 'node:url';

module.exports = {
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    defineConfig : {
        transpileDependencies: true
    },
};
