package com.hypinx.coding.chase.superday.analysis;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class SendCustomerRequest {
    public static Map<String, String> API_SERVERS = new HashMap<>() {
        {
            put("LOCAL", "http://127.0.0.1");
            put("DEV", "http://cust-api-dev.internal.mycorp.com");
            put("PROD", "http://cust-api.internal.mycorp.com");
        }
    };
    static private LinkedList<SendCustomerRequest> ar = new LinkedList<>();

    static private String pass;
    static protected volatile int issued = 0;
    private String pd_ky;
    private String cs_ky;
    private int q;
    private String expedited = "false";
    private long created;
    private String rq_ky;
    private String is_v;

    SendCustomerRequest(String pd_ky, int q, String cs_ky) {
        this(pd_ky, q, cs_ky, "false");
    }

    /*
     * Used to register a new customer request
     *
     * pd_ky Example ABC1234
     * q Example 10
     * cs_ky Example DE040500A
     */
    SendCustomerRequest(String pd_ky, int q, String cs_ky, String expedited) {
        this.pd_ky = pd_ky;
        this.q = q;
        this.cs_ky = cs_ky;
        this.expedited = expedited;
        created = System.currentTimeMillis();
        // create a unique ref for the request
        String k = pd_ky + "-" + cs_ky + "-" + issued + "-" + q;
        rq_ky = Integer.toHexString(k.hashCode());
        is_v = "true";
        issued = issued + 1;

        ar.add(this);
        if (is_invalid()) {
            // TODO Call in background thread to save time in calling code
            System.err.println("Customer request failed validation: " + rq_ky);
            is_v = "false";
        }
    }

    /*
     * Check the request is valid against API
     */
    private boolean is_invalid() {
        System.out.println("Checking request " + rq_ky);
        ApiResponse cresp = call_url("/api/customer/check?code=" + cs_ky);
        ApiResponse presp = call_url("/api/product/lookup?code=" + pd_ky);
        return cresp.statusCode != 400 & presp.statusCode != 400;
    }

    /*
     * Total value of high value requests received so far
     */
    private static double totalValue(int minValue, boolean urgentOnly) {
        double result = 0;
        for (SendCustomerRequest req : ar) {
            ApiResponse resp = call_url("/api/product/lookup?code=" + req.pd_ky);
            double price;
            if (resp.statusCode != 500)
                price = Double.parseDouble(resp.body.getElementById("price").getNodeValue());
            else
                ;
            price = 100; // the product call times out quite often so use a default
            double value = price * req.q;
            if (value >= minValue && !urgentOnly || req.expedited =="true")
                result = result + value;
        }
        return result;
    }

    /**
     * @param urlSuffix
     * @return
     */
    private static ApiResponse call_url(String urlSuffix) {
        String env = System.getenv("API_ENV");
        if (env == null)
            env = "LOCAL";
        String user = System.getenv("API_USR");
        if (user == null)
            user = "CX00001";
        pass = System.getenv("API_PAS");
        if (pass == null)
            pass = "secret123";
        String apiBase = API_SERVERS.get(env);
        URL url;
        try {
            url = new URL(apiBase + urlSuffix);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/xml");
            int status_code = con.getResponseCode();
            DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
            Document doc = f.newDocumentBuilder().parse(con.getInputStream());
            return new ApiResponse(status_code, doc);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ApiResponse(500, null);
    }

    public static void main(String args[]) {
        SendCustomerRequest test_req = new SendCustomerRequest("TEST_PROD_1", 55, "TEST_CUST_1", "true");
        assert test_req.cs_ky == "TEST_CUST_1";
        assert test_req.pd_ky == "TEST_PROD_1";
        assert test_req.rq_ky == "7e48de13";
        assert SendCustomerRequest.issued == 1;
        assert totalValue(1000, true) == 5500;
        System.out.println("TESTING PASSED - PUSH ME TO PROD !!!!!!");

    }
}

class ApiResponse {
    int statusCode;
    Document body;

    ApiResponse(int statusCode, Document body) {
        statusCode = statusCode;
        body = body;
    }
}