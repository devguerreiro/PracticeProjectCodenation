INSERT INTO application (id, name, email, password) VALUES
(1, 'conta azul', 'contaazul@conta.com', '123456789'),
(2, 'conta verde', 'contaverde@conta.com', '123456789'),
(3, 'conta vermelha', 'contavermelha@conta.com', '123456789');

INSERT INTO event (id, description, log, level, application_id, created_at) VALUES
(1, 'description 1 para o evento 1', 'log 1 para o evento 1', 'info', 1, '2010-10-10 10:10'),
(2, 'description 1 para o evento 2', 'log 1 para o evento 2', 'warning', 1, null),
(3, 'description 1 para o evento 3', 'log 1 para o evento 3', 'error', 1, null),
(4, 'description 1 para o evento 4', 'log 1 para o evento 4', 'info', 1, null);