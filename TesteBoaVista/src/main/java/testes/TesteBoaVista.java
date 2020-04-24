package testes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Common.Util;

public class TesteBoaVista {
	
	Util util;
	
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");
		driver.manage().window().maximize();
		util = new Util(driver);
		util.escrever("email", "devasconcelosclara@gmail.com");
		util.escrever("senha","123456");
		util.clicar("/html/body/div[2]/form/button");
	}
	
	@After
	public void finaliza() {
		util.fecharBrowser();
	}

	@Test
	public void testeExclusaoConta() throws InterruptedException {
		
			util.clicar("//*[@id=\"navbar\"]/ul/li[2]/a");
			util.clicar("//*[@id=\"navbar\"]/ul/li[2]/ul/li[2]/a");
			util.clicar("//*[@id=\"tabelaContas\"]/tbody/tr/td[2]/a[2]/span");
			
			Assert.assertEquals("Conta removida com sucesso!", util.textoPresente("/html/body/div[1]"));
		
}
	@Test
	public void testeLancarMovimentacaoDataInvalida() {

		// Movimentação
		util.clicar("//*[@id=\"navbar\"]/ul/li[3]/a");
		util.selecionarComboPeloValor("tipo","DESP");
		util.escrever("data_transacao","Invalida");
		util.escrever("data_pagamento","Invalida");

		util.clicarRadio("status_pago");
		util.clicar("/html/body/div[2]/form/div[4]/button");

		// Validacao
		Assert.assertEquals(
				"Data da Movimentação inválida (DD/MM/YYYY)\r\n" + "Data do pagamento inválida (DD/MM/YYYY)\r\n",
				util.textoPresente("/html/body/div[1]"));

	}

	@Test
	public void testeLancarMovimentacaoCreditoCamposObrigatorios() {

		// Movimentação
		util.clicar("//*[@id=\"navbar\"]/ul/li[3]/a");
		
		util.limparcampo("data_transacao");
		util.limparcampo("data_pagamento");
		util.clicarRadio("status_pago");
		util.clicar("/html/body/div[2]/form/div[4]/button");

		// Validacao
		Assert.assertEquals("Data da Movimentação é obrigatório\r\n" + 
				"Data do pagamento é obrigatório", util.textoPresente("/html/body/div[1]/ul/li[1]"));

		

	}
}
