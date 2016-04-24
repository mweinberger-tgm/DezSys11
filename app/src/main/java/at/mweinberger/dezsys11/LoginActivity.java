package at.mweinberger.dezsys11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import at.mweinberger.dezsys11.R;

public class LoginActivity extends AccountForm {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        super.setupForm();

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public Message callRestAPI(Account user){
        Message response = new Message();
        AccountRequester caller = new AccountRequester(getApplicationContext(), new MessageHandler(response));
        caller.login(user);
        return response;
    }

    @Override
    public void success() {
        Intent intent = new Intent(this, SuccessActivity.class);
        intent.putExtra("email", super.getEmail());

        super.clearForm();
        showProgress(false);
        startActivity(intent);
    }

}

