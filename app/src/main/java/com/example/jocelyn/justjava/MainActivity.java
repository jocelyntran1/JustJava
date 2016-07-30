package com.example.jocelyn.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    CheckBox chocolateCB;
    CheckBox creamCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        chocolateCB = (CheckBox) findViewById( R.id.chocolate_checkBox );
        creamCB = (CheckBox) findViewById( R.id.whipped_cream_checkBox );

        EditText nameEditText = (EditText) findViewById( R.id.name_edit_text );
        String name = nameEditText.getText().toString();
        int price = calculateTotalPrice();
        String priceMessage = "Name: " + name + "\nQuantity: " + quantity;
        priceMessage += "\nAdd whipped cream? " + creamCB.isChecked();
        priceMessage += "\nAdd chocolate? " + chocolateCB.isChecked();
        priceMessage += "\nTotal: $" + price + "\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     * This method calculates the total price.
     */
    private int calculateTotalPrice() {
        int price = 5;

        // Chocalate costs $2 per cup
        if( chocolateCB.isChecked() ) {
            price += 2;
        }

        // Whipped cream costs $1 per cup
        if( creamCB.isChecked() ) {
            price++;
        }

        return price * quantity;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /*
     * Increments the quantity by 1. This method is called when the increment button is clicked.
     */
    public void increment(View view) {
        if( quantity == 100 ) {
            Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    /*
     * Decrements the quantity by 1. This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if( quantity == 1 ) {
            Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity--;
        display(quantity);
    }

    /**
     * Sends the order summary info to an email application.
     */
    public void sendEmail(View view) {
        Intent intent = new Intent( Intent.ACTION_SENDTO );
        intent.setData(Uri.parse("mailto:"));
        
        chocolateCB = (CheckBox) findViewById( R.id.chocolate_checkBox );
        creamCB = (CheckBox) findViewById( R.id.whipped_cream_checkBox );

        EditText nameEditText = (EditText) findViewById( R.id.name_edit_text );
        String name = nameEditText.getText().toString();
        int price = calculateTotalPrice();
        String priceMessage = "Name: " + name + "\nQuantity: " + quantity;
        priceMessage += "\nAdd whipped cream? " + creamCB.isChecked();
        priceMessage += "\nAdd chocolate? " + chocolateCB.isChecked();
        priceMessage += "\nTotal: $" + price + "\nThank you!";

        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}