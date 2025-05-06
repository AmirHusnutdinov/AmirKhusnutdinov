<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Список статей с количеством комментариев">
    <title>Главная страница</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/yegor256/tacit@gh-pages/tacit-css-1.6.0.min.css"/>
</head>
<body>

<header>
    <h1>Список статей</h1>
</header>

<main>
    <table class="article-table">
        <thead>
            <tr>
                <th>Название</th>
                <th>Количество комментариев</th>
            </tr>
        </thead>
        <tbody>
            <#list articles as article>
                <tr>
                    <td>${article.title}</td>
                    <td>${article.number}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</main>

<!-- Добавьте скрипты здесь, если это необходимо -->

</body>
</html>
