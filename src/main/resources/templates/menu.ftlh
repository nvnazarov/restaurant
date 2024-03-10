<!DOCTYPE html>
<html>
    <title>Меню</title>
</html>
<body>
    <a href="/admin">Назад</a>

    <hr/>

    <h3>Меню</h3>
    <ul>
        <#list dishes as dish>
            <li style="margin: 2px">
                <form style="display: inline-block" action="menu/delete/${dish.id}" method="post">
                    <button>Удалить</button>
                </form>
                ${dish.name} / ${dish.price} руб. / ${dish.amount} шт. / ${dish.time} сек.
            </li>
            <#else>
            Меню пусто.
        </#list>
    </ul>

    <hr/>

    <b>
        <i>Чтобы изменить количество, цену или сложность приготовления блюда - удалите его и создайте снова.</i>
    </b>

    <form action="menu/add" method="post">
        <input placeholder="Название" type="text" name="name" />
        <input placeholder="Цена" type="number" name="price" />
        <input placeholder="Время приготовления" type="number" name="time" />
        <input placeholder="Количество" type="number" name="amount" />
        <button type="submit">Добавить блюдо</button>
    </form>

    <#if error??>
        <#if error == "add">
            <span style="color: red">
                Не получилось добавить блюдо. Возможно, такое блюдо уже есть в меню,
                или данные не валидны (например, не заполнены).
            </span>
        </#if>
        <#if error == "delete">
            <span style="color: red">
                Не получилось удалить блюдо. Возможно, кто-то заказал это блюдо.
            </span>
        </#if>
    </#if>
</body>