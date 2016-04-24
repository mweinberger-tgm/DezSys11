package at.mweinberger.dezsys11;


import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import at.mweinberger.dezsys11.Message;
import cz.msebera.android.httpclient.Header;

public class MessageHandler extends TextHttpResponseHandler {

    private Message response;

    public MessageHandler(Message response) {
        this.response = response;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        this.processMessage(statusCode, responseString);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {
        this.processMessage(statusCode, responseString);
    }

    private void processMessage(int statusCode, String responseString) {
        try {
            JSONObject jsonMessage = new JSONObject(responseString);
            if (jsonMessage.has("status") && jsonMessage.has("message")) {
                response.setStatus(jsonMessage.getInt("status"));
                response.setMessage(jsonMessage.getString("message"));
            } else {
                throw new IllegalArgumentException("Message does not contain a status or a message");
            }
        } catch (JSONException | IllegalArgumentException e) {
            response.setStatus(statusCode);
            response.setMessage("Unexpected exception occurred: " + e.getMessage());
        }
    }

}
