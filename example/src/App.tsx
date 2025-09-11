/* eslint-disable react-native/no-inline-styles */
import React, { useState, useEffect } from 'react';
import { Text, ScrollView, Image, View } from 'react-native';
import type { InstalledApp } from '../../src/types';
import { getInstalledApps } from 'react-native-get-app-list';

const App: React.FC = () => {
  const [result, setResult] = useState<InstalledApp[]>([]);

  useEffect(() => {
    getInstalledApps().then(setResult);
  }, []);

  return (
    <ScrollView style={{ flex: 1, padding: 10 }}>
      {result.map((item, index) => (
        <View
          key={index}
          style={{
            flexDirection: 'row',
            alignItems: 'center',
            marginBottom: 15,
            backgroundColor: '#f9f9f9',
            padding: 10,
            borderRadius: 8,
          }}
        >
          {/* Show Base64 as Image */}
          <Image
            source={{ uri: `data:image/png;base64,${item.icon}` }}
            style={{ width: 50, height: 50, marginRight: 12, borderRadius: 8 }}
          />

          {/* Show App Info */}
          <View style={{ flex: 1 }}>
            <Text style={{ fontSize: 16, fontWeight: '600', color: 'black' }}>
              {item.appName}
            </Text>
            <Text style={{ color: 'gray' }}>{item.packageName}</Text>
            <Text style={{ color: 'gray' }}>v{item.versionName}</Text>
          </View>
        </View>
      ))}
    </ScrollView>
  );
};

export default App;
