# 2020-08-otus-spring-project-Tronina

### Проект: бронирование переговорных комнат

Реализовано 3 микросервиса:
- Ядро
- Нотификаиця
- Управление пользователями.

Доступ в микросервисы только для авторизованных пользователей.
2 типа пользователей - администратор и обычный пользователь. 
Пользователь имеет профиль, в котором указаны email, телефон и выбран канал оповещения (email и/или sms). 
Переговорка имеет характеристики: кол-во вмещаемых человек; наличие видеоконференц связи; наличие кондиционера. 
Пользователю будет высылаться уведомление за 10 минут до начала его брони. 
Пользователь может подписаться на переговорку, чтобы получать уведомления об ее бронированиях другими участниками. 
Данные о пользователях, переговорках и бронях хранятся в реляционной БД.
Все манипуляции с бронями - в NoSQL. Эти данные не удаляются, не изменяются. Требуются для статистики и аудита. 
Интерфейсная часть не предполагалась, но пришлось сделать минимум (((
