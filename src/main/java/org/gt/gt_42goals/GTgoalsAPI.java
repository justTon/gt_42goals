package org.gt.gt_42goals;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * @author: AKazakov
 * Date: 28.06.12
 * Time: 17:06
 */
public class GTgoalsAPI extends DefaultApi10a
{
    private static final String AUTHORIZATION_URL = "http://42goals.com/settings/authorize/%s/";

    @Override
    public String getAccessTokenEndpoint()
    {
        return "http://api.42goals.com/v1/oauth/access_token/";
    }

    @Override
    public String getRequestTokenEndpoint()
    {
        return "http://api.42goals.com/v1/oauth/request_token/";
    }

    @Override
    public Verb getAccessTokenVerb()
    {
        return Verb.GET;
    }

    @Override
    public Verb getRequestTokenVerb()
    {
        return Verb.GET;
    }

    @Override
    public String getAuthorizationUrl(Token requestToken)
    {
        return String.format(AUTHORIZATION_URL, requestToken.getToken());
    }
}
