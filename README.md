# inmetrics
Repositório referente ao projeto da inmetrics 
Observações referente ao 'funcionarioTest.java'

Inicialmente foram criados duas classes separadas para login antes de cadastrar usuario e um cadastro de usuário, porém posteriormente unifiquei em uma mesma classe conforme pode ser observado no arquivo funcionarioTest.

1 - Tive dificuldades quanto a validação de alguns pontos, dentro de todas limitações citadas (até alguns comentarios no fonte quanto a mesma).

2 - Mesmo com todo conhecimento limitado e (muita) falta de prática a intenção era tentar fazer login sem cadastro, validar mensagem, cadastrar usuario (se tentado cadastrar o mesmo rodando o caso de novo validar que ja estava cadastrado), realizar login (com sucesso), consultar funcionario (sem cadastro), realizar cadastro, realizar consulta do registro, e posteriormente realizar exclusão do registro da base.

3 - LoginUsuarioAntesCadastroTest : se for executado funcionará na primeira execução quando o seu usuário não estiver cadastrado, nas demais vezes (devido a limitações ele irá causar erro, ocorre que com cadastro ele irá realizar o login direto, e causará erro no proximo metodo, assim para mais de uma execução caso desejado comente LoginUsuarioAntesCadastroTest e CadastraUsuarioTest.

4 - Foi realizada validação de mensagem de usuário ja cadastrado porém está comentada e o motivo é o seguinte: Utilizado xpath devido a no <span class="txt1"... ao tentar cadastrar usuario ja cadastrado ao carregar objeto/transformar em string era encontrado os texto "Usuário/Usuário Cadastrado" porém sempre comparava com o texto Usuário que é o que está fora da DIV, neste caso utilizei o xpath pois foi o unico modo que encontrei pra conseguir retornar apenas ele, porém, ocorre o ponto citado acima.

5 - ConsultaFuncionario também utilizado xpath pra encontrar o campo de texto a ser preenchido para consulta

6 - CadastraNovoFuncionario utilizado xpath para clicar no textLink "Novo Funcionário" por dificuldades de encontrar o campo com alguma das propriedades disponiveis listadas ao verificar o elemento.

Campo CPF estava se perdendo ao preencher, ele preenchia alguns valores em posições incorretas, assim, por esse motivo você verá uma linha pra cada numero do cpf (cpf pego do gerador de cpf do 4devs)

Quando exibido as mensagens na tela de sucesso de cadastro, ou deleção do registro esta retornando dois valores para o campo se consultado atraves de carregamento por propriedades do campo, porém sei que um dos valores está em uma div, geralmente carrega : a mensagem e si, e numa segunda linha um *

Ao final do procedimento eu validaria se a mensagem exibida foi a de sucesso e utilizaria o assert (mesmo sendo redundante), utilizaria esse if pra chamar o procedimento de consultaFuncionario novamente.

7 - no consulta funcionario o grid é carregado de forma que se tem registros carrega, e se não tem carrega com o className = dataTables_empty, o que causou problemas em reutilizar o mesmo trecho de codigo pra consulta depois de cadastrado porque estava tentando carregar o objeto porém esse objeto do classname não existe mais em decorrencia de ter valores, embora eu saiba que se a mensagem de sucesso tenha sido exibida eu saberia que tinha sido cadastrado e poderia validar com o minimo de programação carregar esse objeto ou não, contúdo me dei conta disso neste exato momento em que escrevo essas observações.
     
8 - ExcluiRegistro copiado parte do consultaFuncionario apenas para preencher o campo (ainda sim com xpath também), carregado objeto do botão excluir e fiz clicar nele, assim validando, o asserEquals com a mesma questão de todas as mensagens citadas anteriormente quanto a carregar e verificar ela contendo a mensagem desejada e em uma segunda linha um *

9 - um bug encontrado, ao cadastrar um funcionário a mensagem é 'SUCESSO! Usuário cadastrado com sucesso' porém o cadastro é de funcionário e não de usuário.
        
