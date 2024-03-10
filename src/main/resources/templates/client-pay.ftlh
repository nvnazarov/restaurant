<!DOCTYPE html>
<html>
    <title>Клиент</title>
</html>
<body>
    <a href="/login">Выйти</a>
    <hr/>

    <h3>Клиент: ${email}</h3>
    <hr/>

    <h3>Меню</h3>

    <i>Цифры меню могут быть неактуальны, поскольку не обновляются в реальном времени.</i>

    <ul>
        <#list menu as dish>
            <li style="margin: 2px">
                <form style="display: inline-block" action="/order/add/${dish.id}" method="post">
                    <button disabled>Добавить</button>
                </form>
                ${dish.name} / ${dish.price} руб. / ${dish.amount} шт. / ${dish.time} сек.
            </li>

            <#else>
            Меню пусто.
        </#list>
    </ul>

    <hr/>

    <h3>Заказ готов, оплатите его</h3>
    <form style="display: inline-block" action="/cancel" method="post">
        <button type="submit" disabled>Отменить</button>
    </form>
    <form style="display: inline-block" action="/pay" method="post">
            <button type="submit">Заплатить</button>
        </form>

    <ul>
        <#list order as dish>
            <li style="margin: 2px">
                ${dish.name} / ${dish.price} руб.
            </li>
        </#list>
    </ul>

    <hr/>

    <h3>Все заказы</h3>

   <table>
       <tr>
           <td style="border: 1px solid #000; padding: 5px;"><b>Время приготовления</b></td>
           <td style="border: 1px solid #000; padding: 5px;"><b>Стоимость</b></td>
           <td style="border: 1px solid #000; padding: 5px;"><b>Статус</b></td>
       </tr>
       <#list all as process>
           <tr>
               <td style="border: 1px solid #000; padding: 5px;">${process.totalTime} сек.</td>
               <td style="border: 1px solid #000; padding: 5px;">${process.totalPrice} руб.</td>
               <td style="border: 1px solid #000; padding: 5px;">${process.status}</td>
           </tr>
       </#list>
   </table>
</body>