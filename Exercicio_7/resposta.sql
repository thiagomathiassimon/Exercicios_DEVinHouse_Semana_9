SELECT p.nome as "paciente", p.nascimento, p.email, p.telefone, m.nome as "médico", m.crm
	FROM paciente p INNER JOIN atendimento a ON p.codigo = a.paciente
	INNER JOIN medico m ON a.medico = m.codigo ORDER BY p.nome;