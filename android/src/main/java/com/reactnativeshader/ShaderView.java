package com.reactnativeshader;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.facebook.react.bridge.ReadableMap;

public class ShaderView extends GLSurfaceView {

  private GLRenderer renderer;
  private ReadableMap uniforms;

  public void setSource(String source) {
    renderer = new GLRenderer(source);
    // Set the Renderer for drawing on the GLSurfaceView
    setRenderer(renderer);
  }

  public void setUniforms(ReadableMap uniforms) {
    this.uniforms = uniforms;
  }

  public ShaderView(Context context) {
    super(context);

    // Create an OpenGL ES 2.0 context
    setEGLContextClientVersion(2);

  }

}
