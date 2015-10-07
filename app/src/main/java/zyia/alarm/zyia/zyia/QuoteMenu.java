package zyia.alarm.zyia.zyia;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class QuoteMenu extends FragmentActivity {

    int tempx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_menu);
        Intent receive = getIntent();

        tempx = receive.getIntExtra("ALM",0);
    }


    public void quotesOne(View view)
    {
        Intent intent = new Intent(QuoteMenu.this, Quotes1.class);
        intent.putExtra("ALM",tempx);
        startActivity(intent);
        finish();
    }

    public void quotesTwo(View view)
    {
        Intent intent = new Intent(QuoteMenu.this, Quotes2.class);
        intent.putExtra("ALM",tempx);
        startActivity(intent);
        finish();
    }

    public void quoteThree(View view)
    {
        Intent intent = new Intent(QuoteMenu.this, quote3.class);
        intent.putExtra("ALM",tempx);
        startActivity(intent);
        finish();
    }

    public void quoteFour(View view)
    {
        Intent intent = new Intent(QuoteMenu.this, Quotes4.class);
        intent.putExtra("ALM",tempx);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
