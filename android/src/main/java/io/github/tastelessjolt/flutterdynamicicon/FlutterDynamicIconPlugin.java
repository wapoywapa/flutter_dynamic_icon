package io.github.tastelessjolt.flutterdynamicicon;

import android.content.Context;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

/** FlutterDynamicIconPlugin */
public class FlutterDynamicIconPlugin implements FlutterPlugin {
  private static final String CHANNEL_NAME = "flutter_dynamic_icon";
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
    setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    teardownChannel();
  }

  private void setupChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, CHANNEL_NAME);
    MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
    channel.setMethodCallHandler(handler);
  }

  private void teardownChannel() {
    if (channel != null) {
      channel.setMethodCallHandler(null);
      channel = null;
    }
  }
}
