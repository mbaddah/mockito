package mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    Database databaseMock;

    @Test
    void testQuery() {
        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);
        Service service = new Service(databaseMock);
        boolean check = service.query();
        assertTrue(check);
    }

    @Test
    void testQueryId() {
        assertNotNull(databaseMock);
        when(databaseMock.getUniqueId()).thenReturn(50);
        Service service = new Service(databaseMock);
        assertEquals(50,service.queryId());
    }
}
