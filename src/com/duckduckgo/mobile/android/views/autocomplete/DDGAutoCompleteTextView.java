package com.duckduckgo.mobile.android.views.autocomplete;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.AutoCompleteTextView;

public class DDGAutoCompleteTextView extends AutoCompleteTextView {

	public DDGAutoCompleteTextView(Context context) {
		super(context);
	}

	public DDGAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DDGAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	private BackButtonPressedEventListener backButtonPressedEventListener;

	public void setOnBackButtonPressedEventListener(BackButtonPressedEventListener eventListener) {
		backButtonPressedEventListener = eventListener;
	}
	
	public String getTrimmedText(){
		return getText().toString().trim();
	}

	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
			backButtonPressedEventListener.onBackButtonPressed();
			return false;
		}
		return super.dispatchKeyEvent(event);
	}

	public void addBang() {
		if(isCursorAtEnd() && !lastCharIsSpaceOrNull()){
			getText().insert(getSelectionStart(), " !");	
		}else{
			getText().insert(getSelectionStart(), "!");
		}
	}
	
	private boolean lastCharIsSpaceOrNull(){
		return !hasText() || getText().charAt(getText().length() - 1) == ' ';
	}

	private boolean hasText() {
		return getText().length() > 0;
	}

	private boolean isCursorAtEnd() {
		return getSelectionStart() == getText().length();
	}

	public void addTextWithTrailingSpace(String phrase) {
		setText(phrase.trim() + " ");
		setSelection(getText().length());
	}
}
