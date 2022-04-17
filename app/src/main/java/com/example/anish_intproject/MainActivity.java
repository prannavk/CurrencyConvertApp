package com.example.anish_intproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSpinner;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {

    TextView fromC, ToC, ts1, ts2, ts3;
    String toCq = "";
    String fromCq = "";
    Button pickcountryA, pickcountryB, convertPress, dispconvrate, abt_act, clear_bt;
    EditText enteredAmt;
    Dialog fromspin, ToSpin;
    ArrayList<String> aral, aralc;
    String[] country = {
            "ALL" , "XCD" , "EUR" , "BBD" , "BTN" , "BND" , "XAF" , "CUP" , "USD" , "FKP" , "GIP" , "HUF" , "IRR" , "JMD" , "AUD" , "LAK" , "LYD" , "MKD" , "XOF" , "NZD" , "OMR" , "PGK" , "RWF" , "WST" , "RSD" , "SEK" , "TZS" , "AMD" , "BSD" , "BAM" , "CVE" , "CNY" , "CRC" , "CZK" , "ERN" , "GEL" , "HTG" , "INR" , "JOD" , "KRW" , "LBP" , "MWK" , "MRO" , "MZN" , "ANG" , "PEN" , "QAR" , "STD" , "SLL" , "SOS" , "SDG" , "SYP" , "AOA" , "AWG" , "BHD" , "BZD" , "BWP" , "BIF" , "KYD" , "COP" , "DKK" , "GTQ" , "HNL" , "IDR" , "ILS" , "KZT" , "KWD" , "LSL" , "MYR" , "MUR" , "MNT" , "MMK" , "NGN" , "PAB" , "PHP" , "RON" , "SAR" , "SGD" , "ZAR" , "SRD" , "TWD" , "TOP" , "VEF" , "DZD" , "ARS" , "AZN" , "BYR" , "BOB" , "BGN" , "CAD" , "CLP" , "CDF" , "DOP" , "FJD" , "GMD" , "GYD" , "ISK" , "IQD" , "JPY" , "KPW" , "LVL" , "CHF" , "MGA" , "MDL" , "MAD" , "NPR" , "NIO" , "PKR" , "PYG" , "SHP" , "SCR" , "SBD" , "LKR" , "THB" , "TRY" , "AED" , "VUV" , "YER" , "AFN" , "BDT" , "BRL" , "KHR" , "KMF" , "HRK" , "DJF" , "EGP" , "ETB" , "XPF" , "GHS" , "GNF" , "HKD" , "XDR" , "KES" , "KGS" , "LRD" , "MOP" , "MVR" , "MXN" , "NAD" , "NOK" , "PLN" , "RUB" , "SZL" , "TJS" , "TTD" , "UGX" , "UYU" , "VND" , "TND" , "UAH" , "UZS" , "TMT" , "GBP" , "ZMW" , "BTC" , "BYN" , "BMD" , "GGP" , "CLF" , "CUC" , "IMP" , "JEP" , "SVC" , "ZMK" , "XAG" , "ZWL" ,
    };
    String nameOfCurrency[]={
            "0. currencyName", "1. Albanian Lek", "2. East Caribbean Dollar", "3. Euro", "4. Barbadian Dollar", "5. Bhutanese Ngultrum", "6. Brunei Dollar", "7. Central African CFA Franc", "8. Cuban Peso", "9. United States Dollar", "10. Falkland Islands Pound", "11. Gibraltar Pound", "12. Hungarian Forint", "13. Iranian Rial", "14. Jamaican Dollar", "15. Australian Dollar", "16. Lao Kip", "17. Libyan Dinar", "18. Macedonian Denar", "19. West African CFA Franc", "20. New Zealand Dollar", "21. Omani Rial", "22. Papua New Guinean Kina", "23. Rwandan Franc", "24. Samoan Tala", "25. Serbian Dinar", "26. Swedish Krona", "27. Tanzanian Shilling", "28. Armenian Dram", "29. Bahamian Dollar", "30. Bosnia And Herzegovina Konvertibilna Marka", "31. Cape Verdean Escudo", "32. Chinese Yuan", "33. Costa Rican Colon", "34. Czech Koruna", "35. Eritrean Nakfa", "36. Georgian Lari", "37. Haitian Gourde", "38. Indian Rupee", "39. Jordanian Dinar", "40. South Korean Won", "41. Lebanese Lira", "42. Malawian Kwacha", "43. Mauritanian Ouguiya", "44. Mozambican Metical", "45. Netherlands Antillean Gulden", "46. Peruvian Nuevo Sol", "47. Qatari Riyal", "48. Sao Tome And Principe Dobra", "49. Sierra Leonean Leone", "50. Somali Shilling", "51. Sudanese Pound", "52. Syrian Pound", "53. Angolan Kwanza", "54. Aruban Florin", "55. Bahraini Dinar", "56. Belize Dollar", "57. Botswana Pula", "58. Burundi Franc", "59. Cayman Islands Dollar", "60. Colombian Peso", "61. Danish Krone", "62. Guatemalan Quetzal", "63. Honduran Lempira", "64. Indonesian Rupiah", "65. Israeli New Sheqel", "66. Kazakhstani Tenge", "67. Kuwaiti Dinar", "68. Lesotho Loti", "69. Malaysian Ringgit", "70. Mauritian Rupee", "71. Mongolian Tugrik", "72. Myanma Kyat", "73. Nigerian Naira", "74. Panamanian Balboa", "75. Philippine Peso", "76. Romanian Leu", "77. Saudi Riyal", "78. Singapore Dollar", "79. South African Rand", "80. Surinamese Dollar", "81. New Taiwan Dollar", "82. Paanga", "83. Venezuelan Bolivar", "84. Algerian Dinar", "85. Argentine Peso", "86. Azerbaijani Manat", "87. Belarusian Ruble", "88. Bolivian Boliviano", "89. Bulgarian Lev", "90. Canadian Dollar", "91. Chilean Peso", "92. Congolese Franc", "93. Dominican Peso", "94. Fijian Dollar", "95. Gambian Dalasi", "96. Guyanese Dollar", "97. Icelandic Króna", "98. Iraqi Dinar", "99. Japanese Yen", "100. North Korean Won", "101. Latvian Lats", "102. Swiss Franc", "103. Malagasy Ariary", "104. Moldovan Leu", "105. Moroccan Dirham", "106. Nepalese Rupee", "107. Nicaraguan Cordoba", "108. Pakistani Rupee", "109. Paraguayan Guarani", "110. Saint Helena Pound", "111. Seychellois Rupee", "112. Solomon Islands Dollar", "113. Sri Lankan Rupee", "114. Thai Baht", "115. Turkish New Lira", "116. UAE Dirham", "117. Vanuatu Vatu", "118. Yemeni Rial", "119. Afghan Afghani", "120. Bangladeshi Taka", "121. Brazilian Real", "122. Cambodian Riel", "123. Comorian Franc", "124. Croatian Kuna", "125. Djiboutian Franc", "126. Egyptian Pound", "127. Ethiopian Birr", "128. CFP Franc", "129. Ghanaian Cedi", "130. Guinean Franc", "131. Hong Kong Dollar", "132. Special Drawing Rights", "133. Kenyan Shilling", "134. Kyrgyzstani Som", "135. Liberian Dollar", "136. Macanese Pataca", "137. Maldivian Rufiyaa", "138. Mexican Peso", "139. Namibian Dollar", "140. Norwegian Krone", "141. Polish Zloty", "142. Russian Ruble", "143. Swazi Lilangeni", "144. Tajikistani Somoni", "145. Trinidad and Tobago Dollar", "146. Ugandan Shilling", "147. Uruguayan Peso", "148. Vietnamese Dong", "149. Tunisian Dinar", "150. Ukrainian Hryvnia", "151. Uzbekistani Som", "152. Turkmenistan Manat", "153. British Pound", "154. Zambian Kwacha", "155. Bitcoin", "156. New Belarusian Ruble", "157. Bermudan Dollar", "158. Guernsey Pound", "159. Chilean Unit Of Account", "160. Cuban Convertible Peso", "161. Manx pound", "162. Jersey Pound", "163. Salvadoran Colón", "164. Old Zambian Kwacha", "165. Silver (troy ounce)", "166. Zimbabwean Dollar"
    };
    String convertFromValue, convertToValue, conversionValue;
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ts1 = (TextView) findViewById(R.id.textView6);
        ts2 = (TextView) findViewById(R.id.textView7776);
        ts3 = (TextView) findViewById(R.id.textView77756);
        pickcountryA = (Button) findViewById(R.id.button1211);
        pickcountryB = (Button) findViewById(R.id.button2112);
        convertPress = (Button) findViewById(R.id.button3);
        dispconvrate = (Button) findViewById(R.id.button121132);
        abt_act = (Button) findViewById(R.id.button4);
        enteredAmt = (EditText) findViewById(R.id.editTextNumberDecimal);
        fromC = (TextView) findViewById(R.id.textView7);
        ToC = (TextView) findViewById(R.id.textView8);
        clear_bt = (Button)findViewById(R.id.clear_ll_text);
        vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        /*aral = new ArrayList<>();
        for (String i : country) {
            aral.add(i);
        }*/
        aral = new ArrayList<>();
        aral.addAll(Arrays.asList(country));
        aralc = new ArrayList<>();
        aralc.addAll(Arrays.asList(nameOfCurrency));

        abt_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abt_i = new Intent(MainActivity.this,AboutActivity_1.class);
                startActivity(abt_i);

            }
        });

        pickcountryA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromspin = new Dialog(MainActivity.this);
                fromspin.setContentView(R.layout.from_spinner);
                fromspin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                /*fromspin.getWindow().setLayout(390, 500);*/
                fromspin.show();

                EditText c_edt = (EditText) fromspin.findViewById(R.id.editTextTextPersonName);
                ListView fromlist = (ListView) fromspin.findViewById(R.id.country_list_view);
                ImageView xfrom = (ImageView) fromspin.findViewById(R.id.imageView_x_from);
                /*Button frombtn_set = (Button) fromspin.findViewById(R.id.ok_btn_card);*/

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this ,android.R.layout.simple_list_item_1 ,aralc);
                /*ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.list_content, aral);*/
                fromlist.setAdapter(arrayAdapter);

                c_edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence ,int i ,int i1 ,int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence ,int i ,int i1 ,int i2) {
                        Editable e = c_edt.getText();
                        arrayAdapter.getFilter().filter(e);
                        /*arrayAdapter1.getFilter().filter(e);*/

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                fromlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView ,View view ,int i ,long l) {
                        c_edt.setText(arrayAdapter.getItem(i));
                        /*fromCq = arrayAdapter1.getItem(i).toString().trim();*/
                        String fromget = arrayAdapter.getItem(i).trim();
                        StringBuilder sba = new StringBuilder();
                        StringBuilder sbb = new StringBuilder();
                        Pattern nrm = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                        int j;
                        char che;
                        for(j = 0; j<fromget.length(); j++){
                            che = fromget.charAt(j);
                            sba.append(che);
                            if(nrm.matcher(sba.toString()).matches())
                                sbb.append(che);
                            sba.delete(0,1);
                        }
                        Log.i("exceptions_JSONrequest","Selected: "+fromget);
                        fromC.setText(arrayAdapter.getItem(i));
                        String fC = sbb.toString().trim();
                        int ibb = Integer.parseInt(fC) - 1;
                        fromCq = aral.get(ibb);
                        Log.i("exceptions_JSONrequest","picked curr: "+fromCq);
                        fromspin.dismiss();

                    }
                });
                xfrom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Drawable rr = AppCompatResources.getDrawable(getApplicationContext(),R.drawable.blue_border_rounded_cornwe);
                        xfrom.setImageDrawable(rr);
                        fromspin.dismiss();
                    }
                });


            }
        });
        pickcountryB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToSpin = new Dialog(MainActivity.this);
                ToSpin.setContentView(R.layout.to_spinner);
                ToSpin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                /*ToSpin.getWindow().setLayout(390,500);*/
                ToSpin.show();

                EditText toedit = (EditText) ToSpin.findViewById(R.id.editTextTextPName);
                ListView list_to = (ListView) ToSpin.findViewById(R.id.country_list_view_2);
                ImageView xto = (ImageView) ToSpin.findViewById(R.id.imageView_x_to);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this ,android.R.layout.simple_list_item_1 ,aralc);
                /*ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.list_content,aral);*/
                list_to.setAdapter(arrayAdapter);

                toedit.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence ,int i ,int i1 ,int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence ,int i ,int i1 ,int i2) {
                        Editable s = toedit.getText();
                        arrayAdapter.getFilter().filter(s);
                        /*arrayAdapter2.getFilter().filter(s);*/

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                list_to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView ,View view ,int i ,long l) {
                        toedit.setText(arrayAdapter.getItem(i));
                        ToSpin.dismiss();
                        /*toCq = arrayAdapter.getItem(i).toString().trim();*/
                        ToC.setText(arrayAdapter.getItem(i));
                        StringBuilder tba = new StringBuilder();
                        StringBuilder tbb = new StringBuilder();
                        String aralc_item = arrayAdapter.getItem(i).trim();
                        Pattern tptn = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                        int k;
                        char ts;
                        for(k = 0; k <aralc_item.length() ; k++){
                            ts = aralc_item.charAt(k);
                            tba.append(ts);
                            Matcher m = tptn.matcher(tba.toString());
                            if(m.matches())
                                tbb.append(ts);
                            tba.delete(0,1);
                        }
                        Log.i("exceptions_JSONrequest","To Country index: "+tbb.toString());
                        int to_getter_i = Integer.parseInt(tbb.toString().trim()) - 1;
                        toCq = aral.get(to_getter_i);
                        Log.i("exceptions_JSONrequest","To Curr picked: "+toCq);

                    }
                });
                xto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Drawable rrtr = AppCompatResources.getDrawable(getApplicationContext(),R.drawable.blue_border_rounded_cornwe);
                        xto.setImageDrawable(rrtr);
                        ToSpin.dismiss();
                    }
                });

            }
        });

        dispconvrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                convertFromValue = fromCq;
                convertToValue = toCq;
                String url = "https://free.currconv.com/api/v7/convert?q=" + convertFromValue + "_" + convertToValue + "&compact=ultra&apiKey=681dca0c55b0d0fdca26";
                /*https://free.currconv.com/api/v7/convert?q=USD_PHP&compact=ultra&apiKey=681dca0c55b0d0fdca26*/
                /*https://free.currconv.com/api/v7/convert?q=XAF_ERN&compact=ultra&apiKey=681dca0c55b0d0fdca26*/
                try{
                    StringRequest strr = new StringRequest(Request.Method.GET ,url ,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsb = null;
                            try {
                                jsb = new JSONObject(response);
                                conversionValue = jsb.get(convertFromValue+"_"+convertToValue).toString().trim();
                                Toast.makeText(MainActivity.this ,"Conversion Rate for unit: "+conversionValue ,Toast.LENGTH_SHORT).show();
                            }
                            catch (JSONException je){
                                je.printStackTrace();
                            }

                        }
                    } ,new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext() ,"Error response while retreiving conversion rate, hence opened in browser" ,Toast.LENGTH_SHORT).show();
                            Log.i("exceptions_JSONrequest","Error response while retreiving conversion rate value");
                            /*Intent gil = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(gil);*/
                        }
                    });
                    queue.add(strr);
                }
                catch (Exception e){
                    Log.i("exceptions_JSONrequest","Exception occoured while retreiving conversion rate value");
                    e.printStackTrace();
                }


            }
        });

        dispconvrate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext() , "Objective: Convert "+fromCq+" to "+toCq , Toast.LENGTH_SHORT).show();
                vibe.vibrate(400);
                return false;
            }
        });

        convertPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eAmt = enteredAmt.getText().toString().trim();
                convertFromValue = fromCq;
                convertToValue = toCq;
                if(convertFromValue.equalsIgnoreCase("") && convertToValue.equalsIgnoreCase("")){
                   /* try{
                        Double amtToConv = Double.valueOf(eAmt);
                    } catch (Exception e){
                        Log.i("exceptions_JSONrequest","Error while retreiving and converting given amount");
                    }*/
                    Log.i("exceptions_JSONrequest","Error while converting given amount - Countries either not picked or cleared");
                    Toast.makeText(getApplicationContext(), "Pick Countries First", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        Double amtToConv = Double.valueOf(eAmt);
                        getConversionRate(convertFromValue ,convertToValue ,amtToConv);
                    } catch (Exception exception) {
                        Toast.makeText(getApplicationContext() ,"getConversionRate exception occoured" ,Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    private void getConversionRate(String convertFromValue ,String convertToValue ,Double amtToConv) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q=" + convertFromValue + "_" + convertToValue + "&compact=ultra&apiKey=681dca0c55b0d0fdca26";
        /* https://free.currconv.com/api/v7/convert?q=USD_PHP&compact=ultra&apiKey=5fdb987da4a5c16abfb5 */
        /* https://free.currconv.com/api/v7/convert?q=USD_PHP&compact=ultra&apiKey=2be0e5f857553ec7088d */
        /*"https://free.currconv.com/api/v7//convert?q="+convertFromValue+"_"+convertToValue+"&compact=ultra&apiKey=22e91ab924eb2aa6f9a4"*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET ,url ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double conversionRateValue = round(((Double) jsonObject.get(convertFromValue + "_" + convertToValue)) ,3);
                    /*Toast.makeText(getApplicationContext() ,"Conversion Rate:  "+conversionRateValue ,Toast.LENGTH_SHORT).show();*/
                    conversionValue = ("" + round((conversionRateValue * amtToConv), 3)).toString();
                    ts1.setText(conversionValue);

                } catch (JSONException ej) {
                    ej.printStackTrace();
                    Log.i("exceptions_JSONrequest","Error while retreiving and converting given amount");
                }
            }
        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext() ,"Error response while retreiving rate and converting the currency" ,Toast.LENGTH_SHORT).show();
                Log.i("exceptions_JSONrequest","Error response for retreiving and then converting the amount");
            }
        });
        queue.add(stringRequest);

        ts2.setText(String.format("Currency value in: %s",convertToValue));
        ts3.setText(ToC.getText().toString().trim());
        clear_bt.setVisibility(View.VISIBLE);
        clear_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromC.setText("Pick Country-");
                ToC.setText("Pick Country-");
                fromCq="";
                toCq="";
                enteredAmt.setText("");
                ts1.setText("");
                ts2.setText("");
                ts3.setText("");
                clear_bt.setVisibility(View.INVISIBLE);
                clear_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toat message or Log tag
                    }
                });
            }
        });

    }

    public static double round (Double aDouble,int i){
        if (i < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(aDouble);
        bd = bd.setScale(i ,RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}

