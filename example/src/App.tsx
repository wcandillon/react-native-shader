import * as React from 'react';

import { StyleSheet, View, Dimensions } from 'react-native';
import SkiaViewManager from 'react-native-shader';

const { width } = Dimensions.get('window');

const hue = `
void main() {
  gl_FragColor = vec4(v_tex_coord.x, v_tex_coord.y, 0.5 + 0.5 * cos(u_time * 1000 / 500.0), 1.0);
}`;

export default function App() {
  return (
    <View style={styles.container}>
      <SkiaViewManager
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
