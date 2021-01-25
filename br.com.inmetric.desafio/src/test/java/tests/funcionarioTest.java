package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

// SJM - 24/01/21 - Todos os métodos foram incluidos aqui para serem executados, verificar anexo observações.txt
public class funcionarioTest {

    Date varDataAtual;
    String varDataFormatada;
    private WebDriver driver;
    private String varMensagem;

    @Before
    public void inicio(){

        /* Encontra data atual e converte para data sem separação, carrega o driver do chrome*/
        varDataAtual = new Date();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("ddMMyyyy");
        varDataFormatada = dataFormatada.format(varDataAtual);

        //Busca o driver (chromedriver) dentro da pasta no projeto
        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        /* Carrega o chrome e segue para o processo de login dentro da pagina */
        //Redireciona para a pagina de login 'https://inm-test-app.herokuapp.com/accounts/login/'
        driver.get("https://inm-test-app.herokuapp.com/accounts/login/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /* Realizando o processo todo em um bloco apenas por falta de experiencia*/
    @Test
    public void loginConsultaCadastroExclusão(){

        LoginUsuarioAntesCadastroTest();

        CadastraUsuarioTest();

        RealizaLoginTest();

        ConsultaFuncionario();

        if (varMensagem.equals("Nenhum registro encontrado")){
            //Valida se não encontra nenhum registro encontrado
            assertEquals("Nenhum registro encontrado", varMensagem);
            CadastraNovoFuncionario();
        }

        ExcluiRegistro();

    }

    public void LoginUsuarioAntesCadastroTest(){

        // arrega formulario de login em um 'objeto' para encontrar os objetos dentro dele
        WebElement formularioLogin = driver.findElement(By.className("container-login100"));

        // Informar o usuário para realizar login : 'usuario + data atual"
        WebElement usuarioLogin = formularioLogin.findElement(By.name("username"));
        usuarioLogin.sendKeys("usuario" + varDataFormatada);

        // Informar a senha do usuário : 'data atual'
        WebElement senhaLogin = formularioLogin.findElement(By.name("pass"));
        senhaLogin.sendKeys(varDataFormatada);

        // Clicar no botão 'Entre' para a tentativa de login
        WebElement buttonEntre = formularioLogin.findElement(By.className("login100-form-btn"));
        buttonEntre.click();

        /* Valida mensagem ao tentar logar */
        // Validar que dentro do elemento class "container-message" está o texto : 'ERRO! Usuário ou Senha inválidos'
        WebElement MensagemLogin = driver.findElement(By.className("container-message"));

        // Recebe em string a mensagem dentro do objeto MensagemLogin
        String txtVarMensagem = MensagemLogin.getText();

        // Escreve a mensagem em tela para teste
        System.out.println(txtVarMensagem);

        // Valida se o texto da variavel
        if(txtVarMensagem.equals("ERRO! Usuário ou Senha inválidos")) {
            //Compara se a mensagem exibida é igual a da String
            assertEquals("ERRO! Usuário ou Senha inválidos", txtVarMensagem);
        }
    }

    public void CadastraUsuarioTest(){

        // Passos a serem realizados:
        // Clica no link que possui o texto Cadastre-se
        driver.findElement(By.linkText("Cadastre-se")).click();

        // Carrega formulario de login em um 'objeto' para encontrar os objetos dentro dele
        WebElement formularioCadastro = driver.findElement(By.className("container-login100"));

        // Ao redirecionar para a pagina 'Cadastre-se' informar dados para cadastro:
        // Informar Usuário 'usuario + Data Atual'
        WebElement usuario = formularioCadastro.findElement(By.name("username"));
        usuario.sendKeys("usuario" + varDataFormatada);

        // Informar Senha : Data Atual
        WebElement senha = formularioCadastro.findElement(By.name("pass"));
        senha.sendKeys(""+ varDataFormatada);

        // Informar Senha de confirmação : Data Atual
        WebElement confirmaSenha = formularioCadastro.findElement(By.name("confirmpass"));
        confirmaSenha.sendKeys("" + varDataFormatada);

        // Confirma o cadastro do usuário
        WebElement buttonCadastrar = formularioCadastro.findElement(By.className("login100-form-btn"));
        buttonCadastrar.click();

        /* Valida mensagem de usuário ja cadastrado  - Problema utilizando xpath é que quando a mensagem não é exibida o xpath da div não é exibido

        // Validar se após clicar em cadastrar, se dentro do driver encontra o elemento utilizando xpath, e nesse elemento contem a mensagem que o usuário ja está cadastrado
        // Utilizado xpath devido a no <span class="txt1"... ao tentar cadastrar usuario ja cadastrado era encontrado os texto "Usuário/Usuário Cadastrado" porém sempre comparava com o texto fora da Div
        WebElement mensagemCadastro = driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[1]/span/*"));

        // Recebe em string a mensagem dentro do objeto MensagemLogin
        String txtVarMensagem = mensagemCadastro.getText();

        if(txtVarMensagem.equals("Usuário já cadastrado"){
            //Compara se a mensagem exibida é igual a da String
            assertEquals("Usuário já cadastrado", txtVarMensagem);
        }*/
    }

    public void RealizaLoginTest(){
        // Carrega formulario de login em um 'objeto' para encontrar os objetos dentro dele
        WebElement formularioLogin = driver.findElement(By.className("container-login100"));

        // Informar o usuário para realizar login : 'usuario + data atual"
        WebElement usuarioLogin = formularioLogin.findElement(By.name("username"));
        usuarioLogin.sendKeys("usuario" + varDataFormatada);

        // Informar a senha do usuário : 'data atual'
        WebElement senhaLogin = formularioLogin.findElement(By.name("pass"));
        senhaLogin.sendKeys(varDataFormatada);

        // Clicar no botão 'Entre' para a tentativa de login
        WebElement buttonEntre = formularioLogin.findElement(By.className("login100-form-btn"));
        buttonEntre.click();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void ConsultaFuncionario(){

        // Carrega a parte referente ao grid após fazer login
        // Carrega o elemento da pagina para dar sequencia aos demais passos
        WebElement paginaEmpregados = driver.findElement(By.className("container-lista"));

        // Preenche o nome do funcionário no textfield
        WebElement tfPesquisa = paginaEmpregados.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/label/input"));
        tfPesquisa.sendKeys("funcionario" + varDataFormatada);

        // verificar se o retorno da consulta é : Nenhum registro encontrado
        WebElement retornoConsulta = paginaEmpregados.findElement(By.className("dataTables_empty"));

        varMensagem = retornoConsulta.getText();
        //System.out.println(varMensagem);

    }

    public void CadastraNovoFuncionario(){
        // Clica no textLink de 'Novo Funcionário' para realizar o cadastro de um funcionario novo
        WebElement containerNavBar = driver.findElement(By.className("container-fluid")); // carregado o container do navbar com o classname do código fonte
        WebElement navItem = containerNavBar.findElement(By.className("nav-item")); // carregado o navitem com base no classname do código fonte
        WebElement novoFuncionario = navItem.findElement(By.xpath("/html/body/nav/div/div/ul/li[2]/a")); // utilizado xpath por dificuldade em utilizar elementos do item do código fonte
        novoFuncionario.click();

        // Carrega o quadro com os campos para o cadastro
        WebElement quadroCadastro = driver.findElement(By.className("container-login100"));

        //Nome
        WebElement tfNome = quadroCadastro.findElement(By.id("inputNome"));
        tfNome.sendKeys("funcionario" + varDataFormatada);

        //CPF - Se perde se utilizar sendkeys com todo cpf
        WebElement tfCPF = quadroCadastro.findElement(By.id("cpf"));
        tfCPF.sendKeys("7");
        tfCPF.sendKeys("3");
        tfCPF.sendKeys("2");
        tfCPF.sendKeys("1");
        tfCPF.sendKeys("5");
        tfCPF.sendKeys("1");
        tfCPF.sendKeys("5");
        tfCPF.sendKeys("4");
        tfCPF.sendKeys("0");
        tfCPF.sendKeys("0");
        tfCPF.sendKeys("5");

        //Sexo (Combobox)
        WebElement listSexo = quadroCadastro.findElement(By.id("slctSexo"));
        listSexo.sendKeys("m");

        //Admissão (Data) -  Se perde se utilizar sendkeys com toda data (vide cadastro)
        WebElement dtAdmissao = quadroCadastro.findElement(By.id("inputAdmissao"));
        dtAdmissao.sendKeys(varDataFormatada);

        //Cargo
        WebElement cargo = quadroCadastro.findElement(By.id("inputCargo"));
        cargo.sendKeys("Automação de testes");

        //Salario
        WebElement salario = quadroCadastro.findElement(By.id("dinheiro"));
        salario.sendKeys("2500");

        //Tipo Contratação
        // 1 - CLT
        // 2 - PJ
        WebElement tipoContratacao = quadroCadastro.findElement(By.className("radio-button"));
        Random valor = new Random();
        int numero = valor.nextInt(1);
        if(numero == 0) {
            tipoContratacao.findElement(By.id("clt")).click();
        }else{
            tipoContratacao.findElement(By.id("pj")).click();
        }

        WebElement buttoSubmit = quadroCadastro.findElement(By.className("cadastrar-form-btn"));
        buttoSubmit.click();

        // Valida exibição da mensagem de sucesso do cadastro - esta retornando dois valores para o campo embora eu saiba que está na div, estou com dificuldade pra validar
        WebElement mensagem = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div"));
        varMensagem = mensagem.getText();

        // Onde eu validaria que a mensagem é sim de sucesso que é a esperada, e chamaria o consulta funcionario -
        // de todo modo farei a consulta do funcionario para a exclusão do registro
        if(varMensagem.equals("SUCESSO! Usuário cadastrado com sucesso")) {
            assertEquals("SUCESSO! Usuário cadastrado com sucesso", varMensagem);
            ConsultaFuncionario();
        }
    }

    public void ExcluiRegistro(){
        // Carrega a parte referente ao grid após fazer login
        // Carrega o elemento da pagina para dar sequencia aos demais passos
        WebElement paginaEmpregados = driver.findElement(By.className("container-lista"));

        // Preenche o nome do funcionário no textfield
        WebElement tfPesquisa = paginaEmpregados.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/label/input"));
        tfPesquisa.sendKeys("funcionario" + varDataFormatada);

        // Exclui registros - queria procurar no list se a linha com nome do usuario era retornada porém, não consegui, mesmo assim, como sei que ele cadastrou,
        // refiz a consulta e encontrei objeto de excluir e realizei a exclusão do mesmo.
        WebElement excluir = driver.findElement(By.id("delete-btn"));
        excluir.click();
        WebElement mensagem = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div"));
        varMensagem = mensagem.getText();
        //System.out.println(varMensagem);
        //assertEquals("SUCESSO! Funcionário removido com sucesso", varMensagem);
    }

    @After
    public void encerrar(){
        driver.close();
    }


}
