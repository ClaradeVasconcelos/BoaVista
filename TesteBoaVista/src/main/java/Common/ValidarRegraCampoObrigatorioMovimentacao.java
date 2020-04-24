package Common;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Parameterized.class)
public class ValidarRegraCampoObrigatorioMovimentacao {

	@Parameter
	String tipo;
	@Parameter(value = 1)
	String dataTransacao;
	@Parameter(value = 2)
	String dataPagamento;
	@Parameter(value = 3)
	boolean pago;
	@Parameter(value = 4)
	String mensagemEsperada1;
	@Parameter(value = 5)
	String mensagemEsperada2;

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
				{ "", "", "", true, "Data da Movimentação é obrigatório", "Data do pagamento é obrigatório"},
//				{ "", "20/04/2020", "", true, "Data do pagamento é obrigatório",""}, 
//				{ "", "", "21/04/2020", true, "Data da Movimentação é obrigatório"}, 
//				{ "DESP", "", "", true, "Data da Movimentação é obrigatório", "Data do pagamento é obrigatório"}, 
//				{ "DESP", "20/04/2020", "", true, "Data do pagamento é obrigatório"},
//				{ "DESP", "", "21/04/2020", true, "Data da Movimentação é obrigatório"},
//				{ "DESP", "", "", false, "Data da Movimentação é obrigatório"},
				});
	}

	@Test
	public void testeLancarMovimentacaoCreditoCamposObrigatorios() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\felip\\Downloads\\chromedriver_win32.1\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me");
		driver.manage().window().maximize();

		// Logar
		driver.findElement(By.id("nome")).sendKeys("Clara de Vasconcelos");
		driver.findElement(By.id("email")).sendKeys("testeboavista@email.com.br");
		driver.findElement(By.id("senha")).sendKeys("123456");
		// driver.findElement(By.className("btn btn-primary")).click();

		// Movimentação
		driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
		WebElement element = driver.findElement(By.id("tipo"));
		Select combo = new Select(element);
		if (tipo == "DES") {
			combo.selectByValue("DESP");
		}

		driver.findElement(By.id("data_transacao")).sendKeys(dataTransacao);
		driver.findElement(By.id("data_pagamento")).sendKeys(dataPagamento);

		element = driver.findElement(By.id("conta"));
		combo = new Select(element);
		combo.selectByValue("110874");

		if (pago == true) {
			driver.findElement(By.id("status_pago")).click();
		}
		driver.findElement(By.xpath("/html/body/div[2]/form/div[4]/button")).click();

		// Validacao
		String mensagem1 = driver.findElement(By.xpath("/html/body/div[1]/ul/li[1]")).getText();
		String mensagem2 = driver.findElement(By.xpath("/html/body/div[1]/ul/li[2]")).getText();
		System.out.println(mensagem1 + mensagem2);
		Assert.assertEquals(mensagemEsperada1, mensagem1);
		Assert.assertEquals(mensagemEsperada1, mensagem2);
		
		driver.quit();
	}
}
