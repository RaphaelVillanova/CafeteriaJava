package me.rapha.Cafeteria;

import me.rapha.Cafeteria.API.ArquivoReader;
import me.rapha.Cafeteria.API.Entrada;

public class Main {

    public static char op = ' ';
    public static Cafeteria cafeteria = new Cafeteria();
    public static ArquivoReader arquivo = new ArquivoReader("dados.dat");
	
	public static void main(String[] args) {
		carregarDados();
		exibirMenuPrincipal();
	}
	
	public static void carregarDados() {
		if (arquivo.abrirLeitura()) {
			cafeteria.setContadorCafeCurto(Integer.parseInt(arquivo.lerLinha()));
			cafeteria.setContadorCafeLongo(Integer.parseInt(arquivo.lerLinha()));
			cafeteria.setContadorCappuccino(Integer.parseInt(arquivo.lerLinha()));
			cafeteria.setContadorLateMacchiato(Integer.parseInt(arquivo.lerLinha()));
			cafeteria.setNivelReservatorioAgua(Integer.parseInt(arquivo.lerLinha()));
			cafeteria.setNivelReservatorioCafe(Integer.parseInt(arquivo.lerLinha()));
			cafeteria.setNivelReservatorioLeite(Integer.parseInt(arquivo.lerLinha()));
			arquivo.fecharArquivo();
		}
	}
	
    public static void exibirMenuPrincipal() {
        String menu = "M E N U    P R I N C I P A L\n\n" +
                "[a] Menu do Usuario\n" +
                "[s] Menu do administrador\n" +
                "[x] Sair do sistema\n\n" +
                "Digite sua opção:";
        while (op != 'x') {
            op = Entrada.leiaChar(menu);
            op = Character.toLowerCase(op);

            switch (op) {
                case 'a':
                    exibirMenuUsuario();
                    break;
                case 's':
                    exibirMenuAdministrador();
                    break;
            }
        }
    }
	
    public static void exibirMenuUsuario() {
        String menuUsuario = "M E N U \n\n" +
                "[q] Pegar Café Curto\n" +
                "[w] Pegar Café Longo\n" +
                "[r] Pegar Cappuccino\n" +
                "[t] Pegar Late Macchiato\n" +
                "Digite sua opção: ";
        op = Entrada.leiaChar(menuUsuario);

        switch (op) {
        case 'q':
            servirBebida(cafeteria.ServirCafeCurto(), "Café Curto");
            break;
        case 'w':
            servirBebida(cafeteria.ServirCafeLongo(), "Café Longo");
            break;
        case 'r':
            servirBebida(cafeteria.ServirCappuccino(), "Cappuccino");
            break;
        case 't':
            servirBebida(cafeteria.ServirLateMacchiato(), "Latte Macchiato");
            break;
        default:
            System.out.println("Opção inválida!");
        }
        
        if (arquivo.abrirEscrita()){
        	arquivo.escreverLinha(cafeteria.nivelReservatorioAgua+"");
        	arquivo.escreverLinha(cafeteria.nivelReservatorioCafe+"");
        	arquivo.escreverLinha(cafeteria.nivelReservatorioLeite+"");
        	arquivo.escreverLinha(String.valueOf(cafeteria.contadorCafeCurto));
        	arquivo.escreverLinha(String.valueOf(cafeteria.contadorCafeLongo));
        	arquivo.escreverLinha(String.valueOf(cafeteria.contadorCappuccino));
        	arquivo.escreverLinha(String.valueOf(cafeteria.contadorLateMacchiato));
        	arquivo.fecharArquivo();
        }
    }
    
    private static void servirBebida(boolean sucesso, String bebida) {
        if (sucesso) {
            System.out.println(bebida + " Servido com Sucesso.");
        } else {
            System.out.println("Erro! Fale com o administrador para abastecer a máquina.");
        }
    }
	
	public static void exibirMenuAdministrador() {
        String menuADM = " M E N U  D O  A D M I N I S T R A D O R\n\n" + "[d] Abastecer os reservatórios\n" + "[e] Consultar o reservatório\n" + "Digite sua opção: ";
        
        op = Entrada.leiaChar(menuADM);
        switch (op) {
		case 'd':
			abastecerReservatorios();
			break;
		case 'e':
			consultarReservatorios();
			break;
		}
	}
	
	public static void abastecerReservatorios() {
		int nivelA = Entrada.leiaInt("Abastecer água [0-100] litros");
		int nivelL = Entrada.leiaInt("Abastecer leite [0-150] litros");
		int nivelC = Entrada.leiaInt("Abastecer café [0-100] gramas");
		
		if (cafeteria.abastecerAgua(nivelA) && cafeteria.abastecerLeite(nivelL) && cafeteria.abastecerCafe(nivelC)) {
			System.out.println("Operações realizadas com sucesso!");
		}else{
			System.out.println("Falha ao realizar a operação.");
		}
	}
	
	public static void consultarReservatorios() {
		System.out.println("Nível dos reservatórios:");
		System.out.println("Água: " + cafeteria.getNivelReservatorioAgua());
		System.out.println("Café: " + cafeteria.getNivelReservatorioCafe());
		System.out.println("Leite: " + cafeteria.getNivelReservatorioLeite());
		System.out.println();
	}
}
