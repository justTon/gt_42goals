package org.gt.gt_42goals;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

import java.util.Scanner;

/**
 * User: AKazakov
 * Date: 27.06.12
 * Time: 14:57
 */
public class App {

    public static void main(String[] args) {
        OAuthService service = new ServiceBuilder()
                .provider(GT42goalsAPI.class)
                .apiKey(GT42goalsConst.APP_TOKEN)
                .apiSecret(GT42goalsConst.TOKEN_SECRET)
                .build();
        Token requestToken = service.getRequestToken();

        System.out.println("Now going and authorizing GT Planner here:");
        System.out.println(service.getAuthorizationUrl(requestToken));

        String token = getToken(service.getAuthorizationUrl(requestToken));

        Verifier verifier = new Verifier(token);
        Token accessToken = service.getAccessToken(requestToken, verifier);

        OAuthRequest request = new OAuthRequest(Verb.GET, GT42goalsConst.GET_ALL_GOALS_URL);
        service.signRequest(accessToken, request);
        request.addHeader("Accept", "text/json");

        Response response = request.send();

        System.out.println("Response data:");
        System.out.println();
        System.out.println("Code: " + response.getCode());
        System.out.println("Content:\n" + response.getBody());
    }

    private static String getToken(String url) {
        AuthorizationDialog ad = new AuthorizationDialog(url);
        ad.setVisible(true);

        String token = ad.getApprovedToken();
        ad.dispose();
        return token;
    }


    private static void consoleGetAllGoals() {
        OAuthService service = new ServiceBuilder()
                .provider(GT42goalsAPI.class)
                .apiKey(GT42goalsConst.APP_TOKEN)
                .apiSecret(GT42goalsConst.TOKEN_SECRET)
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
