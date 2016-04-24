package at.mweinberger.dezsys11;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class EditTextView extends EditText {
    public interface IOnBackButtonListener {
        boolean OnEditTextBackButton();
    }

    public EditTextView(Context context) {
        super(context);
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnBackButtonListener(IOnBackButtonListener l) {
        _listener = l;
    }

    IOnBackButtonListener _listener;

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event)
    {
        if (event.getAction()==KeyEvent.ACTION_UP && keyCode==KeyEvent.KEYCODE_BACK)
        {
            if (_listener!=null && _listener.OnEditTextBackButton())
                return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
