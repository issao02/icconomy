package br.net.eii.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static br.net.eii.app.R.string.sCDI;

public class MainActivity extends AppCompatActivity  implements ActionBar.TabListener{


    private Button btAtualizar;
    private TextView txtCDI;
    private TextView txtIPCA;
    private TextView txtIGPM;
    private TextView txtSelic;
    private TextView txtPoupanca;

    private boolean bBotaoAtualizar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        //new PegaValores().execute();

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Three tab to display in actionbar
        ab.addTab(ab.newTab().setText(R.string.sMenu1).setTabListener(this));
        ab.addTab(ab.newTab().setText(R.string.sMenu2).setTabListener(this));
        ab.addTab(ab.newTab().setText(R.string.sMenu3).setTabListener(this));
        ab.addTab(ab.newTab().setText(R.string.sMenu4).setTabListener(this));

        CarregaCamposHome();
    }

    public void CarregaCamposHome(){
        txtCDI = (TextView)findViewById(R.id.txtCDI);
        txtIPCA = (TextView)findViewById(R.id.txtIPCA);
        txtIGPM = (TextView)findViewById(R.id.txtIGPM);
        txtSelic = (TextView)findViewById(R.id.txtSelic);
        txtPoupanca = (TextView)findViewById(R.id.txtPoupanca);
        txtCDI.setText(Global.sCDI);
        txtIPCA.setText(Global.sIPCA);
        txtIGPM.setText(Global.sIGPM);
        txtSelic.setText(Global.sSelic);
        txtPoupanca.setText(Global.sPoupanca);
    }


    public void MensagemAlerta(String sMsg){
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setMessage(sMsg);
        dlg.setNeutralButton(this.getString(R.string.sOk),null);
        dlg.show();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //Called when a tab is selected
        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                setContentView(R.layout.act_main);
                CarregaCamposHome();
                break;
            case 1:
                setContentView(R.layout.act_simulacao);
                break;
            case 2:
                setContentView(R.layout.actionbar_tab_2);
                break;
            case 3:
                setContentView(R.layout.actionbar_tab_3);
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    /*public class PegaValores extends AsyncTask<Void,Void,String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            String[] text = null;
            try {
                bBotaoAtualizar = false;

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
            bBotaoAtualizar = true;
            //btAtualizar.setEnabled(true);
            if(result != null){
                txtCDI = (TextView)findViewById(R.id.txtCDI);
                txtIPCA = (TextView)findViewById(R.id.txtIPCA);
                txtIGPM = (TextView)findViewById(R.id.txtIGPM);
                txtSelic = (TextView)findViewById(R.id.txtSelic);
                txtPoupanca = (TextView)findViewById(R.id.txtPoupanca);
                txtCDI.setText(result[0]+" a.a");
                txtIPCA.setText(result[1]+" a.a");
                txtIGPM.setText(result[2]+" a.a");
                txtSelic.setText(result[3]+" a.a");
                txtPoupanca.setText(result[4]+"% a.m");
            }else{
                MensagemAlerta(getString(R.string.sErroCarregar));
            }
        }
    }*/
}
