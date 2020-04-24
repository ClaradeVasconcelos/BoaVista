package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Common.Util;

public class TesteCadastrar {
	Util util;
	
	
	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");
		driver.manage().window().maximize();
		util = new Util(driver);
	}
	
	@After
	public void finaliza() {
		util.fecharBrowser();
	}
	@Test
	public void testeCadastrarUsuario() {
	
		util.clicar("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[2]");
		util.escrever("nome","Clara de Vasconcelos");
		util.escrever("email", "testeboavista@email.com.br");
		util.escrever("senha","123456");
		util.clicar("/html/body/div[2]/form/input");
		
		Assert.assertTrue(util.elementoPresente("/html/body/div[1]"));
		
	}
	
}
