SELECT m.nome as "médico", COUNT (*) as "quantidade de pacientes" FROM medico m 
	INNER JOIN atendimento a ON m.codigo = a.medico INNER JOIN paciente p ON a.paciente = p.codigo
	GROUP BY m.nome;