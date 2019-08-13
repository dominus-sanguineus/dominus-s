<template>
    <div class="chat-notification">
        <div class="chat-notification-text">{{ text }}</div>
    </div>
</template>

<script>
    export default {
        name: "chat-notification",
        props: [
            'text'
        ]
    }
</script>

<style scoped lang="sass">
    .chat-notification
        text-align: center
        margin-bottom: 20px

        .chat-notification-text
            display: inline-block
            padding: 5px
            border-radius: 5px
            background-color: aqua

</style>