import webpack from 'webpack';

export default {
  entry: "./index.mjs",
  output: {
    filename: `index.js`,
    libraryTarget: 'commonjs'
  },
  target: 'node',
  mode: 'production'
};