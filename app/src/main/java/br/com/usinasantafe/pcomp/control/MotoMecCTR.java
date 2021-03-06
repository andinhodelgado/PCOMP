package br.com.usinasantafe.pcomp.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcomp.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcomp.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.pcomp.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pcomp.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.pcomp.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.pcomp.model.dao.ApontMMDAO;
import br.com.usinasantafe.pcomp.model.dao.AtividadeDAO;
import br.com.usinasantafe.pcomp.model.dao.BoletimMMDAO;
import br.com.usinasantafe.pcomp.model.dao.FuncDAO;
import br.com.usinasantafe.pcomp.model.dao.MotoMecDAO;
import br.com.usinasantafe.pcomp.model.dao.OSDAO;
import br.com.usinasantafe.pcomp.model.dao.TurnoDAO;
import br.com.usinasantafe.pcomp.util.AtualDadosServ;
import br.com.usinasantafe.pcomp.util.Tempo;

public class MotoMecCTR {

    private BoletimMMBean boletimMMBean;
    private MotoMecBean motoMecBean;

    public MotoMecCTR() {
        if (boletimMMBean == null)
            boletimMMBean = new BoletimMMBean();
    }

    public boolean verBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.verBolAberto();
    }

    public void atualApont(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        ApontMMBean apontMMBean = apontMMDAO.getApontMMAberto();
        apontMMDAO.updApont(apontMMBean);
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public boolean verOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.verOS(nroOS);
    }

    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

    public void setFuncBol(Long matric){
        boletimMMBean = new BoletimMMBean();
        boletimMMBean.setMatricFuncBolMM(matric);
    }

    public void setEquipBol(){
        ConfigCTR configCTR = new ConfigCTR();
        boletimMMBean.setIdEquipBolMM(configCTR.getEquip().getIdEquip());
    }

    public void setTurnoBol(Long idTurno){
        boletimMMBean.setIdTurnoBolMM(idTurno);
    }

    public void setOSBol(Long os){
        boletimMMBean.setOsBolMM(os);
    }

    public void setAtivBol(Long ativ){
        ConfigCTR configCTR = new ConfigCTR();
        boletimMMBean.setAtivPrincBolMM(ativ);
        boletimMMBean.setStatusConBolMM(configCTR.getConfig().getStatusConConfig());
    }


    public void setHodometroInicialBol(Double horimetroNum, Double longitude, Double latitude){
        boletimMMBean.setHodometroInicialBolMM(horimetroNum);
        boletimMMBean.setHodometroFinalBolMM(0D);
        boletimMMBean.setIdExtBolMM(0L);
        boletimMMBean.setLongitudeBolMM(longitude);
        boletimMMBean.setLatitudeBolMM(latitude);
    }

    public void setHodometroFinalBol(Double horimetroNum){
        boletimMMBean.setHodometroFinalBolMM(horimetroNum);
    }

    public void setMotoMecBean(MotoMecBean motoMecBean) {
        this.motoMecBean = motoMecBean;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

    public Long getTurno(){
        return boletimMMBean.getIdTurnoBolMM();
    }

    public Long getFunc(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getMatricNomeFunc().getMatricFunc();
    }

    public FuncBean getMatricNomeFunc(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getMatricNomeFunc();
    }

    public List getMotoMecList(Long aplic) {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getMotoMecList(aplic);
    }

    public List getParadaList(Long aplic) {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getParadaList(aplic);
    }

    public List<TurnoBean> getTurnoList(){
        ConfigCTR configCTR = new ConfigCTR();
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.funcList(configCTR.getEquip().getCodTurno());
    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS MOTOMEC BOLETIM //////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolAberto(boletimMMBean);
    }

    public void salvarBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolFechado(boletimMMBean);
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verEnvioBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public boolean verEnvioBolFechMM() {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolFechadoList().size() > 0;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolAbertoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolAberto();
    }

    public String dadosEnvioBolFechadoMM(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolFechado();
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updBolAbertoMM(String retorno){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.updateBolAberto(retorno);
    }

    public void delBolFechadoMM(String retorno) {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.deleteBolFechado(retorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public Boolean verEnvioDadosApont(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return (apontMMDAO.getListApontEnvio().size() > 0);
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioApontBolMM(Long idBol){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio(idBol));
    }

    public String dadosEnvioApontMM(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio());
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updateApontMM(String retorno) {
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.updateApont(retorno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosFunc(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("MotoristaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    public void atualDadosLeira(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList leiraArrayList = new ArrayList();
        leiraArrayList.add("LeiraBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, leiraArrayList);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// VERIFICAÇÃO E ATUALIZAÇÃO DE DADOS ////////////////////////////

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        OSDAO osDAO = new OSDAO();
        osDAO.verOS(dado, telaAtual, telaProx, progressDialog);
    }

    public void verAtiv(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        atividadeDAO.verAtiv(dado  + "_" + configCTR.getEquip().getNroEquip(), telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////// RETORNO DE LISTA DAS ATIVIDADES DA OS /////////////////////////////

    public ArrayList getAtivArrayList(Long nroOS){
        ConfigCTR configCTR = new ConfigCTR();
        AtividadeDAO atividadeDAO = new AtividadeDAO();
        return atividadeDAO.retAtivArrayList(configCTR.getEquip().getIdEquip(), nroOS);
    }

    //////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////// CRIAR E ATUALIZAR APONTAMENTO ////////////////////////////////////

    public void insApontMM(Double longitude, Double latitude, Long statusCon){

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        apontMMDAO.salvarApont(motoMecBean, configCTR.getConfig(), boletimMMDAO.getBolMMAberto(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());

//        if(status == 0L){
//            CabecPneuDAO cabecPneuDAO = new CabecPneuDAO();
//            cabecPneuDAO.salvarDados(func, equip, getIdApont());
//        }

    }

    public boolean verifBackupApont() {
        ConfigCTR configCTR = new ConfigCTR();
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.verifBackupApont(motoMecBean, configCTR.getConfig());
    }

    public void insParadaCheckList(Double longitude, Double latitude, Long statusCon, Long aplic){

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        apontMMDAO.salvarApont(motoMecDAO.getCheckList(aplic), configCTR.getConfig(), boletimMMDAO.getBolMMAberto(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());
    }

    public void insVoltaTrab(Double longitude, Double latitude, Long statusCon, Long aplic){

        ApontMMDAO apontMMDAO = new ApontMMDAO();
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        ConfigCTR configCTR = new ConfigCTR();
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        apontMMDAO.salvarApont(motoMecDAO.getVoltaTrabalho(aplic), configCTR.getConfig(), boletimMMDAO.getBolMMAberto(), longitude, latitude, statusCon);

        atualQtdeApontBol();

        configCTR.setDtUltApontConfig(Tempo.getInstance().dataComHora().getDataHora());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// ATUALIZAR QTDE DE APONTAMENTO DO BOLETIM ///////////////////////////

    public void atualQtdeApontBol(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.atualQtdeApontBol();
    }

    ////////////////////////////////////////////////////////////////////////////////////


}
