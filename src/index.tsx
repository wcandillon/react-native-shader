import { requireNativeComponent, ViewStyle } from 'react-native';

type ShaderProps = {
  color: string;
  style: ViewStyle;
};

export const ShaderViewManager = requireNativeComponent<ShaderProps>(
'ShaderView'
);

export default ShaderViewManager;
