import com.docusign.esign.api.OntologyApi;
import org.junit.Test;

public class OntologyApiTest {

    @Test
    public void TestGetEnvelope() throws Exception {
        String contentHash = "aaa";
        String contractAddr = "bffa4832edd490cc769b19f4d90a62af21067a5f";
        String ip = "http://polaris1.ont.io";
        String restUrl = ip + ":" + "20334";
        OntologyApi api = new OntologyApi(restUrl, contractAddr, 2500, 5000000);
        OntologyApi.Envelope envelope = api.getEnvelope(contentHash);
        System.out.println(envelope.contentHash);
    }
}
