package com.example.calc3f;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        display = findViewById( R.id.textView );
        display.setShowSoftInputOnFocus( false );

        display.setOnClickListener( v -> {
            if (getString( R.string.display ).equals( display.getText().toString() )) {
                display.setText( "" );
            }
        } );
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring( 0, cursorPos );
        String rightStr = oldStr.substring( cursorPos );
        if (getString(R.string.display ).equals(display.getText().toString())){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        display.setSelection(cursorPos + 1);
    }

    public void zeroBTN(View view){

        updateText( "0" );
    }
    public void clearBTN(View view){

        display.setText( "" );
    }
    public void parenthesesBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().charAt( i ) == '(') {
                openPar += 1;
            }
            if (display.getText().toString().charAt( i ) == ')') {
                closedPar += 1;
            }
        }

        if (openPar == closedPar || display.getText().toString().charAt( textLen - 1 ) == '(') {
            updateText( "(" );
        }
        else if (closedPar < openPar && display.getText().toString().charAt( textLen - 1 ) != '(') {
            updateText( ")" );
        }
        display.setSelection( cursorPos + 1 );
    }
    @SuppressLint("SetTextI18n")
    public void squareRootBTN(View view){
        String userExp = display.getText().toString();
        double num = Double.parseDouble( userExp );
        display.setText( Math.sqrt( num ) + " " );
    }
    public void divideBTN(View view){
        updateText( "÷" );
    }
    public void sevenBTN(View view){
        updateText( "7" );
    }
    public void eightBTN(View view){
        updateText( "8" );
    }
    public void nineBTN(View view){
        updateText( "9" );
    }
    public void multiplyBTN(View view){
        updateText("×");
    }
    public void fourBTN(View view){
        updateText( "4" );
    }
    public void fiveBTN(View view){
        updateText( "5" );
    }
    public void sixBTN(View view){
        updateText( "6" );
    }
    public void subtractBTN(View view){
        updateText( "-" );
    }
    public void oneBTN(View view){
        updateText( "1" );
    }
    public void twoBTN(View view){
        updateText( "2" );
    }
    public void threeBTN(View view){
        updateText( "3" );
    }
    public void addBTN(View view){
        updateText( "+" );
    }
    public void plusMinusBTN(View view) {
        float userExp = Float.parseFloat( display.getText() + "" );
        userExp = userExp * -1;
        display.setText(String.valueOf( userExp ) );
    }
    public void pointBTN(View view){
        updateText( "." );
    }
    public void equalsBTN(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll( "÷", "/" );
        userExp = userExp.replaceAll( "×", "*" );

        Expression exp = new Expression( userExp );
        String result  = String.valueOf(exp.calculate());
        display.setText( result );
        display.setSelection( result.length() );
    }
    public void backspace(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace( cursorPos - 1, cursorPos, "" );
            display.setText( selection );
            display.setSelection( cursorPos - 1 );
        }
    }
}