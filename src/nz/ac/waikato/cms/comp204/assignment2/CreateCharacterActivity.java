package nz.ac.waikato.cms.comp204.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class CreateCharacterActivity extends Activity {

	private final int MAX_ATTRIBUTES = 15; 			// the maximum attribute points that can be used to create the character
	
	private EditText txtStrength;
	private EditText txtDexterity;
	private EditText txtPower;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_character);
		
		// Get text fields
		txtStrength = (EditText)findViewById(R.id.txtStrength);
		txtDexterity = (EditText)findViewById(R.id.txtDexterity);
		txtPower = (EditText)findViewById(R.id.txtPower);
		
		// Register text changed listeners
		txtStrength.addTextChangedListener(onAttributeTextChanged);
		txtDexterity.addTextChangedListener(onAttributeTextChanged);
		txtPower.addTextChangedListener(onAttributeTextChanged);
		
		populateInitialValues();		// populates the text fields with their appropriate values
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_character, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * This TextWatcher will update the attributes left value when the
	 * attribute values are being changed by the user
	 */
	private TextWatcher onAttributeTextChanged = new TextWatcher() {
		public void afterTextChanged(Editable s) {
			// Logic for after text changes
		}
		
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// Logic for before text changes
		}
		
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// The string values of the attributes
			String strengthString = txtStrength.getText().toString();
			String dexterityString = txtDexterity.getText().toString();
			String powerString = txtPower.getText().toString();
			
			System.out.println("Hello - Get String Values");
			
			// The integer values of the attributes
			int strength = 0;
			int dexterity = 0;
			int power = 0;
			
			if (strengthString != null && !strengthString.equals(""))
				strength = Integer.parseInt(strengthString);
			
			System.out.println("Hello - Get Integer Values (Strength)");
			
			if (dexterityString != null && !dexterityString.equals(""))
				dexterity = Integer.parseInt(dexterityString);
			
			System.out.println("Hello - Get Integer Values (Dexterity)");
			
			if (powerString != null && !powerString.equals(""))
				power = Integer.parseInt(powerString);
			
			System.out.println("Hello - Get Integer Values (Power)");
			
			int attributesUsed = strength + dexterity + power;
			int attributesLeft = MAX_ATTRIBUTES - attributesUsed;
			
			TextView lblAttributesLeft = (TextView)findViewById(R.id.lblAttributesLeft);
			lblAttributesLeft.setText(getString(R.string.attributes_left) + " " + String.valueOf(attributesLeft));
		}
	};
	
	/**
	 * Populates the attribute fields with their initial values
	 */
	private void populateInitialValues() {
		int startValue = MAX_ATTRIBUTES;
		int indexCounter = 0;
		int numAttributes = 3;
		
		// Indices of the attributes in the attributes array 
		int strengthIndex = 0;
		int dexterityIndex = 1;
		int powerIndex = 2;
		
		// Set the array to all zeros
		int[] attributes = new int[numAttributes];
		
		// Go through all the attributes to set their respective initial values
		while (startValue != 0) {
			attributes[indexCounter]++;
			
			indexCounter++;
			
			if (indexCounter == numAttributes)
				indexCounter = 0;
			
			startValue--;
		}
		
		txtStrength.setText(String.valueOf(attributes[strengthIndex]));
		txtDexterity.setText(String.valueOf(attributes[dexterityIndex]));
		txtPower.setText(String.valueOf(attributes[powerIndex]));
	}
}
