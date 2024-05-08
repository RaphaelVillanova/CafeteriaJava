package me.rapha.Cafeteria.API;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoReader {

	private BufferedReader bfReader;
	private BufferedWriter bfWriter;
	private String nomeArquivo;
	private char status;
	
	public ArquivoReader(String nomeArquivo){
		this.nomeArquivo = nomeArquivo;
		status = 'C';
	}
	
	public boolean abrirLeitura(){
		boolean sucesso = true;
		FileReader flReader = null;
		if (status != 'C'){
			System.err.println("ERRO! Arquivo " + nomeArquivo + " já está aberto!");
			sucesso = false;
		}else{
			try{
				flReader = new FileReader(nomeArquivo);
			}catch (FileNotFoundException e){
				System.err.println("ERRO! Arquivo " + nomeArquivo + " não foi encontrado!");
				sucesso = false;
				e.printStackTrace();
			}
			if (sucesso){
				status = 'R';
				bfReader = new BufferedReader(flReader);
			}
		}
		return (sucesso);
	}
	
	public boolean abrirEscrita(){
		boolean sucesso = true;
		FileWriter flWriter = null;
		if (status != 'C'){
			System.err.println("ERRO! Arquivo " + nomeArquivo + " já está aberto!");
			sucesso = false;
		}else{
			try{
				flWriter = new FileWriter(nomeArquivo);
			}catch (IOException e){
				System.err.println("ERRO! Não foi possivel escrever no arquivo " + nomeArquivo + "!");
				sucesso = false;
				e.printStackTrace();
			}
			if (sucesso){
				status = 'W';
				bfWriter = new BufferedWriter(flWriter);
			}
		}
		return (sucesso);
	}
	
	public String lerLinha(){
		String linha = null;
		if (status == 'R'){
			try{
				linha = bfReader.readLine();
			}catch (IOException e){
				System.err.println("ERRO! Não foi possivel fazer a leitura do arquivo " + nomeArquivo + "!");
				e.printStackTrace();
			}
		}else{
			System.err.println("ERRO! O arquivo " + nomeArquivo + " não está pronto para leitura!");
		}
		return (linha);
	}
	
	public void escreverLinha(String linha){
		if (status == 'W'){
			try{
				bfWriter.write(linha + "\n");
			}catch (IOException e){
				System.err.println("ERRO! Não foi possivel escrever no arquivo " + nomeArquivo + "!");
				e.printStackTrace();
			}
		}else{
			System.err.println("ERRO! O arquivo " + nomeArquivo + " não está pronto para escrita!");
		}
	}
	
	public void fecharArquivo(){
		if (status == 'R'){
			try{
				bfReader.close();
				status = 'C';
			}catch (Exception e){
				System.err.println("ERRO! Não foi possivel fechar o arquivo " + nomeArquivo + "!");
				e.printStackTrace();
			}
		}
		if (status == 'W'){
			try{
				bfWriter.close();
				status = 'C';
			}catch (IOException e){
				System.err.println("ERRO! Não foi possivel fechar o arquivo " + nomeArquivo + "!");
				e.printStackTrace();
			}
		}
	}
}
