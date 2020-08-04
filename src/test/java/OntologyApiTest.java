import com.docusign.esign.ontology.api.OntologyApi;
import org.junit.Test;

public class OntologyApiTest {

    @Test
    public void TestGetEnvelope() throws Exception {
        String contentHash = "aaa";
        String contractAddr = "a47a0ed3f94794fa1d4006fc26f44253e7810116";
        String ip = "http://polaris1.ont.io";
        String restUrl = ip + ":" + "20334";
        OntologyApi api = new OntologyApi(restUrl, contractAddr, 2500, 5000000);
        OntologyApi.ContractEnvelope envelope = api.getEnvelope(contentHash);
        System.out.println(envelope.ownerOntId);
    }
}
