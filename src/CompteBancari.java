public class CompteBancari {

    private String titular;
    private String iban;
    private double saldo;

    private static final double SALDO_BAIX_MAX = 1000.0;
    private static final double SALDO_NORMAL_MAX = 5000.0;

    public CompteBancari(String titular, String iban, double saldoInicial) {
        if (titular == null || titular.equals("")) {
            throw new IllegalArgumentException("El titular no pot ser buit");
        }
        if (iban == null || iban.equals("")) {
            throw new IllegalArgumentException("L'IBAN no pot ser buit");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no pot ser negatiu");
        }

        this.titular = titular;
        this.iban = iban;
        this.saldo = saldoInicial;
    }

    public void ingressar(double quantitat) {
        if (quantitat <= 0) {
            throw new IllegalArgumentException("La quantitat a ingressar ha de ser positiva");
        }
        saldo = saldo + quantitat;
        System.out.println("S'ha ingressat " + quantitat);
        System.out.println("Saldo actual " + saldo);
        System.out.println(getEstatSaldo());
    }

    public void retirar(double quantitat) {
        if (quantitat <= 0) {
            throw new IllegalArgumentException("La quantitat a retirar ha de ser positiva");
        }
        if (quantitat > saldo) {
            throw new IllegalArgumentException("Fons insuficients");
        }
        saldo = saldo - quantitat;
        System.out.println("S'ha retirat " + quantitat);
        System.out.println("Saldo actual " + saldo);
        System.out.println(getEstatSaldo());
    }

    public void mostrarDades() {
        System.out.println("Titular: " + titular);
        System.out.println("IBAN: " + iban);
        System.out.println("Saldo: " + saldo);
        System.out.println(getEstatSaldo());
    }

    private String getEstatSaldo() {
        if (saldo < SALDO_BAIX_MAX) return "Saldo baix";
        if (saldo < SALDO_NORMAL_MAX) return "Saldo normal";
        return "Saldo alt";
    }

    public String getTitular() {
        return titular;
    }

    public String getIban() {
        return iban;
    }

    public double getSaldo() {
        return saldo;
    }
}