# react-native-shader

React Native Shader Library

⚠️ **DOT NOT USE THIS.** We have [something much better in the oven for you](https://www.youtube.com/watch?v=AxLmUsJAH7c)

## Installation

```sh
npm install react-native-shader
```

## Usage

```js
import * as React from 'react';

import { StyleSheet, View, Dimensions } from 'react-native';
import Shader from 'react-native-shader';

const { width } = Dimensions.get('window');

const hue = `
void main() {
  gl_FragColor = vec4(v_tex_coord.x, v_tex_coord.y, 0.5 + 0.5 * cos(u_time * 1000 / 500.0), 1.0);
}`;

export default function App() {
  return (
    <View style={styles.container}>
      <Shader
        source={hue}
        uniforms={{ blue: 0, opacity: 0.5 }}
        style={styles.box}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: width,
    height: width,
    marginVertical: 20,
  },
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
