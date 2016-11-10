package com.example.localtionmanager;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d("xcp", "x=" + "我".getBytes().length);

		EditText opinion = (EditText) findViewById(R.id.edit);
		opinion.setFilters(new InputFilter[] { inputFilter });
	}


	private InputFilter inputFilter = new InputFilter() {
		@Override
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			try {
				int len = 0;
				boolean more = false;
				do {
					SpannableStringBuilder builder = new SpannableStringBuilder(
							dest).replace(dstart, dend,
							source.subSequence(start, end));
					len = builder.toString().getBytes().length;
					more = len > 32;
					if (more) {
						end--;
						source = source.subSequence(start, end);
					}
				} while (more);
				return source;
			} catch (Exception e) {
				return "Exception";
			}
		}
	};
}
