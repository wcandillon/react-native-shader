import * as React from 'react';

import { StyleSheet, View, Dimensions } from 'react-native';
import Shader from 'react-native-shader';

const { width } = Dimensions.get('window');

const hue = `
void main() {
  gl_FragColor = vec4(v_tex_coord.x, v_tex_coord.y, blue, 1.0);
}`;

export default function App() {
  return (
    <View style={styles.container}>
      <Shader source={hue} uniforms={{ blue: 1 }} style={styles.box} />
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
