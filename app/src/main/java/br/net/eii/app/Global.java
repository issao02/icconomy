package br.net.eii.app;

import android.os.AsyncTask;
import android.widget.TextView;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PC on 24/08/2017.
 */

public class Global {

    public static String sCDI;
    public static String sIPCA;
    public static String sIGPM;
    public static String sSelic;
    public static String sPoupanca;
    public static String steste;


    public static class PegaValores extends AsyncTask<Void,Void,String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            String[] text = null;
            try {

                Document doc;
                Elements newsHeadlines;

                StringBuilder result = new StringBuilder();
                URL url = new URL("http://carteirarica.com.br/cron/tabela.js");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = rd.readLine();
                String[] lineSplit = line.split("td");
                //CDI
                String a1 = lineSplit[3].substring (0, lineSplit[3].length() - 2).substring (1);
                //IPCA
                String a2 = lineSplit[11].substring (0, lineSplit[11].length() - 2).substring (1);
                //Selic
                String a4 = lineSplit[7].substring (0, lineSplit[7].length() - 2).substring (1);
                //Poupança
                String a5 = lineSplit[15].substring (0, lineSplit[15].length() - 2).substring (1);
                //Dolar
                String a6 = lineSplit[19].substring (0, lineSplit[19].length() - 2).substring (1);
                rd.close();


                //IGPM
                //doc = Jsoup.connect("https://www.cetip.com.br/Home").get();
                //newsHeadlines = doc.select("#ctl00_Banner_lblTaxDI");
                //String a3 = newsHeadlines.text();
                String a3 = "-";


                text = new String[]{a1,a2,a3,a4,a5};

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return text;
        }

        @Override
        protected void onPostExecute(String[] result) {
            super.onPostExecute(result);
            //btAtualizar.setEnabled(true);
            if(result != null){
                sCDI = result[0]+" a.a";
                sIPCA = result[1]+" a.a";
                sIGPM = result[2]+" a.a";
                sSelic = result[3]+" a.a";
                sPoupanca = result[4]+"% a.m";
            //}else{
            //    MensagemAlerta(getString(R.string.sErroCarregar));
            }
        }
    }
}


