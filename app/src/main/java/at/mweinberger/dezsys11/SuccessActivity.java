package at.mweinberger.dezsys11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import at.mweinberger.dezsys11.R;


public class SuccessActivity extends AppCompatActivity {

    /**
     * @see android.support.v7.app.AppCompatActivity#onCreate(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        // append email to success message
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("email");
            TextView mPasswordView = (TextView) findViewById(R.id.message);
            if (email != null && mPasswordView != null) {
                mPasswordView.append(" as " + email);
            }
        }
    }
}
