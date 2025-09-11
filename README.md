# react-native-get-app-list

A React Native library to retrieve a list of all installed applications on the device, including their package names and version codes.

## Note: This Library only available for Android

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Reference](#api-reference)
- [Contributing](#contributing)
- [License](#license)

## Installation

To install the package, use npm or yarn:

```sh
npm install react-native-get-app-list
```

or

```sh
yarn add react-native-get-app-list
```

## Usage

To retrieve the list of installed apps, including their package names and version codes, use the `getInstalledApps` function:

```javascript
import { getInstalledApps } from 'react-native-get-app-list';

// ...

async function fetchInstalledApps() {
  try {
    const result = await getInstalledApps();
    console.log(result); // Displays the list of installed apps
  } catch (error) {
    console.error('Error fetching installed apps:', error);
  }
}

// Call the function to fetch installed apps
fetchInstalledApps();
```

### Example Output

The output will be an array of objects, each containing the `packageName`, `versionName`, and `versionCode` of the installed apps:

```json
[
  {
    "icon":"iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACA...",
    "appName": "Calculator",
    "packageName": "com.miui.calculator",
    "versionName": "15.0.14"
  },
  {
      "icon":"iVBORw0KGgoAAAA...",
      "appName": "Scanner",
      "packageName": "com.xiaomi.scanner",
      "versionName": "13.2204.5"
  },
  {
      "icon":"iVBORw0KGgoAAAA...",
      "appName": "Gallery Editor",
      "packageName": "com.miui.mediaeditor",
      "versionName": "1.8.0.12-global"
  },
  {
      "icon":"iVBORw0KGgoAAAA...",
      "appName": "Find My Device",
      "packageName": "com.google.android.apps.adm",
      "versionName": "3.1.173-1"
  }
]
```

## API Reference

### `getInstalledApps()`

Returns a promise that resolves with an array of installed applications, including their package names and version codes.

**Returns:** `Promise<Array<Object>>`

**Example:**

```javascript
const apps = await getInstalledApps();
```

## Contributing

Contributions are welcome! Please see the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
