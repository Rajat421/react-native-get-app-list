import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-get-app-list' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const GetAppList = NativeModules.GetAppList
  ? NativeModules.GetAppList
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function getInstalledApps(): Promise<any> {
  if (Platform.OS !== 'android') {
    return Promise.reject(
      new Error('This library is only available on Android.')
    );
  }
  return GetAppList.getInstalledApps();
}
