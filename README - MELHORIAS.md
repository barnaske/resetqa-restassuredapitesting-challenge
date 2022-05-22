# resetqa-restassuredapitesting-challenge

Melhorias plausíveis para a Treinamento-api

Auth - CreateToken

	- O status code poderia ser alterado para 201 Created pois enquadra melhor a criação de um token.
	
Booking - GetBookingIds

	- A filtragem utilizando apenas o parametro checkin ou checkout que é retornada parece não condizer com o filtro implementado na URI, acredito que seja viável validar que deve ser retornado ao informar um filtro de checkin, na minha leitura o ideal seria retornar apenas IDs de reservas que possuam a data de checkin/out correspondente ao filtro aplicado na URI.

Booking - GetBooking

	- Nada a declarar de melhoria.
	
Booking- CreateBooking

	- O status code correto a utilizar seria o 201 Created.
	
Booking - UpdateBooking

	- A documentação do Header possui o parametro Authorization escrito de forma incorreta ("Authorisation");
	- Ao tentar atualizar uma reserva que supostamente não existe, o retorno recebido é status code 405, o ideal seria que o SC fosse algum na casa dos 500-599 com uma mensagem de exceção tratada, informando que não é possível atualizar uma reserva inexistente
	
Booking - PartialUpdateBooking

	- Não é possível atualizar uma reserva parcialmente usando o método PATCH, a princípio a funcionalidade parece estar desativada.

Booking - DeleteBooking

	- Retornar status code 200 OK ao invés de 201 Created, pois não faz sentido informar que uma deleção foi criada;
	- Corrigir documentação do Exemplo 2 - Basic auth e também citação no header pois Authorization está escrito como "Authorisation", o que não é considerado um header válido;
	- Ao tentar excluir uma reserva que supostamente não existe, o retorno recebido é status code 405, o ideal seria que o SC fosse algum na casa dos 500-599 com uma mensagem de exceção tratada, informando que não é possível excluir uma inexistente
	
Ping - Healthcheck

	- Retornar status code 200 OK ao invés de 201 Created, pois não faz sentido informar que um retorno de ping foi criado.



