package me.rapha.Cafeteria;

public class Cafeteria {

	//Níveis
	public int nivelReservatorioAgua;
	public int nivelReservatorioCafe;
	public int nivelReservatorioLeite;
	
	//Contadores
	public int contadorCafeCurto;
	public int contadorCafeLongo;
	public int contadorCappuccino;
	public int contadorLateMacchiato;
	
	//Estoque Maximo
	private static final int MAX_AGUA = 100;
	private static final int MAX_CAFE = 100;
	private static final int MAX_LEITE = 150;
	
	//Café Curto
	private static final int AGUA_CAFE_CURTO = 50;
	private static final int CAFE_CAFE_CURTO = 6;
	
	//Café Longo
	private static final int AGUA_CAFE_LONGO = 120;
	private static final int CAFE_CAFE_LONGO = 12;
	
	//Cappuccino
	private static final int LEITE_CAPPUCCINO = 30;
	private static final int AGUA_CAPPUCCINO = 150;
	private static final int CAFE_CAPPUCCINO = 12;
	
	//Late Macchiato
    private static final int AGUA_LATE_MACCHIATO = 160;
    private static final int CAFE_LATE_MACCHIATO = 14;
    private static final int LEITE_LATE_MACCHIATO = 45;
	
	//Preços
	public static final double PRECO_CAFE_CURTO = 1.50;
	public static final double PRECO_CAFE_LONGO = 2.50;
	public static final double PRECO_CAPPUCCINO = 3.00;
	public static final double PRECO_LATE_MACCHIATO = 4.00;
	public double valorCaixa;
	
	
	public int getNivelReservatorioAgua( ){
		return nivelReservatorioAgua;
	}
	
	public void setNivelReservatorioAgua(int nivelReservatorioAgua) {
		this.nivelReservatorioAgua = nivelReservatorioAgua;
	}
	
	public int getNivelReservatorioCafe() {
		return nivelReservatorioCafe;
	}
	
	public void setNivelReservatorioCafe(int nivelReservatorioCafe) {
		this.nivelReservatorioCafe = nivelReservatorioCafe;
	}
	
	public int getNivelReservatorioLeite() {
		return nivelReservatorioLeite;
	}
	
	public void setNivelReservatorioLeite(int nivelReservatorioLeite) {
		this.nivelReservatorioLeite = nivelReservatorioLeite;
	}
	
	public int getContadorCafeCurto() {
		return contadorCafeCurto;
	}
	
	public void setContadorCafeCurto(int contadorCafeCurto) {
		this.contadorCafeCurto = contadorCafeCurto;
	}
	
	public int getContadorCafeLongo() {
		return contadorCafeLongo;
	}
	
	public void setContadorCafeLongo(int contadorCafeLongo) {
		this.contadorCafeLongo = contadorCafeLongo;
	}
	
	public int getContadorCappuccino() {
		return contadorCappuccino;
	}
	
	public void setContadorCappuccino(int contadorCappuccino) {
		this.contadorCappuccino = contadorCappuccino;
	}
	
	public int getContadorLateMacchiato() {
		return contadorLateMacchiato;
	}
	
	public void setContadorLateMacchiato(int contadorLateMacchiato) {
		this.contadorLateMacchiato = contadorLateMacchiato;
	}
	
	public double getValorCaixa() {
		return valorCaixa;
	}
	
	
	public boolean abastecerAgua(int quantidade) {
		if (quantidade > 0 && nivelReservatorioAgua + quantidade <= MAX_AGUA) {
			nivelReservatorioAgua += quantidade;
			return true;
		}
		return false;
	}
	
	public boolean abastecerLeite(int quantidade) {
		if (quantidade > 0 && nivelReservatorioLeite + quantidade <= MAX_LEITE) {
			nivelReservatorioLeite += quantidade;
			return true;
		}
		return false;
	}
	
	public boolean abastecerCafe(int quantidade) {
		if (quantidade > 0 && nivelReservatorioCafe + quantidade <= MAX_CAFE) {
			nivelReservatorioCafe += quantidade;
			return true;
		}
		return false;
	}
	
	public boolean ServirCafeCurto() {
		if (verificarIngredientes(AGUA_CAFE_CURTO, CAFE_CAFE_CURTO, 0)) {
			contadorCafeCurto++;
			valorCaixa += PRECO_CAFE_CURTO;
            nivelReservatorioAgua -= AGUA_CAFE_CURTO;
            nivelReservatorioCafe -= CAFE_CAFE_CURTO;
            return true;
		}
		return false;
	}
	
	public boolean ServirCafeLongo() {
		if (verificarIngredientes(AGUA_CAFE_LONGO, CAFE_CAFE_LONGO, 0)) {
			contadorCafeLongo++;
			valorCaixa += PRECO_CAFE_LONGO;
			nivelReservatorioAgua -= AGUA_CAFE_LONGO;
			nivelReservatorioCafe -= CAFE_CAFE_LONGO;
			return true;
		}
		return false;
	}
	
	public boolean ServirCappuccino() {
		if(verificarIngredientes(AGUA_CAPPUCCINO, CAFE_CAPPUCCINO, LEITE_CAPPUCCINO)) {
			contadorCappuccino++;
			valorCaixa += PRECO_CAPPUCCINO;
			nivelReservatorioAgua -= AGUA_CAPPUCCINO;
			nivelReservatorioCafe -= CAFE_CAPPUCCINO;
			nivelReservatorioLeite -= LEITE_CAPPUCCINO;
			return true;
		}
		return false;
	}
	
	public boolean ServirLateMacchiato() {
		if (verificarIngredientes(AGUA_LATE_MACCHIATO, CAFE_LATE_MACCHIATO, LEITE_LATE_MACCHIATO)) {
			contadorLateMacchiato++;
			valorCaixa += PRECO_LATE_MACCHIATO;
			nivelReservatorioAgua -= AGUA_LATE_MACCHIATO;
			nivelReservatorioCafe -= CAFE_LATE_MACCHIATO;
			nivelReservatorioLeite -= LEITE_LATE_MACCHIATO;
			return true;
		}
		return false;
	}
	
	private boolean verificarIngredientes(int aguaNecessaria, int cafeNecessario, int leiteNecessario) {
		return nivelReservatorioAgua >= aguaNecessaria && nivelReservatorioCafe >= cafeNecessario && nivelReservatorioLeite >= leiteNecessario;
	}
	
	public void reinicializar() {
		contadorCafeCurto = 0;
		contadorCafeLongo = 0;
		contadorCappuccino = 0;
		contadorLateMacchiato = 0;
		valorCaixa = 0;
		nivelReservatorioAgua = 0;
		nivelReservatorioCafe = 0;
		nivelReservatorioLeite = 0;
	}
}
