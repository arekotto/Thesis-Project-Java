package javatest.arkadiuszotto.com.javatest.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javatest.arkadiuszotto.com.javatest.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public class WebSocketFragment extends Fragment {
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private final class EchoWebSocketListener extends WebSocketListener {

        private boolean isOpen = false;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            isOpen = true;
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    connectionButton.setText(R.string.fragment_web_socket_button_connection_disconnect);
                }
            });
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            final String newMessage = text;
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    receivedMessagesTextView.append(newMessage + '\n');
                }
            });
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            isOpen = false;
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    connectionButton.setText(R.string.fragment_web_socket_button_connection_connect);
                }
            });
        }

        boolean isOpen() {
            return isOpen;
        }
    }

    TextView receivedMessagesTextView;
    EditText messageTextField;
    Button connectionButton;

    private OkHttpClient client = new OkHttpClient();
    private WebSocket webSocket;
    private EchoWebSocketListener webSocketListener = new EchoWebSocketListener();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_socket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        receivedMessagesTextView = view.findViewById(R.id.text_view_received_messages);
        receivedMessagesTextView.setMovementMethod(new ScrollingMovementMethod());

        messageTextField = view.findViewById(R.id.text_field_message);

        view.findViewById(R.id.button_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webSocketListener.isOpen()) {
                    webSocket.send(messageTextField.getText().toString());
                } else {
                    showNoConnectionToast();
                }
            }
        });


        view.findViewById(R.id.button_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receivedMessagesTextView.setText("");
            }
        });

        connectionButton = view.findViewById(R.id.button_connection);
        connectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectionButtonTapped();
            }
        });
        connectionButton.setText(R.string.fragment_web_socket_button_connection_connect);
    }

    private void connectionButtonTapped() {
        if (webSocketListener.isOpen()) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        } else {
            Request request = new Request.Builder().url("ws://echo.websocket.org").build();
            webSocket = client.newWebSocket(request, webSocketListener);
        }
    }

    private void showNoConnectionToast() {
        Context context = getActivity().getApplicationContext();
        Toast.makeText(context, R.string.no_connection, Toast.LENGTH_SHORT).show();
    }
}
