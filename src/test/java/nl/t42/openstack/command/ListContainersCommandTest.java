package nl.t42.openstack.command;

import nl.t42.openstack.BaseCommandTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class ListContainersCommandTest extends BaseCommandTest {

    @Before
    public void setup() throws IOException {
        super.setup();
    }

    @Test
    public void listContainers() throws IOException {
        new ListContainersCommand(httpClient, defaultAccess).execute();
    }

    @Test
    public void listContainersWithNoneThere() throws IOException {
        when(statusLine.getStatusCode()).thenReturn(204);
        new ListContainersCommand(httpClient, defaultAccess).execute();
    }

    @Test
    public void unknownError() throws IOException {
        checkForError(500, new ListContainersCommand(httpClient, defaultAccess), CommandExceptionError.UNKNOWN);
    }
}