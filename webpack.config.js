const path = require('path')
const baseDirPath = './src/main/resources/static'
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
    entry: baseDirPath + "/main.js",
    output: {
        path: path.resolve(baseDirPath, "dist"),
        filename: "bundle.js"
    },
    module: {
        rules: [
            {
                test: /\.vue$/,
                use: 'vue-loader'
            },
            {
                test: /\.js$/,
                use: 'babel-loader'
            },
            {
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    'css-loader'
                ]
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin()
    ]
}