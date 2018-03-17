package org.gluu.super_gluu.app.fragments.PinCodeFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mhk.android.passcodeview.PasscodeView;

import org.gluu.super_gluu.app.customGluuAlertView.CustomGluuAlert;
import org.gluu.super_gluu.app.services.GlobalNetworkTime;
import org.gluu.super_gluu.app.settings.Settings;

import SuperGluu.app.R;
import butterknife.BindView;
import butterknife.ButterKnife;

//import com.github.simonpercic.rxtime.RxTime;

/**
 * Created by nazaryavornytskyy on 3/24/16.
 */
public class PinCodeFragment extends Fragment {

    @BindView(R.id.nav_drawer_toolbar)
    Toolbar toolbar;
    @BindView(R.id.attemptsLabel)
    TextView attemptsLabel;

    @BindView(R.id.pin_view)
    PasscodeView pinCodeView;


    public PinCodeViewListener pinCodeViewListener;

    private String fragmentType;
    private boolean isSettings;
    private boolean newPin;
    private boolean isWrongPin;
    private int setNewPinAttempts;
    private boolean isSetNewPinCode;

    public static PinCodeFragment newInstance(String fragmentType, boolean isNewPinCode, boolean isSettings) {
        PinCodeFragment pinCodeFragment = new PinCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FRAGMENT_TYPE, fragmentType);
        bundle.putBoolean(Constant.NEW_PIN_CODE, isNewPinCode);
        bundle.putBoolean(Constant.IS_SETTINGS, isSettings);
        pinCodeFragment.setArguments(bundle);
        return pinCodeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pin_code, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        fragmentType = getArguments().getString(Constant.FRAGMENT_TYPE, Constant.ENTER_CODE);
        isSettings = getArguments().getBoolean(Constant.IS_SETTINGS, false);
        isSetNewPinCode = getArguments().getBoolean(Constant.NEW_PIN_CODE, false);

        if (fragmentType.equals(Constant.SET_CODE)) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.set_passcode));
        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.enter_passcode));
        }

        setHasOptionsMenu(true);

        updatePinCodeView();
        setNewPinAttempts = 0;

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pin_code_entry, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cancel_action:
                exitScreen();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updatePinCodeView(){
        //get local variables
        final String pinCode = Settings.getPinCode(getContext());
        int attempts = Settings.getCurrentPinCodeAttempts(getContext());

        //Show the keyboard
        pinCodeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                pinCodeView.requestToShowKeyboard();
            }
        }, 400);

        //Set attempts left view
        attemptsLabel.setText(getAttemptsLeftText(attempts));
        if (attempts <= 2) {
            attemptsLabel.setTextColor(getResources().getColor(R.color.redColor));
        }
        if (pinCode == null){
            attemptsLabel.setVisibility(View.GONE);
        }

        //Setup pin code listener
        pinCodeView.setPasscodeEntryListener(new PasscodeView.PasscodeEntryListener() {

            @Override
            public void onPasscodeEntered(String passcode) {
                //If there is no pin code set
                if (pinCode == null) {
                    if (setNewPinAttempts == 0){
                        setNewPinAttempts++;
                        pinCodeView.clearText();
                    } else {
                        attemptsLabel.setVisibility(View.VISIBLE);
                        attemptsLabel.setText(R.string.successfully_set_pin);
                        setNewPin(passcode);
                    }
                    //If we are setting a new code
                } else if (newPin) {
                    if (passcode.equalsIgnoreCase(pinCode)) {
                        showAlertView(getString(R.string.same_pin_code));
                        getActivity().onBackPressed();
                    } else {
                        showAlertView(getString(R.string.new_pin_success));
                        Settings.savePinCode(getContext(), passcode);
                        getActivity().onBackPressed();
                    }
                    newPin = false;
                    //If user entered correct pin code
                } else if (passcode.equalsIgnoreCase(Settings.getPinCode(getContext()))) {
                    if (isSetNewPinCode) {
                        attemptsLabel.setVisibility(View.GONE);
                        pinCodeView.clearText();
                        newPin = true;
                        Settings.resetCurrentPinAttempts(getContext());
                        return;
                    } else {
                        attemptsLabel.setTextColor(getResources().getColor(R.color.greenColor));
                        attemptsLabel.setText(R.string.correct_pin_code);
                    }
                    if (pinCodeViewListener != null) {
                        pinCodeViewListener.onCorrectPinCode(true);
                    }
                    isWrongPin = false;
                    Settings.resetCurrentPinAttempts(getContext());
                } else {
                    wrongPinCode();
                }
            }
        });

    }

    @SuppressLint("DefaultLocale")
    private String getAttemptsLeftText(int attempts) {
        return String.format(Constant.ATTEMPTS_LEFT_FORMAT, attempts);
    }

    private void setNewPin(String passcode){
        isWrongPin = false;
        Settings.savePinCode(getContext(), passcode);
        pinCodeViewListener.onCorrectPinCode(true);
        Settings.resetCurrentPinAttempts(getContext());

        //Hide keyboard
        ((InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);

        getActivity().onBackPressed();
    }

    private void wrongPinCode(){
        isWrongPin = true;
        increaseAttempts();

        int attempts = Settings.getCurrentPinCodeAttempts(getContext());
        String attemptsText = getAttemptsLeftText(attempts);
        if (attempts <= 2) {
            attemptsLabel.setTextColor(getResources().getColor(R.color.redColor));
        }
        attemptsLabel.setText(attemptsText);

        pinCodeView.clearText();
        if (attempts <= 0) {
            Settings.resetCurrentPinAttempts(getContext());
            if (isSettings) {
                Settings.setAppLocked(getContext(), true);
                setCurrentNetworkTime();
            }
            pinCodeViewListener.onCorrectPinCode(false);
        }
    }

    private void setCurrentNetworkTime() {
        new GlobalNetworkTime().getCurrentNetworkTime(getContext(), new GlobalNetworkTime.GetGlobalTimeCallback() {
            @Override
            public void onGlobalTime(Long time) {
                Settings.setAppLockedTime(getContext(), String.valueOf(time));
            }
        });
    }

    private void showAlertView(String message){
        CustomGluuAlert gluuAlert = new CustomGluuAlert(getActivity());
        gluuAlert.setMessage(message);
        gluuAlert.setYesTitle(getActivity().getApplicationContext().getString(R.string.ok));
        gluuAlert.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PinCodeViewListener) {
            pinCodeViewListener = (PinCodeViewListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PinCodeViewListener");
        }
    }

    public void increaseAttempts(){
        int attempts = Settings.getCurrentPinCodeAttempts(getContext());
        attempts--;
        Settings.setCurrentPinCodeAttempts(getContext(), attempts);
    }

    private void exitScreen() {
        Settings.saveIsReset(getContext());
        getActivity().onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface PinCodeViewListener{
        public void onNewPinCode(String pinCode);
        public void onCorrectPinCode(boolean isPinCodeCorrect);
    }

    public class Constant {
        public static final String FRAGMENT_TYPE = "code_type";
        public static final String NEW_PIN_CODE = "new_pin_code";
        public static final String IS_SETTINGS = "is_settings";


        public static final String ENTER_CODE = "enter_code";
        public static final String SET_CODE = "set_code";

        public static final String ATTEMPTS_LEFT_FORMAT = "You have %d attempts left";
    }
}
