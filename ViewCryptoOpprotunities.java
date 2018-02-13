package com.example.alexander.cryptarbitrage2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.PriorityQueue;
/**
 * Created by Alexander on 1/7/2018.
 */

public class ViewCryptoOpprotunities extends Activity implements View.OnClickListener{
    ArbitrageFinder arbitrageFinder;
    PriorityQueue<Opportunity> bestOpportunitiesWithinExchanges;
    PriorityQueue<Opportunity> bestOpportunitiesAcrossExchanges;

    Opportunity [] topOpportunitiesArray;

    static boolean hasData = true;

    TextView opportunity1Price;
    TextView opportunity2Price;
    TextView opportunity3Price;
    TextView opportunity4Price;
    TextView opportunity5Price;

    TextView opportunity1Cryptocurrency;
    TextView opportunity2Cryptocurrency;
    TextView opportunity3Cryptocurrency;
    TextView opportunity4Cryptocurrency;
    TextView opportunity5Cryptocurrency;

    TextView opportunity1Exchange;
    TextView opportunity2Exchange;
    TextView opportunity3Exchange;
    TextView opportunity4Exchange;
    TextView opportunity5Exchange;

    Button opportunity1Type;
    Button opportunity2Type;
    Button opportunity3Type;
    Button opportunity4Type;
    Button opportunity5Type;

    Button prev5Button;
    Button next5Button;
    Button refreshDataButton;

    AlertDialog Opportunity1AlertDialog;
    AlertDialog Opportunity2AlertDialog;
    AlertDialog Opportunity3AlertDialog;
    AlertDialog Opportunity4AlertDialog;
    AlertDialog Opportunity5AlertDialog;
    AlertDialog alertDialog;
    int counter;

