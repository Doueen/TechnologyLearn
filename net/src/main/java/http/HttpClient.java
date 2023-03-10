package http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpClient {

    private final String url;
    private final String method;
    private Map<String, String> headers;
    private final String params;

    private String charset ="UTF-8";

    public HttpClient(String url, String method, Map<String, String> headers, String params) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
    }

    public HttpClient(String url, String method, Map<String, String> headers, String params, String charset) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
        this.charset = charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void execute() throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod(method);
        conn.setDoOutput(true);
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
        }
        if (params != null) {
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode >= 200 && responseCode < 300) {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            StringBuilder responseBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                responseBuilder.append(inputLine);
                responseBuilder.append("\n");
            }
            inputReader.close();
            String responseBody = responseBuilder.toString();
            System.out.println("Response Body: \n" + responseBody);
            System.out.println("Response Headers: \n");
            for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
                if (header.getKey() != null) {
                    System.out.println(header.getKey() + ": " + header.getValue().get(0));
                }
            }
        } else {
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
            StringBuilder errorBuilder = new StringBuilder();
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorBuilder.append(errorLine);
                errorBuilder.append("\n");
            }
            errorReader.close();
            String errorMessage = errorBuilder.toString();
            System.out.println("Error Message: \n" + errorMessage);
        }
        conn.disconnect();
    }


}
