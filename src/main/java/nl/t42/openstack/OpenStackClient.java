package nl.t42.openstack;

import nl.t42.openstack.command.AuthenticationCommand;
import nl.t42.openstack.command.ListContainerCommand;
import nl.t42.openstack.model.access.Access;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class OpenStackClient {

    private Access access;

    private HttpClient httpClient = new DefaultHttpClient();

    private boolean authenticated = false;

    public void authenticate(String username, String password, String authUrl) throws IOException {

        this.access = null;
        this.authenticated = false;

        this.access = new AuthenticationCommand(httpClient, authUrl, username, password).execute();
        this.authenticated = true;
    }

    public String[] listContainers() throws IOException {
        return new ListContainerCommand(httpClient, access).execute();
    }

    public boolean isAuthenticated() { return this.authenticated; }
    public void setHttpClient(HttpClient httpClient) { this.httpClient = httpClient; }
}