    String typeOneOpportunityMessage;
    String typeTwoOpportunityMessage;
    String typeThreeOpportunityMessage;
    String typeFourOpportunityMessage;
    String typeFiveOpportunityMessage;
    String typeSixOpportunityMessage;
    String typeSevenOpportunityMessage;
    String typeEightOpportunityMessage;
    String typeNineOpportunityMessage;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_opprotunities);

        setUpMessages();
        Opportunity1AlertDialog = new AlertDialog.Builder(this).create();
        Opportunity2AlertDialog = new AlertDialog.Builder(this).create();
        Opportunity3AlertDialog = new AlertDialog.Builder(this).create();
        Opportunity4AlertDialog = new AlertDialog.Builder(this).create();
        Opportunity5AlertDialog = new AlertDialog.Builder(this).create();
        alertDialog = new AlertDialog.Builder(this).create();
        arbitrageFinder = new ArbitrageFinder(HomePage.minGainsWanted);

        int x = recalculateNumbers();

        if(topOpportunitiesArray != null) {
            for (int i = 0; i < topOpportunitiesArray.length; i++) {
                System.out.println(topOpportunitiesArray[i].getPercentGain());
            }
        }

        counter = 0;

        connectJavaToXML();
        if(x == 1){
            getDataToScreen();
        }



    }

    private void connectJavaToXML(){
        opportunity1Price = findViewById(R.id.opportunity1Price);
        opportunity2Price = findViewById(R.id.opportunity2Price);
        opportunity3Price = findViewById(R.id.opportunity3Price);
        opportunity4Price = findViewById(R.id.opportunity4Price);
        opportunity5Price = findViewById(R.id.opportunity5Price);

        opportunity1Cryptocurrency = findViewById(R.id.opportunity1Cryptocurrency);
        opportunity2Cryptocurrency = findViewById(R.id.opportunity2Cryptocurrency);
        opportunity3Cryptocurrency = findViewById(R.id.opportunity3Cryptocurrency);
        opportunity4Cryptocurrency = findViewById(R.id.opportunity4Cryptocurrency);
        opportunity5Cryptocurrency = findViewById(R.id.opportunity5Cryptocurrency);

        opportunity1Exchange = findViewById(R.id.opportunity1Exchange);
        opportunity2Exchange = findViewById(R.id.opportunity2Exchange);
        opportunity3Exchange = findViewById(R.id.opportunity3Exchange);
        opportunity4Exchange = findViewById(R.id.opportunity4Exchange);
        opportunity5Exchange = findViewById(R.id.opportunity5Exchange);

        opportunity1Type = findViewById(R.id.opportunity1Type);
        opportunity1Type.setOnClickListener(this);
        opportunity2Type = findViewById(R.id.opportunity2Type);
        opportunity2Type.setOnClickListener(this);
        opportunity3Type = findViewById(R.id.opportunity3Type);
        opportunity3Type.setOnClickListener(this);
        opportunity4Type = findViewById(R.id.opportunity4Type);
        opportunity4Type.setOnClickListener(this);
        opportunity5Type = findViewById(R.id.opportunity5Type);
        opportunity5Type.setOnClickListener(this);

        prev5Button = findViewById(R.id.prev5Button);
        prev5Button.setOnClickListener(this);
        next5Button = findViewById(R.id.next5Button);
        next5Button.setOnClickListener(this);
        refreshDataButton = findViewById(R.id.refreshDataButton);
        refreshDataButton.setOnClickListener(this);
    }


    private void setEverythingAfterBlank(int level) {
        switch (level) {
            case 1:
                opportunity2Cryptocurrency.setText("");
                opportunity2Exchange.setText("");
                opportunity2Price.setText("");
                opportunity2Type.setText("");

            case 2:
                opportunity3Cryptocurrency.setText("");
                opportunity3Exchange.setText("");
                opportunity3Price.setText("");
                opportunity3Type.setText("");

            case 3:
                opportunity4Cryptocurrency.setText("");
                opportunity4Exchange.setText("");
                opportunity4Price.setText("");
                opportunity4Type.setText("");

            case 4:
                opportunity5Cryptocurrency.setText("");
                opportunity5Exchange.setText("");
                opportunity5Price.setText("");
                opportunity5Type.setText("");
        }
    }


    private void getDataToScreen() {

            opportunity1Cryptocurrency.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getName());
            opportunity1Type.setText(Integer.toString(topOpportunitiesArray[counter].getType()));
            opportunity1Price.setText(doubleToStringFiveSigDigs(topOpportunitiesArray[counter].getPercentGain()));
            if (topOpportunitiesArray[counter].getType() > 6) {
                opportunity1Exchange.setText("Buy " + topOpportunitiesArray[counter].getLowPriceCoinExchange().getExchange().
                        concat(" Sell ").concat(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange()));
            }
            else {
                opportunity1Exchange.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange());
            }
            counter++;


        if (counter < topOpportunitiesArray.length) {
            opportunity2Cryptocurrency.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getName());
            opportunity2Type.setText(Integer.toString(topOpportunitiesArray[counter].getType()));
            opportunity2Price.setText(doubleToStringFiveSigDigs(topOpportunitiesArray[counter].getPercentGain()));
            if (topOpportunitiesArray[counter].getType() > 6) {
                opportunity2Exchange.setText("Buy " + topOpportunitiesArray[counter].getLowPriceCoinExchange().
                        getExchange().concat(" Sell ").concat(topOpportunitiesArray[counter].
                        getHighPriceCoinExchange().getExchange()));
            }
            else {
                opportunity2Exchange.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange());
            }
            counter++;
        }
        else{
            setEverythingAfterBlank(1);
            counter += 4;
            return;
        }

        if (counter < topOpportunitiesArray.length) {
            opportunity3Cryptocurrency.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getName());
            opportunity3Type.setText(Integer.toString(topOpportunitiesArray[counter].getType()));
            opportunity3Price.setText(doubleToStringFiveSigDigs(topOpportunitiesArray[counter].getPercentGain()));
            if (topOpportunitiesArray[counter].getType() > 6) {
                opportunity3Exchange.setText("Buy " + topOpportunitiesArray[counter].getLowPriceCoinExchange().getExchange().
                        concat(" Sell ").concat(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange()));
            }
            else {
                opportunity3Exchange.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange());
            }
            counter++;
        }
        else{
            setEverythingAfterBlank(2);
            counter += 3;
            return;
        }

        if (counter < topOpportunitiesArray.length) {
            opportunity4Cryptocurrency.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getName());
            opportunity4Type.setText(Integer.toString(topOpportunitiesArray[counter].getType()));
            opportunity4Price.setText(doubleToStringFiveSigDigs(topOpportunitiesArray[counter].getPercentGain()));
            String s = getDialogInfo(topOpportunitiesArray[counter]);
            if (topOpportunitiesArray[counter].getType() > 6) {
                opportunity4Exchange.setText("Buy " + topOpportunitiesArray[counter].getLowPriceCoinExchange().getExchange().
                        concat(" Sell ").concat(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange()));
            }
            else {
                opportunity4Exchange.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange());
            }
            counter++;
        }
        else{
            setEverythingAfterBlank(3);
            counter+=2;
            return;
        }

        if (counter < topOpportunitiesArray.length) {
            opportunity5Cryptocurrency.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getName());
            opportunity5Type.setText(Integer.toString(topOpportunitiesArray[counter].getType()));
            opportunity5Price.setText(doubleToStringFiveSigDigs(topOpportunitiesArray[counter].getPercentGain()));
            if (topOpportunitiesArray[counter].getType() > 6) {
                opportunity5Exchange.setText("Buy " + topOpportunitiesArray[counter].getLowPriceCoinExchange().getExchange().
                        concat(" Sell ").concat(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange()));
            }
            else {
                opportunity5Exchange.setText(topOpportunitiesArray[counter].getHighPriceCoinExchange().getExchange());
            }
            counter++;
        }
        else{
            setEverythingAfterBlank(4);
            counter++;
        }
    }

    public int min(int i1, int i2){
        if(i1 > i2){
            return i2;
        }
        else{
            return i1;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next5Button:
                if(topOpportunitiesArray.length > counter + 1){
                    getDataToScreen();
                }
                else{
                    Toast.makeText(this,"No More Arbitrage Opportunities",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.prev5Button:
                if(counter >= 10){
                    counter -= 10;
                    getDataToScreen();
                }
                else {
                    Toast.makeText(this, "No More Arbitrage Opportunities", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.refreshDataButton:
                //get clock data here
                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);

                break;
            case R.id.opportunity1Type:
                if(opportunity1Cryptocurrency.getText().toString().equals("")){
                    break;
                }
                alertDialog.setTitle("Details");
                alertDialog.setMessage(getDialogInfo(topOpportunitiesArray[counter - 5]));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;
            case R.id.opportunity2Type:
                if(opportunity2Cryptocurrency.getText().toString().equals("")){
                    break;
                }
                alertDialog.setTitle("Details");
                alertDialog.setMessage(getDialogInfo(topOpportunitiesArray[counter - 4]));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;

            case R.id.opportunity3Type:
                if(opportunity3Cryptocurrency.getText().toString().equals("")){
                    break;
                }
                alertDialog.setTitle("Details");
                alertDialog.setMessage(getDialogInfo(topOpportunitiesArray[counter - 3]));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;

            case R.id.opportunity4Type:
                if(opportunity4Cryptocurrency.getText().toString().equals("")){
                    break;
                }
                alertDialog.setTitle("Details");
                alertDialog.setMessage(getDialogInfo(topOpportunitiesArray[counter - 2]));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;

            case R.id.opportunity5Type:
                if(opportunity5Cryptocurrency.getText().toString().equals("")){
                    break;
                }
                alertDialog.setTitle("Details");
                alertDialog.setMessage(getDialogInfo(topOpportunitiesArray[counter - 1]));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                break;
        }
    }

    private String doubleToStringFiveSigDigs(double d1){
        if(d1 >= 1000){
            return String.format("%.1f", d1);
        }
        else if (d1 >= 100){
            return String.format("%.2f", d1);
        }
        else if(d1 >= 10){
            return String.format("%.3f", d1);
        }
        else if (d1 >= 1){
            return String.format("%.4f", d1);
        }
        else if (d1 >= .1){
            return String.format("%.5f", d1);
        }
        else if(d1 >= .01){
            return String.format("%.6f", d1);
        }
        else if (d1 >= .001){
            return String.format("%.7f", d1);
        }
        else if (d1 >= .0001){
            return String.format("%.8f", d1);
        }
        else if(d1 >= .00001){
            return String.format("%.9f", d1);
        }
        else if (d1 >= .000001){
            return String.format("%.10f", d1);
        }
        else if (d1 >= .00000001){
            return String.format("%.11f", d1);
        }

        return null;
    }

    public int recalculateNumbers(){
        if(HomePage.typeOfArbitrageString.equals("Inter-Exchange and Cross Exchange Arbitrage")) {
            bestOpportunitiesAcrossExchanges = arbitrageFinder.getBestOpportunitiesAcrossExchange();
            bestOpportunitiesWithinExchanges = arbitrageFinder.getBestOpportunitiesWithinExchange();
        }
        else if (HomePage.typeOfArbitrageString.equals("Inter-Exchange Arbitrage Only")) {
            bestOpportunitiesWithinExchanges = arbitrageFinder.getBestOpportunitiesWithinExchange();
        }
        else {
            bestOpportunitiesAcrossExchanges = arbitrageFinder.getBestOpportunitiesAcrossExchange();

        }

        if(bestOpportunitiesAcrossExchanges.size() == 0 && bestOpportunitiesWithinExchanges.size() == 0){
            ViewCryptoOpprotunities.hasData = false;
            Intent k = new Intent(this, HomePage.class);
            startActivity(k);
            return 0;
        }
        if(bestOpportunitiesWithinExchanges.size() == 0){
            topOpportunitiesArray = new Opportunity[min(bestOpportunitiesAcrossExchanges.size(),50)];

            for(int i = 0; i < topOpportunitiesArray.length; i++){
                System.out.println("First Loop");
                topOpportunitiesArray[i] = bestOpportunitiesAcrossExchanges.poll();
            }
        }
        else if (bestOpportunitiesAcrossExchanges.size() == 0){
            topOpportunitiesArray = new Opportunity[min(bestOpportunitiesWithinExchanges.size(),50)];

            for(int i = 0; i < topOpportunitiesArray.length; i++){
                System.out.println("Second loop");
                topOpportunitiesArray[i] = bestOpportunitiesWithinExchanges.poll();
            }
        }
        else {
            topOpportunitiesArray = new Opportunity[min(bestOpportunitiesAcrossExchanges.size()
                    + bestOpportunitiesWithinExchanges.size(), 50)];

            for(int i = 0; i < topOpportunitiesArray.length; i++){
                System.out.println("Third loop");
                if(bestOpportunitiesAcrossExchanges.size() == 0){
                    System.out.println("Added 1");
                    topOpportunitiesArray[i] = bestOpportunitiesWithinExchanges.poll();
                }
                else if (bestOpportunitiesWithinExchanges.size() == 0){
                    topOpportunitiesArray[i] = bestOpportunitiesAcrossExchanges.poll();
                    System.out.println("Added 2");
                }
                else if(bestOpportunitiesAcrossExchanges.peek().getPercentGain() >
                        bestOpportunitiesWithinExchanges.peek().getPercentGain()) {
                    topOpportunitiesArray[i] = bestOpportunitiesAcrossExchanges.poll();
                    System.out.println("Added 3");
                }
                else{
                    topOpportunitiesArray[i] = bestOpportunitiesWithinExchanges.poll();
                    System.out.println("Added 4");
                }
            }
        }
        return 1;
    }

    public void setUpMessages(){
        typeOneOpportunityMessage = "Type One Opportunities are when you buy the coin for USD," +
                " convert it to Bitcoin, sell bitcoin for USD, buy coin for USD, convert it to Bitcoin, ect..." +
                "\nWhere you start in the cycle doesn't matter, but the order does";
    }

    public String getDialogInfo(Opportunity opportunity){
        StringBuilder stringBuilder = new StringBuilder();
        switch (opportunity.getType()){
            case 1:
                stringBuilder.append("Step1:\nBuy ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getAskPriceUSD()));
                stringBuilder.append(" USD\nStep 2:\nCovert ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" to Bitcoin at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceBTC()));
                stringBuilder.append(" Bitcoin\nStep 3:\nSell your Bitcoin for USD at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getBidPriceUSD()));
                stringBuilder.append("Note you may start at any point in this cycle");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 2:
                stringBuilder.append("Step1:\nBuy Bitcoin at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getAskPriceUSD()));
                stringBuilder.append("Dollars \nStep 2:\nCovert Bitcoin to ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append("at ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getAskPriceBTC()));
                stringBuilder.append(" Bitcoin\nStep 3:\nSell your");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" for USD at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceUSD()));
                stringBuilder.append(" USD");
                stringBuilder.append("\nNote: you may start at any point in this cycle");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 3:
                stringBuilder.append("Step1:\nBuy ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getAskPriceUSD()));
                stringBuilder.append(" USD\nStep 2:\nCovert ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" to Ethereum at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceETH()));
                stringBuilder.append(" Ethereum\nStep 3:\nSell your Ethereum for USD at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getBidPriceUSD()));
                stringBuilder.append(" USD\nNote: you may start at any point in this cycle");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 4:
                stringBuilder.append("Step1:\nBuy Ethereum at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getAskPriceUSD()));
                stringBuilder.append(" USD\nStep 2:\nCovert Ethereum to ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append("at ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getAskPriceETH()));
                stringBuilder.append(" Ethereum\nStep 3:\nSell your");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" For USD at: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceUSD()));
                stringBuilder.append(" USD\nNote: you may start at any point in this cycle");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 5:
                stringBuilder.append("Step1:\nConvert Your Ethereum to: ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append( "at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getAskPriceETH()));
                stringBuilder.append("Ethereum\nStep 2:\nConvert ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" to Bitcoin at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceBTC()));
                stringBuilder.append(" Bitcoin\nStep 3:\nConvert Your Bitcoin to Ethereum at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getAskPriceBTC()));
                stringBuilder.append(" Bitcoin\nNote: you may start at any point in this cycle");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 6:
                stringBuilder.append("Step1:\nConvert Your Bitcoin to: ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append( "at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getAskPriceBTC()));
                stringBuilder.append(" Bitcoin\nStep 2:\nConvert ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" to Ethereum at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceETH()));
                stringBuilder.append(" Ethereum\nStep 3:\nConvert Your Ethereum to Bitcoin at: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getBidPriceBTC()));
                stringBuilder.append(" Bitcoin\nNote: you may start at any point in this cycle");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 7:
                stringBuilder.append("Step 1\nBuy ");
                stringBuilder.append(opportunity.getLowPriceCoinExchange().getName());
                stringBuilder.append(" on ");
                stringBuilder.append(opportunity.getLowPriceCoinExchange().getExchange());
                stringBuilder.append(" for: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getAskPriceUSD()));
                stringBuilder.append(" USD");
                stringBuilder.append("\nStep 2:\n");
                stringBuilder.append("Sell ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" on ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getExchange());
                stringBuilder.append(" for: $");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceUSD()));
                stringBuilder.append(" USD");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 8:
                stringBuilder.append("Step 1\nBuy ");
                stringBuilder.append(opportunity.getLowPriceCoinExchange().getName());
                stringBuilder.append(" on ");
                stringBuilder.append(opportunity.getLowPriceCoinExchange().getExchange());
                stringBuilder.append(" for: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getAskPriceBTC()));
                stringBuilder.append(" Bitcoin\nStep 2:\n");
                stringBuilder.append("Sell ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" on ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getExchange());
                stringBuilder.append(" for: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceBTC()));
                stringBuilder.append(" Bitcoin");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();

            case 9:
                stringBuilder.append("Step 1\nBuy ");
                stringBuilder.append(opportunity.getLowPriceCoinExchange().getName());
                stringBuilder.append(" on ");
                stringBuilder.append(opportunity.getLowPriceCoinExchange().getExchange());
                stringBuilder.append(" for: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getLowPriceCoinExchange().getAskPriceETH()));
                stringBuilder.append(" Ethereum\nStep 2:\n");
                stringBuilder.append("Sell ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getName());
                stringBuilder.append(" on ");
                stringBuilder.append(opportunity.getHighPriceCoinExchange().getExchange());
                stringBuilder.append(" for: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getHighPriceCoinExchange().getBidPriceETH()));
                stringBuilder.append(" Ethereum");
                stringBuilder.append("\n\nPercent Profit: ");
                stringBuilder.append(doubleToStringFiveSigDigs(opportunity.getPercentGain()));
                stringBuilder.append("\n");
                return stringBuilder.toString();
        }
        return null;
    }
}