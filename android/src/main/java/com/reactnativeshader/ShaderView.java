package com.reactnativeshader;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.facebook.react.bridge.ReadableMap;

public class ShaderView extends GLSurfaceView {

  private final GLRenderer renderer;
  private String shader;
  private ReadableMap uniforms;

  public void setShader(String shader) {
    this.shader = shader;
  }

  public void setUniforms(ReadableMap uniforms) {
    this.uniforms = uniforms;
  }

  public ShaderView(Context context) {
    super(context);

    // Create an OpenGL ES 2.0 context
    setEGLContextClientVersion(2);

    renderer = new GLRenderer();
    // Set the Renderer for drawing on the GLSurfaceView
    setRenderer(renderer);
  }

}
