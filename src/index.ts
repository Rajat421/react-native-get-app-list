import { NativeModules } from 'react-native';
import type { InstalledApp } from './types';

const { InstalledApps } = NativeModules;

export const getInstalledApps = async (): Promise<InstalledApp[]> => {
  try {
    const appsArray: InstalledApp[] = await InstalledApps.getInstalledApps();
    return appsArray;
  } catch (error) {
    console.error('Failed to fetch installed apps', error);
    return [];
  }
};
