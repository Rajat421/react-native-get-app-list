const path = require('path');
const { getDefaultConfig } = require('@react-native/metro-config');
const { getConfig } = require('react-native-builder-bob/metro-config');
const pkg = require('../package.json');

const root = path.resolve(__dirname, '..');
const defaultConfig = getDefaultConfig(__dirname);

/**
 * Metro configuration
 * https://facebook.github.io/metro/docs/configuration
 *
 * @type {import('metro-config').MetroConfig}
 */
module.exports = {
  ...getConfig(defaultConfig, {
    root,
    pkg,
    project: __dirname,
  }),
  resolver: {
    ...defaultConfig.resolver,
    extraNodeModules: {
      // redirect react-native to the root installation
      'react-native': path.resolve(root, 'node_modules/react-native'),
    },
  },
  watchFolders: [
    ...defaultConfig.watchFolders,
    root,
    path.resolve(root, 'node_modules'),
  ],
};
