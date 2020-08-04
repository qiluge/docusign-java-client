package com.docusign.esign.api;

import com.github.ontio.OntSdk;
import com.github.ontio.account.Account;
import com.github.ontio.common.Helper;
import com.github.ontio.core.transaction.Transaction;
import com.github.ontio.smartcontract.neovm.abi.AbiFunction;
import com.github.ontio.smartcontract.neovm.abi.BuildParams;
import com.github.ontio.smartcontract.neovm.abi.Parameter;

public class OntologyApi {

    private OntSdk ontSdk;
    private long gasPrice;
    private long gasLimit;
    private String contractAddr;
    private String nodeRestURL;

    private final String abiJSON = "{\"hash\":\"3d9f87abb9c1d2076f72b39239e23bfe8ae129b2\",\"entrypoint\":\"Main\",\"functions\":[{\"name\":\"commitEnvelop\",\"parameters\":[{\"name\":\"fromOntId\",\"type\":\"\"},{\"name\":\"signerPubKeyIndex\",\"type\":\"\"},{\"name\":\"envelopeId\",\"type\":\"\"},{\"name\":\"signers\",\"type\":\"\"}]},{\"name\":\"deleteEnvelope\",\"parameters\":[{\"name\":\"fromOntId\",\"type\":\"\"},{\"name\":\"signerPubKeyIndex\",\"type\":\"\"},{\"name\":\"envelopeId\",\"type\":\"\"}]}]}";

    public OntologyApi(String nodeRestURL, String contractAddr, long gasPrice, long gasLimit) throws Exception {
        this.nodeRestURL = nodeRestURL;
        ontSdk = OntSdk.getInstance();
        ontSdk.setRestful(nodeRestURL);
        ontSdk.setDefaultConnect(ontSdk.getRestful());
        this.contractAddr = contractAddr;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
    }

    public void updateContractAddr(String contractAddr) {
        this.contractAddr = contractAddr;
    }

    public void updateSDKUrl(String nodeRestURL) {
        ontSdk.setRestful(nodeRestURL);
    }

    public String commitEnvelope(Account payer, Account fromOntIdSigner, String fromOntId, String signerPubKeyIndex,
                                 String envelopeId, String[] signers) throws Exception {
        String name = "commitEnvelop";
        Parameter fromOntIdParam = new Parameter("fromOntId", Parameter.Type.String, fromOntId);
        Parameter pubKeyIndexParam = new Parameter("signerPubKeyIndex", Parameter.Type.Integer,
                signerPubKeyIndex);
        Parameter envelopeIdParam = new Parameter("envelopeId", Parameter.Type.String, envelopeId);
        Parameter signersParam = new Parameter("signers", Parameter.Type.Array, signers);
        AbiFunction func = new AbiFunction(name, fromOntIdParam, pubKeyIndexParam, envelopeIdParam, signersParam);
        byte[] params = BuildParams.serializeAbiFunction(func);
        Transaction tx = ontSdk.vm().makeInvokeCodeTransaction(Helper.reverse(contractAddr), null, params,
                payer.getAddressU160().toBase58(), gasLimit, gasPrice);
        ontSdk.addSign(tx, payer);
        ontSdk.addSign(tx, fromOntIdSigner);
        boolean success = ontSdk.getConnect().sendRawTransaction(tx.toHexString());
        if (success) {
            return tx.hash().toHexString();
        }
        return "";
    }

    public String deleteEnvelope(Account payer, Account fromOntIdSigner, String fromOntId, String signerPubKeyIndex,
                                 String envelopeId) throws Exception {
        String name = "deleteEnvelope";
        Parameter fromOntIdParam = new Parameter("fromOntId", Parameter.Type.String, fromOntId);
        Parameter pubKeyIndexParam = new Parameter("signerPubKeyIndex", Parameter.Type.Integer,
                signerPubKeyIndex);
        Parameter envelopeIdParam = new Parameter("envelopeId", Parameter.Type.String, envelopeId);
        AbiFunction func = new AbiFunction(name, fromOntIdParam, pubKeyIndexParam, envelopeIdParam);
        byte[] params = BuildParams.serializeAbiFunction(func);
        Transaction tx = ontSdk.vm().makeInvokeCodeTransaction(Helper.reverse(contractAddr), null, params,
                payer.getAddressU160().toBase58(), gasLimit, gasPrice);
        ontSdk.addSign(tx, payer);
        ontSdk.addSign(tx, fromOntIdSigner);
        boolean success = ontSdk.getConnect().sendRawTransaction(tx.toHexString());
        if (success) {
            return tx.hash().toHexString();
        }
        return "";
    }
}
