package com.example.jocelyn.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById( R.id.name_edit_text );
        String name = nameEditText.getText().toString();
        int price = calculateTotalPrice();
        String priceMessage = "Name: " + name + "\nQuantity: " + quantity + "\nTotal: $" + price +
                "\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     * This method calculates the total price.
     */
    private int calculateTotalPrice() {
        int price = 5;

        // Chocalate costs $2 per cup
        CheckBox chocolateCB = (CheckBox) findViewById( R.id.chocolate_checkBox );
        if( chocolateCB.isChecked() ) {
            price += 2;
        }

        // Whipped cream costs $1 per cup
        CheckBox creamCB = (CheckBox) findViewById( R.id.whipped_cream_checkBox );
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
        quantity++;
        display(quantity);
    }

    /*
     * Decrements the quantity by 1. This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {
        if( quantity > 0 ) quantity--;
        display(quantity);
    }
}