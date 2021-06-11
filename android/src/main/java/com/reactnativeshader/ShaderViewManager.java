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

    @ReactProp(name = "source")
    public void setSource(ShaderView view, String source) {
      view.setSource(source);
    }

    @ReactProp(name = "uniforms")
    public void setUniforms(ShaderView view, ReadableMap uniforms) {
      view.setUniforms(uniforms);
    }
}
