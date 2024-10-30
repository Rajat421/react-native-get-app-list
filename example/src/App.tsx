import React, { useState, useEffect } from 'react';
import { Text, ScrollView } from 'react-native';
import type { InstalledApp } from '../../src/types';
import { getInstalledApps } from 'react-native-get-app-list';

const App: React.FC = () => {
  const [result, setResult] = useState<InstalledApp[]>([]);

  useEffect(() => {
    getInstalledApps().then(setResult);
  }, []);

  return (
    <ScrollView>
      <Text>{JSON.stringify(result, null, 2)}</Text>
    </ScrollView>
  );
};

export default App;
