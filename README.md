<b>Bem-vindo ao sistema de locadora de filmes!</b>
<br>
Após rodar o scrip locadora.sql e subir o projeto local, acesse o link para verificar a documentação da api.
<br>
<a href="http://localhost:8081/swagger-ui.html">http://localhost:8081/swagger-ui.html</a>
<br>
<p>
	<img src="https://imgur.com/vVviWne.png"/>
</p>
<br>


<br>
Para cadastrar um usuário acesse o link, (método POST)
<br>
<a href="http://localhost:8081/usuarios">http://localhost:8081/usuarios</a>
<br>
como mostra imagem abaixo:
<br>
<img src="https://imgur.com/lqr5WJ0.png">
<p>

<br>

</p>

<p>Para verificar os usuários cadastrados método GET:<br>
	<a href="http://localhost:8081/usuarios">http://localhost:8081/usuarios</a>
	como mostra imagem abaixo: 
	<br>
	<img src="https://imgur.com/FRms4Mz.png">
</p>

<br>

<p>
	Para autenticar um usuário no sistema, acesse o link:
	<a href="http://localhost:8081/auth">http://localhost:8081/auth</a>
	<br>
	conforme imagem abaixo:
	<br>
	<img src="https://imgur.com/dLLIkEk.png">
</p>


<p>
	Para realizar a locação de um filme, acesse
	<br>
	<a href="http://localhost:8081/locacao/locar">http://localhost:8081/locacao/locar</a>
	<br>
	no campo "body" você deve informar o id do usuário e o id filme,
	<br>
	como mostra na imagem abaixo:
	<br>
	<img src="https://imgur.com/3jlXsP8.png">
	<br>
	e no campo "Authorization" deverá informar o token do tipo "Bearen Token"
	<br>
	<img src="https://imgur.com/sYwbfnW.png">
</p>



<p>
	Para realizar a devolução de um filme, acesse
	<br>
	<a href="http://localhost:8081/locacao/devolver">http://localhost:8081/locacao/devolver</a>
	<br>
	no campo "body" você deve informar o id da locação e o id do usuário.
	<br>
	como mostra na imagem abaixo:
	<br>
	<img src="https://imgur.com/mH6MmtK.png">
</p>

<p>
	Pra listar filmes disponíveis, acesse
	<br>
	<a href="http://localhost:8081/filmes">http://localhost:8081/filmes</a>
	<br>
	como mostra na imagem abaixo:
	<br>
	<img src="https://imgur.com/AIIIrWG.png">
</p>


<p>
	Pra listar um filme específico, acesse
	<br>
	<a href="http://localhost:8081/filmes/Matrix/pesquisar">http://localhost:8081/filmes/Matrix/pesquisar</a>
	<br>
	como mostra na imagem abaixo:
	<br>
	<img src="https://imgur.com/AIIIrWG.png">
</p>


