package at.mweinberger.dezsys11;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MessageHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import at.mweinberger.dezsys11.Account;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AccountRequester {

    private Context context;
    private MessageHandlerInterface handler;
    private AsyncHttpClient client;

    public AccountRequester(Context context, MessageHandlerInterface handler) {
        this.client = new SyncHttpClient();
        this.client.setTimeout(50000);
        this.handler = handler;
        this.context = context;
    }

    public void register(Account user) {
        this.post("/register", user);
    }

    public void login(Account user) {
        this.post("/login", user);
    }

    private void post(String host, Account user) {
        StringEntity entity = this.createEntity(user);
        client.post(this.context, host, entity, "application/json", this.handler);
    }

    private StringEntity createEntity(Account user) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());
            return new StringEntity(jsonObject.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
