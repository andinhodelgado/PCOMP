package br.com.usinasantafe.pcomp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcomp.PCOMPContext;
import br.com.usinasantafe.pcomp.R;
import br.com.usinasantafe.pcomp.model.bean.estaticas.ProdutoBean;
import br.com.usinasantafe.pcomp.util.ConexaoWeb;

public class ProdutoActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txtResult;
    private ProdutoBean produtoBean;
    private PCOMPContext pcompContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_produto);

            pcompContext = (PCOMPContext) getApplication();
            txtResult = (TextView) findViewById(R.id.txResult);

            Button buttonOkOS = (Button) findViewById(R.id.buttonOkProd);
            Button buttonCancOS = (Button) findViewById(R.id.buttonCancProd);
            Button btnCapturaBarra = (Button) findViewById(R.id.btnCapturaBarra);

            buttonOkOS.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(!txtResult.getText().equals("PRODUTO:")) {

                        Long statusCon;
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(ProdutoActivity.this)) {
                            statusCon = 1L;
                        }
                        else{
                            statusCon = 0L;
                        }

                        pcompContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                        pcompContext.getConfigCTR().setStatusApontConfig(2L);
                        pcompContext.getCompostoCTR().apontCarreg(ProdutoActivity.this, produtoBean);

                        Intent it = new Intent(ProdutoActivity.this, MenuMotoMecActivity.class);
                        startActivity(it);
                        finish();

                    }

                }
            });

            buttonCancOS.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent it = new Intent(ProdutoActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                }
            });

        btnCapturaBarra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                produtoBean = pcompContext.getCompostoCTR().getProduto("A500055");
                txtResult.setText("PRODUTO: " + produtoBean.getCodProduto() + "\n" + produtoBean.getDescProduto());

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){

            String cod = data.getStringExtra("SCAN_RESULT");
            if (pcompContext.getCompostoCTR().verProduto(cod)) {
                produtoBean = pcompContext.getCompostoCTR().getProduto(cod);
                txtResult.setText("PRODUTO: " + produtoBean.getCodProduto() + "\n" + produtoBean.getDescProduto());
            }

        }

    }

    public void onBackPressed()  {
    }

}
