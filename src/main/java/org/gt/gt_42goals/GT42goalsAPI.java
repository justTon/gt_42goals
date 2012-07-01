package org.gt.gt_42goals;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * User: AKazakov
 * Date: 28.06.12
 * Time: 17:06
 */
public class GT42goalsAPI extends DefaultApi10a
{

    @Override
    public String getAccessTokenEndpoint()
    {
        return GT42goalsConst.GET_ACCESS_TOKEN_URL;
    }

    @Override
    public String getRequestTokenEndpoint()
    {
        return GT42goalsConst.GET_REQUEST_TOKEN_URL;
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
        return String.format(GT42goalsConst.AUTHORIZATION_URL, requestToken.getToken());
    }
}
