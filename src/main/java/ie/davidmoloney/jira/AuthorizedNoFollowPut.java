package ie.davidmoloney.jira;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicHeader;

public class AuthorizedNoFollowPut extends HttpPut {
    public AuthorizedNoFollowPut(ConnectionDetails connectionDetails, String test_issue) {
        super(connectionDetails.getBaseUri() + test_issue);
        setHeader(createAuthorizationHeader(connectionDetails.getUser(), connectionDetails.getPassword()));
    }

    public Header createAuthorizationHeader( String username, String password ) {
        StringBuilder authorizationValue = new StringBuilder();
        authorizationValue.append("Basic ");
        Base64Encoder encoder = new Base64Encoder();
        String encodedUserPassCombo = encoder.encode(username, password);
        authorizationValue.append(encodedUserPassCombo);
        return new BasicHeader(HttpHeaders.AUTHORIZATION, authorizationValue.toString());
    }
}
