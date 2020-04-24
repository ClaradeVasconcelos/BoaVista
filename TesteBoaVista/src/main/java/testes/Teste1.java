package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Common.Util;

public class Teste1 {
	
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
	public void testeCriarContaMesmoNome() {

		// Criar primeira conta
		util.clicar("//*[@id=\"navbar\"]/ul/li[2]/a");
		util.clicar("//*[@id=\"navbar\"]/ul/li[2]/ul/li[1]/a");
		util.escrever("nome","Conta com nome igual");
		util.clicar("/html/body/div[2]/form/div[2]/button");

		// Criar segunda conta
		util.clicar("//*[@id=\"navbar\"]/ul/li[2]/a");
		util.clicar("//*[@id=\"navbar\"]/ul/li[2]/ul/li[1]/a");
		util.escrever("nome","Conta com nome igual");
		util.clicar("/html/body/div[2]/form/div[2]/button");

		// Validacao
		Assert.assertEquals("Já existe uma conta com esse nome!", util.textoPresente("/html/body/div[1]"));
	}
}
