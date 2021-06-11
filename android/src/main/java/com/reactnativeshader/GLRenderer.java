package com.reactnativeshader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.facebook.react.bridge.ReadableMap;

/**
 * Provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 * <ul>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onDrawFrame}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceChanged}</li>
 * </ul>
 */
public class GLRenderer implements GLSurfaceView.Renderer {
  private static final String TAG = "GLRenderer";
  private  Shader mShader;
  private final String mSource;
  private ReadableMap mUniforms;

  GLRenderer(String source){
    mSource = source;
  }

  void setUniforms(ReadableMap uniforms) {
    mUniforms = uniforms;
    if (mShader != null) {
      mShader.setUniforms(uniforms);
    }
  }

  @Override
  public void onSurfaceCreated(GL10 unused, EGLConfig config) {
    // Set the background frame color
    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    mShader = new Shader(mSource);
    if (mUniforms != null) {
      mShader.setUniforms(mUniforms);
    }

  }
  @Override
  public void onDrawFrame(GL10 unused) {
    GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    mShader.draw();
  }

  @Override
  public void onSurfaceChanged(GL10 unused, int width, int height) {
    // Adjust the viewport based on geometry changes,
    // such as screen rotation
    GLES20.glViewport(0, 0, width, height);
    // this projection matrix is applied to object coordinates
    // in the onDrawFrame() method
  }
  /**
   * Utility method for compiling a OpenGL shader.
   *
   * <p><strong>Note:</strong> When developing shaders, use the checkGlError()
   * method to debug shader coding errors.</p>
   *
   * @param type - Vertex or fragment shader type.
   * @param shaderCode - String containing the shader code.
   * @return - Returns an id for the shader.
   */
  public static int loadShader(int type, String shaderCode){
    // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
    // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
    int shader = GLES20.glCreateShader(type);
    // add the source code to the shader and compile it
    GLES20.glShaderSource(shader, shaderCode);
    GLES20.glCompileShader(shader);
    return shader;
  }
  /**
   * Utility method for debugging OpenGL calls. Provide the name of the call
   * just after making it:
   *
   * <pre>
   * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
   * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
   *
   * If the operation is not successful, the check throws an error.
   *
   * @param glOperation - Name of the OpenGL call to check.
   */
  public static void checkGlError(String glOperation) {
    int error;
    while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
      Log.e(TAG, glOperation + ": glError " + error);
      throw new RuntimeException(glOperation + ": glError " + error);
    }
  }
}
