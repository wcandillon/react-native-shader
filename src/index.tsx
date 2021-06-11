import { requireNativeComponent, ViewStyle } from 'react-native';

type SkiaProps = {
  source: string;
  uniforms: object;
  style: ViewStyle;
};

export const SkiaViewManager = requireNativeComponent<SkiaProps>('SkiaView');

export default SkiaViewManager;
