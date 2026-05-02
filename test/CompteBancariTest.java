import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Proves unitàries per a la classe CompteBancari.
 * Segueix la convenció: [metodeProvat]_[escenari]_[resultatEsperat]
 */
public class CompteBancariTest {

    private CompteBancari compte;

    // S'executa abans de cada test per garantir un estat net
    @BeforeEach
    void setUp() {
        compte = new CompteBancari("Anna Garcia", "ES91 2100 0418 4502 0005 1332", 500.0);
    }

    // ─────────────────────────────────────────────
    // 1. CREACIÓ CORRECTA
    // ─────────────────────────────────────────────

    @Test
    void constructor_dadesValides_creaCompteCorrectament() {
        assertEquals("Anna Garcia", compte.getTitular());
        assertEquals("ES91 2100 0418 4502 0005 1332", compte.getIban());
        assertEquals(500.0, compte.getSaldo(), 0.001);
    }

    @Test
    void constructor_saldoInicialZero_creaCompteAmBSaldoZero() {
        CompteBancari compteNou = new CompteBancari("Pere Puig", "ES12 3456 7890 1234 5678 9012", 0.0);
        assertEquals(0.0, compteNou.getSaldo(), 0.001);
    }

    // ─────────────────────────────────────────────
    // 2. ERRORS DE VALIDACIÓ AL CONSTRUCTOR
    // ─────────────────────────────────────────────

    @Test
    void constructor_titularNull_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            new CompteBancari(null, "ES91 2100 0418 4502 0005 1332", 100.0)
        );
    }

    @Test
    void constructor_titularBuit_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            new CompteBancari("   ", "ES91 2100 0418 4502 0005 1332", 100.0)
        );
    }

    @Test
    void constructor_ibanNull_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            new CompteBancari("Anna Garcia", null, 100.0)
        );
    }

    @Test
    void constructor_ibanBuit_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            new CompteBancari("Anna Garcia", "", 100.0)
        );
    }

    @Test
    void constructor_saldoInicialNegatiu_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            new CompteBancari("Anna Garcia", "ES91 2100 0418 4502 0005 1332", -1.0)
        );
    }

    // ─────────────────────────────────────────────
    // 3. INGRÉS CORRECTE
    // ─────────────────────────────────────────────

    @Test
    void ingressar_quantitatPositiva_incrementaSaldo() {
        compte.ingressar(200.0);
        assertEquals(700.0, compte.getSaldo(), 0.001);
    }

    @Test
    void ingressar_quantitatDecimal_incrementaSaldoCorrectament() {
        compte.ingressar(50.75);
        assertEquals(550.75, compte.getSaldo(), 0.001);
    }

    @Test
    void ingressar_quantitatZero_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            compte.ingressar(0.0)
        );
    }

    @Test
    void ingressar_quantitatNegativa_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            compte.ingressar(-50.0)
        );
    }

    // ─────────────────────────────────────────────
    // 4. RETIRADA CORRECTA
    // ─────────────────────────────────────────────

    @Test
    void retirar_quantitatInferiorAlSaldo_decrementaSaldo() {
        compte.retirar(200.0);
        assertEquals(300.0, compte.getSaldo(), 0.001);
    }

    @Test
    void retirar_quantitatIgualAlSaldo_deixaSaldoZero() {
        compte.retirar(500.0);
        assertEquals(0.0, compte.getSaldo(), 0.001);
    }

    @Test
    void retirar_quantitatZero_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            compte.retirar(0.0)
        );
    }

    @Test
    void retirar_quantitatNegativa_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            compte.retirar(-100.0)
        );
    }

    // ─────────────────────────────────────────────
    // 5. ERROR SI ES VOL RETIRAR MÉS SALDO DEL DISPONIBLE
    // ─────────────────────────────────────────────

    @Test
    void retirar_quantitatSuperiorAlSaldo_llancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            compte.retirar(600.0)
        );
    }

    @Test
    void retirar_quantitatSuperiorAlSaldo_saldoNoEsModifica() {
        try {
            compte.retirar(600.0);
        } catch (IllegalArgumentException e) {
            // esperem l'excepció
        }
        assertEquals(500.0, compte.getSaldo(), 0.001);
    }
}
