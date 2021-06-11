import { requireNativeComponent, ViewStyle } from 'react-native';

type ShaderProps = {
  source: string;
  uniforms: object;
  style: ViewStyle;
};

export const ShaderView = requireNativeComponent<ShaderProps>('ShaderView');

export default ShaderView;
