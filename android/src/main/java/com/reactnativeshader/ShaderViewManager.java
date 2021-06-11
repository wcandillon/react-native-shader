package com.reactnativeshader;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ShaderViewManager extends SimpleViewManager<ShaderView> {
    public static final String REACT_CLASS = "ShaderView";

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ShaderView createViewInstance(ThemedReactContext reactContext) {
        return new ShaderView(reactContext);
    }

    @ReactProp(name = "shader")
    public void setShader(View view, String shader) {
      //view.setBackgroundColor(Color.parseColor("red"));
    }

    @ReactProp(name = "uniforms")
    public void setUniforms(View view, ReadableMap uniforms) {
        //view.setBackgroundColor(Color.parseColor("red"));
    }
}
