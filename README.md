# Система управления заказами в ресторане

Простое приложение на языке Java, предоставляющая
пользователям-посетителям заказывать и оплачивать заказы, а
пользователям-администраторам менять меню ресторана.

# Шаблоны проектирования

Шаблоны, встроенные в Spring:

- Model-View-Controller;
- Репозиторий;

Остальные шаблоны:

- Строитель (для удобства настройки моделей);

#  Запуск программы

Используйте docker и docker-compose для запуска базы данных. Для
этого, находясь в корневой директории проекта, выполните команду:

`docker-compose up --build`

Подождите, пока поднимется контейнер с базой данных, и запустите
проект (например, через IntelliJ).

# Использование программы

Перейдите по адресу http://localhost:8080/register, придумайте
почту (не обязательно существующую) и пароль и зарегистрируйтесь.
Далее, войдите в созданный аккаунт. Вам будет показана страница
пользователя с меню ресторана, состоянием текущего заказа и таблицей
всех заказов этого пользователя.

Меню будет пустым, так что перейдите в аккаунт администратора 
(для этого выйдите из текущего аккаунта или перейдите по адресу 
http://localhost:8080/login и введите `admin@admin` и `admin` в 
качестве почты и пароля). Вас перекинет на страницу администратора,
где можно будет поменять меню (все инструкции на странице). Добавьте
несколько блюд, указав название блюда, его стоимость, время приготовления и
количество.

Теперь можете вернуться на аккаунт пользователя, меню обновится и блюда станет
возможно добавлять в заказ. Добавьте столько блюд, сколько желаете, можете
несколько раз отменять заказ (если выбрали не то, что нужно) и нажмите кнопку
"оформить заказ". После этого заказ будет готовиться (статус `COOKING`), информация
о нем будет представлена в таблице снизу. Во время приготовления заказа его можно
отменить, в таком случае он будет помечен статусом `CANCELED`, а пользователь сможет
создать другой заказ. Когда заказ будет приготовлен, его статус поменяется на `READY`
(периодически обновляйте страницу, чтобы статус обновлялся), и Вы сможете оплатить его,
нажав на кнопку "Заплатить". Заказ будет помечен статусом `PAYED`, а Вам будет
предоставлена возможность оформить следующий заказ.