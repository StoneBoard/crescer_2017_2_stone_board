package br.com.crescer.stone_board.service;

import br.com.crescer.stone_board.service.Exemplo2Service;
import br.com.crescer.stone_board.service.ExemploService;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author carloshenrique
 */
@RunWith(MockitoJUnitRunner.class)
public class ExemploServiceTest {

    @Mock
    private Exemplo2Service exemplo2Service;
    
    @InjectMocks
    private ExemploService exemploService;

    /**
     * Test of get method, of class ExemploService.
     */
    @Test
    public void testGet() {
        
        when(exemplo2Service.getTen()).thenReturn(BigDecimal.ONE);
        
        final BigDecimal bigDecimal = exemploService.get();
        
        assertEquals(BigDecimal.ONE,  bigDecimal);
        
        Mockito.verify(exemplo2Service).save(BigDecimal.ONE);

    }

}
