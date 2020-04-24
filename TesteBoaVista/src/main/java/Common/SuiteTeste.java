package Common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testes.Teste1;
import testes.TesteBoaVista;
import testes.TesteCadastrar;

@RunWith(Suite.class)
@SuiteClasses({
	Teste1.class,
	TesteCadastrar.class,
	TesteBoaVista.class
	
})
public class SuiteTeste{
	
}


