package br.com.territorio.territorypredictiveinsights.servico;

import br.com.territorio.territorypredictiveinsights.dominio.Medicao;
import br.com.territorio.territorypredictiveinsights.dominio.Sensor;
import br.com.territorio.territorypredictiveinsights.excecao.DadosInvalidosException;
import br.com.territorio.territorypredictiveinsights.persistencia.AlertaRepository;
import br.com.territorio.territorypredictiveinsights.persistencia.MedicaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicoMonitoramentoTest {

    @Mock
    private MedicaoRepository medicaoRepository;

    @Mock
    private AlertaRepository alertaRepository;

    @InjectMocks
    private ServicoMonitoramento servicoMonitoramento;

    @Test
    public void deveProcessarLeituraSensorComSucesso() {
        // Arrange
        Sensor sensorMock = mock(Sensor.class);
        when(sensorMock.verificarFuncionamento()).thenReturn(true);
        when(sensorMock.lerValor()).thenReturn(45.0);
        when(sensorMock.retornarTipo()).thenReturn("Pluviômetro (Volume de Chuva - mm)");

        // Act
        Medicao resultado = servicoMonitoramento.processarLeituraSensor(sensorMock);

        // Assert
        assertNotNull(resultado);
        assertEquals(45.0, resultado.getValor());
        verify(medicaoRepository, times(1)).save(any(Medicao.class));
    }

    @Test
    public void deveLancarExcecaoQuandoSensorInoperante() {
        // Arrange
        Sensor sensorMock = mock(Sensor.class);
        when(sensorMock.verificarFuncionamento()).thenReturn(false);
        when(sensorMock.retornarTipo()).thenReturn("Sensor Nível de Água");

        // Act & Assert
        assertThrows(DadosInvalidosException.class, () -> {
            servicoMonitoramento.processarLeituraSensor(sensorMock);
        });

        verify(medicaoRepository, never()).save(any(Medicao.class));
    }

    @Test
    public void deveLancarExcecaoQuandoValorMedicaoNegativo() {
        // Arrange
        Sensor sensorMock = mock(Sensor.class);
        when(sensorMock.verificarFuncionamento()).thenReturn(true);
        when(sensorMock.lerValor()).thenReturn(-5.0);
        // Removido o stubbing desnecessário de retornarTipo()

        // Act & Assert
        assertThrows(DadosInvalidosException.class, () -> {
            servicoMonitoramento.processarLeituraSensor(sensorMock);
        });
    }

    @Test
    public void singletonConfiguracaoDeveRetornarMesmaInstancia() {
        // Arrange & Act
        ConfiguracaoSistema config1 = ConfiguracaoSistema.getInstancia();
        ConfiguracaoSistema config2 = ConfiguracaoSistema.getInstancia();

        // Assert
        assertSame(config1, config2);
    }

    @Test
    public void deveAlterarLimiarChuvaNoSingleton() {
        // Arrange
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();

        // Act
        config.setLimiarChuvaCritica(120.0);

        // Assert
        assertEquals(120.0, config.getLimiarChuvaCritica());
    }
}