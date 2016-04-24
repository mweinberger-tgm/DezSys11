package at.mweinberger.dezsys11;

import android.os.Bundle;
import android.widget.Toast;

import at.mweinberger.dezsys11.R;

public class RegisterActivity extends AccountForm {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        super.setupForm();

        ((EditTextView) findViewById(R.id.email)).setOnBackButtonListener(new EditTextView.IOnBackButtonListener() {
            @Override
            public boolean OnEditTextBackButton() {
                finish();
                return true;
            }
        });

        ((EditTextView) findViewById(R.id.password)).setOnBackButtonListener(new EditTextView.IOnBackButtonListener() {
            @Override
            public boolean OnEditTextBackButton() {
                finish();
                return true;
            }
        });

    }

    @Override
    public Message callRestAPI(Account user) {

        Message response = new Message();
        AccountRequester caller = new AccountRequester(getApplicationContext(), new MessageHandler(response));
        caller.register(user);
        return response;
    }

    @Override
    public void success() {
        showProgress(false);
        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
        finish();
    }
}

