package org.gt.gt_42goals;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

/**
 *
 * @author: ANKA0709
 * Date: 27.06.12
 * Time: 14:57
 */
public class App {

    private static String REQUEST_TOKEN = "6bb7d89fefeff80f254042327dc60583";
    private static String TOKEN_SECRET = "4dac6b985ae1c660eeaa279e8b19a064";

    public static void main(String[] args) {

        OAuthService service = new ServiceBuilder()
                .provider(GTgoalsAPI.class)
                .apiKey(REQUEST_TOKEN)
                .apiSecret(TOKEN_SECRET)
                .build();

        Scanner in = new Scanner(System.in);

        Token requestToken = service.getRequestToken();

        System.out.println("Now go and authorize GT Planner here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());
        System.out.println();

        Token accessToken = service.getAccessToken(requestToken, verifier);

        OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.42goals.com/v1/goals/");
        service.signRequest(accessToken, request);
        request.addHeader("Accept", "text/json");

        Response response = request.send();

        System.out.println("Response data:");
        System.out.println();
        System.out.println("Code: " + response.getCode());
        System.out.println("Content:\n" + response.getBody());
    }
}
