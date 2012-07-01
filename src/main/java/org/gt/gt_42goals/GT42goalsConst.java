package org.gt.gt_42goals;

/**
 * User: AKazakov
 * Date: 01.07.12 14:16
 */
public interface GT42goalsConst {

    public static String APP_TOKEN = "6bb7d89fefeff80f254042327dc60583";
    public static String TOKEN_SECRET = "4dac6b985ae1c660eeaa279e8b19a064";

    public static final String AUTHORIZATION_URL = "http://42goals.com/settings/authorize/%s/";
    public static final String GET_ACCESS_TOKEN_URL = "http://api.42goals.com/v1/oauth/access_token/";
    public static final String GET_REQUEST_TOKEN_URL = "http://api.42goals.com/v1/oauth/request_token/";

    public static final String GET_ALL_GOALS_URL = "http://api.42goals.com/v1/goals/";
}
