import React, { useEffect, useState } from 'react';

import { StyleSheet, View, Dimensions } from 'react-native';
import Shader from 'react-native-shader';

const { width } = Dimensions.get('window');

const hue = `
precision mediump float;
varying vec2 v_tex_coord;
uniform float blue;

void main() {
  gl_FragColor = vec4(v_tex_coord.x, v_tex_coord.y, blue, 1.0);
}`;

export default function App() {
  const [blue, setBlue] = useState(1);
  useEffect(() => {
    setTimeout(() => {
      setBlue(0);
      console.log('DONE');
    }, 2000);
  }, []);
  return (
    <View style={styles.container}>
      <Shader source={hue} uniforms={{ blue }} style={styles.box} />
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
