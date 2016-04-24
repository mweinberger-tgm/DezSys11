package at.mweinberger.dezsys11;

import android.os.AsyncTask;
import android.widget.Toast;

import at.mweinberger.dezsys11.AccountForm;
import at.mweinberger.dezsys11.Message;
import at.mweinberger.dezsys11.Account;

public class AccountTask extends AsyncTask<Void, Void, Boolean> {

    private AccountForm userForm;
    private Account user;
    private Message response;

    public AccountTask(AccountForm userForm) {
        this.user = new Account(userForm.getEmail(), userForm.getPassword());
        this.userForm = userForm;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        response = userForm.callRestAPI(user);
        return response.getStatus() == 200 || response.getStatus() == 201;
    }

    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            userForm.success();
        } else {
            userForm.showProgress(false);
            Toast.makeText(userForm.getApplicationContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCancelled() {
        userForm.showProgress(false);
    }
}

