package com.reactnativeshader;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

/**
 * A two-dimensional triangle for use as a drawn object in OpenGL ES 2.0.
 */
public class Shader {
  private final String vertexShaderCode =
      "attribute vec4 aPosition;" +
      "varying vec2 v_tex_coord;" +
      "void main() {" +
      "  gl_Position = aPosition;" +
      "v_tex_coord = vec2((aPosition.x + 1.0) / 2.0, (aPosition.y + 1.0) / 2.0);" +
      "}";

  private final int mProgram;
  private int mPositionHandle;
  private ReadableMap mUniforms = null;

  private FloatBuffer vertexBuffer;
  private ShortBuffer drawListBuffer;
  static final int COORDS_PER_VERTEX = 2;

  static float vertices[] = {
    -1.0f, -1.0f,
    -1, 1.0f,
    1.0f, 1.0f,
    1.0f, -1.0f}; // top right

  private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
  private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

  void setUniforms(ReadableMap uniforms) {
    mUniforms = uniforms;
  }

  /**
   * Sets up the drawing object data for use in an OpenGL ES context.
   */
  public Shader(String source) {

    // prepare shaders and OpenGL program
    int vertexShader = GLRenderer.loadShader(
      GLES20.GL_VERTEX_SHADER, vertexShaderCode);
    int fragmentShader = GLRenderer.loadShader(
      GLES20.GL_FRAGMENT_SHADER, source);
    mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
    GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
    GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
    GLES20.glLinkProgram(mProgram);                  // create OpenGL program executables

    ByteBuffer bb = ByteBuffer.allocateDirect(
      // (# of coordinate values * 4 bytes per float)
      vertices.length * 4);
    bb.order(ByteOrder.nativeOrder());
    vertexBuffer = bb.asFloatBuffer();
    vertexBuffer.put(vertices);
    vertexBuffer.position(0);

    // initialize byte buffer for the draw list
    ByteBuffer dlb = ByteBuffer.allocateDirect(
      // (# of coordinate values * 2 bytes per short)
      drawOrder.length * 2);
    dlb.order(ByteOrder.nativeOrder());
    drawListBuffer = dlb.asShortBuffer();
    drawListBuffer.put(drawOrder);
    drawListBuffer.position(0);

  }
  /**
   * Encapsulates the OpenGL ES instructions for drawing this shape.
   *
   */
  public void draw() {
    GLES20.glUseProgram(mProgram);

    // get handle to vertex shader's vPosition member
    mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");

    // Enable a handle to the triangle vertices
    GLES20.glEnableVertexAttribArray(mPositionHandle);

    // Prepare the triangle coordinate data
    GLES20.glVertexAttribPointer(
      mPositionHandle, COORDS_PER_VERTEX,
      GLES20.GL_FLOAT, false,
      vertexStride, vertexBuffer);

    if (mUniforms != null) {
      ReadableMapKeySetIterator it = mUniforms.keySetIterator();
      while(it.hasNextKey()) {
        String key = it.nextKey();
          ReadableType type = mUniforms.getType(key);
          int handle = GLES20.glGetUniformLocation(mProgram, key);
          switch (type) {
            case Number:
              GLES20.glUniform1f(handle, (float) mUniforms.getDouble(key));
              break;
          }
      }
    }

    // Draw the square
    GLES20.glDrawElements(
      GLES20.GL_TRIANGLES, drawOrder.length,
      GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

    // Disable vertex array
    GLES20.glDisableVertexAttribArray(mPositionHandle);
  }
}
