package com.reactnativeshader;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class ShaderView extends GLSurfaceView {

  private final GLRenderer renderer;

  public ShaderView(Context context) {
    super(context);

    // Create an OpenGL ES 2.0 context
    setEGLContextClientVersion(2);

    renderer = new GLRenderer();
    // Set the Renderer for drawing on the GLSurfaceView
    setRenderer(renderer);
  }

}
