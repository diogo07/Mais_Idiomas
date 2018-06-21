package br.com.maisidiomas.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.maisidiomas.R;
import br.com.maisidiomas.controller.ControllerDashBoard;

public class DashBoardActivity extends ModeloActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String nome, login;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nome = getIntent().getStringExtra("nome");
        login = getIntent().getStringExtra("login");
        String i = getIntent().getStringExtra("id").toString();
        id = Integer.parseInt(i);

        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        new ControllerDashBoard(this, id);

        TextView novaFonte = (TextView) findViewById(R.id.tv_ranking);
        novaFonte.setTypeface(getFont());


        View headerView = navigationView.getHeaderView(0);

        TextView tvNome = headerView.findViewById(R.id.tv_nome);
        tvNome.setText(nome);

        TextView tvLogin = headerView.findViewById(R.id.tv_login);
        tvLogin.setText(login);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_jogar) {
            Intent i = new Intent(DashBoardActivity.this, LevelActivity.class);
            i.putExtra("id", ""+id);
            i.putExtra("login", ""+login);
            startActivity(i);
        } else if (id == R.id.nav_sobre) {
            Intent i = new Intent(DashBoardActivity.this, SobreActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_conf) {
            Intent i = new Intent(DashBoardActivity.this, ConfiguracoesActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_add_quest) {
            Intent i = new Intent(DashBoardActivity.this, InserirQuestaoActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_sair) {
            finish();
        } else if (id == R.id.nav_exemplo) {

        } else if (id == R.id.nav_dicionario) {
            Intent intent = new Intent(this, DicionarioActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exibirMensagem(String mensagem){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Mensagem");
        alertDialogBuilder
                .setMessage(mensagem)
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
