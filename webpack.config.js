const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const baseDirPath = './src/main/resources/static'

module.exports = {
    mode: 'development',
    devtool: "source-map",
    entry: baseDirPath + "/main.js",
    output: {
        path: path.resolve(baseDirPath, "dist"),
        filename: "bundle.js"
    },
    devServer: {
        historyApiFallback: true
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.vue$/,
                use: 'vue-loader',
                // resolve: {
                //     modules: [
                //         './src/main/resources/components'
                //     ]
                // }
            },
            {
                test: /\.css$/,
                use: [
                    'vue-style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.(sass|scss)$/,
                use: [
                    'vue-style-loader',
                    {
                        loader: 'css-loader',
                        options: {
                            sourceMap: true
                        }
                    },
                    {
                        loader: 'sass-loader',
                        options: {
                            indentedSyntax: true,
                            sourceMap: true
                        }
                    }
                ],
                resolve: {
                    modules: [
                        baseDirPath + "/style"
                    ]
                }
            },
            {
                test: /\.ts$/,
                loader: 'ts-loader',
                options: {appendTsSuffixTo: [/\.vue$/]}
            }
        ]
    },
    plugins: [
        new VueLoaderPlugin()
    ],
    resolve: {
        modules: [
            path.resolve('./src/main/resources/'),
            path.resolve(baseDirPath),
            path.resolve(__dirname, 'node_modules')
        ],
        extensions: ['.js', '.vue', '.json', 'scss', 'sass'],
        alias: {
            'vue$': 'vue/dist/vue.esm.js',
        }
    }
}