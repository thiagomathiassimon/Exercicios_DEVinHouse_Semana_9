SELECT * FROM medico m LEFT JOIN atendimento a ON m.codigo = a.medico WHERE a.medico IS NULL;