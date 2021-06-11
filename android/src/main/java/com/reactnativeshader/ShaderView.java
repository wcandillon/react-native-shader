package com.reactnativeshader;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.facebook.react.bridge.ReadableMap;

public class ShaderView extends GLSurfaceView {

  private GLRenderer renderer;
  private ReadableMap mUniforms;

  public void setSource(String source) {
    renderer = new GLRenderer(source);
    // Set the Renderer for drawing on the GLSurfaceView
    setRenderer(renderer);
    if (mUniforms != null) {
      renderer.setUniforms(mUniforms);
    }
  }

  public void setUniforms(ReadableMap uniforms) {
    mUniforms = uniforms;
    if (renderer != null) {
      renderer.setUniforms(uniforms);
    }
  }

  public ShaderView(Context context) {
    super(context);

    // Create an OpenGL ES 2.0 context
    setEGLContextClientVersion(2);

  }

}
