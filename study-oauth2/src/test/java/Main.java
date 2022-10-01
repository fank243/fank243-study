import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

public class Main {
    private static final ClientAuthenticationMethod JWT_CLIENT_ASSERTION_AUTHENTICATION_METHOD =
            new ClientAuthenticationMethod("urn:ietf:params:oauth:client-assertion-type:jwt-bearer");
    private static final ClientAuthenticationMethod clientAuthenticationMethod =
            new ClientAuthenticationMethod("client_secret_jwt");

    public static void main(String[] args){
        System.out.println(JWT_CLIENT_ASSERTION_AUTHENTICATION_METHOD.getValue());
        System.out.println(clientAuthenticationMethod.getValue());
    }
}
