<template>
    <ul>
        <li>Имя: {{ account.username }}</li>
        <li>Идентификатор: {{ account.id }}</li>
        <li>Пароль: {{ account.password }}</li>
    </ul>
</template>

<script>
    export default {
        name: "account-entry",
        props: [
            'account'
        ]
    }
</script>

<style scoped lang="sass">
    @import "../static/style/main"
</style>