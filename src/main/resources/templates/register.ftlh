<!DOCTYPE html>
<html>
    <title>Регистрация</title>
</html>
<body>
    <h3>Зарегистрируйтесь</h3>

    <form action="/register" method="post">
        <input placeholder="Почта" type="email" name="email" />
        <input placeholder="Пароль" type="password" name="password" />
        <button type="submit">Создать аккаунт</button>
    </form>

    <#if error??>
        <span style="color: red">${error}</span>
    </#if>

    <hr/>

    <a href="/login">Есть аккаунт? Войти</a>
</body>