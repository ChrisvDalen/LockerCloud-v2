import { describe, expect, it, vi } from 'vitest';

import { configureSocket } from './app.js';

class FakeSocket {
  listeners = new Map();

  addEventListener(type, listener) {
    this.listeners.set(type, listener);
  }

  emit(type, event = {}) {
    this.listeners.get(type)(event);
  }
}

describe('configureSocket', () => {
  it('forwards lifecycle events and messages', () => {
    const socket = new FakeSocket();
    const handlers = {
      open: vi.fn(),
      close: vi.fn(),
      error: vi.fn(),
      message: vi.fn()
    };

    configureSocket(socket, handlers);
    socket.emit('open');
    socket.emit('message', { data: 'Connection established' });

    expect(handlers.open).toHaveBeenCalledOnce();
    expect(handlers.message).toHaveBeenCalledWith('Connection established');
  });
});
