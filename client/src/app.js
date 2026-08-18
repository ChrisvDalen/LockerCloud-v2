export function configureSocket(socket, handlers) {
  socket.addEventListener('open', () => handlers.open());
  socket.addEventListener('close', () => handlers.close());
  socket.addEventListener('error', () => handlers.error());
  socket.addEventListener('message', event => handlers.message(String(event.data)));
  return socket;
}

export function connect(url, handlers, WebSocketImplementation = WebSocket) {
  return configureSocket(new WebSocketImplementation(url), handlers);
}

if (typeof document !== 'undefined') {
  const status = document.querySelector('#status');
  const messages = document.querySelector('#messages');
  const form = document.querySelector('#message-form');
  const input = document.querySelector('#message');

  const socket = connect('ws://localhost:8080/sync', {
    open: () => status.textContent = 'Connected',
    close: () => status.textContent = 'Disconnected',
    error: () => status.textContent = 'Connection error',
    message: message => {
      const item = document.createElement('li');
      item.textContent = message;
      messages.append(item);
    }
  });

  form.addEventListener('submit', event => {
    event.preventDefault();
    socket.send(input.value);
    input.value = '';
  });
}
