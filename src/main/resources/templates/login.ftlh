<!DOCTYPE html>
<html>
    <title>Вход</title>
</html>
<body>
    <h3>Войдите в аккаунт</h3>

    <#if error??>
        <span style="color: red">${error}</span>
    </#if>

    <form action="/login" method="post">
        <input placeholder="Почта" type="email" name="username" />
        <input placeholder="Пароль" type="password" name="password" />
        <button type="submit">Войти</button>
    </form>

    <hr/>

    <a href="/register">Нет аккаунта? Зарегистрироваться</a>
</body>