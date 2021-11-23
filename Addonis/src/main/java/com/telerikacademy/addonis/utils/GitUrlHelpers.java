package com.telerikacademy.addonis.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class GitUrlHelpers {

    public static final String URL_GIT_API_REPOS = "https://api.github.com/repos/";

    /*
       Example
       input:  https://github.com/spring-projects/spring-boot
       output: https://api.github.com/repos/spring-projects/spring-boot
        */
    public static String getApiRepoURL(String originURL) {

        return URL_GIT_API_REPOS + extractPath(originURL);
    }

    /*
        Example
        input:  https://github.com/spring-projects/spring-boot
        output: https://api.github.com/repos/spring-projects/spring-boot/commits?per_page=1
         */
    public static String getLastCommitURL(String originURL) {

        return URL_GIT_API_REPOS + extractPath(originURL) + "/commits?per_page=1";
    }

    /*
       Example
       input:  https://github.com/spring-projects/spring-boot
       output: https://api.github.com/repos/spring-projects/spring-boot/issues?per_page=100
        */
    public static String getAllIssuesURL(String originURL) {

        return URL_GIT_API_REPOS + extractPath(originURL) + "/issues?per_page=100";
    }

    /*
     Example
     input:  https://github.com/spring-projects/spring-boot
     output: spring-projects/spring-boot
  */
    private static String extractPath(String originURL) {
        try {
            return (new URI(originURL)).getPath().substring(1);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //    public static final String URL_GIT_API_SEARCH = "https://api.github.com/search/";

//    /*
//       Example
//       input:  https://github.com/spring-projects/spring-boot
//       output: https://api.github.com/search/issues?q=repo:spring-projects/spring-boot%20is:issue%20is:open%20is:pr
//    */
//    public static String getOpenPullRequestsURL(String originURL) {
//
//        //return URL_GIT_API_SEARCH +"issues?q=" + extractPath(originURL) + "%20is:issue%20is:open%20is:pr";
//        return getOpenIssuesURL(originURL)+" is:pr";
//    }
//
//
//    /*
//        Example
//        input:  https://github.com/spring-projects/spring-boot
//        output: https://api.github.com/search/issues?q=repo:spring-projects/spring-boot%20is:issue%20is:open
//    */
//    public static String getOpenIssuesURL(String originURL) {
//        //UriUtils.encodePath(" is:issue is open", "UTF-8");
//        return URL_GIT_API_SEARCH +"issues?q=repo:" + extractPath(originURL) + " is:issue is:open";
//
//    }



}
