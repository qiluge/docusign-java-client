package com.docusign.esign.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.ontio.OntSdk;
import com.github.ontio.account.Account;
import com.github.ontio.common.Helper;
import com.github.ontio.core.transaction.Transaction;
import com.github.ontio.smartcontract.neovm.abi.AbiFunction;
import com.github.ontio.smartcontract.neovm.abi.BuildParams;
import com.github.ontio.smartcontract.neovm.abi.Parameter;

public class OntologyApi {

    public class Envelope {
        public String owner;
        public String contentHash;
        public String[] signers;

        public Envelope(String owner, String contentHash, String[] signers) {
            this.owner = owner;
            this.contentHash = contentHash;
            this.signers = signers;
        }
    }

    private OntSdk ontSdk;
    private long gasPrice;
    private long gasLimit;
    private String contractAddr;
    private String nodeRestURL;

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

    public String commitEnvelope(Account payer, Account fromOntIdSigner, int signerPubKeyIndex,
                                 Envelope envelope) throws Exception {
        String name = "commitEnvelope";
        Parameter fromOntIdParam = new Parameter("fromOntId", Parameter.Type.String, envelope.owner);
        Parameter pubKeyIndexParam = new Parameter("signerPubKeyIndex", Parameter.Type.Integer,
                signerPubKeyIndex);
        Parameter contentHashParam = new Parameter("contentHash", Parameter.Type.String,
                envelope.contentHash);
        Parameter signersParam = new Parameter("signers", Parameter.Type.Array, envelope.signers);
        AbiFunction func = new AbiFunction(name, fromOntIdParam, pubKeyIndexParam, contentHashParam, signersParam);
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

    public Envelope getEnvelope(String contentHash) throws Exception {
        String name = "getEnvelope";
        Parameter contentHashParam = new Parameter("contentHash", Parameter.Type.String, contentHash);
        AbiFunction func = new AbiFunction(name, contentHashParam);
        Object obj = ontSdk.neovm().sendTransaction(Helper.reverse(contractAddr), null, null, 0,
                0, func, true);
        JSONArray res = ((JSONObject) obj).getJSONArray("Result");
        if (res.size() != 3) {
            throw new Exception("illegal envelope");
        }
        String hexOwner = (String) res.get(0);
//        String hexContextHash = (String) res.get(1);
        JSONArray hexSigners = (JSONArray) res.get(2);
        String owner = new String(Helper.hexToBytes(hexOwner));
//        String contentHash = new String(Helper.hexToBytes(hexContextHash));
        String[] signers = new String[hexSigners.size()];
        for (int i = 0; i < hexSigners.size(); i++) {
            String signerOntId = (String) hexSigners.get(i);
            signers[i] = new String(Helper.hexToBytes(signerOntId));
        }
        return new Envelope(owner, contentHash, signers);
    }

    public String deleteEnvelope(Account payer, Account fromOntIdSigner, String fromOntId, String signerPubKeyIndex,
                                 String contentHash) throws Exception {
        String name = "deleteEnvelope";
        Parameter fromOntIdParam = new Parameter("fromOntId", Parameter.Type.String, fromOntId);
        Parameter pubKeyIndexParam = new Parameter("signerPubKeyIndex", Parameter.Type.Integer,
                signerPubKeyIndex);
        Parameter contentHashParam = new Parameter("contentHash", Parameter.Type.String, contentHash);
        AbiFunction func = new AbiFunction(name, fromOntIdParam, pubKeyIndexParam, contentHashParam);
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
