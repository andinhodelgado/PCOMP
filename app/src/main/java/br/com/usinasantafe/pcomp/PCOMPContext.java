package br.com.usinasantafe.pcomp;

import android.app.Application;

import br.com.usinasantafe.pcomp.control.CheckListCTR;
import br.com.usinasantafe.pcomp.control.ConfigCTR;
import br.com.usinasantafe.pcomp.control.CompostoCTR;
import br.com.usinasantafe.pcomp.control.MotoMecCTR;
import br.com.usinasantafe.pcomp.control.PneuCTR;

/**
 * Created by anderson on 10/11/2016.
 */
public class PCOMPContext extends Application {

    private MotoMecCTR motoMecCTR;
    private CheckListCTR checkListCTR;
    private PneuCTR pneuCTR;
    private ConfigCTR configCTR;
    private CompostoCTR compostoCTR;
    private boolean verTelaLeira;
    private boolean verTimer;

    public static String versaoAplic = "2.00";
    private String verAtualCL;

    private int posCheckList;
    private int verPosTela;
    // 1 - Inicio do Aplicativo;
    // 2 - Pesagem Tara;

    private int contDataHora;

    public void onCreate() {
        super.onCreate();
    }

    public MotoMecCTR getMotoMecCTR(){
        if (motoMecCTR == null)
            motoMecCTR = new MotoMecCTR();
        return motoMecCTR;
    }

    public CheckListCTR getCheckListCTR() {
        if (checkListCTR == null)
            checkListCTR = new CheckListCTR();
        return checkListCTR;
    }

    public PneuCTR getPneuCTR(){
        if (pneuCTR == null)
            pneuCTR = new PneuCTR();
        return pneuCTR;
    }

    public ConfigCTR getConfigCTR(){
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public CompostoCTR getCompostoCTR(){
        if (compostoCTR == null)
            compostoCTR = new CompostoCTR();
        return compostoCTR;
    }

    public boolean isVerTelaLeira() {
        return verTelaLeira;
    }

    public void setVerTelaLeira(boolean verTelaLeira) {
        this.verTelaLeira = verTelaLeira;
    }

    public boolean isVerTimer() {
        return verTimer;
    }

    public void setVerTimer(boolean verTimer) {
        this.verTimer = verTimer;
    }

    public int getPosCheckList() {
        return posCheckList;
    }

    public void setPosCheckList(int posCheckList) {
        this.posCheckList = posCheckList;
    }

    public String getVerAtualCL() {
        return verAtualCL;
    }

    public void setVerAtualCL(String verAtualCL) {
        this.verAtualCL = verAtualCL;
    }

    public int getVerPosTela() {
        return verPosTela;
    }

    public void setVerPosTela(int verPosTela) {
        this.verPosTela = verPosTela;
    }

    public int getContDataHora() {
        return contDataHora;
    }

    public void setContDataHora(int contDataHora) {
        this.contDataHora = contDataHora;
    }
}
