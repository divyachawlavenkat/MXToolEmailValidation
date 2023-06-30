import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EmailVerifier {
    public static void main(String[] args) {
        String email = "example@example.com"; // Replace with the email to be verified
        
        try {
            String url = "https://mxtoolbox.com/SuperTool.aspx?action=mx%3a" + email;
            String response = sendGetRequest(url);
            
            boolean isValid = checkResponseForValidity(response);
            
            if (isValid) {
                System.out.println("Email is valid.");
            } else {
                System.out.println("Email is not valid.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String sendGetRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        
        in.close();
        return response.toString();
    }
    
    private static boolean checkResponseForValidity(String response) {
        // Modify this method according to the response parsing logic specific to the MXToolBox website
        
        // Here, you can check for specific HTML elements or text patterns in the response
        // that indicate if the email is valid or not.
        
        // Example logic:
        if (response.contains("The email address")) {
            // Email is not valid
            return false;
        } else {
            // Email is valid
            return true;
        }
    }
}
