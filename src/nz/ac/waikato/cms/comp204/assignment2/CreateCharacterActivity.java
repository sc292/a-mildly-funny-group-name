package nz.ac.waikato.cms.comp204.assignment2;

import nz.ac.waikato.cms.comp204.assignment2.classes.Player;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCharacterActivity extends Activity implements OnClickListener {

	private final int MAX_ATTRIBUTES = 15; 			// the maximum attribute points that can be used to create the character
	
	// The integer value of the initial characters attributes
	private int strength; 
	private int dexterity;
	private int power;
	
	private EditText txtStrength;
	private EditText txtDexterity;
	private EditText txtPower;
	
	private Button btnCreate;
	private Button btnBattle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_character);
		
		// Get text fields
		txtStrength = (EditText)findViewById(R.id.txtStrength);
		txtDexterity = (EditText)findViewById(R.id.txtDexterity);
		txtPower = (EditText)findViewById(R.id.txtPower);
		
		// Get Button
		btnCreate = (Button)findViewById(R.id.btnCreate);
		btnBattle = (Button)findViewById(R.id.btnGoToBattle);
		
		// Register text changed listeners
		txtStrength.addTextChangedListener(onAttributeTextChanged);
		txtDexterity.addTextChangedListener(onAttributeTextChanged);
		txtPower.addTextChangedListener(onAttributeTextChanged);
		
		// Register onClick event listener
		btnCreate.setOnClickListener(this);
		btnBattle.setOnClickListener(this);
		
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
			
			if (strengthString != null && !strengthString.equals(""))
				strength = Integer.parseInt(strengthString);
			else
				strength = 0;
			
			if (dexterityString != null && !dexterityString.equals(""))
				dexterity = Integer.parseInt(dexterityString);
			else
				dexterity = 0;
			
			if (powerString != null && !powerString.equals(""))
				power = Integer.parseInt(powerString);
			else
				power = 0;
			
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

	/**
	 * Handles the click event of the create button
	 * 
	 * @param v
	 */
	
	@Override
	public void onClick(View v) {
		if (v.getId() == btnCreate.getId()) {
			Player player = createPlayer();
			
			if (player != null) {
				Intent intent = new Intent(this, OverWorld.class);
				startActivity(intent);
			}
		}
		
		if (v.getId() == btnBattle.getId()) {
			Player player = createPlayer();
			
			if (player != null) {
				Intent intent = new Intent(this, BattleActivity.class);
				startActivity(intent);
			}
		}
	}
	
	/**
	 * Creates a player object to play the game
	 * 
	 * @return the player to play the game
	 */
	private Player createPlayer() {
		if (strength == 0 || dexterity == 0 || power == 0) {
			showToast("Attribute values must be greater than 0");
			return null;
		}
		
		if (strength + dexterity + power > MAX_ATTRIBUTES) {
			showToast("Attribute assignments exceed max amount");
			return null;
		}
		
		return new Player(strength, dexterity, power);
	}
	
	/**
	 * Shows a toast with the error on it
	 * 
	 * @param text the text to be shown
	 */
	private void showToast(CharSequence text) {
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
