const path = require('path')
const baseDirPath = './src/main/resources/static'

module.exports = {
    entry: baseDirPath + "/main.js",
    output: {
        path: path.resolve(baseDirPath, "dist"),
        filename: "bundle.js"
    },
    module: {
        rules: [
            {test: /\.js$/, use: 'babel-loader'},
            {test: /\.vue$/, use: 'vue-loader'}
        ]
    }
}