package com.trello.navi;

import android.os.Bundle;
import android.os.PersistableBundle;
import com.trello.navi.Event.Type;
import com.trello.navi.internal.NaviEmitter;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public final class AllEventTest {

  private final NaviEmitter emitter = NaviEmitter.createActivityEmitter();

  // Test event without listener params works
  @Test public void startAllListener() {
    Listener<Type> listener = mock(Listener.class);
    emitter.addListener(Event.ALL, listener);

    emitter.onStart();
    verify(listener).call(Type.START);

    emitter.removeListener(listener);
    emitter.onStart();
    verifyNoMoreInteractions(listener);
  }

  // Test event with listener params works
  @Test public void createAllListener() {
    Listener<Type> listener = mock(Listener.class);
    emitter.addListener(Event.ALL, listener);

    Bundle bundle = new Bundle();
    emitter.onCreate(bundle);
    verify(listener).call(Type.CREATE);

    emitter.removeListener(listener);
    emitter.onCreate(bundle);
    verifyNoMoreInteractions(listener);
  }

  // Test persistable Activities
  @Test public void createPersistableListener() {
    Listener<Type> listener = mock(Listener.class);
    emitter.addListener(Event.ALL, listener);

    Bundle bundle = new Bundle();
    PersistableBundle persistableBundle = mock(PersistableBundle.class);
    emitter.onCreate(bundle, persistableBundle);
    verify(listener, times(2)).call(Type.CREATE);

    emitter.removeListener(listener);
    emitter.onCreate(bundle, persistableBundle);
    verifyNoMoreInteractions(listener);
  }
}
