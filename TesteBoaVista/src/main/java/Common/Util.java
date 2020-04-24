package Common;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Util {
	WebDriver driver;
	public Util(WebDriver driver) {
		this.driver = driver;
	}
	

	public void escrever(String campo, String texto) {
		driver.findElement(By.id(campo)).sendKeys(texto);
	}
	
	public void clicar(String campo) {
		driver.findElement(By.xpath(campo)).click();
	}
	
	public void clicarRadio(String campo) {
		driver.findElement(By.id(campo)).click();
	}
	
	public boolean elementoPresente(String campo) {
		boolean mensagem = driver.findElement(By.xpath(campo)).isDisplayed();
		return mensagem;
	}
	
	public void fecharBrowser() {
		driver.quit();
	}
	
	public String textoPresente(String campo) {
		String texto = driver.findElement(By.xpath(campo)).getText();
		return texto;
	}
	
	public void selecionarComboPeloValor(String campo, String valor) {
		WebElement element = driver.findElement(By.id(campo));
		Select combo = new Select(element);
		combo.selectByValue(valor);
	}
	
	public void limparcampo(String campo) {
		driver.findElement(By.id(campo)).clear();
	}
}
