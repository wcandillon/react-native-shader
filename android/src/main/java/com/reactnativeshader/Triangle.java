package com.reactnativeshader;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import android.opengl.GLES20;
/**
 * A two-dimensional triangle for use as a drawn object in OpenGL ES 2.0.
 */
public class Triangle {
  private final String vertexShaderCode =
      "attribute vec4 vPosition;" +
      "void main() {" +
      // the matrix must be included as a modifier of gl_Position
      // Note that the uMVPMatrix factor *must be first* in order
      // for the matrix multiplication product to be correct.
      "  gl_Position = vPosition;" +
      "}";
  private final String fragmentShaderCode =
    "precision mediump float;" +
      "void main() {" +
      "  gl_FragColor = vec4(0.0, 0.0, 1.0, 1.0);" +
      "}";
  private final FloatBuffer vertexBuffer;
  private final int mProgram;
  private int mPositionHandle;
  // number of coordinates per vertex in this array
  static final int COORDS_PER_VERTEX = 3;
  static float triangleCoords[] = {
    // in counterclockwise order:
    0.0f,  0.622008459f, 0.0f,   // top
    -0.5f, -0.311004243f, 0.0f,   // bottom left
    0.5f, -0.311004243f, 0.0f    // bottom right
  };
  private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
  private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex
  /**
   * Sets up the drawing object data for use in an OpenGL ES context.
   */
  public Triangle() {
    // initialize vertex byte buffer for shape coordinates
    ByteBuffer bb = ByteBuffer.allocateDirect(
      // (number of coordinate values * 4 bytes per float)
      triangleCoords.length * 4);
    // use the device hardware's native byte order
    bb.order(ByteOrder.nativeOrder());
    // create a floating point buffer from the ByteBuffer
    vertexBuffer = bb.asFloatBuffer();
    // add the coordinates to the FloatBuffer
    vertexBuffer.put(triangleCoords);
    // set the buffer to read the first coordinate
    vertexBuffer.position(0);
    // prepare shaders and OpenGL program
    int vertexShader = GLRenderer.loadShader(
      GLES20.GL_VERTEX_SHADER, vertexShaderCode);
    int fragmentShader = GLRenderer.loadShader(
      GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
    mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
    GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
    GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
    GLES20.glLinkProgram(mProgram);                  // create OpenGL program executables
  }
  /**
   * Encapsulates the OpenGL ES instructions for drawing this shape.
   *
   * @param mvpMatrix - The Model View Project matrix in which to draw
   * this shape.
   */
  public void draw(float[] mvpMatrix) {
    // Add program to OpenGL environment
    GLES20.glUseProgram(mProgram);
    // get handle to vertex shader's vPosition member
    mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
    // Enable a handle to the triangle vertices
    GLES20.glEnableVertexAttribArray(mPositionHandle);
    // Prepare the triangle coordinate data
    GLES20.glVertexAttribPointer(
      mPositionHandle, COORDS_PER_VERTEX,
      GLES20.GL_FLOAT, false,
      vertexStride, vertexBuffer);
    // get handle to shape's transformation matrix
    GLRenderer.checkGlError("glGetUniformLocation");
    // Apply the projection and view transformation
    GLRenderer.checkGlError("glUniformMatrix4fv");
    // Draw the triangle
    GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
    // Disable vertex array
    GLES20.glDisableVertexAttribArray(mPositionHandle);
  }
}
